import { Component, OnInit } from '@angular/core';
import { Sort } from '@angular/material/sort';
import { ReservationSearch } from '../model/reservation-search';
import { AdventureService } from './adventure.service';

@Component({
  selector: 'app-adventures',
  templateUrl: './adventures.component.html',
  styleUrls: ['./adventures.component.css']
})
export class AdventuresComponent implements OnInit {

  adventures: any[] = []
  sortedData: any[] = []
  search = new ReservationSearch('ADVENTURE', null, null, '', null, null);

  constructor(private _adventureService: AdventureService) { }

  ngOnInit(): void {
    this.getAdventures();
  }

  getAdventures() {
    this._adventureService.getAdventures().subscribe(
      adventures => {
        this.adventures = adventures;
        this.sortedData = this.adventures.slice();
      }
    )
  }

  public onClickSubmit(): void {
    this._adventureService.getServices(this.search).subscribe(
      services => {
        this.adventures = services;
        this.sortedData = this.adventures.slice();
      }
    )
  }

  sortData(sort: Sort) {
    const data = this.adventures.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name': return compare(a.name, b.name, isAsc);
        case 'address': return compare(a.address, b.address, isAsc);
        case 'maxPeople': return compare(a.maxNumberOfPeople, b.maxNumberOfPeople, isAsc);
        case 'averageGrade': return compare(a.averageGrade, b.averageGrade, isAsc);
        default: return 0;
      }
    });
  }
}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
