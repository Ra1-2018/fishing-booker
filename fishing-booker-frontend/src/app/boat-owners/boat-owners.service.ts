import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class BoatOwnersService {

  constructor(private _http: HttpClient) { }

  getBoatOwners(): Observable<any[]> {
    return this._http.get<any[]>(server + 'users/boat-owners')
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}
