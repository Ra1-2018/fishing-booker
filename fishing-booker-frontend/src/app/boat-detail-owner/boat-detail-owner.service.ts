import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BoatDetailOwnerService {

  constructor(private _http: HttpClient) { }

  getBoat(id: number): Observable<any> {
    return this._http.get<any>('http://localhost:8080/boats/' + id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  addFreePeriod(freePeriod: any): Observable<any> {
    return this._http.post<Observable<any>>('http://localhost:8080/timeRanges', freePeriod);
  }

  addAdditionalService(additionalService: any): Observable<any> {
    return this._http.post<Observable<any>>('http://localhost:8080/additionalServices', additionalService);
  }

  addAction(action: any): Observable<any> {
    return this._http.post<Observable<any>>('http://localhost:8080/actions', action);
  }

  makeReservation(reservation: any) {
    return this._http.post('http://localhost:8080/reservations', reservation);
  }

  getClients(): Observable<any> {
    return this._http.get<any>('http://localhost:8080/users/owner/' + localStorage.getItem('userId'));
  }

  getImages(id: number): Observable<any> {
    return this._http.get<any>('http://localhost:8080/images/' + id);
  }

  public uploadImage(image: File, id: number): Observable<any> {
    const formData = new FormData();
    formData.append('image', image);
    return this._http.post('http://localhost:8080/images/' + id, formData);   
  }
}
