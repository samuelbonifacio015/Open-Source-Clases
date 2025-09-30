import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {BaseService} from '../../shared/infrastructure/base-service.service';
import {Course} from '../domain/model/course.entity';

/**
 * API endpoint path for courses resource obtained from environment configuration.
 */
const coursesResourceEndpointPath = environment.coursesEndpointPath;

/**
 * Service responsible for managing Course entities through HTTP requests.
 * Extends the BaseService to inherit common CRUD operations.
 *
 * Available operations inherited from BaseService:
 * GET /api/v1/courses - Retrieve all courses
 * GET /api/v1/courses/{id} - Retrieve a course by ID
 * POST /api/v1/courses - Create a new course
 * PUT /api/v1/courses/{id} - Update an existing course
 * DELETE /api/v1/courses/{id} - Delete a course by ID
 *
 */
@Injectable({
  providedIn: 'root'
})
export class CourseService extends BaseService<Course> {

  constructor() {
    super();
    this.resourceEndpointPath = coursesResourceEndpointPath;
  }

}
