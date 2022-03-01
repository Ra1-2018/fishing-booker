import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientAdventuresReservationsService {

  constructor(private _http: HttpClient) { }

  public getReservations(): Observable<any[]> {
    return this._http.get<any[]>('http://localhost:8080/reservations/client-adventures/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  postReview(review: any): Observable<any> {
    return this._http.post<Observable<any>>('http://localhost:8080/reviews', review)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}
