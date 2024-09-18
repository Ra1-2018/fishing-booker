import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ReportService } from './report.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  public readonly reportFormGroup: FormGroup;
  reservation: any;
  selectedValue = '';
  errorMessage = '';

  constructor(private router: Router, private location: Location, private route: ActivatedRoute, private reportService: ReportService, private readonly formBuilder: FormBuilder) {
    this.reportFormGroup = this.formBuilder.group({
      sanctionRequested: [false],
      comment: ['', Validators.required]
    });
   }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.getReservation(id);
    }
  }

  getReservation(id: number): void {
    this.reportService.getReservation(id).subscribe({
      next: reservation => this.reservation = reservation,
      error: err => this.errorMessage = err
    })
  }

  submitReport() {
    if(this.reportFormGroup.invalid) {
      alert("Invalid input");
      return;
    }
    const report = {
      comment: this.reportFormGroup.get('comment')?.value,
      sanctionRequested: this.reportFormGroup.get('sanctionRequested')?.value,
      client: this.reservation.client,
      owner: {id: localStorage.getItem("userId")}
    }
    this.reportService.submitReport(this.reservation.id, report).subscribe({
      next: () => {
        alert("Report submitted");
        this.location.back();
        document.getElementById("closeButton")?.click();
      },
      error: () => alert("An error occurred")
    });
  }

  givePenal()
  {
    this.reportService.givePenal(this.reservation).subscribe({
      next: () => {
        alert("Penal given");
        this.location.back();
      },
      error: () => alert("An error occurred")
    });
  }
}
