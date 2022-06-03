import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CalendarViewService {

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

  addOwnerUnavailablePeriod(period:any): Observable<any> {
    return this._http.post<Observable<any>>('http://localhost:8080/unavailablePeriods/owner', period)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  getUnavailablePeriodsOfOwner(id:number): Observable<any[]> {
    return this._http.get<any[]>('http://localhost:8080/unavailablePeriods/owner/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}
