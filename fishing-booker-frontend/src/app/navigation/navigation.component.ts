import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login/login.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(public readonly loginService: LoginService) { }

  ngOnInit(): void {
  }

  logout(): void {
    localStorage.removeItem('loggedUser');
    this.loginService.isLoggedIn = false;
  }
}
