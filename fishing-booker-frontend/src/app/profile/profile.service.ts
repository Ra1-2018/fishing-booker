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

  approveReviewRequest(id: number): Observable<any> {
    return this._http.get<Observable<any>>('http://localhost:8080/users/approveReview/' + id);
  }

  declineReviewRequest(id: number): Observable<any> {
    return this._http.get<Observable<any>>('http://localhost:8080/users/declineReview/' + id);
  }

  approveDeletionRequest(id: number): Observable<any> {
    return this._http.get<Observable<any>>('http://localhost:8080/users/approveDeletion/' + id);
  }

  approveComplaintRequest(id: number): Observable<any> {
    return this._http.get<Observable<any>>('http://localhost:8080/users/approveComplaint/' + id);
  }

  getRequests(): Observable<any[]> {
    return this._http.get<any[]>('http://localhost:8080/users/requests')
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  getReviews(): Observable<any[]> {
    return this._http.get<any[]>('http://localhost:8080/users/reviews')
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  getDeletionRequests(): Observable<any[]> {
    return this._http.get<any[]>('http://localhost:8080/users/deletionRequests')
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  getComplaintRequests(): Observable<any[]> {
    return this._http.get<any[]>('http://localhost:8080/users/complaints')
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  submitDeletionRequest(deletionRequest: any) {
    return this._http.post('http://localhost:8080/deletionRequests', deletionRequest)
    .pipe(
      tap(data => console.log("data: ", data))
    );
  }

  submitChangePassword(changePassword: any) {
    return this._http.post('http://localhost:8080/users/changePassword', changePassword)
    .pipe(
      tap(data => console.log("data: ", data))
    );
  }

  submitRegistrationRequest(registrationRequest:any) {
    return this._http.post<Observable<any>>('http://localhost:8080/users/decline', registrationRequest)
  }

  submitDeletionResponse(deletionResponse:any) {
    return this._http.post<Observable<any>>('http://localhost:8080/users/declineDeletion', deletionResponse)
  }

  submitComplaintResponse(complaintResponse:any) {
    return this._http.post<Observable<any>>('http://localhost:8080/users/declineComplaint', complaintResponse)
  }

}
