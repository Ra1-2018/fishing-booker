import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(private _http: HttpClient) { }

  getReservation(id: number): Observable<any> {
    return this._http.get<any>('http://localhost:8080/reservations/' + id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  submitReport(id: number, report: any) {
    return this._http.post('http://localhost:8080/reports/' + id, report)
    .pipe(
      tap(data => console.log("data: ", data))
    );
  }

  givePenal(reservation: any) {
    return this._http.post('http://localhost:8080/reservations/penal', reservation)
    .pipe(
      tap(data => console.log("data: ", data))
    );
  }
}
