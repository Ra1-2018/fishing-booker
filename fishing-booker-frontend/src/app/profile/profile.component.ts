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
  public readonly deletionResponseFormGroup: FormGroup;
  public readonly complaintRequestFormGroup: FormGroup;
  complaintRequests: any[] = [];
  deletionRequests: any[] = [];
  requests: any[] = [];
  reviews: any[]=[];
  selectedUser: any;
  userEmail:string = ''
  userID:number = 0;
  requestID:number = 0;
  deletionRequestID: number = 0;
  complaintRequestID: number = 0;

  constructor(public readonly loginService: LoginService,
              private profileService: ProfileService,
              private readonly formBuilder: FormBuilder) {
                this.myFormGroup = this.formBuilder.group({
                  id: [],
                  email: ['', Validators.compose([Validators.required, Validators.email])],
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
              this.deletionResponseFormGroup = this.formBuilder.group({
                explanation: []
              });
              this.complaintRequestFormGroup = this.formBuilder.group({
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
    this.getReviews();
    this.getDeletionRequests();
    this.getComplaintRequests();
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

  submitDeletionResponse() {
    const deletionResponse = {
      explanation: this.deletionResponseFormGroup.get('explanation')?.value,
      userID: this.userID,
      requestID: this.deletionRequestID,
    }
    this.profileService.submitDeletionResponse(deletionResponse).subscribe({
      next: () => {
        this.getDeletionRequests(); 
        alert("Deleted");
        document.getElementById("closeButton")?.click();
      },
      error: () => alert("An error occurred")
    }) 
  }

  submitComplaintResponse() {
    const complaintRequest = {
      content: this.complaintRequestFormGroup.get('explanation')?.value,
      userID: this.userID,
      requestID: this.complaintRequestID,
    }
    this.profileService.submitComplaintResponse(complaintRequest).subscribe({
      next: () => {
        this.getComplaintRequests(); 
        alert("Complaint denied");
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

  getReviews(){
    this.profileService.getReviews().subscribe(
      reviews => {
        this.reviews = reviews;
      }
    )
  }

  getDeletionRequests() {
    this.profileService.getDeletionRequests().subscribe(
      deletionRequests => {
        this.deletionRequests = deletionRequests;
      }
    )
  }

  getComplaintRequests() {
    this.profileService.getComplaintRequests().subscribe(
      complaintRequests => {
        this.complaintRequests = complaintRequests;
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

  onApproveReviewRequest(id:number):void {
    this.profileService.approveReviewRequest(id).subscribe(
      response => {this.getReviews(); 
                   alert('Request for review approved');
                  }
      );
    return;
  }

  onDeclineReviewRequest(id:number):void {
    this.profileService.declineReviewRequest(id).subscribe(
      response => {this.getReviews(); 
                   alert('Request for review declined');
                  }
      );
    return;
  }

  onApproveDeletionRequest(id:number):void {
    this.profileService.approveDeletionRequest(id).subscribe(
      response => {this.getDeletionRequests(); 
                   alert('Request for deletion approved');
                  }
      );
    return;
  }

  onApproveComplaintRequest(id:number):void {
    this.profileService.approveComplaintRequest(id).subscribe(
      response => {this.getComplaintRequests(); 
                   alert('Request for complaint approved');
                  }
      );
    return;
  }

  public onDecline(id:number): void{
    this.requestID = id;
  }

  public onDeclineDeletion(id:number): void{
    this.deletionRequestID = id;
  }

  public onDeclineComplaint(id:number): void{
    this.complaintRequestID = id;
  }
}
