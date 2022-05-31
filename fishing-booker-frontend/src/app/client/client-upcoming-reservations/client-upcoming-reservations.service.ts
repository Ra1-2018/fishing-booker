import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from 'src/app/app-global';

@Injectable({
  providedIn: 'root'
})
export class ClientUpcomingReservationsService {

  constructor(private _http: HttpClient) { }

  public getReservations(): Observable<any[]> {
    return this._http.get<any[]>(server + 'reservations/client-upcoming/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  public cancelReservation(reservation: any) {
    return this._http.get(server + 'reservations/cancel/' + reservation.id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}
