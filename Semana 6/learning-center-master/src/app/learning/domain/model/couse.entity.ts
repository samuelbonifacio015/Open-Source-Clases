import {Category} from './category.entity';

/**
 * Represents a course entity with id, title, description, and category.
 *
 * @remarks
 * This class is used as a domain model for courses in the learning context.
 * It encapsulates the properties and behaviors associated with a course, including its category association.
 *
 * @example
 * ```typescript
 * const course = new Course({ id: 1, title: 'Introduction to TypeScript', description: 'Learn the basics of TypeScript.', categoryId: 2 });
 * console.log(course.id); // Output: 1
 * console.log(course.title); // Output: Introduction to TypeScript
 * course.categoryId = 3;
 * ```
 */
export class Course {
  /**
   * The category associated with the course.
   * @remarks
   * This is an object reference to the {@link Category} entity. It may be null if not set.
   */
  get category(): Category | null {
    return this._category;
  }

  /**
   * Sets the category associated with the course.
   *
   * @param value - The {@link Category} to associate with the course.
   */
  set category(value: Category | null) {
    this._category = value;
  }

  /**
   * Unique identifier for the course.
   * @defaultValue 0
   */
  private _id: number;

  /**
   * Title of the course.
   * @defaultValue ''
   */
  private _title: string;

  /**
   * Description of the course.
   * @defaultValue ''
   */
  private _description: string;

  /**
   * Identifier of the category associated with the course.
   * @defaultValue 0
   */
  private _categoryId: number;

  /**
   * The category object associated with the course, or null if not set.
   * @defaultValue null
   */
  private _category: Category | null;

  /**
   * Creates a new instance of the Course class.
   *
   * @param course - An object containing optional properties to initialize the course.
   * @param course.id - The unique identifier for the course.
   * @param course.title - The title of the course.
   * @param course.description - The description of the course.
   * @param course.categoryId - The identifier of the category associated with the course.
   */
  constructor(course: { id: number; title: string; description: string; categoryId: number; category?: Category | null }) {
    this._id = course.id;
    this._title = course.title;
    this._description = course.description;
    this._categoryId = course.categoryId;
    this._category = course.category ?? null;
  }

  /**
   * Gets the course id.
   * @returns The unique identifier for the course.
   */
  get id(): number {
    return this._id;
  }

  /**
   * Sets the course id.
   * @param value - The unique identifier to set for the course.
   */
  set id(value: number) {
    this._id = value;
  }

  /**
   * Gets the course title.
   * @returns The title of the course.
   */
  get title(): string {
    return this._title;
  }

  /**
   * Sets the course title.
   * @param value - The title to set for the course.
   */
  set title(value: string) {
    this._title = value;
  }

  /**
   * Gets the course description.
   * @returns The description of the course.
   */
  get description(): string {
    return this._description;
  }

  /**
   * Sets the course description.
   * @param value - The description to set for the course.
   */
  set description(value: string) {
    this._description = value;
  }

  /**
   * Gets the category id associated with the course.
   * @returns The identifier of the category.
   */
  get categoryId(): number {
    return this._categoryId;
  }

  /**
   * Sets the category id associated with the course.
   * @param value - The identifier of the category to associate with the course.
   */
  set categoryId(value: number) {
    this._categoryId = value;
  }
}
