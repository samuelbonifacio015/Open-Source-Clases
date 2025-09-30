import {computed, inject, Injectable, signal} from '@angular/core';
import {Course} from '../domain/model/course.entity';
import {CourseService} from '../infrastructure/course.service';

@Injectable({
  providedIn: 'root'
})

export class CoursesStore {

  private coursesSignal = signal<Course[]>([]);
  private coursesApi = inject(CourseService);

  readonly courses = computed(() => this.coursesSignal());

}
