import { Component, OnInit } from '@angular/core';
import { OwnedBoatsService } from './owned-boats.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Sort } from '@angular/material/sort';

@Component({
  selector: 'app-owned-boats',
  templateUrl: './owned-boats.component.html',
  styleUrls: ['./owned-boats.component.css']
})
export class OwnedBoatsComponent implements OnInit {

  query: '' | undefined;

  public readonly myFormGroup: FormGroup;

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
      address: ['', Validators.required],
      maxNumberOfPeople: [null, Validators.required],
      fishingEquipment: ['', Validators.required],
      cancellationTerms: ['', Validators.required],
      ownerId: localStorage.getItem('userId'),
  });
  }

  ngOnInit(): void {
    this.getBoats();
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
}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}

