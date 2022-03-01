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

  constructor(private route: ActivatedRoute, 
    private router: Router, 
    private cottageDetailService: CottageDetailService) { }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    if (this.id) {
      this.getCottage(this.id);
    }
  }

  getCottage(id: number): void {
    this.cottageDetailService.getCottage(id).subscribe({
      next: cottage => this.cottage = cottage,
      error: err => this.errorMessage = err
    })
  }
}
