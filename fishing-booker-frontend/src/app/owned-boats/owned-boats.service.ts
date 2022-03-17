import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OwnedBoatsService {

  constructor(private _http: HttpClient) { }
  
  getBoats(): Observable<any[]> {
    return this._http.get<any[]>('http://localhost:8080/boats/owner/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  createBoat(boat: any): Observable<any> {
    return this._http.post<Observable<any>>('http://localhost:8080/boats/' + localStorage.getItem('userId'), boat)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  getUser(): Observable<any> {
    return this._http.get<Observable<any>>('http://localhost:8080/users/' + localStorage.getItem('userId'));
  }

  deleteBoat(id: number){
    console.log("Blabla");
    return this._http.get<any>('http://localhost:8080/boats/delete/' + id)
     .pipe(
       tap(data => console.log("data: ", data))
     )
   }
}

