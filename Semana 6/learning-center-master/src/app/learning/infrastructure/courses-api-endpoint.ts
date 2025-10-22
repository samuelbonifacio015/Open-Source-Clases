import {BaseApiEndpoint} from '../../shared/infrastructure/base-api-endpoint';
import {Course} from '../domain/model/couse.entity';
import {CourseResource, CoursesResponse} from './courses-response';
import {CourseAssembler} from './course-assembler';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';


/**
 * API endpoint for managing courses.
 */
export class CoursesApiEndpoint extends BaseApiEndpoint<Course, CourseResource, CoursesResponse, CourseAssembler> {
  constructor(http: HttpClient) {
    super(http, `${environment.platformProviderApiBaseUrl}${environment.platformProviderCoursesEndpointPath}`, new CourseAssembler());
  }
}
