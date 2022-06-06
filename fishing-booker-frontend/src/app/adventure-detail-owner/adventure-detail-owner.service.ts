import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class AdventureDetailOwnerService {

  constructor(private _http: HttpClient) { }

  getAdventure(id: number): Observable<any> {
    return this._http.get<any>(server + 'adventures/' + id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  getClients(): Observable<any> {
    return this._http.get<any>('http://localhost:8080/users/owner/' + localStorage.getItem('userId'));
  }

  makeReservation(reservation: any) {
    return this._http.post('http://localhost:8080/reservations', reservation);
  }

  deleteAdventure(id: number): Observable<any> {
    return this._http.delete<Observable<any>>(server + 'adventures/' + id)
  }

  addFreePeriod(freePeriod: any): Observable<any> {
    return this._http.post<Observable<any>>(server + '/timeRanges', freePeriod);
  }

  addAdditionalService(additionalService: any): Observable<any> {
    return this._http.post<Observable<any>>(server + 'additionalServices', additionalService);
  }

  addAction(action: any): Observable<any> {
    return this._http.post<Observable<any>>(server + 'actions', action);
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
    return this._http.post<Observable<any>>('http://localhost:8080/timeRanges/unavailablePeriod/service', period)
  }
}
