import {CourseResource, CoursesResponse} from './courses-response';
import {Course} from '../domain/model/course.entity';

export class CourseAssembler {
  static toEntityFromResource(resource: CourseResource): Course {
    return {
      id: resource.id,
      title: resource.title,
      description: resource.description
    };
  }

  static toEntitiesFromResponse(response: CoursesResponse): Course[] {
    return response.courses.map(course => this.toEntityFromResource(course));
  }
}
