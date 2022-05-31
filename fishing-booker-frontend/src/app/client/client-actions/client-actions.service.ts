import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from 'src/app/app-global';

@Injectable({
  providedIn: 'root'
})
export class ClientActionsService {

  constructor(private _http: HttpClient) { }

  public getActions(id: number): Observable<any[]> {
    return this._http.get<any[]>(server + 'actions/service/' + id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  public makeReservation(action: any) {
    return this._http.get(server + 'actions/reservation/' + localStorage.getItem('userId') + '/' + action.id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}
