import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReservationClientService } from './reservation-client.service';

@Component({
  selector: 'app-reservation-client',
  templateUrl: './reservation-client.component.html',
  styleUrls: ['./reservation-client.component.css']
})
export class ReservationClientComponent implements OnInit {

  client: any
  errorMessage = '';

  constructor(private route: ActivatedRoute, private router: Router, private reservationClientService: ReservationClientService) { }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.getClient(id);
    }
  }

  getClient(id: number): void {
    this.reservationClientService.getClient(id).subscribe({
      next: client => this.client = client,
      error: err => this.errorMessage = err
    })
  }
}


