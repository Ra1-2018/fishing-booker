import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImageSlideshowService {

  constructor(private http: HttpClient) { }

  deleteImage(id: number): Observable<any> {
    return this.http.get<any>('http://localhost:8080/images/delete/' + id);
  }

  getImages(id: number): Observable<any> {
    return this.http.get<any>('http://localhost:8080/images/' + id);
  }
}
