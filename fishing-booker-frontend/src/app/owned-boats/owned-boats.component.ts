import { Component, OnInit } from '@angular/core';
import 'ol/ol.css';
import Map from 'ol/Map';
import View from 'ol/View';
import { OSM } from 'ol/source';
import TileLayer from 'ol/layer/Tile';
import { OwnedBoatsService } from './owned-boats.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Sort } from '@angular/material/sort';
import { toLonLat } from 'ol/proj';
import { createInjectorType } from '@angular/compiler/src/render3/r3_injector_compiler';
import { add } from 'ol/coordinate';

@Component({
  selector: 'app-owned-boats',
  templateUrl: './owned-boats.component.html',
  styleUrls: ['./owned-boats.component.css']
})
export class OwnedBoatsComponent implements OnInit {

  query: '' | undefined;

  public readonly myFormGroup: FormGroup;

  public map!: Map
  boats: any[] = []
  sortedData: any[] = []

  constructor(private _boatService: OwnedBoatsService, private readonly formBuilder: FormBuilder) { 
    this.myFormGroup = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      length: ['', Validators.required],
      numberOfEngines: [null, Validators.required],
      enginePower: ['', Validators.required],
      maximumVelocity: ['', Validators.required],
      navigationEquipment: ['', Validators.required],
      behaviorRules: ['', Validators.required],
      pricePerDay: ['', Validators.required],
      city: ['', Validators.required],
      street: ['', Validators.required],
		  number: ['', Validators.required],
			zipCode: ['', Validators.required],
			latitude: ['', Validators.required],
			longitude: ['', Validators.required],
      maxNumberOfPeople: [null, Validators.required],
      fishingEquipment: ['', Validators.required],
      cancellationTerms: ['', Validators.required],
      ownerId: localStorage.getItem('userId'),
  });
  }

  ngOnInit(): void {
    this.getBoats();
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

  getBoats() {
    this._boatService.getBoats().subscribe(
      boats => {
        this.boats = boats;
        this.sortedData = this.boats.slice();
      }
    )
  }
  
  public onClickDelete(id: number): void {
    this._boatService.deleteBoat(id).subscribe( response => 
      {
      this.getBoats();
      }
    );
  }

  public onClickSubmit(): void {
    if (this.myFormGroup.invalid) {
        alert('Invalid input');
        return;
    }

    this._boatService.createBoat(this.myFormGroup.getRawValue()).subscribe({
      next: (data) => {
      alert("Succesfully created!")
      this.getBoats();

    },
      error: (err) => {alert("Error has occured, boat was not created!")}
    });
  }

  sortData(sort: Sort) {
    const data = this.boats.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name': return compare(a.name, b.name, isAsc);
        case 'address': return compare(a.address, b.address, isAsc);
        case 'boatOwner': return compare(a.boatOwner.email, b.boatOwner.email, isAsc);
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

class ImageSnippet {
  constructor(public src: string, public file: File) {}
}

