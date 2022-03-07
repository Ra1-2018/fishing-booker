import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterAdminServiceService {

  constructor(private _http: HttpClient) { }

  registerAdmin(appUser: any): Observable<any> {
    return this._http.post<Observable<any>>('http://localhost:8080/users/admin', appUser);
  }
}
