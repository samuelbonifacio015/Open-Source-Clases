import {BaseResource, BaseResponse} from '../../shared/infrastructure/base-response';

/**
 * Represents a single course resource returned from the API.
 *
 * @remarks
 * This interface extends {@link BaseResource} and includes the core properties of a course.
 *
 * @property id - Unique identifier for the course.
 * @property title - Title of the course.
 * @property description - Description of the course.
 */
export interface CourseResource extends BaseResource {
  /**
   * Unique identifier for the course.
   */
  id: number;
  /**
   * Title of the course.
   */
  title: string;
  /**
   * Description of the course.
   */
  description: string;

  /**
   * Identifier for the category this course belongs to.
   */
  categoryId: number;
}

/**
 * Represents the response structure for a list of courses from the API.
 *
 * @remarks
 * This interface extends {@link BaseResponse} and contains an array of {@link CourseResource} objects.
 *
 * @property courses - Array of course resources included in the response.
 *
 * @example
 * ```typescript
 * const response: CoursesResponse = {
 *   status: 'success',
 *   courses: [
 *     { id: 1, title: 'Intro to TypeScript', description: 'Learn TS', ... },
 *     { id: 2, title: 'Advanced Angular', description: 'Deep dive', ... }
 *   ]
 * };
 * ```
 */
export interface CoursesResponse extends BaseResponse {
  /**
   * Array of course resources included in the response.
   */
  courses: CourseResource[];
}
