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
  appUser: any;

  constructor(private loginNewAdminService: LoginNewAdminService,
              private readonly formBuilder: FormBuilder,
              private router: Router) {
                this.myFormGroup = this.formBuilder.group({
                  password: ['', Validators.required],
                  rePassword: ['', Validators.required]
              });
               }

  ngOnInit(): void {
    this.retrieveData();
  }

  private retrieveData(): void {
    this.loginNewAdminService.getUser()
        .subscribe((user: any) => {
            this.appUser = user;
        });
  }

  public onClickUpdate(): void {
    if (this.myFormGroup.invalid) {
      // stop here if it's invalid
      alert('Invalid input');
      return;
    }  
    if(this.myFormGroup.get('password')?.value != this.myFormGroup.get('rePassword')?.value) {
      alert('Passwords must match');
      return;
    }

  this.appUser.password = this.myFormGroup.get('password')?.value;

  this.loginNewAdminService.loginNewAdmin(this.appUser).subscribe({
    next: (data) => {
      alert("Your password has been changed");
      this.router.navigate(['profile']);
    },
    error: (err) => {alert("An unexpected error!")}
  });
  
  }

}
