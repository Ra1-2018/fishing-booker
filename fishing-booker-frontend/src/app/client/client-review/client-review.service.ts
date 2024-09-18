import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from 'src/app/app-global';

@Injectable({
  providedIn: 'root'
})
export class ClientReviewService {

  constructor(private _http: HttpClient) { }

  postReview(review: any): Observable<any> {
    return this._http.post<Observable<any>>(server + 'reviews', review)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }

  getServices(): Observable<any[]> {
    return this._http.get<any[]>(server + 'services/eligible/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    );
  }
}
