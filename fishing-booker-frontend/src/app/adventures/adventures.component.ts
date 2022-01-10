import { Component, OnInit } from '@angular/core';
import { Sort } from '@angular/material/sort';
import { AdventureService } from './adventure.service';

@Component({
  selector: 'app-adventures',
  templateUrl: './adventures.component.html',
  styleUrls: ['./adventures.component.css']
})
export class AdventuresComponent implements OnInit {

  adventures: any[] = []
  sortedData: any[] = []

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
        case 'maxPeople': return compare(a.maxPeople, b.maxPeople, isAsc);
        case 'instructor': return compare(a.instructor.email, b.instructor.email, isAsc);
        default: return 0;
      }
    });
  }
}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
