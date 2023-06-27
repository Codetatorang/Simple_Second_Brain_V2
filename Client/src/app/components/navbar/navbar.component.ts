import { Location } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit, OnDestroy{
  title: String = "Simple Second Brain";
  isLoggedIn: boolean = false;
  private loginStatusSubscription!: Subscription;
  
  //!todo uncomment loginservice codes after service class implementation
  constructor(private router: Router, private location: Location, ) { } //private loginSvc: LoginService

  loginPage() {
    this.router.navigate(["/login"]);
  }

  logout() {
    //this.loginSvc.setLoginStatus(false);
  }


  homePage() {
    if (this.location.path() !== '/') {
      this.router.navigate(["/"]);
    }
  }

  ngOnInit(): void {
    //suscribe to login status
    // this.isLoggedIn = this.loginSvc.getLoginStatus();

    // this.loginStatusSubscription = this.loginSvc.loginStatusChanged.subscribe((status: boolean) => {
    //   this.isLoggedIn = status;
    // });
  }

  ngOnDestroy(): void {
    if (this.loginStatusSubscription) {
      this.loginStatusSubscription.unsubscribe();
    }
  }
}
