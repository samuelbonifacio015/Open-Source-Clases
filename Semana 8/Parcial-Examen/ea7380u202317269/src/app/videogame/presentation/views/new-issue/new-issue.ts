import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { VideogameApi } from '../../../infrastructure/videogame-api';
import { Issues } from '../../../domain/model/issues.entity';
import { Servers } from '../../../domain/model/servers.entity';
import { ServiceOrders } from '../../../domain/model/service-orders.entity';

@Component({
  selector: 'app-new-issue',
  imports: [CommonModule, FormsModule],
  templateUrl: './new-issue.html',
  styleUrl: './new-issue.css'
})
export class NewIssue implements OnInit {
  servers: Servers[] = [];
  issues: Issues[] = [];
  issueTypes: string[] = [];

  selectedServerId: number | null = null;
  selectedIssueType: string = '';

  isSubmitting: boolean = false;
  errorMessage: string = '';

  constructor(
    private videogameApi: VideogameApi,
    private router: Router
  ) {}

  ngOnInit() {
    this.loadData();
  }

  private loadData() {
    this.videogameApi.getServers().subscribe(servers => {
      this.servers = servers;
    });

    this.videogameApi.getIssues().subscribe(issues => {
      this.issues = issues;
      this.extractIssueTypes();
    });
  }

  private extractIssueTypes() {
    this.issueTypes = [...new Set(this.issues.map(issue => issue.issueType))];
  }

  onSubmit() {
    if (!this.selectedServerId || !this.selectedIssueType) {
      this.errorMessage = 'Please select both server and issue type.';
      return;
    }

    if (!this.canCreateIssueForServer(this.selectedServerId)) {
      this.errorMessage = 'Only one issue per server can be registered per day.';
      return;
    }

    this.isSubmitting = true;
    this.errorMessage = '';

    const newIssue = new Issues(
      0,
      this.selectedServerId,
      this.selectedIssueType,
      new Date(),
      'OPEN'
    );

    this.videogameApi.createIssues(newIssue).subscribe({
      next: (createdIssue) => {
        this.createServiceOrder(createdIssue);
      },
      error: (error) => {
        this.errorMessage = 'Failed to create issue. Please try again.';
        this.isSubmitting = false;
        console.error('Error creating issue:', error);
      }
    });
  }

  private createServiceOrder(issue: Issues) {
    const server = this.servers.find(s => s.id === issue.serverId);
    if (!server) {
      this.errorMessage = 'Server not found for creating service order.';
      this.isSubmitting = false;
      return;
    }

    const serviceOrder = new ServiceOrders(
      0,
      issue.serverId,
      issue.id,
      server.defaultAction,
      'NORMAL'
    );

    this.videogameApi.createServiceOrders(serviceOrder).subscribe({
      next: () => {
        this.router.navigate(['/home']);
      },
      error: (error) => {
        this.errorMessage = 'Issue created but failed to create service order.';
        this.isSubmitting = false;
        console.error('Error creating service order:', error);
      }
    });
  }

  private canCreateIssueForServer(serverId: number): boolean {
    const today = new Date();
    today.setHours(0, 0, 0, 0);

    const tomorrow = new Date(today);
    tomorrow.setDate(tomorrow.getDate() + 1);

    const todayIssues = this.issues.filter(issue => {
      const issueDate = new Date(issue.registeredAt);
      return issue.serverId === serverId &&
             issueDate >= today &&
             issueDate < tomorrow;
    });

    return todayIssues.length === 0;
  }

  onCancel() {
    this.router.navigate(['/home']);
  }
}
