import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Sort } from '@angular/material/sort';
import { ClientAdventuresReservationsService } from './client-adventures-reservations.service';

@Component({
  selector: 'app-client-adventure-reservations',
  templateUrl: './client-adventure-reservations.component.html',
  styleUrls: ['./client-adventure-reservations.component.css']
})
export class ClientAdventureReservationsComponent implements OnInit {

  public readonly myFormGroup: FormGroup;
  reservations: any[] = []
  sortedData: any[] = []
  service: any = {}

  constructor(private _reservationService: ClientAdventuresReservationsService,
              private readonly formBuilder: FormBuilder) {
                this.myFormGroup = this.formBuilder.group({
                  grade: ['', Validators.required],
                  revision: ['']
                });
               }

  ngOnInit(): void {
    this.getReservations();
  }

  getReservations() {
    this._reservationService.getReservations().subscribe(
      reservations => {
        this.reservations = reservations;
        this.sortedData = this.reservations.slice();
      }
    )
  }

  sortData(sort: Sort) {
    const data = this.reservations.slice();
    if (!sort.active || sort.direction === '') {
      this.sortedData = data;
      return;
    }

    this.sortedData = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name': return compare(a.service.name, b.service.name, isAsc);
        case 'price': return compare(a.price, b.price, isAsc);
        case 'reservationStartDateAndTime': return compare(a.reservationStartDateAndTime, b.reservationStartDateAndTime, isAsc);
        case 'durationInDays': return compare(a.durationInDays, b.durationInDays, isAsc);
        default: return 0;
      }
    });
  }

  selectService(service: any) {
    this.service = service;
  }

  public onClickSubmit(): void {
    if (this.myFormGroup.invalid) {
      // stop here if it's invalid
      alert('Invalid input');
      return;
    }
    const review = {
      grade: this.myFormGroup.get('grade')?.value,
      revision: this.myFormGroup.get('revision')?.value,
      client: {id: parseInt(localStorage.getItem('userId') as string)},
      service: this.service
    }
    this._reservationService.postReview(review).subscribe({
      next: () => {
        alert("Review submitted");
        document.getElementById("closeButton")?.click();
      },
      error: () => "An error ocurred"
    });
  }
}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
