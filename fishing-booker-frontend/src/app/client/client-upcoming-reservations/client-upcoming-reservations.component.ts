import { Component, OnInit } from '@angular/core';
import { Sort } from '@angular/material/sort';
import { ClientUpcomingReservationsService } from './client-upcoming-reservations.service';

@Component({
  selector: 'app-client-upcoming-reservations',
  templateUrl: './client-upcoming-reservations.component.html',
  styleUrls: ['./client-upcoming-reservations.component.css']
})
export class ClientUpcomingReservationsComponent implements OnInit {

  reservations: any[] = []
  sortedData: any[] = []

  constructor(private _reservationService: ClientUpcomingReservationsService) { }

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

  isInMoreThanThreeDays(reservation: any):boolean {
    let today = new Date();
    let threeDaysBefore = new Date(reservation.reservationStartDateAndTime);
    threeDaysBefore.setDate(threeDaysBefore.getDate() - 3);
    return today.getTime() - threeDaysBefore.getTime() < 0;
  }

  cancelReservation(reservation: any) {
    if(confirm("Are you sure to cancel your reservation? ")) {
      this._reservationService.cancelReservation(reservation).subscribe({
        next: () => {
          alert("Reservation cancelled")
          this.getReservations();
        },
        error: () => alert("An error occured")
      })
    }
  }
}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
