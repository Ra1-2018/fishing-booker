import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BoatService {

  constructor(private _http: HttpClient) { }

  getBoats(): Observable<any[]> {
    return this._http.get<any[]>('http://localhost:8080/boats')
    .pipe(
      tap(data => console.log('data: ', data))
    )
  }
}
