import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { LoginNewAdminService } from './login-new-admin.service';

@Component({
  selector: 'app-login-new-admin',
  templateUrl: './login-new-admin.component.html',
  styleUrls: ['./login-new-admin.component.css']
})
export class LoginNewAdminComponent implements OnInit {

  public readonly myFormGroup: FormGroup;

  constructor(private loginNewAdminService: LoginNewAdminService,
              private readonly formBuilder: FormBuilder,
              private router: Router) {
                this.myFormGroup = this.formBuilder.group({
                  password: ['', Validators.required],
                  rePassword: ['', Validators.required]
              });
               }

  ngOnInit(): void {
  }

  public onClickUpdate(): void {
    if (this.myFormGroup.invalid) {
      // stop here if it's invalid
      alert('Invalid input');
      return;
  }
  this.loginNewAdminService.loginNewAdmin(this.myFormGroup.getRawValue()).subscribe({
    next: (data) => {
      this.router.navigate(['profile']);
    },
    error: (err) => {alert("An unexpected error!")}
  });
  }

}
