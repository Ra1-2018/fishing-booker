import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class InstructorsService {

  constructor(private _http: HttpClient) { }

  getInstructors(): Observable<any[]> {
    return this._http.get<any[]>(server + 'users/instructors')
    .pipe(
      tap(data => console.log("data: ", data))
    )
  }
}
