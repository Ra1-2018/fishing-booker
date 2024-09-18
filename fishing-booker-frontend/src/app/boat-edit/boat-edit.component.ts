import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BoatEditService } from './boat-edit.service';
import 'ol/ol.css';
import Map from 'ol/Map';
import View from 'ol/View';
import { OSM } from 'ol/source';
import TileLayer from 'ol/layer/Tile';
import { fromLonLat, toLonLat } from 'ol/proj';
import { createInjectorType } from '@angular/compiler/src/render3/r3_injector_compiler';
import { add } from 'ol/coordinate';

@Component({
  selector: 'app-boat-edit',
  templateUrl: './boat-edit.component.html',
  styleUrls: ['./boat-edit.component.css']
})
export class BoatEditComponent implements OnInit {

  public map!: Map
  boat: any
  errorMessage = '';
  coord: any[] = []
  public readonly myFormGroup: FormGroup;

  constructor(private route: ActivatedRoute, 
    private router: Router, 
    private boatEditService: BoatEditService,
    private readonly formBuilder: FormBuilder) {
        this.myFormGroup = this.formBuilder.group({
        id: [],
        name: [],
        type: [],
        length: [],
        numberOfEngines: [],
        enginePower: [],
        maximumVelocity: [],
        navigationEquipment: [],
        city: [],
        street: [],
        number: [],
        zipCode: [],
        latitude: [],
        longitude: [],
        description: [],
        capacity: [],
        behaviorRules: [],
        fishingEquipment: [],
        priceList: [],
        cancellationTerms: [],
        maxNumberOfPeople: [],
        pricePerDay: [],
        boatOwner: []
    }); 
  }


  ngOnInit(): void {
    this.retrieveData();
  }

  private retrieveData(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.boatEditService.getBoat(id) .subscribe((res: any) => { 
      this.myFormGroup.patchValue(res); 
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
    if (this.myFormGroup.invalid) {
      alert('Invalid input');
      return;
    }
    this.boatEditService.updateBoat(this.myFormGroup.getRawValue()).subscribe({
      next: (data) => {alert("Succesfully updated!")
      this.back()},
      error: (err) => {alert("An unexpected error!")}
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

    back(): void {
      this.router.navigate(['boat-detail-owner/'+ this.route.snapshot.paramMap.get('id')]);
    }
}



