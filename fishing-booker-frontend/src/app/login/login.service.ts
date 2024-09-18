import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  isLoggedIn = false;
  userType = '';
  isSanctioned = false;

  constructor(private _http: HttpClient) { }

  loginUser(appUser: any): Observable<any> {
    return this._http.post<Observable<any>>(server + 'users/login', appUser)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  };

  loginNewAdmin(appUser: any): Observable<any> {
    return this._http.post<Observable<any>>(server + 'users/login-new-admin', appUser);
  }
  
}
