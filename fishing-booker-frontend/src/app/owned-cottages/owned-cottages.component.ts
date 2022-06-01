import { Component, OnInit } from '@angular/core';
import 'ol/ol.css';
import Map from 'ol/Map';
import View from 'ol/View';
import { OSM } from 'ol/source';
import TileLayer from 'ol/layer/Tile';
import { OwnedCottagesService } from './owned-cottages.service';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Sort } from '@angular/material/sort';
import { CottageService } from '../cottages/cottage.service';
import { toLonLat } from 'ol/proj';
import { createInjectorType } from '@angular/compiler/src/render3/r3_injector_compiler';
import { add } from 'ol/coordinate';
import { Room } from '../model/room';

@Component({
  selector: 'app-owned-cottages',
  templateUrl: './owned-cottages.component.html',
  styleUrls: ['./owned-cottages.component.css']
})
export class OwnedCottagesComponent implements OnInit {

  query: '' | undefined;

  public map!: Map
  cottages: any[] = [];
  sortedData: any[] = [];
  selectedFile: any;

  rooms: Array<Room> = [];

  cottage: any = {
    name: '',
    description: '',
    behaviorRules: '',
    ownerId: localStorage.getItem('userId'),
    pricePerDay: 0,
    maxNumberOfPeople: 0,
    city: '',
    street: '',
    number: '',
    zipCode: '',
    latitude: '',
    longitude: '',
    rooms: this.rooms
  }

  constructor(private _cottageService: OwnedCottagesService) {
  }

  ngOnInit(): void {
    this.getCottages();
    this.map = new Map({
      layers: [
        new TileLayer({
          source: new OSM(),
        }),
      ],
      target: 'map',
      view: new View({ 
        center: [2277587.8314708155, 5590969.855426764],
        zoom:10, maxZoom: 18, 
      }),
    });

    this.map.on('click', (e) => {
      console.log(e.coordinate);
      this.reverseGeocode(e.coordinate);
    });
  }

  public addRoom(): void {
    this.rooms.push(new Room('', 1, null));
  }
  
  public removeRoom(room : Room): void {
    const index: number = this.rooms.indexOf(room);
    if (index !== -1) {
      this.rooms.splice(index, 1);
    } 
  }

  getCottages() {
    this._cottageService.getCottages().subscribe(
      cottages => {
        this.cottages = cottages;
        this.sortedData = this.cottages.slice();
      }
    )
  }

  public onClickDelete(id: number): void {
    this._cottageService.deleteCottage(id).subscribe( response => 
      {
      this.getCottages();
      }
    );
  }

  public onClickSubmit(): void {
    if (this.cottage.invalid) {
        alert('Invalid input');
        return;
    }

    this.cottage.rooms = this.rooms;
    this._cottageService.createCottage(this.cottage).subscribe({
      next: (data) => {
      alert("Succesfully created!")
      this.getCottages();

    },
      error: (err) => {alert("Error has occured, cottage was not created!")}
    });
  }

  sortData(sort: Sort) {
    const data = this.cottages.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name': return compare(a.name, b.name, isAsc);
        //case 'address': return compare(a.address, b.address, isAsc);
        //case 'roomsTotalNumber': return compare(a.roomsTotalNumber, b.roomsTotalNumber, isAsc);
        case 'description': return compare(a.description, b.description, isAsc);
        default: return 0;
      }
    });
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
}
  
  function compare(a: number | string, b: number | string, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

  class ImageSnippet {
    constructor(public src: string, public file: File) {}
  }





