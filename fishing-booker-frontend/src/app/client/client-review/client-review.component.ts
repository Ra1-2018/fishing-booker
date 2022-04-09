import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Review } from 'src/app/model/review';
import { ClientReviewService } from './client-review.service';

@Component({
  selector: 'app-client-review',
  templateUrl: './client-review.component.html',
  styleUrls: ['./client-review.component.css']
})
export class ClientReviewComponent implements OnInit {

  review = new Review(null, null, '', {id: parseInt(localStorage.getItem('userId') as string)});
  services: any[] = [];

  constructor(private _reviewService: ClientReviewService) { }

  ngOnInit(): void {
    this.getServices();
  }

  getServices() {
    this._reviewService.getServices().subscribe(
      services => this.services = services
    );
  }

  public onClickSubmit(reviewForm: NgForm): void {
    this._reviewService.postReview(this.review).subscribe({
      next: () => {
        alert("Review submitted");
        reviewForm.resetForm();
      },
      error: () => "An error ocurred"
    });
  }
}
