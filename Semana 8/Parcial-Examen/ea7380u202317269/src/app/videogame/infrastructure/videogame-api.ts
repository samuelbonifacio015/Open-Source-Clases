import {Injectable} from '@angular/core';
import {BaseApi} from '../../shared/infrastructure/base-api';
import {ServersApiEndpoint} from './servers-api-endpoint';
import {IssuesApiEndpoint} from './issues-api-endpoint';
import {ServiceOrdersApiEndpoint} from './service-orders-endpoint';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Servers} from '../domain/model/servers.entity';
import {Issues} from '../domain/model/issues.entity';
import {ServiceOrders} from '../domain/model/service-orders.entity';

@Injectable({providedIn: 'root'})
export class VideogameApi extends BaseApi {
  private readonly serversEndpoint:  ServersApiEndpoint;
  private readonly issuesEndpoint: IssuesApiEndpoint;
  private readonly serviceOrdersEndpoint: ServiceOrdersApiEndpoint;

  constructor(http: HttpClient) {
    super();
    this.serversEndpoint =       new ServersApiEndpoint(http);
    this.issuesEndpoint =        new IssuesApiEndpoint(http);
    this.serviceOrdersEndpoint = new ServiceOrdersApiEndpoint(http);
  }

  getServers(): Observable<Servers[]> {
    return this.serversEndpoint.getAll();
  }

  createServers(server: Servers): Observable<Servers> {
    return this.serversEndpoint.create(server);
  }

  updateServers(server: Servers): Observable<Servers> {
    return this.serversEndpoint.update(server, server.id);
  }

  deleteServers(id: number): Observable<void> {
    return this.serversEndpoint.delete(id);
  }

  getIssues(): Observable<Issues[]> {
    return this.issuesEndpoint.getAll();
  }

  createIssues(issue: Issues): Observable<Issues> {
    return this.issuesEndpoint.create(issue);
  }

  updateIssues(issue: Issues): Observable<Issues> {
    return this.issuesEndpoint.update(issue, issue.id);
  }

  deleteIssues(id: number): Observable<void> {
    return this.issuesEndpoint.delete(id);
  }

  getServiceOrders(): Observable<ServiceOrders[]> {
    return this.serviceOrdersEndpoint.getAll();
  }

  createServiceOrders(serviceOrder: ServiceOrders): Observable<ServiceOrders> {
    return this.serviceOrdersEndpoint.create(serviceOrder);
  }

  updateServiceOrders(serviceOrder: ServiceOrders): Observable<ServiceOrders> {
    return this.serviceOrdersEndpoint.update(serviceOrder, serviceOrder.id);
  }

  deleteServiceOrders(id: number): Observable<void> {
    return this.serviceOrdersEndpoint.delete(id);
  }

}
