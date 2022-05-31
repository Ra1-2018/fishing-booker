import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private _http: HttpClient) { }

  getUser(): Observable<any> {
    return this._http.get<Observable<any>>(server + 'users/' + localStorage.getItem('userId'));
  }

  updateUser(appUser: any): Observable<any> {
    return this._http.post<Observable<any>>(server + 'users/update', appUser);
  }

  approveRequest(id: number): Observable<any> {
    return this._http.get<Observable<any>>(server + 'users/approve/' + id);
  }

  declineRequest(id: number): Observable<any> {
    return this._http.get<Observable<any>>(server + 'users/decline/' + id)
  }

  getRequests(): Observable<any[]> {
    return this._http.get<any[]>(server + 'users/requests')
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  submitDeletionRequest(deletionRequest: any) {
    return this._http.post(server + 'deletionRequests', deletionRequest)
    .pipe(
      tap(data => console.log("data: ", data))
    );
  }
}
