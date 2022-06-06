import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdventureService {

  constructor(private _http: HttpClient) { }

  getAdventures(): Observable<any[]> {
    return this._http.get<any[]>('http://localhost:8080/adventures')
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  getServices(serviceCriteria: any) : Observable<any> {
    return this._http.post<Observable<any>>('http://localhost:8080/services/search-default', serviceCriteria);
  }
}
