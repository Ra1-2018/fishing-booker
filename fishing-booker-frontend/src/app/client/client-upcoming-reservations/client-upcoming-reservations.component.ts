import { Component, OnInit } from '@angular/core';
import { ClientUpcomingReservationsService } from './client-upcoming-reservations.service';

@Component({
  selector: 'app-client-upcoming-reservations',
  templateUrl: './client-upcoming-reservations.component.html',
  styleUrls: ['./client-upcoming-reservations.component.css']
})
export class ClientUpcomingReservationsComponent implements OnInit {

  reservations: any[] = []

  constructor(private _reservationService: ClientUpcomingReservationsService) { }

  ngOnInit(): void {
    this.getReservations();
  }

  getReservations() {
    this._reservationService.getReservations().subscribe(
      reservations => {
        this.reservations = reservations;
      }
    )
  }
}
