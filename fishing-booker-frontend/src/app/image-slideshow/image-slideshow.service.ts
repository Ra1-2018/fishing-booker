import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { server } from '../app-global';

@Injectable({
  providedIn: 'root'
})
export class ImageSlideshowService {

  constructor(private http: HttpClient) { }

  deleteImage(id: number): Observable<any> {
    return this.http.get<any>(server + 'images/delete/' + id);
  }

  getImages(id: number): Observable<any> {
    return this.http.get<any>(server + 'images/' + id);
  }
}
