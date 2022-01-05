import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SpecialRegistrationService } from './special-registration.service';

@Component({
  selector: 'app-special-registration',
  templateUrl: './special-registration.component.html',
  styleUrls: ['./special-registration.component.css']
})
export class SpecialRegistrationComponent implements OnInit {

  public readonly myFormGroup: FormGroup;

  constructor(private specialRegistrationService: SpecialRegistrationService,
    private readonly formBuilder: FormBuilder) { 
      this.myFormGroup = this.formBuilder.group({
        email: ['', Validators.compose([Validators.required, Validators.email])],
        password: ['', Validators.required],
        rePassword: ['', Validators.required],
        name: [],
        surname: [],
        address: [],
        city: [],
        country: [],
        telephone: [],
        userType: [],
        explanation: []
    });
  }

  ngOnInit(): void {
  }

  public onClickSubmit(): void {
    if (this.myFormGroup.invalid) {
        alert('Invalid input');
        return;
    }
    var rePassword = this.myFormGroup.get('rePassword')?.value;
    var password = this.myFormGroup.get('password')?.value;
    if(password != rePassword) {
      alert("Password doesn't match!");
      return;
    }

    this.specialRegistrationService.registerSpecialUser(this.myFormGroup.getRawValue()).subscribe({
      next: (data) => {alert("Succesfully registered!")},
      error: (err) => {alert("Email already in use!")}
    });
  }
}
