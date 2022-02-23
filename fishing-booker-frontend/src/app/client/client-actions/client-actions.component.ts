import { Component, OnInit } from '@angular/core';
import { Sort } from '@angular/material/sort';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientActionsService } from './client-actions.service';

@Component({
  selector: 'app-client-actions',
  templateUrl: './client-actions.component.html',
  styleUrls: ['./client-actions.component.css']
})
export class ClientActionsComponent implements OnInit {

  actions: any[] = []
  sortedData: any[] = []
  id: number | undefined

  constructor(private route: ActivatedRoute,
              private router: Router, 
              private actionsService: ClientActionsService) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    if (this.id) {
      this.getActions(this.id);
    }
  }

  getActions(id: number): void {
    this.actionsService.getActions(id).subscribe(
      actions => {
        this.actions = actions;
        this.sortedData = this.actions.slice();
      }
    )
  }

  sortData(sort: Sort) {
    const data = this.actions.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name': return compare(a.service.name, b.service.name, isAsc);
        case 'originalPrice': return compare(a.originalPrice, b.originalPrice, isAsc);
        case 'discount': return compare(a.discount, b.discount, isAsc);
        case 'price': return compare(a.price, b.price, isAsc);
        case 'startTime': return compare(a.startTime, b.startTime, isAsc);
        case 'durationInDays': return compare(a.durationInDays, b.durationInDays, isAsc);
        default: return 0;
      }
    });
  }

  makeReservation(action: any) {
    this.actionsService.makeReservation(action).subscribe({
      next: result => {
        alert("Successful reservation");
        this.getActions(this.id as number);
      },
      error: err => alert("An error occured.")
    });
  }
}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
