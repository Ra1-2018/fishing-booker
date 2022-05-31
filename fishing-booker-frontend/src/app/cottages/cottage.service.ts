import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class CottageService {

  constructor(private _http: HttpClient) { }

  getCottages(): Observable<any[]> {
    return this._http.get<any[]>(server + 'cottages')
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}
