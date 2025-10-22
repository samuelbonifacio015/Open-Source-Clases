import {Injectable} from '@angular/core';
import {BaseApi} from '../../shared/infrastructure/base-api';
import {Course} from '../domain/model/couse.entity';
import {Category} from '../domain/model/category.entity';
import {HttpClient} from '@angular/common/http';
import {CoursesApiEndpoint} from './courses-api-endpoint';
import {CategoriesApiEndpoint} from './categories-api-endpoint';
import {Observable} from 'rxjs';

/**
 * API service for managing endpoints in the learning context (courses and categories).
 */
@Injectable({providedIn: 'root'})
export class LearningApi extends BaseApi {
  private readonly coursesEndpoint:     CoursesApiEndpoint;
  private readonly categoriesEndpoint:  CategoriesApiEndpoint;

  constructor(http: HttpClient) {
    super();
    this.coursesEndpoint =    new CoursesApiEndpoint(http);
    this.categoriesEndpoint = new CategoriesApiEndpoint(http);
  }

  /**
   * Retrieves all courses from the API.
   * @returns An Observable for an array of Course objects.
   */
  getCourses(): Observable<Course[]> {
    return this.coursesEndpoint.getAll();
  }

  /**
   * Retrieves a single course by ID.
   * @param id - The ID of the course.
   * @returns An Observable of the Course object.
   */
  getCourse(id: number): Observable<Course> {
    return this.coursesEndpoint.getById(id);
  }

  /**
   * Creates a new course.
   * @param course - The course to create.
   * @returns An Observable of the created Course object.
   */
  createCourse(course: Course): Observable<Course> {
    return this.coursesEndpoint.create(course);
  }

  /**
   * Updates an existing course.
   * @param course - The course to update.
   * @returns An Observable of the updated Course object.
   */
  updateCourse(course: Course): Observable<Course> {
    return this.coursesEndpoint.update(course, course.id);
  }

  /**
   * Deletes a course by ID.
   * @param id - The ID of the course to delete.
   * @returns An Observable of void.
   */
  deleteCourse(id: number): Observable<void> {
    return this.coursesEndpoint.delete(id);
  }

  /**
   * Retrieves all categories from the API.
   * @returns An Observable for an array of Category objects.
   */
  getCategories(): Observable<Category[]> {
    return this.categoriesEndpoint.getAll();
  }

  /**
   * Retrieves a single category by ID.
   * @param id - The ID of the category.
   * @returns An Observable of the Category object.
   */
  getCategory(id: number): Observable<Category> {
    return this.categoriesEndpoint.getById(id);
  }

  /**
   * Creates a new category.
   * @param category - The category to create.
   * @returns An Observable of the created Category object.
   */
  createCategory(category: Category): Observable<Category> {
    return this.categoriesEndpoint.create(category);
  }

  /**
   * Updates an existing category.
   * @param category - The category to update.
   * @returns An Observable of the updated Category object.
   */
  updateCategory(category: Category): Observable<Category> {
    return this.categoriesEndpoint.update(category, category.id);
  }

  /**
   * Deletes a category by ID.
   * @param id - The ID of the category to delete.
   * @returns An Observable of void.
   */
  deleteCategory(id: number): Observable<void> {
    return this.categoriesEndpoint.delete(id);
  }
}
