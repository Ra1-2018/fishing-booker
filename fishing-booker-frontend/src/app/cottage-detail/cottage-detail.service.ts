import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CottageDetailService {

  constructor(private _http: HttpClient) { }

  getCottage(id: number): Observable<any> {
    return this._http.get<any>('http://localhost:8080/cottages/' + id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  subscribe(serviceId: number) {
    return this._http.get('http://localhost:8080/services/subscribe/' + serviceId + '/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  getSubscriptions(): Observable<any[]> {
    return this._http.get<any[]>('http://localhost:8080/services/subscriptions/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  unsubscribe(serviceId: number) {
    return this._http.get('http://localhost:8080/services/unsubscribe/' + serviceId + '/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}
