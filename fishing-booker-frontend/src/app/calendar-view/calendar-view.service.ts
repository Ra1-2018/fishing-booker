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
    console.log(period)
    console.log(localStorage.getItem('userId'))
    return this._http.post<Observable<any>>('http://localhost:8080/timeRanges/unavailablePeriod/owner/' + localStorage.getItem('userId'), period)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  // IDE U ADVENTURE DETAIL OWNER, TAMO GDE SE DODAJE FREE PERIOD
  // addServiceUnavailablePeriod(period:any): Observable<any> {
  //   return this._http.post<Observable<any>>('http://localhost:8080/timeRanges/unavailablePeriod/service', period)
  //   .pipe(
  //     tap(data => console.log("data: ", data))
  //   )
  // }
  //
  // getUnavailablePeriodsOfService(id:number): Observable<any[]> {
  //   return this._http.get<any[]>('http://localhost:8080/timeRanges/unavailablePeriods/service/' + id)
  //   .pipe(
  //     tap(data => console.log("data: ", data))
  //   )
  // }

  getUnavailablePeriodsOfOwner(id:number): Observable<any[]> {
    return this._http.get<any[]>('http://localhost:8080/timeRanges/unavailablePeriods/owner/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}
