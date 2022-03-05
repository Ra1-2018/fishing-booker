import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientBoatReservationsService {

  constructor(private _http: HttpClient) { }

  public getReservations(): Observable<any[]> {
    return this._http.get<any[]>('http://localhost:8080/reservations/client-boats/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}
