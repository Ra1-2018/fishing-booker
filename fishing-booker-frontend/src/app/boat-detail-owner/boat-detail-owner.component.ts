import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BoatDetailOwnerService } from './boat-detail-owner.service';
import 'ol/ol.css';
import Map from 'ol/Map';
import View from 'ol/View';
import { OSM } from 'ol/source';
import TileLayer from 'ol/layer/Tile';
import { fromLonLat, toLonLat } from 'ol/proj';

@Component({
  selector: 'app-boat-detail-owner',
  templateUrl: './boat-detail-owner.component.html',
  styleUrls: ['./boat-detail-owner.component.css']
})
export class BoatDetailOwnerComponent implements OnInit {

  public map!: Map
  boat: any;
  images: any[] = [];
  additionalServicesField: any[] = [];
  reservationAdditionalServices: any[] = [];
  price: any;
  reservationPrice: number = 0;
  errorMessage = '';
  services: any[] = [];
  clients: any[] = [];
  id: number = 0;
  selectedFile: any;
  isOwner: boolean = true;
  coord: any[] = []
  public readonly myFormGroup: FormGroup;
  public readonly myFormGroupAction: FormGroup;
  public readonly additionalServiceFormGroup: FormGroup;
  public readonly reservationFormGroup: FormGroup;
  public readonly myUnavailablePeriodFormGroup:FormGroup;

  constructor(private route: ActivatedRoute, 
    private router: Router, private boatDetailOwnerService: BoatDetailOwnerService, 
    private readonly formBuilder: FormBuilder) {
      this.myFormGroup = this.formBuilder.group({
          id: 0,
          startDate: [null, Validators.required],
          endDate: [null, Validators.required],
          serviceId: Number(this.route.snapshot.paramMap.get('id'))
        });
        this.myUnavailablePeriodFormGroup = this.formBuilder.group({
          id: 0,
          startDate: [null, Validators.required],
          endDate: [null, Validators.required],
          serviceId: Number(this.route.snapshot.paramMap.get('id'))
        });
        this.myFormGroupAction = this.formBuilder.group({
          id:0,
          startTime: [null, Validators.required],
          durationInDays: [null, Validators.required],
          maxNumberOfPeople: [null, Validators.required],
          additionalServices: [],
          price: 0,
          service: { id: Number(this.route.snapshot.paramMap.get('id'))} 
        });
        this.additionalServiceFormGroup = this.formBuilder.group({
          id: 0,
          name: ['', Validators.required],
          price: ['', Validators.required],
          serviceId: Number(this.route.snapshot.paramMap.get('id'))
        });
        this.reservationFormGroup = this.formBuilder.group({
          reservationStartDateAndTime: [null, Validators.required],
          durationInDays: [null, Validators.required],
          numberOfPeople: [null, Validators.required],
          client: [null, Validators.required]
        });
     }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    if (this.id) {
      this.getBoat(this.id);
      this.getImages(this.id);
  }
}

