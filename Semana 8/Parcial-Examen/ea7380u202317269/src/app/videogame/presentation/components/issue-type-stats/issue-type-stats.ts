import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Issues } from '../../../domain/model/issues.entity';
import { Servers } from '../../../domain/model/servers.entity';

@Component({
  selector: 'app-issue-type-stats',
  imports: [CommonModule],
  templateUrl: './issue-type-stats.html',
  styleUrl: './issue-type-stats.css'
})
export class IssueTypeStats implements OnInit {
  @Input() issueType!: string;
  @Input() issues: Issues[] = [];
  @Input() servers: Servers[] = [];

  costPerHour: number = 0;
  accumulatedCost: number = 0;
  reportedIssues: number = 0;

  ngOnInit() {
    this.calculateStatistics();
  }

  private calculateStatistics() {
    const issuesForType = this.issues.filter(issue => issue.issueType === this.issueType);
    this.reportedIssues = issuesForType.length;

    const openIssues = issuesForType.filter(issue => issue.status === 'OPEN');
    this.costPerHour = openIssues.reduce((total, issue) => {
      const server = this.servers.find(s => s.id === issue.serverId);
      return total + (server?.costPerHour || 0);
    }, 0);

    this.accumulatedCost = issuesForType.reduce((total, issue) => {
      const server = this.servers.find(s => s.id === issue.serverId);
      if (server) {
        const hoursElapsed = (Date.now() - new Date(issue.registeredAt).getTime()) / (1000 * 60 * 60);
        return total + (server.costPerHour * hoursElapsed);
      }
      return total;
    }, 0);

    this.accumulatedCost = Math.round(this.accumulatedCost * 100) / 100;
  }
}
