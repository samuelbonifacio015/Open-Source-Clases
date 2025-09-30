import { Routes } from '@angular/router';
import {Home} from './shared/presentation/views/home/home';
import {CourseManagement} from './learning/presentation/course-management/course-management';

const about = () =>
  import('./shared/presentation/views/about/about')
  .then(m => m.About);
const pageNotFound = () =>
  import('./shared/presentation/views/page-not-found/page-not-found')
  .then(m => m.PageNotFound);

const baseTitle ='ACME Learning Center';
export const routes: Routes = [
  { path: 'home',           component: Home,                     title: `${baseTitle} - Home` },
  { path: 'about',          loadComponent: about,                 title: `${baseTitle} - About` },
  { path: 'courses',        component: CourseManagement,          title: `${baseTitle} - Courses` },
  { path: '',               redirectTo: 'home', pathMatch: 'full'   },
  { path: '**',            loadComponent: pageNotFound,    title: `${baseTitle} - Page not found` },
];
