import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class CottageDetailService {

  constructor(private _http: HttpClient) { }

  getCottage(id: number): Observable<any> {
    return this._http.get<any>(server + 'cottages/' + id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  getImages(id: number): Observable<any> {
    return this._http.get<any>(server + 'images/' + id);
  }

  delete(id: number): Observable<any> {
    return this._http.delete<Observable<any>>(server + 'cottages/' + id)
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
