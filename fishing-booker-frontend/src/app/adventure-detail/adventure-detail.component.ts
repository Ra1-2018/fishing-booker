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
  id: number|undefined;

  constructor(private route: ActivatedRoute, 
              private router: Router, 
              private adventureDetailService: AdventureDetailService) { 

  }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    if (this.id) {
      this.getAdventure(this.id);
    }
  }

  getAdventure(id: number) : void {
    this.adventureDetailService.getAdventure(id).subscribe({
      next: adventure => this.adventure = adventure,
      error: err => this.errorMessage = err
    })
  }
}
