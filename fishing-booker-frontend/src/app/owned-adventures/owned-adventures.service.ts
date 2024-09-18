import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class OwnedAdventuresService {

  constructor(private _http: HttpClient) { }

  getOwnedAdventures(): Observable<any[]> {
    return this._http.get<any[]>(server + 'adventures/owner/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  createAdventure(adventure: any): Observable<any> {
    return this._http.post<Observable<any>>(server + 'adventures/' + localStorage.getItem('userId'), adventure)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  getUser(): Observable<any> {
    return this._http.get<Observable<any>>(server + 'users/' + localStorage.getItem('userId'));
  }
}
