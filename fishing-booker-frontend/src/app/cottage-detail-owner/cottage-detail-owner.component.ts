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
  additionalServicesField: any[] = []
  price: any
  errorMessage = '';
  public readonly myFormGroup: FormGroup;
  public readonly myFormGroupAction: FormGroup;
  public readonly additionalServiceFormGroup: FormGroup;
  

  constructor(private route: ActivatedRoute, 
    private router: Router, 
    private cottageDetailOwnerService: CottageDetailOwnerService, private readonly formBuilder: FormBuilder) { 
      this.myFormGroup = this.formBuilder.group({
        id: 0,
        startDate: [null, Validators.required],
        endDate: [null, Validators.required],
        serviceId: Number(this.route.snapshot.paramMap.get('id'))
      });
      this.myFormGroupAction = this.formBuilder.group({
        id:0,
        startTime: [null, Validators.required],
        durationInDays: [null, Validators.required],
        maxNumberOfPeople: [null, Validators.required],
        additionalServices: [],
        price: 0,
        service: { id: Number(this.route.snapshot.paramMap.get('id'))} 
      });
      this.additionalServiceFormGroup = this.formBuilder.group({
        id: 0,
        name: ['', Validators.required],
        price: ['', Validators.required],
        serviceId: Number(this.route.snapshot.paramMap.get('id'))
      });
    }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.getCottage(id);
    }
  }

  getCottage(id: number): void {
    this.cottageDetailOwnerService.getCottage(id).subscribe({
      next: cottage => this.cottage = cottage,
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

  public onClickAddAction(): void {

    if (this.myFormGroupAction.invalid) {
      alert('Invalid input');
      return;
  }

  const action = {
      id: this.myFormGroupAction.get('id')?.value,
      startTime: this.myFormGroupAction.get('startTime')?.value,
      durationInDays: this.myFormGroupAction.get('durationInDays')?.value,
      maxNumberOfPeople: this.myFormGroupAction.get('maxNumberOfPeople')?.value,
      additionalServices: this.additionalServicesField,
      price: this.myFormGroupAction.get('price')?.value,
      service: { id: Number(this.route.snapshot.paramMap.get('id'))} 
  }

    this.cottageDetailOwnerService.addAction(action).subscribe({
      next: (data) => {
      alert("Succesfully created!")

    },
      error: (err) => {alert("Error has occured, action was not created!")}
    });
  }


  public addAdditionalService(): void {
    if (this.additionalServiceFormGroup.invalid) {
      alert('Invalid input');
      return;
  }

    this.cottageDetailOwnerService.addAdditionalService(this.additionalServiceFormGroup.getRawValue()).subscribe({
      next: (data) => {
      alert("Succesfully created!")

    },
      error: (err) => {alert("Error has occured, additional service was not created!")}
    });
  }

  fieldsChange(values:any, additionalService: any):void {
    console.log(values.currentTarget.checked);
    if(values.currentTarget.checked) {
      this.additionalServicesField.push(additionalService);
    }
    else {
      const index: number = this.additionalServicesField.indexOf(additionalService);
      if (index !== -1) {
          this.additionalServicesField.splice(index, 1);
      }        
    }
    console.log(this.additionalServicesField);
  }
}
