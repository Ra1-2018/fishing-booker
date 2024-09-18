import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginCredentials } from '../model/loginCredentials';
import { LoginService } from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public show:boolean = true;
  loginCredentials = new LoginCredentials('', '');

  constructor(private loginService: LoginService,
              private router: Router) {}

  ngOnInit(): void {
  }

  public onClickSubmit() {
    this.loginService.loginUser(this.loginCredentials).subscribe({
      next: (data) => {
        localStorage.setItem('userId', data.id);
        localStorage.setItem('userType', data.appUserType)
        localStorage.setItem('jwt', data.userTokenState.accessToken)
        this.loginService.isLoggedIn = true;
        this.loginService.userType = data.appUserType;
        this.loginService.isSanctioned = data.sanctioned;
        if(data.firstReg==true && data.appUserType=='ADMIN') {
            //this.show = true;
            this.router.navigate(['login-new-admin']);
          } else {
            //this.show = false;
            this.router.navigate(['profile']);
          }
      },
      error: (err) => {alert("Invalid username/password!")}
    });
  }

  /*public onClickUpdate(): void {
    if (this.myFormGroup.invalid) {
      // stop here if it's invalid
      alert('Invalid input');
      return;
    }
    this.loginService.loginNewAdmin(this.myFormGroup.getRawValue()).subscribe({
      next: (data) => {
        this.router.navigate(['profile']);
      },
      error: (err) => {alert("An unexpected error!")}
    });
  }*/
}
