import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from 'src/app/app-global';

@Injectable({
  providedIn: 'root'
})
export class ClientBoatReservationsService {

  constructor(private _http: HttpClient) { }

  public getReservations(): Observable<any[]> {
    return this._http.get<any[]>(server + 'reservations/client-boats/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}
