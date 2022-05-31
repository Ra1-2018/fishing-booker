import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class CottageOwnerDetailService {

  constructor(private _http: HttpClient) { }

  getCottageOwner(id: number): Observable<any> {
    return this._http.get<any>(server + 'users/' + id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  delete(id: number): Observable<any> {
    return this._http.delete<Observable<any>>(server + 'users/' + id)
  }
}
