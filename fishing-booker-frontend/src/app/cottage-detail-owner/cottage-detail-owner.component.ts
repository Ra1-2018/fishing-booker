import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CottageDetailOwnerService } from './cottage-detail-owner.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-cottage-detail-owner',
  templateUrl: './cottage-detail-owner.component.html',
  styleUrls: ['./cottage-detail-owner.component.css']
})
export class CottageDetailOwnerComponent implements OnInit {
  cottage: any
  freePeriods: any[] = []
  errorMessage = '';
  public readonly myFormGroup: FormGroup;
  public readonly myFormGroupAction: FormGroup;
  

  constructor(private route: ActivatedRoute, 
    private router: Router, 
    private cottageDetailOwnerService: CottageDetailOwnerService, private readonly formBuilder: FormBuilder) { 
      this.myFormGroup = this.formBuilder.group({
        id: 0,
        startDate: [null, Validators.required],
        endDate: [null, Validators.required],
        serviceId: Number(this.route.snapshot.paramMap.get('id'))
    })
      this.myFormGroupAction = this.formBuilder.group({
        id:0,
        startTime: [null, Validators.required],
        durationInDays: [null, Validators.required],
        maxNumberOfPeople: [null, Validators.required]
      })
    }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.getCottage(id);
    }
  }

  getCottage(id: number): void {
    this.cottageDetailOwnerService.getCottage(id).subscribe({
      next: cottage => { 
        this.cottage = cottage;
        this.freePeriods = cottage.freePeriods; 
      },
      error: err => this.errorMessage = err
    })
  }

  public onClickSubmit(id:number): void {
    this.router.navigate(['cottage-edit/'+ id]);
  }

  public onClickAddFreePeriod(): void{

    if (this.myFormGroup.invalid) {
      alert('Invalid input');
      return;
  }

    this.cottageDetailOwnerService.addFreePeriod(this.myFormGroup.getRawValue()).subscribe({
      next: (data) => {
      alert("Succesfully created!")

    },
      error: (err) => {alert("Error has occured, free period was not created!")}
    });

  }

}
