import {Routes} from '@angular/router';

const courseList = () => import('./course-list/course-list').then(m => m.CourseList);
const courseForm = () => import('./course-form/course-form').then(m => m.CourseForm);
const categoryList = () => import('./category-list/category-list').then(m => m.CategoryList);
const categoryForm = () => import('./category-form/category-form').then(m => m.CategoryForm);
export const learningRoutes: Routes = [
  { path: 'courses',              loadComponent: courseList },
  { path: 'courses/new',          loadComponent: courseForm },
  { path: 'courses/:id/edit',     loadComponent: courseForm },
  { path: 'categories',           loadComponent: categoryList },
  { path: 'categories/new',       loadComponent: categoryForm },
  { path: 'categories/:id/edit',  loadComponent: categoryForm }
];
