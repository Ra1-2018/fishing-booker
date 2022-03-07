import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BoatOwnerDetailService {

  constructor(private _http: HttpClient) { }

  getBoatOwner(id: number): Observable<any> {
    return this._http.get<any>('http://localhost:8080/users/' + id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  delete(id: number): Observable<any> {
    return this._http.delete<Observable<any>>('http://localhost:8080/users/' + id)
  }
}
