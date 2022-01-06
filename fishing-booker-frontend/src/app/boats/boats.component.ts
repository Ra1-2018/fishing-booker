import { Component, OnInit } from '@angular/core';
import { BoatService } from './boat.service';

@Component({
  selector: 'app-boats',
  templateUrl: './boats.component.html',
  styleUrls: ['./boats.component.css']
})
export class BoatsComponent implements OnInit {

  boats: any[] = []

  constructor(private _boatService: BoatService) { }

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
