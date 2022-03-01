import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientOrdinaryReservationService {

  constructor(private _http: HttpClient) { }

  getServices(serviceCriteria: any) : Observable<any> {
    return this._http.post<Observable<any>>('http://localhost:8080/services/search', serviceCriteria);
  }

  makeReservation(reservation: any) {
    return this._http.post('http://localhost:8080/reservations', reservation);
  }
}
