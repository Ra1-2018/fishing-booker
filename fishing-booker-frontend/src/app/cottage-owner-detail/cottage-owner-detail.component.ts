import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { CottageOwnerDetailService } from './cottage-owner-detail.service';

@Component({
  selector: 'app-cottage-owner-detail',
  templateUrl: './cottage-owner-detail.component.html',
  styleUrls: ['./cottage-owner-detail.component.css']
})
export class CottageOwnerDetailComponent implements OnInit {

  owner: any;
  errorMessage = '';

  constructor(public readonly loginService: LoginService,
    private route: ActivatedRoute, 
    private router: Router, 
    private cottageOwnerDetailService: CottageOwnerDetailService) { }

    ngOnInit(): void {
      const id = Number(this.route.snapshot.paramMap.get('id'));
      if (id) {
        this.getCottageOwner(id);
      }
    }
  
    getCottageOwner(id: number): void {
      this.cottageOwnerDetailService.getCottageOwner(id).subscribe({
        next: owner => this.owner = owner,
        error: err => this.errorMessage = err
      })
    }
  
    public delete(id:number):void {
      this.cottageOwnerDetailService.delete(id).subscribe(
        response => {this.router.navigate(['cottage-owners']); }
        );
      return;
    }

}
