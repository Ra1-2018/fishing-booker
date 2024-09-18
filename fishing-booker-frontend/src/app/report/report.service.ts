import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(private _http: HttpClient) { }

  getReservation(id: number): Observable<any> {
    return this._http.get<any>(server + 'reservations/' + id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  submitReport(id: number, report: any) {
    return this._http.post(server + 'reports/' + id, report)
    .pipe(
      tap(data => console.log("data: ", data))
    );
  }

  givePenal(reservation: any) {
    return this._http.post(server + 'reservations/penal', reservation)
    .pipe(
      tap(data => console.log("data: ", data))
    );
  }
}
