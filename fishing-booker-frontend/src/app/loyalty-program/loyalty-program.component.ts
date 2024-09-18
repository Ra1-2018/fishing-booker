import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { LoyaltyProgramService } from './loyalty-program.service';

@Component({
  selector: 'app-loyalty-program',
  templateUrl: './loyalty-program.component.html',
  styleUrls: ['./loyalty-program.component.css']
})
export class LoyaltyProgramComponent implements OnInit {

  loyaltyProgram:any;
  public readonly myFormGroup: FormGroup;

  constructor(private loyaltyService: LoyaltyProgramService,
              private readonly formBuilder: FormBuilder) { 
                this.myFormGroup = this.formBuilder.group({
                  id: [],
                  pointsForBronze: [],
                  percentForBronze: [],
                  pointsForSilver: [],
                  percentForSilver: [],
                  pointsForGold: [],
                  percentForGold: []
              });
              }

  ngOnInit(): void {
    this.retrieveData();
    this.getLoyaltyProgram();
  }

  private retrieveData(): void {
    this.loyaltyService.getLoyaltyProgram()
        .subscribe((res: any) => {
            this.myFormGroup.patchValue(res);
            this.loyaltyProgram = res;
        });
  }

  getLoyaltyProgram() {
    this.loyaltyService.getLoyaltyProgram().subscribe({
      next: (loyaltyProgram: any) => this.loyaltyProgram = loyaltyProgram
    });
  }

  public onClickSubmit(): void {
    if (this.myFormGroup.invalid) {
      alert('Invalid input');
      return;
    } 
    else if(this.loyaltyProgram.pointsForBronze < 0 || this.loyaltyProgram.pointsForSilver < 0 || this.loyaltyProgram.pointsForGold < 0) {
      alert('Points must be greater than 0.');
      return;
    }
    else if (this.loyaltyProgram.percentForBronze < 0 || this.loyaltyProgram.percentForSilver < 0|| this.loyaltyProgram.percentForGold < 0) {
      alert('Percentage must be greater than 0.');
      return;
    }
    else if (this.loyaltyProgram.percentForBronze > 100 || this.loyaltyProgram.percentForSilver > 100 || this.loyaltyProgram.percentForGold > 100) {
      alert('Percentage must be smaller than 100.');
      return;
    }
    else{
      this.loyaltyService.updateLoyaltyProgram(this.myFormGroup.getRawValue()).subscribe({
        next: () => {alert("Succesfully updated!")},
        error: () => alert("An unexpected error!")
      });
    }
  }
}
