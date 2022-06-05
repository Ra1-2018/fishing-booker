import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoyaltyProgramService {

  constructor(private _http:HttpClient) { }

  public getLoyaltyProgram() : Observable<any> {
    return this._http.get<any>('http://localhost:8080/loyalty');
  }

  updateLoyaltyProgram(program: any) {
    return this._http.post('http://localhost:8080/loyalty', program)
  }
}
