import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class CottageDetailOwnerService {

  constructor(private _http: HttpClient) { }

  getCottage(id: number): Observable<any> {
    return this._http.get<any>(server + 'cottages/' + id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  addFreePeriod(freePeriod: any): Observable<any> {
    return this._http.post<Observable<any>>(server + 'timeRanges', freePeriod);
  }

  deleteFreePeriod(id: number) {
    return this._http.get<any>('http://localhost:8080/timeRanges/delete/' + id).pipe(tap(data => console.log("data: ", data)))
  }

  addAdditionalService(additionalService: any): Observable<any> {
    return this._http.post<Observable<any>>(server + 'additionalServices', additionalService);
  }

  addAction(action: any): Observable<any> {
    return this._http.post<Observable<any>>(server + 'actions', action);
  }

  getClients(): Observable<any> {
    return this._http.get<any>(server + 'users/owner/' + localStorage.getItem('userId'));
  }

  makeReservation(reservation: any) {
    return this._http.post(server + 'reservations', reservation);
  }

  getImages(id: number): Observable<any> {
    return this._http.get<any>(server + 'images/' + id);
  }

  public uploadImage(image: File, id: number): Observable<any> {
    const formData = new FormData();
    formData.append('image', image);
    return this._http.post(server + 'images/' + id, formData);   
  }
}
