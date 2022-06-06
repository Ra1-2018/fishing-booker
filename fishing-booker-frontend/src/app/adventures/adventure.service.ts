import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class AdventureService {

  constructor(private _http: HttpClient) { }

  getAdventures(): Observable<any[]> {
    return this._http.get<any[]>(server + 'adventures')
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  getServices(serviceCriteria: any) : Observable<any> {
    return this._http.post<Observable<any>>(server + 'services/search-default', serviceCriteria);
  }
}
