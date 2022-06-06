import { Component, OnInit } from '@angular/core';
import { Sort } from '@angular/material/sort';
import { ReservationSearch } from '../model/reservation-search';
import { BoatService } from './boat.service';

@Component({
  selector: 'app-boats',
  templateUrl: './boats.component.html',
  styleUrls: ['./boats.component.css']
})
export class BoatsComponent implements OnInit {

  boats: any[] = []
  sortedData: any[] = []
  search = new ReservationSearch('BOAT', null, null, '', null, null);

  constructor(private _boatService: BoatService) { }

  ngOnInit(): void {
    this.getBoats();
  }

  getBoats() {
    this._boatService.getBoats().subscribe(
      boats => {
        this.boats = boats;
        this.sortedData = this.boats.slice();
      }
    )
  }

  public onClickSubmit(): void {
    this._boatService.getServices(this.search).subscribe(
      services => {
        this.boats = services;
        this.sortedData = this.boats.slice();
      }
    )
  }

  sortData(sort: Sort) {
    const data = this.boats.slice();
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
        case 'averageGrade': return compare(a.averageGrade, b.averageGrade, isAsc);
        default: return 0;
      }
    });
  }
}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
