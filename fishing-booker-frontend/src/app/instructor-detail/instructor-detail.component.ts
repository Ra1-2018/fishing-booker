import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { InstructorDetailService } from './instructor-detail.service';

@Component({
  selector: 'app-instructor-detail',
  templateUrl: './instructor-detail.component.html',
  styleUrls: ['./instructor-detail.component.css']
})
export class InstructorDetailComponent implements OnInit {

  owner: any;
  errorMessage = '';

  constructor(public readonly loginService: LoginService,
    private route: ActivatedRoute, 
    private router: Router, 
    private instructorDetailService: InstructorDetailService) { }

    ngOnInit(): void {
      const id = Number(this.route.snapshot.paramMap.get('id'));
      if (id) {
        this.getInstructor(id);
      }
    }
  
    getInstructor(id: number): void {
      this.instructorDetailService.getInstructor(id).subscribe({
        next: owner => this.owner = owner,
        error: err => this.errorMessage = err
      })
    }
  
    public delete(id:number):void {
      this.instructorDetailService.delete(id).subscribe(
        response => {this.router.navigate(['instructors']); }
        );
      return;
    }

}
