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
    return this._http.put<Observable<any>>('http://localhost:8080/users/update', appUser);
  }

  approveRequest(request: any): Observable<any> {
    return this._http.get<Observable<any>>('http://localhost:8080/users/approve/' + request.id);
  }

  declineRequest(request: any): Observable<any> {
    return this._http.get<Observable<any>>('http://localhost:8080/users/decline/' + request.id)
  }

  getRequests(): Observable<any[]> {
    return this._http.get<any[]>('http://localhost:8080/users/requests')
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}
