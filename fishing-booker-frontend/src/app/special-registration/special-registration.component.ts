import { Component, OnInit } from '@angular/core';
import { SpecialRegistrationRequest } from '../model/special-registration-request';
import { SpecialRegistrationService } from './special-registration.service';

@Component({
  selector: 'app-special-registration',
  templateUrl: './special-registration.component.html',
  styleUrls: ['./special-registration.component.css']
})
export class SpecialRegistrationComponent implements OnInit {

  user = new SpecialRegistrationRequest('', '', '', '', '', '', '', '', '', '', '');

  constructor(private specialRegistrationService: SpecialRegistrationService) { }

  ngOnInit(): void {
  }

  public onClickSubmit(): void {
    this.specialRegistrationService.registerSpecialUser(this.user).subscribe({
      next: (data) => {alert("Your request was submitted and is pending for approval.")},
      error: (err) => {alert("Email already in use!")}
    });
  }
}
