import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class BoatDetailOwnerService {

  constructor(private _http: HttpClient) { }

  getBoat(id: number): Observable<any> {
    return this._http.get<any>(server + 'boats/' + id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  addFreePeriod(freePeriod: any): Observable<any> {
    return this._http.post<Observable<any>>(server + 'timeRanges', freePeriod);
  }

  deleteFreePeriod(id: number) {
    return this._http.get<any>(server + 'timeRanges/delete/' + id).pipe(tap(data => console.log("data: ", data)))
  }

  addAdditionalService(additionalService: any): Observable<any> {
    return this._http.post<Observable<any>>(server + 'additionalServices', additionalService);
  }

  addAction(action: any): Observable<any> {
    return this._http.post<Observable<any>>(server + 'actions', action);
  }

  makeReservation(reservation: any) {
    return this._http.post(server + 'reservations', reservation);
  }

  getClients(): Observable<any> {
    return this._http.get<any>(server + 'users/owner/' + localStorage.getItem('userId'));
  }

  getImages(id: number): Observable<any> {
    return this._http.get<any>(server + 'images/' + id);
  }

  public uploadImage(image: File, id: number): Observable<any> {
    const formData = new FormData();
    formData.append('image', image);
    return this._http.post(server + 'images/' + id, formData);   
  }

  addServiceUnavailablePeriod(period:any): Observable<any> {
    return this._http.post<Observable<any>>(server + 'timeRanges/unavailablePeriod/service', period)
  }
}
