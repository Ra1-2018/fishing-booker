import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class CottageOwnersService {

  constructor(private _http: HttpClient) { }

  getCottageOwners(): Observable<any[]> {
    return this._http.get<any[]>(server + 'users/cottage-owners')
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}
