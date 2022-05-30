import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { BoatDetailService } from './boat-detail.service';
import 'ol/ol.css';
import Map from 'ol/Map';
import View from 'ol/View';
import { OSM } from 'ol/source';
import TileLayer from 'ol/layer/Tile';
import { fromLonLat, toLonLat } from 'ol/proj';

@Component({
  selector: 'app-boat-detail',
  templateUrl: './boat-detail.component.html',
  styleUrls: ['./boat-detail.component.css']
})
export class BoatDetailComponent implements OnInit {

  public map!: Map
  boat: any;
  errorMessage = '';
  id: number = 0;
  subscriptions:any[] = [];
  images: any[] = [];
  coord: any[] = []

  constructor(private route: ActivatedRoute,
              private router: Router,
              private boatDetailService: BoatDetailService,
              public readonly loginService: LoginService) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    if (this.id) {
      this.getBoat(this.id);
      this.getImages(this.id);
    }
    if(this.loginService.isLoggedIn && this.loginService.userType == 'CLIENT') {
      this.getSubscriptions();
    }
  }

  getBoat(id: number): void {
    this.boatDetailService.getBoat(id).subscribe({
      next: boat => { this.boat = boat, 
        this.coord = fromLonLat([parseFloat(this.boat.longitude), parseFloat(this.boat.latitude)]),
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
    })
  }

  getImages(id: number) {
    this.boatDetailService.getImages(id).subscribe({
      next: images => this.images = images,     
      error: err => this.errorMessage = err
    })
  }

  delete(id:number):void {
    this.boatDetailService.delete(id).subscribe(
      response => {this.router.navigate(['boats']); }
      );
    return;
  }

  subscribe(): void {
    this.boatDetailService.subscribe(this.id as number).subscribe({
      next: () => {
        this.getSubscriptions();
      }
    });
  }

  isSubscribed(): boolean {
    for(let subscription of this.subscriptions) {
      if(subscription.id == this.boat.id) {
        return true;
      }
    }
    return false;
  }

  getSubscriptions() {
    this.boatDetailService.getSubscriptions().subscribe(
      subscriptions => {this.subscriptions = subscriptions;}
    )
  }

  unsubscribe() {
    this.boatDetailService.unsubscribe(this.id as number).subscribe({
      next: () => {
        this.getSubscriptions();
      }
    });
  }
}
