export class Course {
  /** Unique identifier for the course */
  id: number;

  /** Title of the course */
  title: string;

  /** Detailed description of the course */
  description: string;


  /**
   * Creates a new Course instance.
   * @param course - The course data to initialize the instance with.
   * @param course.id - The unique identifier for the course.
   * @param course.title - The title of the course.
   * @param course.description - The detailed description of the course.
   */
  constructor(course: {id?: number, title?: string, description?: string}) {
    this.id = course.id || 0;
    this.title = course.title || '';
    this.description = course.description || '';
  }
}
