import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class AdventureDetailService {

  constructor(private _http: HttpClient) { }

  getAdventure(id: number): Observable<any> {
    return this._http.get<any>(server + 'adventures/' + id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  subscribe(serviceId: number) {
    return this._http.get(server + 'services/subscribe/' + serviceId + '/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  getSubscriptions(): Observable<any[]> {
    return this._http.get<any[]>(server + 'services/subscriptions/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  unsubscribe(serviceId: number) {
    return this._http.get(server + 'services/unsubscribe/' + serviceId + '/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}
