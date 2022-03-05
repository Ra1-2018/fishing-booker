import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Sort } from '@angular/material/sort';
import { ClientBoatReservationsService } from './client-boat-reservations.service';

@Component({
  selector: 'app-client-boat-reservations',
  templateUrl: './client-boat-reservations.component.html',
  styleUrls: ['./client-boat-reservations.component.css']
})
export class ClientBoatReservationsComponent implements OnInit {

  reservations: any[] = []
  sortedData: any[] = []

  constructor(private _reservationService: ClientBoatReservationsService) {}

  ngOnInit(): void {
    this.getReservations();
  }

  getReservations() {
    this._reservationService.getReservations().subscribe(
      reservations => {
        this.reservations = reservations;
        this.sortedData = this.reservations.slice();
      }
    )
  }

  sortData(sort: Sort) {
    const data = this.reservations.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name': return compare(a.service.name, b.service.name, isAsc);
        case 'price': return compare(a.price, b.price, isAsc);
        case 'reservationStartDateAndTime': return compare(a.reservationStartDateAndTime, b.reservationStartDateAndTime, isAsc);
        case 'durationInDays': return compare(a.durationInDays, b.durationInDays, isAsc);
        default: return 0;
      }
    });
  }
}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
