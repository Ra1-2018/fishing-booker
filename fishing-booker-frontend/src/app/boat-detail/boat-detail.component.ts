import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BoatDetailService } from './boat-detail.service';

@Component({
  selector: 'app-boat-detail',
  templateUrl: './boat-detail.component.html',
  styleUrls: ['./boat-detail.component.css']
})
export class BoatDetailComponent implements OnInit {

  boat: any
  errorMessage = ''

  constructor(private route: ActivatedRoute,
              private router: Router,
              private boatDetailService: BoatDetailService) { }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.getBoat(id);
    }
  }

  getBoat(id: number): void {
    this.boatDetailService.getBoat(id).subscribe({
      next: boat => this.boat = boat,
      error: err => this.errorMessage = err
    })
  }
}
