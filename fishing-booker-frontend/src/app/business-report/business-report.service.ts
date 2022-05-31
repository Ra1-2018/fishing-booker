import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class BusinessReportService {

  constructor(private http: HttpClient) { }

  getBusinessReport(id: number): Observable<any> {
    return this.http.get<any>(server + 'businessReports/' + id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}
