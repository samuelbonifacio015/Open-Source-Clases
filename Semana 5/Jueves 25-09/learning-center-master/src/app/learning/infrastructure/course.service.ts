import { Injectable } from '@angular/core';
import {environment} from '../../../environments/environment';
import {BaseService} from '../../shared/infrastructure/base-service.service';
import {Course} from '../domain/model/course.entity';

const coursesResourceEndpointPath = environment.coursesEndpointPath;
@Injectable({
  providedIn: 'root'
})
export class CourseService extends BaseService<Course> {

}
