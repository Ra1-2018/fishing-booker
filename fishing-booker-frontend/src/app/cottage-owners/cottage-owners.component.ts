import { Component, OnInit } from '@angular/core';
import { CottageOwnersService } from './cottage-owners.service';

@Component({
  selector: 'app-cottage-owners',
  templateUrl: './cottage-owners.component.html',
  styleUrls: ['./cottage-owners.component.css']
})
export class CottageOwnersComponent implements OnInit {

  cottage_owners: any[] = [];

  constructor(private cottageOwnersService: CottageOwnersService) { }

  ngOnInit(): void {
    this.getCottageOwners();
  }

  getCottageOwners() {
    this.cottageOwnersService.getCottageOwners().subscribe(
      cottage_owners => {
        this.cottage_owners = cottage_owners;
      }
    )
  }
}
