import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VideogameApi } from '../../../infrastructure/videogame-api';
import { ServiceOrders } from '../../../domain/model/service-orders.entity';
import { Servers } from '../../../domain/model/servers.entity';

@Component({
  selector: 'app-next-service-order',
  imports: [CommonModule],
  templateUrl: './next-service-order.html',
  styleUrl: './next-service-order.css'
})
export class NextServiceOrder implements OnInit {
  nextServiceOrder: ServiceOrders | null = null;
  server: Servers | null = null;
  servers: Servers[] = [];

  constructor(private videogameApi: VideogameApi) {}

  ngOnInit() {
    this.loadData();
  }

  private loadData() {
    this.videogameApi.getServiceOrders().subscribe(serviceOrders => {
      this.findNextHighPriorityOrder(serviceOrders);
    });

    this.videogameApi.getServers().subscribe(servers => {
      this.servers = servers;
      if (this.nextServiceOrder) {
        this.server = servers.find(s => s.id === this.nextServiceOrder!.serverId) || null;
      }
    });
  }

  private findNextHighPriorityOrder(serviceOrders: ServiceOrders[]) {
    const highPriorityOrders = serviceOrders.filter(order => order.priority === 'HIGH');

    if (highPriorityOrders.length > 0) {
      highPriorityOrders.sort((a, b) =>
        new Date(a.registeredAt).getTime() - new Date(b.registeredAt).getTime()
      );
      this.nextServiceOrder = highPriorityOrders[0];

      if (this.servers.length > 0) {
        this.server = this.servers.find(s => s.id === this.nextServiceOrder!.serverId) || null;
      }
    }
  }
}
