import { Component, OnDestroy, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'Client';

  //stores current router url
  routerUrl!: String;
  routersubscription!: Subscription;

  constructor(private router: Router) { }
  
  ngOnInit(): void {
    this.routersubscription = this.router.events.subscribe((event) => { //retrieve current url
      if (event instanceof NavigationEnd) {
        this.routerUrl = event.url;
        console.log("current url" + event.url);
      }
    });
  }

  ngOnDestroy(): void {
    this.routersubscription.unsubscribe();
  }

  //disable navbar if current url is login or signup
  isLoginOrSignup(): boolean {
    if (this.routerUrl === "/login" || this.routerUrl === "/signup") {
      return true;
    }
    return false;
  }
}
