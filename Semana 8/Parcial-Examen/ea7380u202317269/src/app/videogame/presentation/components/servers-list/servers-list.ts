import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-servers-list',
  imports: [CommonModule],
  templateUrl: './servers-list.html',
  styleUrl: './servers-list.css'
})
export class ServersList implements OnInit {
  servers: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadServers();
  }

  loadServers() {
    this.http.get<any[]>('http://localhost:3001/servers').subscribe({
      next: (data) => {
        this.servers = data;
      },
      error: (error) => {
        console.error('Error loading servers:', error);
      }
    });
  }
}
