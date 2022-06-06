import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class LoyaltyProgramService {

  constructor(private _http:HttpClient) { }

  public getLoyaltyProgram() : Observable<any> {
    return this._http.get<any>(server + 'loyalty');
  }

  updateLoyaltyProgram(program: any) {
    return this._http.post(server + 'loyalty', program)
  }
}
