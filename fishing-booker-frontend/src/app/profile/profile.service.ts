import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private _http: HttpClient) { }

  getUser(): Observable<any> {
    return this._http.get<Observable<any>>('http://localhost:8080/users/' + localStorage.getItem('userId'));
  }

  updateUser(appUser: any): Observable<any> {
    return this._http.post<Observable<any>>('http://localhost:8080/users/update', appUser);
  }

  approveRequest(id: number): Observable<any> {
    return this._http.get<Observable<any>>('http://localhost:8080/users/approve/' + id);
  }

  declineRequest(id: number): Observable<any> {
    return this._http.get<Observable<any>>('http://localhost:8080/users/decline/' + id)
  }

  getRequests(): Observable<any[]> {
    return this._http.get<any[]>('http://localhost:8080/users/requests')
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}
