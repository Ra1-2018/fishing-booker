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
  public readonly deletionRequestFormGroup: FormGroup;
  public readonly changePasswordFormGroup: FormGroup;
  public readonly registrationRequestFormGroup: FormGroup;
  requests: any[] = [];
  review: any[]=[];
  selectedUser: any;
  userEmail:string = ''
  userID:number = 0;
  requestID:number = 0;

  constructor(public readonly loginService: LoginService,
              private profileService: ProfileService,
              private readonly formBuilder: FormBuilder) {
                this.myFormGroup = this.formBuilder.group({
                  id: [],
                  email: ['', Validators.compose([Validators.required, Validators.email])],
                  //password: ['', Validators.required],
                  name: [],
                  surname: [],
                  address: [],
                  city: [],
                  country: [],
                  telephone: []
              });
              this.deletionRequestFormGroup = this.formBuilder.group({
                explanation: []
              });
              this.registrationRequestFormGroup = this.formBuilder.group({
                explanation: []
              });
              this.changePasswordFormGroup = this.formBuilder.group({
                currentPassword: [],
                newPassword: [],
                newRePassword: []
              })

              }

  ngOnInit(): void {
    this.retrieveData();
    this.getRequests();
  }

  private retrieveData(): void {
    this.profileService.getUser()
        .subscribe((res: any) => {
            this.myFormGroup.patchValue(res);
            this.userEmail = res.email;
            this.userID = res.id;
        });
  }

  submitDeletionRequest() {
    const deletionRequest = {
      explanation: this.deletionRequestFormGroup.get('explanation')?.value,
      userEmail: this.userEmail
    }
    this.profileService.submitDeletionRequest(deletionRequest).subscribe({
      next: () => {
        alert("Deletion request submitted");
        document.getElementById("closeButton")?.click();
      },
      error: () => alert("An error occurred")
    })
  }

  submitChangePassword() {
    const changePassword = {
      currentPassword: this.changePasswordFormGroup.get('currentPassword')?.value,
      newPassword: this.changePasswordFormGroup.get('newPassword')?.value,
      newRePassword: this.changePasswordFormGroup.get('newRePassword')?.value,
      userID: this.userID
    }
    if(changePassword.newPassword !== changePassword.newRePassword) {
      alert("Passwords must match!") 
    } else {
      this.profileService.submitChangePassword(changePassword).subscribe({
        next: () => {
          alert("Successfully changed password!");
          document.getElementById("closeButton")?.click();
        },
        error: () => alert("An error occurred")
      })
    }
  }

  submitRegistrationRequest() {
    const registrationRequest = {
      explanation: this.registrationRequestFormGroup.get('explanation')?.value,
      userID: this.userID,
      requestID: this.requestID,
    }
    this.profileService.submitRegistrationRequest(registrationRequest).subscribe({
      next: () => {
        this.getRequests(); 
        alert("Registration denied");
        document.getElementById("closeButton")?.click();
      },
      error: () => alert("An error occurred")
    }) 
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
    this.profileService.approveRequest(id).subscribe(
      response => {this.getRequests(); 
                   alert('Request approved');
                  }
      );
    return;
  }

  public onDecline(id:number): void{
    this.requestID = id;
  }
}
