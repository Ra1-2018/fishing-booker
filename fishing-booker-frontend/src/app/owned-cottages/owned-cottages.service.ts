import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class OwnedCottagesService {

  constructor(private _http: HttpClient) { }

  getCottages(): Observable<any[]> {
    return this._http.get<any[]>(server + 'cottages/owner/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  createCottage(cottage: any): Observable<any> {
    return this._http.post<Observable<any>>(server + 'cottages/' + localStorage.getItem('userId'), cottage)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  getUser(): Observable<any> {
    return this._http.get<Observable<any>>(server + 'users/' + localStorage.getItem('userId'));
  }

  deleteCottage(id: number){
   return this._http.get<any>(server + 'cottages/delete/' + id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}

