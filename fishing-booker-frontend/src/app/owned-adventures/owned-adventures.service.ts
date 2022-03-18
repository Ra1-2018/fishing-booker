import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OwnedAdventuresService {

  constructor(private _http: HttpClient) { }

  getOwnedAdventures(): Observable<any[]> {
    return this._http.get<any[]>('http://localhost:8080/adventures/owner/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  createAdventure(adventure: any): Observable<any> {
    return this._http.post<Observable<any>>('http://localhost:8080/adventures/' + localStorage.getItem('userId'), adventure)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  getUser(): Observable<any> {
    return this._http.get<Observable<any>>('http://localhost:8080/users/' + localStorage.getItem('userId'));
  }

  deleteAdventure(id: number){
   return this._http.delete<any>('http://localhost:8080/adventures/' + id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

}
