import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { BoatDetailService } from './boat-detail.service';

@Component({
  selector: 'app-boat-detail',
  templateUrl: './boat-detail.component.html',
  styleUrls: ['./boat-detail.component.css']
})
export class BoatDetailComponent implements OnInit {

  boat: any;
  errorMessage = '';
  id: number|undefined;
  subscriptions:any[] = []

  constructor(private route: ActivatedRoute,
              private router: Router,
              private boatDetailService: BoatDetailService,
              public readonly loginService: LoginService) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    if (this.id) {
      this.getBoat(this.id);
    }
    if(this.loginService.isLoggedIn && this.loginService.userType == 'CLIENT') {
      this.getSubscriptions();
    }
  }

  getBoat(id: number): void {
    this.boatDetailService.getBoat(id).subscribe({
      next: boat => this.boat = boat,
      error: err => this.errorMessage = err
    })
  }

  subscribe(): void {
    this.boatDetailService.subscribe(this.id as number).subscribe({
      next: () => {
        this.getSubscriptions();
      }
    });
  }

  isSubscribed(): boolean {
    for(let subscription of this.subscriptions) {
      if(subscription.id == this.boat.id) {
        return true;
      }
    }
    return false;
  }

  getSubscriptions() {
    this.boatDetailService.getSubscriptions().subscribe(
      subscriptions => {this.subscriptions = subscriptions;}
    )
  }

  unsubscribe() {
    this.boatDetailService.unsubscribe(this.id as number).subscribe({
      next: () => {
        this.getSubscriptions();
      }
    });
  }
}
