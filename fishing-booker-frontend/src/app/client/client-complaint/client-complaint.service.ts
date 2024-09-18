import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from 'src/app/app-global';

@Injectable({
  providedIn: 'root'
})
export class ClientComplaintService {

  constructor(private _http: HttpClient) { }

  getServices(): Observable<any[]> {
    return this._http.get<any[]>(server + 'services/eligible/' + localStorage.getItem('userId'))
    .pipe(
      tap(data => console.log("data: ", data))
    );
  }

  submitComplaint(complaint: any) {
    return this._http.post(server + 'complaints', complaint)
    .pipe(
      tap(data => console.log("data: ", data))
    );
  }
}
