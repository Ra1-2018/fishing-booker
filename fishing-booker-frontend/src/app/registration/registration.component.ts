import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { RegistrationService } from './registration.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user = new User('', '', '', '', '', '', '', '', '');

  constructor(private registrationService: RegistrationService) { }

  ngOnInit(): void {
  }

  public onClickSubmit(): void {
    this.registrationService.registerUser(this.user).subscribe({
      next: (data) => {alert("Succesfully registered!")},
      error: (err) => {alert("Email already in use!")}
    });
  }
}
