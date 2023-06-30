import { Component, OnDestroy, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { MAT_FORM_FIELD_DEFAULT_OPTIONS, MatFormFieldDefaultOptions } from '@angular/material/form-field';

//disable asterisk from form fields globally
const myMatFormFieldDefaultOptions: MatFormFieldDefaultOptions = {
  hideRequiredMarker: true
};

interface SideNavToggle {
  screenWidth: number;
  collapsed: boolean;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  providers: [{ provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: myMatFormFieldDefaultOptions }],
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'Client';

  //for sidebar
  isSideNavCollapsed = false;
  screenWidth = 0;

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

  onToggleSideNav(data: SideNavToggle): void {
    this.screenWidth = data.screenWidth;
    this.isSideNavCollapsed = data.collapsed;
  }

}
