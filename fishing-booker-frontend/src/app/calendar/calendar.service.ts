import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CalendarService {

  constructor(public _http:HttpClient) { }

  getAllReservationsOfOwner(): Observable<any[]> {
    return this._http.get<any[]>('http://localhost:8080/reservations/owner/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  getAllActionsOfOwner(): Observable<any[]> {
    return this._http.get<any[]>('http://localhost:8080/actions/owner/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}
