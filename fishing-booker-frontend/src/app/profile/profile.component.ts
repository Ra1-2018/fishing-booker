import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProfileService } from './profile.service';
import { CommonModule } from "@angular/common";
import { LoginService } from '../login/login.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  public readonly myFormGroup: FormGroup;
  requests: any[] = [];
  selectedUser: any;

  constructor(public readonly loginService: LoginService,
              private profileService: ProfileService,
              private readonly formBuilder: FormBuilder) {
                this.myFormGroup = this.formBuilder.group({
                  id: [],
                  email: ['', Validators.compose([Validators.required, Validators.email])],
                  password: ['', Validators.required],
                  name: [],
                  surname: [],
                  address: [],
                  city: [],
                  country: [],
                  telephone: []
              });
              }

  ngOnInit(): void {
    this.retrieveData();
    this.getRequests();
  }

  private retrieveData(): void {
    this.profileService.getUser()
        .subscribe((res: any) => {
            // Assuming res has a structure like:
            // res = {
            //     field1: "some-string",
            //     field2: "other-string",
            //     subgroupName: {
            //         subfield2: "another-string"
            //     },
            // }
            // Values in res that don't line up to the form structure
            // are discarded. You can also pass in your own object you
            // construct ad-hoc.
            this.myFormGroup.patchValue(res);
        });
  }

  getRequests(){
    this.profileService.getRequests().subscribe(
      requests => {
        this.requests = requests;
      }
    )
  }

  public onClickSubmit(): void {
    if (this.myFormGroup.invalid) {
      // stop here if it's invalid
      alert('Invalid input');
      return;
    }
    this.profileService.updateUser(this.myFormGroup.getRawValue()).subscribe({
      next: (data) => {alert("Succesfully updated!")},
      error: (err) => {alert("An unexpected error!")}
    });
  }

  public onApprove(id:number): void{
    this.profileService.approveRequest(id);
    alert('Request approved');
    return;
  }

  public onDecline(id:number): void{
    this.profileService.declineRequest(id);
    alert('Request declined');
    return;
  }
}
