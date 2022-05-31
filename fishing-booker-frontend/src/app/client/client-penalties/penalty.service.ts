import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from 'src/app/app-global';

@Injectable({
  providedIn: 'root'
})
export class PenaltyService {

  constructor(private _http: HttpClient) { }

  public getPenalties(): Observable<any[]> {
    return this._http.get<any[]>(server + 'penalties/client/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    );
  }
}
