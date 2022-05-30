import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BusinessReportService {

  constructor(private http: HttpClient) { }

  getBusinessReport(id: number): Observable<any> {
    return this.http.get<any>('http://localhost:8080/businessReports/' + id)
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}
