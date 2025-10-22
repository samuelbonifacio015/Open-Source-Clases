import { Routes } from '@angular/router';
import {Home} from './shared/presentation/views/home/home';
import { videogameRoutes } from './videogame/presentation/videogame.routes';

const pageNotFound = () => import('./shared/presentation/views/page-not-found/page-not-found').then(m => m.PageNotFound);
const baseTitle = 'Ubisoft';
export const routes: Routes = [
  { path: 'home',     component:      Home,                         title: `${baseTitle} - Home`  },
  { path: 'support/issues/new', ...videogameRoutes.find(r => r.path === 'support/issues/new')! },
  { path: 'analytics', ...videogameRoutes.find(r => r.path === 'analytics')! },
  { path: 'service-orders/next', ...videogameRoutes.find(r => r.path === 'service-orders/next')! },
  { path: '',         redirectTo:     '/home', pathMatch: 'full'  },
  { path: '**',       loadComponent:  pageNotFound,                 title: `${baseTitle} - Page Not Found`  },
];
