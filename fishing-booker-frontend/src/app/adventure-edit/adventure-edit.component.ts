import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AdventureEditService } from './adventure-edit.service';

@Component({
  selector: 'app-adventure-edit',
  templateUrl: './adventure-edit.component.html',
  styleUrls: ['./adventure-edit.component.css']
})
export class AdventureEditComponent implements OnInit {

  adventure: any
  errorMessage = '';
  public readonly myFormGroup: FormGroup;

  constructor(private route: ActivatedRoute, 
    private router: Router, 
    private adventureEditService: AdventureEditService,
    private readonly formBuilder: FormBuilder) { 
      this.myFormGroup = this.formBuilder.group({
        id: [],
        name: [],
        address: [],
        description: [],
        behaviorRules: [],
        pricePerDay: [],
        maxNumberOfPeople: [],
        instructorBiography: [],
        fishingGear: [],
        cancellation: []
      }); 
    }

  ngOnInit(): void {
    this.retrieveData();
  }

  private retrieveData(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.adventureEditService.getAdventure(id)
        .subscribe((res: any) => {
            this.myFormGroup.patchValue(res);
        });
  }

  public onClickSubmit(): void {
    if (this.myFormGroup.invalid) {
      alert('Invalid input');
      return;
    }
    this.adventureEditService.updateAdventure(this.myFormGroup.getRawValue()).subscribe({
      next: (data) => {alert("Succesfully updated!")
      this.back()},
      error: (err) => {alert("An unexpected error!")}
    });
  }

  back(): void {
    this.router.navigate(['adventure-detail-owner/'+ this.route.snapshot.paramMap.get('id')]);
  }

}
