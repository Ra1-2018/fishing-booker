import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  isLoggedIn = false;
  isCottageOwner = false;
  userType = '';

  constructor(private _http: HttpClient) { }

  loginUser(appUser: any): Observable<any> {
    return this._http.post<Observable<any>>('http://localhost:8080/users/login', appUser)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  };

  loginNewAdmin(appUser: any): Observable<any> {
    return this._http.post<Observable<any>>('http://localhost:8080/users/login-new-admin', appUser);
  }
  
}
