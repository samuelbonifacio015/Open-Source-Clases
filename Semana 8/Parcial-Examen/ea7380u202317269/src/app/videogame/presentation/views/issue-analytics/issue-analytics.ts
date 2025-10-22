import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VideogameApi } from '../../../infrastructure/videogame-api';
import { Issues } from '../../../domain/model/issues.entity';
import { Servers } from '../../../domain/model/servers.entity';
import { IssueTypeStats } from '../../components/issue-type-stats/issue-type-stats';

@Component({
  selector: 'app-issue-analytics',
  imports: [CommonModule, IssueTypeStats],
  templateUrl: './issue-analytics.html',
  styleUrl: './issue-analytics.css'
})
export class IssueAnalytics implements OnInit {
  issues: Issues[] = [];
  servers: Servers[] = [];
  issueTypes: string[] = [];

  constructor(private videogameApi: VideogameApi) {}

  ngOnInit() {
    this.loadData();
  }

  private loadData() {
    this.videogameApi.getIssues().subscribe(issues => {
      this.issues = issues;
      this.extractIssueTypes();
    });

    this.videogameApi.getServers().subscribe(servers => {
      this.servers = servers;
    });
  }

  private extractIssueTypes() {
    this.issueTypes = [...new Set(this.issues.map(issue => issue.issueType))];
  }
}
