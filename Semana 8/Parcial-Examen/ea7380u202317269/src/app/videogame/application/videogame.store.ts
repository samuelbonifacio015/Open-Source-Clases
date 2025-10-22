import {computed, Injectable, Signal, signal} from '@angular/core';
import {Servers} from '../domain/model/servers.entity';
import {Issues} from '../domain/model/issues.entity';
import {ServiceOrders} from '../domain/model/service-orders.entity';
import {VideogameApi} from '../infrastructure/videogame-api';
import {takeUntilDestroyed} from '@angular/core/rxjs-interop';
import {Observable, retry} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VideogameStore {
  readonly serversCount = computed(() => this.servers().length);
  private readonly serversSignal = signal<Servers[]>([])
  readonly servers = this.serversSignal.asReadonly();
  readonly issuesCount = computed(() => this.issues().length);
  private readonly issuesSignal = signal<Issues[]>([])
  readonly issues = this.issuesSignal.asReadonly();
  readonly serviceOrdersCount = computed(() => this.serviceOrders().length);
  private readonly serviceOrdersSignal = signal<ServiceOrders[]>([])
  readonly serviceOrders = this.serviceOrdersSignal.asReadonly();
  private readonly errorSignal = signal<string | null>(null);
  readonly error = this.errorSignal.asReadonly()
  private readonly loadingSignal = signal<boolean>(false);
  readonly loading = this.loadingSignal.asReadonly();

  constructor(private videogameApi: VideogameApi) {
    this.loadServers();
    this.loadIssues();
    this.loadServiceOrders();
  }

  getServerById(id: number): Signal<Servers | undefined> {
    return computed(() => id ? this.servers().find(s => s.id === id) : undefined);
  }

  getIssueById(id: number): Signal<Issues | undefined> {
    return computed(() => id ? this.issues().find(i => i.id === id) : undefined);
  }

  getServiceOrderById(id: number): Signal<ServiceOrders | undefined> {
    return computed(() => id ? this.serviceOrders().find(so => so.id === id) : undefined);
  }
  addServer(server: Servers): void {
    this.loadingSignal.set(true);
    this.errorSignal.set(null);
    this.videogameApi.createServers(server).pipe(retry(2)).subscribe({
      next: createdServer => {
        this.serversSignal.update(servers => [...servers, createdServer]);
        this.loadingSignal.set(false);
      },
      error: err => {
        this.errorSignal.set(this.formatError(err, 'Failed to create server'));
        this.loadingSignal.set(false);
      }
    });
  }

  updateServer(updatedServer: Servers): void {
    this.loadingSignal.set(true);
    this.errorSignal.set(null);
    this.videogameApi.updateServers(updatedServer).pipe(retry(2)).subscribe({
      next: () => {
        this.serversSignal.update(servers => servers.map(s => s.id === updatedServer.id ? updatedServer : s));
        this.loadingSignal.set(false);
      },
      error: err => {
        this.errorSignal.set(this.formatError(err, 'Failed to update server'));
        this.loadingSignal.set(false);
      }
    });
  }

  deleteServer(id: number): void {
    this.loadingSignal.set(true);
    this.errorSignal.set(null);
    this.videogameApi.deleteServers(id).pipe(retry(2)).subscribe({
      next: () => {
        this.serversSignal.update(servers => servers.filter(s => s.id !== id));
        this.loadingSignal.set(false);
      },
      error: err => {
        this.errorSignal.set(this.formatError(err, 'Failed to delete server'));
        this.loadingSignal.set(false);
      }
    });
  }

  addIssue(issue: Issues): void {
    this.loadingSignal.set(true);
    this.errorSignal.set(null);
    this.videogameApi.createIssues(issue).pipe(retry(2)).subscribe({
      next: createdIssue => {
        this.issuesSignal.update(issues => [...issues, createdIssue]);
        this.loadingSignal.set(false);
      },
      error: err => {
        this.errorSignal.set(this.formatError(err, 'Failed to create issue'));
        this.loadingSignal.set(false);
      }
    });
  }

  updateIssue(updatedIssue: Issues): void {
    this.loadingSignal.set(true);
    this.errorSignal.set(null);
    this.videogameApi.updateIssues(updatedIssue).pipe(retry(2)).subscribe({
      next: () => {
        this.issuesSignal.update(issues => issues.map(i => i.id === updatedIssue.id ? updatedIssue : i));
        this.loadingSignal.set(false);
      },
      error: err => {
        this.errorSignal.set(this.formatError(err, 'Failed to update issue'));
        this.loadingSignal.set(false);
      }
    });
  }

  deleteIssue(id: number): void {
    this.loadingSignal.set(true);
    this.errorSignal.set(null);
    this.videogameApi.deleteIssues(id).pipe(retry(2)).subscribe({
      next: () => {
        this.issuesSignal.update(issues => issues.filter(i => i.id !== id));
        this.loadingSignal.set(false);
      },
      error: err => {
        this.errorSignal.set(this.formatError(err, 'Failed to delete issue'));
        this.loadingSignal.set(false);
      }
    });
  }

  addServiceOrder(serviceOrder: ServiceOrders): void {
    this.loadingSignal.set(true);
    this.errorSignal.set(null);
    this.videogameApi.createServiceOrders(serviceOrder).pipe(retry(2)).subscribe({
      next: createdServiceOrder => {
        this.serviceOrdersSignal.update(serviceOrders => [...serviceOrders, createdServiceOrder]);
        this.loadingSignal.set(false);
      },
      error: err => {
        this.errorSignal.set(this.formatError(err, 'Failed to create service order'));
        this.loadingSignal.set(false);
      }
    });
  }

  updateServiceOrder(updatedServiceOrder: ServiceOrders): void {
    this.loadingSignal.set(true);
    this.errorSignal.set(null);
    this.videogameApi.updateServiceOrders(updatedServiceOrder).pipe(retry(2)).subscribe({
      next: () => {
        this.serviceOrdersSignal.update(serviceOrders =>
          serviceOrders.map(so => so.id === updatedServiceOrder.id ? updatedServiceOrder : so)
        );
        this.loadingSignal.set(false);
      },
      error: err => {
        this.errorSignal.set(this.formatError(err, 'Failed to update service order'));
        this.loadingSignal.set(false);
      }
    });
  }

  deleteServiceOrder(id: number): void {
    this.loadingSignal.set(true);
    this.errorSignal.set(null);
    this.videogameApi.deleteServiceOrders(id).pipe(retry(2)).subscribe({
      next: () => {
        this.serviceOrdersSignal.update(serviceOrders => serviceOrders.filter(so => so.id !== id));
        this.loadingSignal.set(false);
      },
      error: err => {
        this.errorSignal.set(this.formatError(err, 'Failed to delete service order'));
        this.loadingSignal.set(false);
      }
    });
  }

  private loadServers(): void {
    this.loadingSignal.set(true);
    this.errorSignal.set(null);
    this.videogameApi.getServers().pipe(takeUntilDestroyed()).subscribe({
      next: servers => {
        this.serversSignal.set(servers);
        this.loadingSignal.set(false);
      },
      error: err => {
        this.errorSignal.set(this.formatError(err, 'Failed to load servers'));
        this.loadingSignal.set(false);
      }
    });
  }

  private loadIssues(): void {
    this.loadingSignal.set(true);
    this.errorSignal.set(null);
    this.videogameApi.getIssues().pipe(takeUntilDestroyed()).subscribe({
      next: issues => {
        this.issuesSignal.set(issues);
        this.loadingSignal.set(false);
      },
      error: err => {
        this.errorSignal.set(this.formatError(err, 'Failed to load issues'));
        this.loadingSignal.set(false);
      }
    });
  }

  private loadServiceOrders(): void {
    this.loadingSignal.set(true);
    this.errorSignal.set(null);
    this.videogameApi.getServiceOrders().pipe(takeUntilDestroyed()).subscribe({
      next: serviceOrders => {
        this.serviceOrdersSignal.set(serviceOrders);
        this.loadingSignal.set(false);
      },
      error: err => {
        this.errorSignal.set(this.formatError(err, 'Failed to load service orders'));
        this.loadingSignal.set(false);
      }
    });
  }

  private formatError(error: any, fallback: string): string {
    if (error instanceof Error) {
      return error.message.includes('Resource not found') ? `${fallback}: Not found` : error.message;
    }
    return fallback;
  }

}
