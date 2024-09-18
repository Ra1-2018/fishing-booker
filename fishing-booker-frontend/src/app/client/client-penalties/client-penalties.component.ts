import { Component, OnInit } from '@angular/core';
import { Sort } from '@angular/material/sort';
import { PenaltyService } from './penalty.service';

@Component({
  selector: 'app-client-penalties',
  templateUrl: './client-penalties.component.html',
  styleUrls: ['./client-penalties.component.css']
})
export class ClientPenaltiesComponent implements OnInit {

  penalties: any[] = [];
  sortedData: any[] = [];

  constructor(private _penaltyService: PenaltyService) { }

  ngOnInit(): void {
    this.getPenalties();
  }

  getPenalties() {
    this._penaltyService.getPenalties().subscribe(
      penalties => {
        this.penalties = penalties;
        this.sortedData = this.penalties.slice();
      }
    );
  }

  sortData(sort: Sort) {
    const data = this.penalties.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'dateIssued': return compare(a.dateIssued, b.dateIssued, isAsc);
        case 'owner': return compare(a.owner, b.owner, isAsc);
        default: return 0;
      }
    });
  }
}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}