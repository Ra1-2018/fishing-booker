import { Component, OnInit } from '@angular/core';
import { ClientCottageReservationsService } from './client-cottage-reservations.service';

@Component({
  selector: 'app-client-cottage-reservations',
  templateUrl: './client-cottage-reservations.component.html',
  styleUrls: ['./client-cottage-reservations.component.css']
})
export class ClientCottageReservationsComponent implements OnInit {

  reservations: any[] = []

  constructor(private _reservationService: ClientCottageReservationsService) { }

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
