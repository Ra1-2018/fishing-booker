import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ClientComplaintService } from './client-complaint.service';

@Component({
  selector: 'app-client-complaint',
  templateUrl: './client-complaint.component.html',
  styleUrls: ['./client-complaint.component.css']
})
export class ClientComplaintComponent implements OnInit {

  public readonly myFormGroup: FormGroup;
  services: any[] = [];

  constructor(private complaintService: ClientComplaintService,
              private readonly formBluilder: FormBuilder) {
                this.myFormGroup = this.formBluilder.group({
                  service: ['', Validators.required],
                  content: ['', Validators.required]
                });
               }

  ngOnInit(): void {
    this.getServices();
  }

  getServices() {
    this.complaintService.getServices().subscribe(
      services => this.services = services
    );
  }

  onClickSubmit() {
    if (this.myFormGroup.invalid) {
      // stop here if it's invalid
      alert('Invalid input');
      return;
    }
    const complaint = {
      service: this.myFormGroup.get('service')?.value,
      client: {id: parseInt(localStorage.getItem('userId') as string)},
      content: this.myFormGroup.get('content')?.value
    }
    this.complaintService.submitComplaint(complaint).subscribe({
      next: () => {
        alert("Complaint submitted");
        this.myFormGroup.reset();
      },
      error: () => alert("An error ocurred")
    })
  }
}
