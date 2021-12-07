import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdventureDetailService } from './adventure-detail.service';

@Component({
  selector: 'app-adventure-detail',
  templateUrl: './adventure-detail.component.html',
  styleUrls: ['./adventure-detail.component.css']
})
export class AdventureDetailComponent implements OnInit {

  adventure: any;
  errorMessage = '';

  constructor(private route: ActivatedRoute, 
              private router: Router, 
              private adventureDetailService: AdventureDetailService) { 

  }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.getAdventure(id);
    }
  }

  getAdventure(id: number) : void {
    this.adventureDetailService.getAdventure(id).subscribe({
      next: adventure => this.adventure = adventure,
      error: err => this.errorMessage = err
    })
  }
}
