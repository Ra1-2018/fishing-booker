import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationClientService {

  constructor(private _http: HttpClient) { }

  getClient(id: number): Observable<any> {
    return this._http.get<any>('http://localhost:8080/users/' + id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  submitReport(report: any) {
    return this._http.post('http://localhost:8080/reports', report)
    .pipe(
      tap(data => console.log("data: ", data))
    );
  }
}



