import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OwnedCottagesService {

  constructor(private _http: HttpClient) { }

  getCottages(): Observable<any[]> {
    return this._http.get<any[]>('http://localhost:8080/cottages/owner/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  createCottage(cottage: any): Observable<any> {
    return this._http.post<Observable<any>>('http://localhost:8080/cottages/' + localStorage.getItem('userId'), cottage)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  getUser(): Observable<any> {
    return this._http.get<Observable<any>>('http://localhost:8080/users/' + localStorage.getItem('userId'));
  }

  deleteCottage(id: number){
   return this._http.get<any>('http://localhost:8080/cottages/delete/' + id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}

