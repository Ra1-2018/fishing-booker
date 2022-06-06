import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class BoatService {

  constructor(private _http: HttpClient) { }

  getBoats(): Observable<any[]> {
    return this._http.get<any[]>(server + 'boats')
    .pipe(
      tap(data => console.log('data: ', data))
    )
  }

  getServices(serviceCriteria: any) : Observable<any> {
    return this._http.post<Observable<any>>('http://localhost:8080/services/search-default', serviceCriteria);
  }
}
