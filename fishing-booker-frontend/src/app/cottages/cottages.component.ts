import { Component, OnInit } from '@angular/core';
import { CottageService } from './cottage.service';

@Component({
  selector: 'app-cottages',
  templateUrl: './cottages.component.html',
  styleUrls: ['./cottages.component.css']
})
export class CottagesComponent implements OnInit {

  cottages: any[] = []

  constructor(private _cottageService: CottageService) { }

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
