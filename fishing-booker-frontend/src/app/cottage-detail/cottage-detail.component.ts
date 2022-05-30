import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { CottageDetailService } from './cottage-detail.service';
import 'ol/ol.css';
import Map from 'ol/Map';
import View from 'ol/View';
import { OSM } from 'ol/source';
import TileLayer from 'ol/layer/Tile';
import { fromLonLat, toLonLat } from 'ol/proj';

@Component({
  selector: 'app-cottage-detail',
  templateUrl: './cottage-detail.component.html',
  styleUrls: ['./cottage-detail.component.css']
})
export class CottageDetailComponent implements OnInit {

  public map!: Map
  cottage: any;
  errorMessage = '';
  id: number = 0;
  subscriptions:any[] = [];
  images: any[] = [];
  coord: any[] = []

  constructor(public readonly loginService: LoginService,
    private route: ActivatedRoute,
    private router: Router,
    private cottageDetailService: CottageDetailService) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    if (this.id) {
      this.getCottage(this.id);
      this.getImages(this.id);
    }
    if(this.loginService.isLoggedIn && this.loginService.userType == 'CLIENT') {
      this.getSubscriptions();
    }
  }

  getCottage(id: number): void {
    this.cottageDetailService.getCottage(id).subscribe({
      next: cottage => { 
        this.cottage = cottage,
        this.coord = fromLonLat([parseFloat(this.cottage.longitude), parseFloat(this.cottage.latitude)]),
        this.map = new Map({
          layers: [
            new TileLayer({
              source: new OSM(),
            }),
          ],
          target: 'map',
          view: new View({ 
            
            center: this.coord,
            zoom:17, maxZoom: 18, 
          }),
        });
      },
      error: err => this.errorMessage = err
    });
    
  }

  getImages(id: number) {
    this.cottageDetailService.getImages(id).subscribe({
      next: images => this.images = images,     
      error: err => this.errorMessage = err
    })
  }

  delete(id:number):void {
    this.cottageDetailService.delete(id).subscribe(
      response => {this.router.navigate(['cottages']); }
      );
    return;
  }

  subscribe(): void {
    this.cottageDetailService.subscribe(this.id as number).subscribe({
      next: () => {
        this.getSubscriptions();
      }
    });
  }

  isSubscribed(): boolean {
    for(let subscription of this.subscriptions) {
      if(subscription.id == this.cottage.id) {
        return true;
      }
    }
    return false;
  }

  getSubscriptions() {
    this.cottageDetailService.getSubscriptions().subscribe(
      subscriptions => {this.subscriptions = subscriptions;}
    )
  }

  unsubscribe() {
    this.cottageDetailService.unsubscribe(this.id as number).subscribe({
      next: () => {
        this.getSubscriptions();
      }
    });
  }
}
