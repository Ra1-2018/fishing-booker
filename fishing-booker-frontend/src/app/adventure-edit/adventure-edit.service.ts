import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class AdventureEditService {

  constructor(private _http: HttpClient) { }

  getAdventure(id: number): Observable<any> {
    return this._http.get<any>(server + 'adventures/' + id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  updateAdventure(adventure: any): Observable<any> {
    return this._http.post<Observable<any>>(server + 'adventures/update', adventure);
  }
}
