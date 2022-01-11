import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { ClientDetailService } from './client-detail.service';

@Component({
  selector: 'app-client-detail',
  templateUrl: './client-detail.component.html',
  styleUrls: ['./client-detail.component.css']
})
export class ClientDetailComponent implements OnInit {

  owner: any;
  errorMessage = '';

  constructor(public readonly loginService: LoginService,
    private route: ActivatedRoute, 
    private router: Router, 
    private clientDetailService: ClientDetailService) { }

    ngOnInit(): void {
      const id = Number(this.route.snapshot.paramMap.get('id'));
      if (id) {
        this.getClient(id);
      }
    }
  
    getClient(id: number): void {
      this.clientDetailService.getClient(id).subscribe({
        next: owner => this.owner = owner,
        error: err => this.errorMessage = err
      })
    }
  
    public delete(id:number):void {
      this.clientDetailService.delete(id).subscribe(
        response => {this.router.navigate(['clients']); }
        );
      return;
    }


}
