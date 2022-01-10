import { Component, OnInit } from '@angular/core';
import { ClientBoatReservationsService } from './client-boat-reservations.service';

@Component({
  selector: 'app-client-boat-reservations',
  templateUrl: './client-boat-reservations.component.html',
  styleUrls: ['./client-boat-reservations.component.css']
})
export class ClientBoatReservationsComponent implements OnInit {

  reservations: any[] = []

  constructor(private _reservationService: ClientBoatReservationsService) { }

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
