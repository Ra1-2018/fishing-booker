import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ClientReviewService } from './client-review.service';

@Component({
  selector: 'app-client-review',
  templateUrl: './client-review.component.html',
  styleUrls: ['./client-review.component.css']
})
export class ClientReviewComponent implements OnInit {

  public readonly myFormGroup: FormGroup;
  services: any[] = [];

  constructor(private _reviewService: ClientReviewService,
              private readonly formBuilder: FormBuilder) {
                this.myFormGroup = this.formBuilder.group({
                  service: ['', Validators.required],
                  grade: ['', Validators.required],
                  revision: ['']
                });
               }

  ngOnInit(): void {
    this.getServices();
  }

  getServices() {
    this._reviewService.getServices().subscribe(
      services => this.services = services
    );
  }

  public onClickSubmit(): void {
    if (this.myFormGroup.invalid) {
      // stop here if it's invalid
      alert('Invalid input');
      return;
    }
    const review = {
      grade: this.myFormGroup.get('grade')?.value,
      revision: this.myFormGroup.get('revision')?.value,
      client: {id: parseInt(localStorage.getItem('userId') as string)},
      service: this.myFormGroup.get('service')?.value
    }
    this._reviewService.postReview(review).subscribe({
      next: () => {
        alert("Review submitted");
        this.myFormGroup.reset();
      },
      error: () => "An error ocurred"
    });
  }
}
