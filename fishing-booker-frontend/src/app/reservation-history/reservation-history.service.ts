import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class ReservationHistoryService {

  constructor(private _http: HttpClient) { }

  public getReservations(): Observable<any[]> {
    return this._http.get<any[]>(server + 'reservations/owner/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}
