import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Sort } from '@angular/material/sort';
import { OwnedAdventuresService } from './owned-adventures.service';

@Component({
  selector: 'app-owned-adventures',
  templateUrl: './owned-adventures.component.html',
  styleUrls: ['./owned-adventures.component.css']
})
export class OwnedAdventuresComponent implements OnInit {

  public readonly myFormGroup: FormGroup;
  adventures: any[] = [];
  sortedData: any[] = [];
  query: '' | undefined;

  constructor(private _adventuresService: OwnedAdventuresService, 
              private readonly formBuilder: FormBuilder) { 
                this.myFormGroup = this.formBuilder.group({
                  name: ['', Validators.required],
                  address: ['', Validators.required],
                  description: ['', Validators.required],
                  behaviorRules: ['', Validators.required],
                  pricePerDay: ['', Validators.required],
                  maxNumberOfPeople: [Validators.required],
                  instructorBiography: ['',Validators.required],
                  fishingGear: ['',Validators.required],
                  cancellation: [Validators.required],
                  ownerId: localStorage.getItem('userId')
              });
              }

  ngOnInit(): void {
    this.getOwnedAdventures();
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
}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
