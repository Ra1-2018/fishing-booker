import { Component, OnInit } from '@angular/core';
import { OwnedBoatsService } from './owned-boats.service';

@Component({
  selector: 'app-owned-boats',
  templateUrl: './owned-boats.component.html',
  styleUrls: ['./owned-boats.component.css']
})
export class OwnedBoatsComponent implements OnInit {

  boats: any[] = []

  constructor(private _boatService: OwnedBoatsService) { }

  ngOnInit(): void {
    this.getBoats();
  }

  getBoats() {
    this._boatService.getBoats().subscribe(
      boats => {
        this.boats = boats;
      }
    )
  }
}

