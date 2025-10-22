import {Routes} from '@angular/router';

const issueAnalytics = () => import('./views/issue-analytics/issue-analytics').then(m => m.IssueAnalytics);
const nextServiceOrder = () => import('./views/next-service-order/next-service-order').then(m => m.NextServiceOrder);
const newIssue = () => import('./views/new-issue/new-issue').then(m => m.NewIssue);

export const videogameRoutes: Routes = [
  {
    path: 'analytics',
    loadComponent: issueAnalytics,
    title: 'Issue Analytics'
  },
  {
    path: 'service-orders/next',
    loadComponent: nextServiceOrder,
    title: 'Next Service Order'
  },
  {
    path: 'support/issues/new',
    loadComponent: newIssue,
    title: 'New Issue'
  }
];
