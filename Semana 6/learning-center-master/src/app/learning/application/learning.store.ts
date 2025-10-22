import {computed, Injectable, Signal, signal} from '@angular/core';
import {Course} from '../domain/model/couse.entity';
import {Category} from '../domain/model/category.entity';
import {LearningApi} from '../infrastructure/learning-api';
import {takeUntilDestroyed} from '@angular/core/rxjs-interop';
import {retry} from 'rxjs';

/**
 * State management store for courses and categories using Angular signals.
 */
@Injectable({
  providedIn: 'root'
})
export class LearningStore {
  readonly courseCount = computed(() => this.courses().length);
  readonly categoryCount = computed(() => this.categories().length);
  private readonly coursesSignal = signal<Course[]>([]);
  readonly courses = this.coursesSignal.asReadonly();
  private readonly categoriesSignal = signal<Category[]>([]);
  readonly categories = this.categoriesSignal.asReadonly();
  private readonly loadingSignal = signal<boolean>(false);
  readonly loading = this.loadingSignal.asReadonly();
  private readonly errorSignal = signal<string | null>(null);
  readonly error = this.errorSignal.asReadonly();

  constructor(private learningApi: LearningApi) {
    this.loadCategories();
    this.loadCourses();
  }

  /**
   * Retrieves a category by its ID as a signal.
   * @param id - The ID of the category.
   * @returns A Signal containing the Category object or undefined if not found.
   */
  getCategoryById(id: number): Signal<Category | undefined> {
    return computed(() => id ? this.categories().find(c => c.id === id) : undefined);
  }

  getCourseById(id: number): Signal<Course | undefined> {
    return computed(() => id ? this.courses().find(c => c.id === id) : undefined);
  }

  /**
   * Adds a new course.
   * @param course - The course to add.
   */
  addCourse(course: Course): void {
    this.loadingSignal.set(true);
    this.errorSignal.set(null);
    this.learningApi.createCourse(course).pipe(retry(2)).subscribe({
      next: createdCourse => {
        createdCourse = this.assignCategoryToCourse(course);
        this.coursesSignal.update(courses => [...courses, createdCourse]);
        this.loadingSignal.set(false);
      },
      error: err => {
        this.errorSignal.set(this.formatError(err, 'Failed to create course'));
        this.loadingSignal.set(false);
      }
    });
  }

  /**
   * Updates an existing course.
   * @param updatedCourse - The course to update.
   */
  updateCourse(updatedCourse: Course): void {
    this.loadingSignal.set(true);
    this.errorSignal.set(null);
    this.learningApi.updateCourse(updatedCourse).pipe(retry(2)).subscribe({
      next: course => {
        course = this.assignCategoryToCourse(course);
        this.coursesSignal.update(courses =>
          courses.map(c => c.id === course.id ? course : c)
        );
        this.loadingSignal.set(false);
      },
      error: err => {
        this.errorSignal.set(this.formatError(err, 'Failed to update course'));
        this.loadingSignal.set(false);
      }
    });
  }

  /**
   * Deletes a course by ID.
   * @param id - The ID of the course to delete.
   */
  deleteCourse(id: number): void {
    this.loadingSignal.set(true);
    this.errorSignal.set(null);
    this.learningApi.deleteCourse(id).pipe(retry(2)).subscribe({
      next: () => {
        this.coursesSignal.update(courses => courses.filter(c => c.id !== id));
        this.loadingSignal.set(false);
      },
      error: err => {
        this.errorSignal.set(this.formatError(err, 'Failed to delete course'));
        this.loadingSignal.set(false);
      }
    });
  }

  /**
   * Adds a new category.
   * @param category - The category to add.
   */
  addCategory(category: Category): void {
    this.loadingSignal.set(true);
    this.errorSignal.set(null);
    this.learningApi.createCategory(category).pipe(retry(2)).subscribe({
      next: createdCategory => {
        this.categoriesSignal.update(categories => [...categories, createdCategory]);
        this.loadingSignal.set(false);
      },
      error: err => {
        this.errorSignal.set(this.formatError(err, 'Failed to create category'));
        this.loadingSignal.set(false);
      }
    });
  }

  /**
   * Updates an existing category.
   * @param updatedCategory - The category to update.
   */
  updateCategory(updatedCategory: Category): void {
    this.loadingSignal.set(true);
    this.errorSignal.set(null);
    this.learningApi.updateCategory(updatedCategory).pipe(retry(2)).subscribe({
      next: category => {
        this.categoriesSignal.update(categories =>
          categories.map(c => c.id === category.id ? category : c)
        );
        this.loadingSignal.set(false);
      },
      error: err => {
        this.errorSignal.set(this.formatError(err, 'Failed to update category'));
        this.loadingSignal.set(false);
      }
    });
  }

  /**
   * Deletes a category by ID.
   * @param id - The ID of the category to delete.
   */
  deleteCategory(id: number): void {
    this.loadingSignal.set(true);
    this.errorSignal.set(null);
    this.learningApi.deleteCategory(id).pipe(retry(2)).subscribe({
      next: () => {
        this.categoriesSignal.update(categories => categories.filter(c => c.id !== id));
        this.loadingSignal.set(false);
      },
      error: err => {
        this.errorSignal.set(this.formatError(err, 'Failed to delete category'));
        this.loadingSignal.set(false);
      }
    });
  }

  /**
   * Loads all courses from the API.
   */
  private loadCourses(): void {
    this.loadingSignal.set(true);
    this.errorSignal.set(null);
    this.learningApi.getCourses().pipe(takeUntilDestroyed()).subscribe({
      next: courses => {
        console.log(courses);
        this.coursesSignal.set(courses);
        this.loadingSignal.set(false);
        this.assignCategoriesToCourses();
      },
      error: err => {
        this.errorSignal.set(this.formatError(err, 'Failed to load courses'));
        this.loadingSignal.set(false);
      }
    });
  }

  /**
   * Loads all categories from the API.
   */
  private loadCategories(): void {
    this.loadingSignal.set(true);
    this.errorSignal.set(null);
    this.learningApi.getCategories().pipe(takeUntilDestroyed()).subscribe({
      next: categories => {
        this.categoriesSignal.set(categories);
        this.loadingSignal.set(false);
      },
      error: err => {
        this.errorSignal.set(this.formatError(err, 'Failed to load categories'));
        this.loadingSignal.set(false);
      }
    });
  }

  private assignCategoriesToCourses(): void {
    this.coursesSignal.update(courses => courses.map(course => this.assignCategoryToCourse(course)));
  }

  private assignCategoryToCourse(course: Course): Course {
    const categoryId = course.categoryId ?? 0;
    course.category = categoryId ? this.getCategoryById(categoryId)() ?? null : null;
    return course;
  }

  /**
   * Formats error messages for user-friendly display.
   * @param error - The error object.
   * @param fallback - The fallback error message.
   * @returns A formatted error message.
   */
  private formatError(error: any, fallback: string): string {
    if (error instanceof Error) {
      return error.message.includes('Resource not found') ? `${fallback}: Not found` : error.message;
    }
    return fallback;
  }
}
