import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AdventureDetailOwnerService } from './adventure-detail-owner.service';

@Component({
  selector: 'app-adventure-detail-owner',
  templateUrl: './adventure-detail-owner.component.html',
  styleUrls: ['./adventure-detail-owner.component.css']
})
export class AdventureDetailOwnerComponent implements OnInit {

  adventure: any;
  additionalServicesField: any[] = [];
  price: any;
  errorMessage = '';
  public readonly myFormGroup: FormGroup;
  public readonly myFormGroupAction: FormGroup;
  public readonly additionalServiceFormGroup: FormGroup;

  constructor(private route: ActivatedRoute, 
    private router: Router, 
    private adventureDetailOwnerService: AdventureDetailOwnerService, 
    private readonly formBuilder: FormBuilder) { 
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
    this.getAdventure(id);
    console.log(id);
    
  }

  getAdventure(id: number): void {
    this.adventureDetailOwnerService.getAdventure(id).subscribe(
      adventure => this.adventure = adventure
      )
  }

  public edit(id:number): void {
    this.router.navigate(['adventure-edit/'+ id]);
  }

  public delete(id:number): void {
    this.adventureDetailOwnerService.deleteAdventure(id).subscribe(
      response => {this.router.navigate(['owned-adventures']); }
      );
    return;
  }

  public onClickAddFreePeriod(): void{

    if (this.myFormGroup.invalid) {
      alert('Invalid input');
      return;
  }

    this.adventureDetailOwnerService.addFreePeriod(this.myFormGroup.getRawValue()).subscribe({
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

    this.adventureDetailOwnerService.addAction(action).subscribe({
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

    this.adventureDetailOwnerService.addAdditionalService(this.additionalServiceFormGroup.getRawValue()).subscribe({
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
