import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class OwnedBoatsService {

  constructor(private _http: HttpClient) { }
  
  getBoats(): Observable<any[]> {
    return this._http.get<any[]>(server + 'boats/owner/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  createBoat(boat: any): Observable<any> {
    return this._http.post<Observable<any>>(server + 'boats/' + localStorage.getItem('userId'), boat)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  getUser(): Observable<any> {
    return this._http.get<Observable<any>>(server + 'users/' + localStorage.getItem('userId'));
  }

  deleteBoat(id: number){
    console.log("Blabla");
    return this._http.get<any>(server + 'boats/delete/' + id)
     .pipe(
       tap(data => console.log("data: ", data))
     )
   }
}

