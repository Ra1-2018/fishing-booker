import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginNewAdminService {

  constructor(private _http: HttpClient) { }

  loginNewAdmin(appUser: any): Observable<any> {
    return this._http.post<Observable<any>>('http://localhost:8080/users/login-new-admin', appUser)
  }

  getUser(): Observable<any> {
    return this._http.get<Observable<any>>('http://localhost:8080/users/' + localStorage.getItem('userId'));
  }

}
