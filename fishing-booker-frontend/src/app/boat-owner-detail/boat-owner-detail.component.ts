import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { BoatOwnerDetailService } from './boat-owner-detail.service';

@Component({
  selector: 'app-boat-owner-detail',
  templateUrl: './boat-owner-detail.component.html',
  styleUrls: ['./boat-owner-detail.component.css']
})
export class BoatOwnerDetailComponent implements OnInit {

  owner: any;
  errorMessage = '';

  constructor(public readonly loginService: LoginService,
    private route: ActivatedRoute, 
    private router: Router, 
    private boatOwnerDetailService: BoatOwnerDetailService) { }

    ngOnInit(): void {
      const id = Number(this.route.snapshot.paramMap.get('id'));
      if (id) {
        this.getBoatOwner(id);
      }
    }
  
    getBoatOwner(id: number): void {
      this.boatOwnerDetailService.getBoatOwner(id).subscribe({
        next: owner => this.owner = owner,
        error: err => this.errorMessage = err
      })
    }
  
    public delete(id:number):void {
      this.boatOwnerDetailService.delete(id).subscribe(
        response => {this.router.navigate(['boat-owners']); }
        );
      return;
    }
}
