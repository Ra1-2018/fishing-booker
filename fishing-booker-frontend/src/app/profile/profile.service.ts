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

  approveReviewRequest(id: number): Observable<any> {
    return this._http.get<Observable<any>>(server + 'users/approveReview/' + id);
  }

  declineReviewRequest(id: number): Observable<any> {
    return this._http.get<Observable<any>>(server + 'users/declineReview/' + id);
  }

  getRequests(): Observable<any[]> {
    return this._http.get<any[]>(server + 'users/requests')
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  getReviews(): Observable<any[]> {
    return this._http.get<any[]>(server + 'users/reviews')
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

  submitChangePassword(changePassword: any) {
    return this._http.post(server + 'users/changePassword', changePassword)
    .pipe(
      tap(data => console.log("data: ", data))
    );
  }

  submitRegistrationRequest(registrationRequest:any) {
    return this._http.post<Observable<any>>(server + 'users/decline', registrationRequest)
  }

}
