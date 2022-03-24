import { Component, OnInit } from '@angular/core';
import { ReservationOwnerService } from './reservation-owner.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-reservation-owner',
  templateUrl: './reservation-owner.component.html',
  styleUrls: ['./reservation-owner.component.css']
})
export class ReservationOwnerComponent implements OnInit {

  services: any[] = [];
  clients: any[] = [];
  viewedService: any = {
    additionalServices: []
  };
  additionalServicesField: any[] = []
  

  public readonly myFormGroup: FormGroup;

  constructor(private reservationService: ReservationOwnerService, private readonly formBuilder: FormBuilder) {
    this.myFormGroup = this.formBuilder.group({
      reservationStartDateAndTime: [null, Validators.required],
      durationInDays: 0,
      numberOfPeople: 0,
      additionalServices: [],
      price: 0.0,
      client: {},
      service: {}
    });
   }

  ngOnInit(): void {
    this.getServices();
  }

  getServices() {
    this.reservationService.getServices().subscribe(
      services => this.services = services
    );
  }

  /*getClients() {
    this.reservationService.getClients().subscribe(
      clients => this.clients = clients
    );
  }*/

  makeReservation(){

    if (this.myFormGroup.invalid) {
      alert('Invalid input');
      return;
    }

    this.reservationService.makeReservation(this.myFormGroup.getRawValue()).subscribe({
      next: (data) => {
      alert("Succesfully created!")
    },
      error: (err) => {alert("Error has occured, cottage was not created!")}
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
}
