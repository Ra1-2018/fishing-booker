import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class ReservationClientService {

  constructor(private _http: HttpClient) { }

  getClient(id: number): Observable<any> {
    return this._http.get<any>(server + 'users/' + id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  submitReport(report: any) {
    return this._http.post(server + 'reports', report)
    .pipe(
      tap(data => console.log("data: ", data))
    );
  }
}



