import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationOwnerService {

  constructor(private _http: HttpClient) { }

  makeReservation(reservation: any) {
    return this._http.post('http://localhost:8080/reservations', reservation);
  }

  getServices(): Observable<any[]> {

    if(localStorage.getItem('userType') == "COTTAGE_OWNER") {
      return this._http.get<any[]>('http://localhost:8080/cottages/owner/' + localStorage.getItem('userId'))
      .pipe(
        tap(data => console.log("data: ", data))
      )
    }
    else {
      return this._http.get<any[]>('http://localhost:8080/boats/owner/' + localStorage.getItem('userId'))
      .pipe(
        tap(data => console.log("data: ", data))
      )
    }
  }

}
