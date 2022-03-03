import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CottageEditService } from './cottage-edit.service';

@Component({
  selector: 'app-cottage-edit',
  templateUrl: './cottage-edit.component.html',
  styleUrls: ['./cottage-edit.component.css']
})
export class CottageEditComponent implements OnInit {

  cottage: any
  errorMessage = '';
  public readonly myFormGroup: FormGroup;

  constructor(private route: ActivatedRoute, 
    private router: Router, 
    private cottageEditService: CottageEditService,
    private readonly formBuilder: FormBuilder) {
        this.myFormGroup = this.formBuilder.group({
        id: [],
        name: [],
        address: [],
        description: [],
        roomsTotalNumber: [],
        behaviorRules: [],
        cottageOwner: []
    }); 
  }

  ngOnInit(): void {
    this.retrieveData();
  }

  private retrieveData(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.cottageEditService.getCottage(id)
        .subscribe((res: any) => {
            this.myFormGroup.patchValue(res);
        });
  }

  public onClickSubmit(): void {
    if (this.myFormGroup.invalid) {
      alert('Invalid input');
      return;
    }
    this.cottageEditService.updateCottage(this.myFormGroup.getRawValue()).subscribe({
      next: (data) => {alert("Succesfully updated!") 
      this.back()},
      error: (err) => {alert("An unexpected error!")}
    });
  }

  back(): void {
    this.router.navigate(['cottage-detail-owner/'+ this.route.snapshot.paramMap.get('id')]);
  }
}



