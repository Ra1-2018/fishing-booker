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
  
}

