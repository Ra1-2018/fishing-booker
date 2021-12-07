import { Component, OnInit } from '@angular/core';
import { AdventureService } from './adventure.service';

@Component({
  selector: 'app-adventures',
  templateUrl: './adventures.component.html',
  styleUrls: ['./adventures.component.css']
})
export class AdventuresComponent implements OnInit {

  adventures: any[] = []

  constructor(private _adventureService: AdventureService) { }

  ngOnInit(): void {
    this.getAdventures();
  }

  getAdventures() {
    this._adventureService.getAdventures().subscribe(
      adventures => {
        this.adventures = adventures;
      }
    )
  }
}
