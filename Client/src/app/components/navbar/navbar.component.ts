import { Location } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { UserAuthService } from 'src/app/services/user-auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit, OnDestroy{
  title: String = "Simple Second Brain";
  isLoggedIn: boolean = false;
  private loginStatusSubscription!: Subscription;
  
  constructor(private router: Router, private location: Location, private userSvc: UserAuthService) { }

  loginPage() {
    this.router.navigateByUrl('/login');
  }

  logout() {
    this.userSvc.setLoginStatus(false);
  }


  homePage() {
    if (this.location.path() !== '/') {
      this.router.navigate(["/"]);
    }
  }

  ngOnInit(): void {
    //suscribe to login status
    this.isLoggedIn = this.userSvc.getLoginStatus();

    this.loginStatusSubscription = this.userSvc.loginStatusChanged.subscribe((status: boolean) => {
      this.isLoggedIn = status;
    });
  }

  ngOnDestroy(): void {
    if (this.loginStatusSubscription) {
      this.loginStatusSubscription.unsubscribe();
    }
  }
}
