import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Sort } from '@angular/material/sort';
import { ClientOrdinaryReservationService } from './client-ordinary-reservation.service';

@Component({
  selector: 'app-client-ordinary-reservation',
  templateUrl: './client-ordinary-reservation.component.html',
  styleUrls: ['./client-ordinary-reservation.component.css']
})
export class ClientOrdinaryReservationComponent implements OnInit {

  services: any[] = [];
  sortedData: any[] = [];
  reservation:any = { 
                      reservationStartDateAndTime: null,
                      durationInDays: 0,
                      numberOfPeople: 0,
                      additionalServices: [],
                      price: 0.0,
                      client: {},
                      service: {},
                      location: ''
                    };
  viewedService: any = {}

  public readonly myFormGroup: FormGroup;

  constructor(private reservationService: ClientOrdinaryReservationService,
              private readonly formBuilder: FormBuilder) {
                this.myFormGroup = this.formBuilder.group({
                  serviceType: [null, Validators.required],
                  startDate: [null, Validators.required],
                  durationInDays: [null, Validators.required],
                  numberOfPeople: [null, Validators.required],
                  address: ['']
                })
               }

  ngOnInit(): void {
  }

  public onClickSubmit(): void {
    if (this.myFormGroup.invalid) {
      // stop here if it's invalid
      alert('Invalid input');
      return;
    }
    this.reservation.reservationStartDateAndTime = this.myFormGroup.controls['startDate'].value;
    this.reservation.durationInDays = this.myFormGroup.controls['durationInDays'].value;
    this.reservation.numberOfPeople = this.myFormGroup.controls['numberOfPeople'].value;
    this.reservation.location = this.myFormGroup.controls['address'].value;
    this.reservationService.getServices(this.myFormGroup.getRawValue()).subscribe(
      services => {
        this.services = services;
        this.sortedData = this.services.slice();
      }
    )
  }

  sortData(sort: Sort) {
    const data = this.services.slice();
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
        case 'maxNumberOfPeople': return compare(a.maxNumberOfPeople, b.maxNumberOfPeople, isAsc);
        default: return 0;
      }
    });
  }

  makeReservation() {
    this.reservationService.makeReservation(this.reservation).subscribe(
      response => {alert("Successful reservation");
                   location.reload();
                  },
      error => alert("An error occured.")
    )
  }

  openReservationModal(service: any) {
    this.viewedService = service;
    this.reservation.service = service
    this.reservation.client.id = parseInt(localStorage.getItem('userId') as string);
    this.reservation.additionalServices = [];
    this.reservation.price = service.pricePerDay * this.reservation.durationInDays;
  }

  fieldsChange(values:any, additionalService: any):void {
    console.log(values.currentTarget.checked);
    if(values.currentTarget.checked) {
      this.reservation.additionalServices.push(additionalService);
      this.reservation.price += additionalService.price * this.reservation.durationInDays;
    }
    else {
      const index: number = this.reservation.additionalServices.indexOf(additionalService);
      if (index !== -1) {
          this.reservation.additionalServices.splice(index, 1);
          this.reservation.price -= additionalService.price * this.reservation.durationInDays;
      }        
    }
    console.log(this.reservation.additionalServices);
  }
}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
