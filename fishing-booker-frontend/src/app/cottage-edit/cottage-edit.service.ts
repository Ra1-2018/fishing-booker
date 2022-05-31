import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class CottageEditService {

  constructor(private _http: HttpClient) { }

  getCottage(id: number): Observable<any> {
      return this._http.get<any>(server + 'cottages/' + id)
      .pipe(
        tap(data => console.log("data: ", data))
      )
    }


  updateCottage(cottage: any): Observable<any> {
    return this._http.post<Observable<any>>(server + 'cottages/update', cottage);
  }
}
