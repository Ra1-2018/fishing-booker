import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginService } from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public readonly myFormGroup: FormGroup;

  constructor(private loginService: LoginService,
              private readonly formBuilder: FormBuilder) {
                this.myFormGroup = this.formBuilder.group({
                  email: ['', Validators.compose([Validators.required, Validators.email])],
                  password: ['', Validators.required],
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
    this.loginService.loginUser(this.myFormGroup.getRawValue()).subscribe({
      next: (data) => {
        alert("Succesfully logged in!");
        localStorage.setItem('loggedUser', JSON.stringify(data));
        this.loginService.isLoggedIn = true;
      },
      error: (err) => {alert("Invalid username/password!")}
    });
}
}
