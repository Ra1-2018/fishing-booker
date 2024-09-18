import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Complaint } from 'src/app/model/complaint';
import { ClientComplaintService } from './client-complaint.service';

@Component({
  selector: 'app-client-complaint',
  templateUrl: './client-complaint.component.html',
  styleUrls: ['./client-complaint.component.css']
})
export class ClientComplaintComponent implements OnInit {

  services: any[] = [];
  complaint = new Complaint(null, '', {id: parseInt(localStorage.getItem('userId') as string)});

  constructor(private complaintService: ClientComplaintService) { }

  ngOnInit(): void {
    this.getServices();
  }

  getServices() {
    this.complaintService.getServices().subscribe(
      services => this.services = services
    );
  }

  onClickSubmit(complaintForm: NgForm) {
    this.complaintService.submitComplaint(this.complaint).subscribe({
      next: () => {
        alert("Complaint submitted");
        complaintForm.resetForm();
      },
      error: () => alert("An error ocurred")
    });
  }
}
