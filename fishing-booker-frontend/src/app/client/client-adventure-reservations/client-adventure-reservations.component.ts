import { Component, OnInit } from '@angular/core';
import { ClientAdventuresReservationsService } from './client-adventures-reservations.service';

@Component({
  selector: 'app-client-adventure-reservations',
  templateUrl: './client-adventure-reservations.component.html',
  styleUrls: ['./client-adventure-reservations.component.css']
})
export class ClientAdventureReservationsComponent implements OnInit {

  reservations: any[] = []

  constructor(private _reservationService: ClientAdventuresReservationsService) { }

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
