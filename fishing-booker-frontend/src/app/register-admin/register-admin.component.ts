import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterAdminServiceService } from './register-admin-service.service';

@Component({
  selector: 'app-register-admin',
  templateUrl: './register-admin.component.html',
  styleUrls: ['./register-admin.component.css']
})
export class RegisterAdminComponent implements OnInit {

  public readonly myFormGroup: FormGroup;

  constructor(private registrationService: RegisterAdminServiceService,
    private readonly formBuilder: FormBuilder) { 
      this.myFormGroup = this.formBuilder.group({
        email: ['', Validators.compose([Validators.required, Validators.email])],
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
    this.registrationService.registerAdmin(this.myFormGroup.getRawValue()).subscribe({
      next: (data) => {alert("Succesfully registered!")},
      error: (err) => {alert("Email already in use!")}
    });
  }
}
