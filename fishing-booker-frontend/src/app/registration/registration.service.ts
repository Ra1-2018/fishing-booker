import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private _http: HttpClient) { }

  registerUser(appUser: any): Observable<any> {
    return this._http.post<Observable<any>>(server + 'users', appUser);
  }
}
