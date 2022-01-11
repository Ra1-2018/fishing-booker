import { Component, OnInit } from '@angular/core';
import { InstructorsService } from './instructors.service';

@Component({
  selector: 'app-instructors',
  templateUrl: './instructors.component.html',
  styleUrls: ['./instructors.component.css']
})
export class InstructorsComponent implements OnInit {

  instructors: any[] = [];

  constructor(private instructorService: InstructorsService) { }

  ngOnInit(): void {
    this.getInstructors();
  }

  getInstructors() {
    this.instructorService.getInstructors().subscribe(
      instructors => {
        this.instructors = instructors;
      }
    )
  }

}
