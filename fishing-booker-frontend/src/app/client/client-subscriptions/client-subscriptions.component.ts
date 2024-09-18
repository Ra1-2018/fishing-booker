import { Component, OnInit } from '@angular/core';
import { Sort } from '@angular/material/sort';
import { ClientSubscriptionsService } from './client-subscriptions.service';

@Component({
  selector: 'app-client-subscriptions',
  templateUrl: './client-subscriptions.component.html',
  styleUrls: ['./client-subscriptions.component.css']
})
export class ClientSubscriptionsComponent implements OnInit {

  subscriptions: any[] = []
  sortedData: any[] = []

  constructor(private _subscriptionService: ClientSubscriptionsService) { }

  ngOnInit(): void {
    this.getSubscriptions();
  }

  getSubscriptions() {
    this._subscriptionService.getSubscriptions().subscribe(
      subscriptions => {
        this.subscriptions = subscriptions;
        this.sortedData = this.subscriptions.slice();
      }
    )
  }

  unsubscribe(service: any) {
    this._subscriptionService.unsubscribe(service.id).subscribe({
      next: () => {
        this.getSubscriptions();
      }
    });
  }

  sortData(sort: Sort) {
    const data = this.subscriptions.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name': return compare(a.name, b.name, isAsc);
        case 'address': return compare(a.address, b.address, isAsc);
        case 'capacity': return compare(a.maxNumberOfPeople, b.maxNumberOfPeople, isAsc);
        default: return 0;
      }
    });
  }
}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
