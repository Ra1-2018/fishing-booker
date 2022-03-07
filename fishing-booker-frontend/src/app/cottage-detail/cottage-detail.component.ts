import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { CottageDetailService } from './cottage-detail.service';

@Component({
  selector: 'app-cottage-detail',
  templateUrl: './cottage-detail.component.html',
  styleUrls: ['./cottage-detail.component.css']
})
export class CottageDetailComponent implements OnInit {

  cottage: any;
  errorMessage = '';
  id: number|undefined;
  subscriptions:any[] = []

  constructor(public readonly loginService: LoginService,
    private route: ActivatedRoute,
    private router: Router,
    private cottageDetailService: CottageDetailService) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    if (this.id) {
      this.getCottage(this.id);
    }
    if(this.loginService.isLoggedIn && this.loginService.userType == 'CLIENT') {
      this.getSubscriptions();
    }
  }

  getCottage(id: number): void {
    this.cottageDetailService.getCottage(id).subscribe({
      next: cottage => this.cottage = cottage,
      error: err => this.errorMessage = err
    })
  }

  delete(id:number):void {
    this.cottageDetailService.delete(id).subscribe(
      response => {this.router.navigate(['cottages']); }
      );
    return;
  }

  subscribe(): void {
    this.cottageDetailService.subscribe(this.id as number).subscribe({
      next: () => {
        this.getSubscriptions();
      }
    });
  }

  isSubscribed(): boolean {
    for(let subscription of this.subscriptions) {
      if(subscription.id == this.cottage.id) {
        return true;
      }
    }
    return false;
  }

  getSubscriptions() {
    this.cottageDetailService.getSubscriptions().subscribe(
      subscriptions => {this.subscriptions = subscriptions;}
    )
  }

  unsubscribe() {
    this.cottageDetailService.unsubscribe(this.id as number).subscribe({
      next: () => {
        this.getSubscriptions();
      }
    });
  }
}
