import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Sort } from '@angular/material/sort';
import { OwnedAdventuresService } from './owned-adventures.service';
import 'ol/ol.css';
import Map from 'ol/Map';
import View from 'ol/View';
import { OSM } from 'ol/source';
import TileLayer from 'ol/layer/Tile';
import { fromLonLat, toLonLat } from 'ol/proj';

@Component({
  selector: 'app-owned-adventures',
  templateUrl: './owned-adventures.component.html',
  styleUrls: ['./owned-adventures.component.css']
})
export class OwnedAdventuresComponent implements OnInit {

  public readonly myFormGroup: FormGroup;

  public map!: Map
  adventures: any[] = [];
  sortedData: any[] = [];
  query: '' | undefined;

  constructor(private _adventuresService: OwnedAdventuresService, 
              private readonly formBuilder: FormBuilder) { 
                this.myFormGroup = this.formBuilder.group({
                  name: ['', Validators.required],
                  description: ['', Validators.required],
                  behaviorRules: ['', Validators.required],
                  pricePerDay: [0, Validators.required],
                  maxNumberOfPeople: [Validators.required],
                  instructorBiography: ['',Validators.required],
                  fishingGear: ['',Validators.required],
                  cancellation: [Validators.required],
                  ownerId: localStorage.getItem('userId'),
                  city: ['', Validators.required],
                  street: ['', Validators.required],
                  number: ['', Validators.required],
                  zipCode: ['', Validators.required],
                  latitude: ['', Validators.required],
                  longitude: ['', Validators.required],
              });
              }

  ngOnInit(): void {
    this.getOwnedAdventures();
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

  getOwnedAdventures() {
    this._adventuresService.getOwnedAdventures().subscribe(
      adventures => {
        this.adventures = adventures;
        this.sortedData = this.adventures.slice();
      }
    )
  }

  public onClickSubmit(): void {
    if (this.myFormGroup.invalid) {
        // stop here if it's invalid
        alert('Invalid input');
        return;
    }

    this._adventuresService.createAdventure(this.myFormGroup.getRawValue()).subscribe({
      next: (data) => {
      alert("Succesfully created!")
      this.getOwnedAdventures();
    },
      error: (err) => {alert("Error has occured, adventure was not created!")}
    });
  }

  sortData(sort: Sort) {
    const data = this.adventures.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name': return compare(a.name, b.name, isAsc);
        case 'address': return compare(a.address, b.address, isAsc);
        case 'pricePerDay': return compare(a.pricePerDay, b.pricePerDay, isAsc);
        case 'description': return compare(a.description, b.description, isAsc);
        default: return 0;
      }
    });
  }

  reverseGeocode(coords: any) {
    var coord = toLonLat(coords)
    this.myFormGroup.controls['longitude'].setValue(coord[0].toString());
    this.myFormGroup.controls['latitude'].setValue(coord[1].toString());
   fetch('https://nominatim.openstreetmap.org/reverse?lat=' + coord[1] + '&lon=' + coord[0] + '&format=json')
      .then(function(response) {
             return response.json();
         }).then((json) => {
            console.log(json);

            console.log(json.address);
            if (json.address.city) {
            this.myFormGroup.controls['city'].setValue(json.address.city); 
            } else if (json.address.city_district) {
            this.myFormGroup.controls['city'].setValue(json.address.city_district);
            }

            if (json.address.road) {
            this.myFormGroup.controls['street'].setValue(json.address.road);
            }

            if (json.address.house_number) {
            this.myFormGroup.controls['number'].setValue(json.address.house_number);
            }

            if(json.address.postcode){
            this.myFormGroup.controls['zipCode'].setValue(json.address.postcode);
            }
        });
    }
}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
