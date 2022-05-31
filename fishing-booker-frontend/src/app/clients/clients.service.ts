import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class ClientsService {

  constructor(private _http: HttpClient) { }

  getClients(): Observable<any[]> {
    return this._http.get<any[]>(server + 'users/clients')
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}
