import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientActionsService {

  constructor(private _http: HttpClient) { }

  public getActions(id: number): Observable<any[]> {
    return this._http.get<any[]>('http://localhost:8080/actions/service/' + id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}
