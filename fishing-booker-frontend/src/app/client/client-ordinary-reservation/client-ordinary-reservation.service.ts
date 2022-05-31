import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { server } from 'src/app/app-global';

@Injectable({
  providedIn: 'root'
})
export class ClientOrdinaryReservationService {

  constructor(private _http: HttpClient) { }

  getServices(serviceCriteria: any) : Observable<any> {
    return this._http.post<Observable<any>>(server + 'services/search', serviceCriteria);
  }

  makeReservation(reservation: any) {
    return this._http.post(server + 'reservations', reservation);
  }
}
