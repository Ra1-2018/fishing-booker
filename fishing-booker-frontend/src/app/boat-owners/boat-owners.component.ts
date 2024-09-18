import { Component, OnInit } from '@angular/core';
import { BoatOwnersService } from './boat-owners.service';

@Component({
  selector: 'app-boat-owners',
  templateUrl: './boat-owners.component.html',
  styleUrls: ['./boat-owners.component.css']
})
export class BoatOwnersComponent implements OnInit {

  boat_owners: any[] = [];

  constructor(private boatOwnersService: BoatOwnersService) { }

  ngOnInit(): void {
    this.getBoatOwners();
  }

  getBoatOwners() {
    this.boatOwnersService.getBoatOwners().subscribe(
      boat_owners => {
        this.boat_owners = boat_owners;
      }
    )
  }
}
