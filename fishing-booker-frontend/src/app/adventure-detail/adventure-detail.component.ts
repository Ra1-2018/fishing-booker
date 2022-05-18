import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { AdventureDetailService } from './adventure-detail.service';

@Component({
  selector: 'app-adventure-detail',
  templateUrl: './adventure-detail.component.html',
  styleUrls: ['./adventure-detail.component.css']
})
export class AdventureDetailComponent implements OnInit {

  adventure: any;
  errorMessage = '';
  id: number = 0;
  subscriptions:any[] = []
  images: any[] = [];

  constructor(private route: ActivatedRoute, 
              private router: Router, 
              private adventureDetailService: AdventureDetailService,
              public readonly loginService: LoginService) { 

  }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    if (this.id) {
      this.getAdventure(this.id);
      this.getImages(this.id);
    }
    if(this.loginService.isLoggedIn && this.loginService.userType == 'CLIENT') {
      this.getSubscriptions();
    }
  }

  getAdventure(id: number) : void {
    this.adventureDetailService.getAdventure(id).subscribe({
      next: adventure => this.adventure = adventure,
      error: err => this.errorMessage = err
    })
  }

  getImages(id: number) {
    this.adventureDetailService.getImages(id).subscribe({
      next: images => this.images = images,     
      error: err => this.errorMessage = err
    })
  }

  subscribe(): void {
    this.adventureDetailService.subscribe(this.id as number).subscribe({
      next: () => {
        this.getSubscriptions();
      }
    });
  }

  isSubscribed(): boolean {
    for(let subscription of this.subscriptions) {
      if(subscription.id == this.adventure.id) {
        return true;
      }
    }
    return false;
  }

  getSubscriptions() {
    this.adventureDetailService.getSubscriptions().subscribe(
      subscriptions => {this.subscriptions = subscriptions;}
    )
  }

  unsubscribe() {
    this.adventureDetailService.unsubscribe(this.id as number).subscribe({
      next: () => {
        this.getSubscriptions();
      }
    });
  }
}
