import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CottageEditService } from './cottage-edit.service';
import 'ol/ol.css';
import Map from 'ol/Map';
import View from 'ol/View';
import { OSM } from 'ol/source';
import TileLayer from 'ol/layer/Tile';
import { fromLonLat, toLonLat } from 'ol/proj';
import { createInjectorType } from '@angular/compiler/src/render3/r3_injector_compiler';
import { add } from 'ol/coordinate';
import { Room } from '../model/room';

@Component({
  selector: 'app-cottage-edit',
  templateUrl: './cottage-edit.component.html',
  styleUrls: ['./cottage-edit.component.css']
})
export class CottageEditComponent implements OnInit {

  public map!: Map
  errorMessage = '';
  coord: any[] = []

  rooms: Array<Room> = [];

  cottage: any = {
    name: '',
    description: '',
    behaviorRules: '',
    ownerId: localStorage.getItem('userId'),
    priceList: '',
    city: '',
    street: '',
    number: '',
    zipCode: '',
    latitude: '',
    longitude: '',
    rooms: this.rooms
  }

  constructor(private route: ActivatedRoute, private router: Router, private cottageEditService: CottageEditService) { }

  ngOnInit(): void {
    this.retrieveData();
  }

  private retrieveData(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.cottageEditService.getCottage(id).subscribe((res: any) => {
      this.cottage = res;
      this.coord = fromLonLat([parseFloat(res.longitude), parseFloat(res.latitude)]),
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
      this.map.on('click', (e) => {
      console.log(e.coordinate);
      this.reverseGeocode(e.coordinate);
      });
    });
  }

  public onClickSubmit(): void {
    if (this.cottage.invalid) {
      alert('Invalid input');
      return;
    }
    this.cottageEditService.updateCottage(this.cottage).subscribe({
      next: (data) => {alert("Succesfully updated!") 
      this.back()},
      error: (err) => {alert("An unexpected error!")}
    });
  }

  public addRoom(): void {
    this.cottage.rooms.push(new Room('', 1, null));
  }
  
  public removeRoom(room : Room): void {
    const index: number = this.cottage.rooms.indexOf(room);
    if (index !== -1) {
      this.cottage.rooms.splice(index, 1);
    } 
  }

  reverseGeocode(coords: any) {
    var coord = toLonLat(coords)
    this.cottage.longitude = coord[0].toString();
    this.cottage.latitude = coord[1].toString();
   fetch('https://nominatim.openstreetmap.org/reverse?lat=' + coord[1] + '&lon=' + coord[0] + '&format=json')
      .then(function(response) {
             return response.json();
         }).then((json) => {
            console.log(json);

            console.log(json.address);
            if (json.address.city) {
            this.cottage.city = json.address.city; 
            } else if (json.address.city_district) {
            this.cottage.city = json.address.city_district;
            }

            if (json.address.road) {
            this.cottage.street = json.address.road;
            }

            if (json.address.house_number) {
            this.cottage.number = json.address.house_number;
            }

            if(json.address.postcode){
            this.cottage.zipCode = json.address.postcode;
            }
        });
    }

  back(): void {
    this.router.navigate(['cottage-detail-owner/'+ this.route.snapshot.paramMap.get('id')]);
  }
}



