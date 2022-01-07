import { Component, OnInit } from '@angular/core';
import { OwnedCottagesService } from './owned-cottages.service';

@Component({
  selector: 'app-owned-cottages',
  templateUrl: './owned-cottages.component.html',
  styleUrls: ['./owned-cottages.component.css']
})
export class OwnedCottagesComponent implements OnInit {

  cottages: any[] = []
  
  constructor(private _cottageService: OwnedCottagesService) { }

  ngOnInit(): void {
    this.getCottages();
  }

  getCottages() {
    this._cottageService.getCottages().subscribe(
      cottages => {
        this.cottages = cottages;
      }
    )
  }
}
