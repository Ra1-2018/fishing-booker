import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegistrationService } from './registration.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  public readonly myFormGroup: FormGroup;

  constructor(private registrationService: RegistrationService,
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
        telephone: []
    });
    }

  ngOnInit(): void {
  }

  public onClickSubmit(): void {
    if (this.myFormGroup.invalid) {
        // stop here if it's invalid
        alert('Invalid input');
        return;
    }
    var rePassword = this.myFormGroup.get('rePassword')?.value;
    var password = this.myFormGroup.get('password')?.value;
    if(password != rePassword) {
      alert("Password doesn't match!");
      return;
    }
    this.registrationService.registerUser(this.myFormGroup.getRawValue()).subscribe({
      next: (data) => {alert("Succesfully registered!")},
      error: (err) => {alert("Email already in use!")}
    });
  }
}
