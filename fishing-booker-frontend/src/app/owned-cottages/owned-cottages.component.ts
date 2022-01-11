import { Component, OnInit } from '@angular/core';
import { OwnedCottagesService } from './owned-cottages.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CottageService } from '../cottages/cottage.service';

@Component({
  selector: 'app-owned-cottages',
  templateUrl: './owned-cottages.component.html',
  styleUrls: ['./owned-cottages.component.css']
})
export class OwnedCottagesComponent implements OnInit {

  public readonly myFormGroup: FormGroup;

  cottages: any[] = []

  
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

}
