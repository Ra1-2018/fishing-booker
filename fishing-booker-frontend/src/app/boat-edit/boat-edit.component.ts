import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BoatEditService } from './boat-edit.service';

@Component({
  selector: 'app-boat-edit',
  templateUrl: './boat-edit.component.html',
  styleUrls: ['./boat-edit.component.css']
})
export class BoatEditComponent implements OnInit {

  boat: any
  errorMessage = '';
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
        address: [],
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
    this.boatEditService.getBoat(id)
        .subscribe((res: any) => {
            this.myFormGroup.patchValue(res);
        });
  }

  public onClickSubmit(): void {
    if (this.myFormGroup.invalid) {
      alert('Invalid input');
      return;
    }
    this.boatEditService.updateBoat(this.myFormGroup.getRawValue()).subscribe({
      next: (data) => {alert("Succesfully updated!")},
      error: (err) => {alert("An unexpected error!")}
    });
  }
}