addServiceUnavailablePeriod(){
  if (this.myUnavailablePeriodFormGroup.invalid) {
    alert('Invalid input');
    return;
  }

  this.boatDetailOwnerService.addServiceUnavailablePeriod(this.myUnavailablePeriodFormGroup.getRawValue()).subscribe({
    next: (data) => {
    alert("Succesfully created!")
    this.getBoat(this.id)
  },
    error: (err) => {alert("Error has occured, unavailable period was not created!")}
  });
}

  getBoat(id: number): void {
    this.boatDetailOwnerService.getBoat(id).subscribe({
      next:  boat => { this.boat = boat,
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

  getClients() {
    this.boatDetailOwnerService.getClients().subscribe({
      next: clients => this.clients = clients,
      error: err => this.errorMessage = err
    })
  }

  getImages(id: number) {
    this.boatDetailOwnerService.getImages(id).subscribe({
      next: images => this.images = images,     
      error: err => this.errorMessage = err
    })
  }


  public onClickSubmit(id:number): void {
    this.router.navigate(['boat-edit/'+ id]);
  }

  public onClickAddFreePeriod(): void{

    if (this.myFormGroup.invalid) {
      alert('Invalid input');
      return;
  }

    this.boatDetailOwnerService.addFreePeriod(this.myFormGroup.getRawValue()).subscribe({
      next: (data) => {
      alert("Succesfully created!")

    },
      error: (err) => {alert("Error has occured, free period was not created!")}
    });

  }

  public onClickDelete(id: number): void {
    this.boatDetailOwnerService.deleteFreePeriod(id).subscribe({
      next: (data) => {
        this.boatDetailOwnerService.getBoat(this.id).subscribe( {
        next: boat => this.boat = boat,
        error: err => this.errorMessage = err
      })
    },
    error: (err) => {alert("Error has occured, free period was not deleted!")}
    });
  }

  public onClickAddAction(): void {

    if (this.myFormGroupAction.invalid) {
      alert('Invalid input');
      return;
  }

  const action = {
      id: this.myFormGroupAction.get('id')?.value,
      startTime: this.myFormGroupAction.get('startTime')?.value,
      durationInDays: this.myFormGroupAction.get('durationInDays')?.value,
      maxNumberOfPeople: this.myFormGroupAction.get('maxNumberOfPeople')?.value,
      additionalServices: this.additionalServicesField,
      price: this.myFormGroupAction.get('price')?.value,
      service: { id: Number(this.route.snapshot.paramMap.get('id'))} 
  }

    this.boatDetailOwnerService.addAction(action).subscribe({
      next: (data) => {
      alert("Succesfully created!")

    },
      error: (err) => {alert("Error has occured, action was not created!")}
    });
  }


  public addAdditionalService(): void {
    if (this.additionalServiceFormGroup.invalid) {
      alert('Invalid input');
      return;
  }

    this.boatDetailOwnerService.addAdditionalService(this.additionalServiceFormGroup.getRawValue()).subscribe({
      next: (data) => {
      alert("Succesfully created!")

    },
      error: (err) => {alert("Error has occured, additional service was not created!")}
    });
  }

  fieldsChange(values:any, additionalService: any):void {
    console.log(values.currentTarget.checked);
    if(values.currentTarget.checked) {
      this.additionalServicesField.push(additionalService);
    }
    else {
      const index: number = this.additionalServicesField.indexOf(additionalService);
      if (index !== -1) {
          this.additionalServicesField.splice(index, 1);
      }        
    }
    console.log(this.additionalServicesField);
  }

  public makeReservation(): void {

    if (this.reservationFormGroup.invalid) {
      alert('Invalid input');
      return;
    }

    const reservation = {
        reservationStartDateAndTime: this.reservationFormGroup.get('reservationStartDateAndTime')?.value,
        durationInDays: this.reservationFormGroup.get('durationInDays')?.value,
        numberOfPeople: this.reservationFormGroup.get('numberOfPeople')?.value,
        client: this.reservationFormGroup.get('client')?.value,
        additionalServices: this.reservationAdditionalServices,
        service: { id: this.id},
        price: this.reservationPrice 
    }

    console.log(reservation);
    this.boatDetailOwnerService.makeReservation(reservation).subscribe({
      next: (data) => {
      this.getBoat(this.id as number);
      alert("Succesfully created!")

      },
      error: (err) => {alert("Error has occured, reservation was not created!")}
    });
  }

  onDurationChange(): void {   
    this.reservationPrice = this.boat.pricePerDay * this.reservationFormGroup.get('durationInDays')?.value;
    for(let additionalService of this.reservationAdditionalServices) {
      this.reservationPrice += additionalService.price * this.reservationFormGroup.get('durationInDays')?.value;
    }
    console.log(this.reservationPrice);
  }

  fieldsChangeReservation(values:any, additionalService: any):void {
    console.log(values.currentTarget.checked);
    if(values.currentTarget.checked) {
      this.reservationAdditionalServices.push(additionalService);
      this.reservationPrice += additionalService.price * this.reservationFormGroup.get('durationInDays')?.value;
    }
    else {
      const index: number = this.reservationAdditionalServices.indexOf(additionalService);
      if (index !== -1) {
          this.reservationAdditionalServices.splice(index, 1);
          this.reservationPrice -= additionalService.price * this.reservationFormGroup.get('durationInDays')?.value;
      }        
    }
    console.log(this.reservationAdditionalServices);
  }

  processFile(imageInput: any) {
    const file: File = imageInput.files[0];
    const reader = new FileReader();

    reader.addEventListener('load', (event: any) => {

      this.selectedFile = new ImageSnippet(event.target.result, file);
    });

    reader.readAsDataURL(file);
  }

  onClickAddImage() {

    this.boatDetailOwnerService.uploadImage(this.selectedFile.file, this.id as number).subscribe({
      next: (data) => 
      { 
        alert("Succesfully uploaded image!"); 
        this.getImages(this.id as number);
      },
      error: (err) => 
      {
        alert("Error has occured, image was not uploaded!")
      }
    });
  }

}

class ImageSnippet {
  constructor(public src: string, public file: File) {}
}
