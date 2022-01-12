import { Component, OnInit } from '@angular/core';
import { OwnedCottagesService } from './owned-cottages.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Sort } from '@angular/material/sort';
import { CottageService } from '../cottages/cottage.service';

@Component({
  selector: 'app-owned-cottages',
  templateUrl: './owned-cottages.component.html',
  styleUrls: ['./owned-cottages.component.css']
})
export class OwnedCottagesComponent implements OnInit {

  query: '' | undefined;

  public readonly myFormGroup: FormGroup;

  cottages: any[] = []
  sortedData: any[] = []

  constructor(private _cottageService: OwnedCottagesService, private readonly formBuilder: FormBuilder) {
    this.myFormGroup = this.formBuilder.group({
      name: ['', Validators.required],
      address: ['', Validators.required],
      description: ['', Validators.required],
      roomsTotalNumber: [],
      behaviorRules: ['', Validators.required],
      ownerId: localStorage.getItem('userId'),
      priceList: ['', Validators.required],
  });
  
   }

  ngOnInit(): void {
    this.getCottages();
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
    if (this.myFormGroup.invalid) {
        // stop here if it's invalid
        alert('Invalid input');
        return;
    }

    this._cottageService.createCottage(this.myFormGroup.getRawValue()).subscribe({
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
        case 'address': return compare(a.address, b.address, isAsc);
        case 'roomsTotalNumber': return compare(a.roomsTotalNumber, b.roomsTotalNumber, isAsc);
        case 'cottageOwner': return compare(a.cottageOwner.email, b.cottageOwner.email, isAsc);
        default: return 0;
      }
    });
  }

}
  
  function compare(a: number | string, b: number | string, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }


