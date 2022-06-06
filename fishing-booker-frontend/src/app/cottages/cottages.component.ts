import { Component, OnInit } from '@angular/core';
import { Sort } from '@angular/material/sort';
import { ReservationSearch } from '../model/reservation-search';
import { CottageService } from './cottage.service';

@Component({
  selector: 'app-cottages',
  templateUrl: './cottages.component.html',
  styleUrls: ['./cottages.component.css']
})
export class CottagesComponent implements OnInit {

  query: '' | undefined;

  cottages: any[] = []
  sortedData: any[] = []
  search = new ReservationSearch('COTTAGE', null, null, '', null, null);

  constructor(private _cottageService: CottageService) { }

  ngOnInit(): void {
    this.getCottages();
  }

  getCottages() {
    this._cottageService.getCottages().subscribe(
      cottages => {
        this.cottages = cottages;
        this.sortedData = this.cottages.slice();
      }
    )
  }

  public onClickSubmit(): void {
    this._cottageService.getServices(this.search).subscribe(
      services => {
        this.cottages = services;
        this.sortedData = this.cottages.slice();
      }
    )
  }

  sortData(sort: Sort) {
    const data = this.cottages.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name': return compare(a.name, b.name, isAsc);
        case 'address': return compare(a.address, b.address, isAsc);
        //case 'roomsTotalNumber': return compare(a.roomsTotalNumber, b.roomsTotalNumber, isAsc);
        case 'averageGrade': return compare(a.averageGrade, b.averageGrade, isAsc);
        default: return 0;
      }
    });
  }
}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
