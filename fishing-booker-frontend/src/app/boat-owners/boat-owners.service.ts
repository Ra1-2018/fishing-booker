import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BoatOwnersService {

  constructor(private _http: HttpClient) { }

  getBoatOwners(): Observable<any[]> {
    return this._http.get<any[]>('http://localhost:8080/users/boat-owners')
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}
