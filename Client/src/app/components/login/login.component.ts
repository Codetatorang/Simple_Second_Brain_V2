import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { lastValueFrom } from 'rxjs';
import { UserLogin } from 'src/app/models/user.model';
import { UserAuthService } from 'src/app/services/user-auth.service';
import { SHA256 } from 'crypto-js';
import { SocialAuthService, SocialUser } from '@abacritt/angularx-social-login';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  title: String = "Log in";
  loginForm!: FormGroup
  username_icon = faUser;
  hide = true;
  onSubmitError!: boolean;
  errorResp!: string;

  user!: SocialUser;
  loggedIn!: boolean;
  
  constructor(private router: Router, private location: Location, private fb: FormBuilder, private userSvc: UserAuthService, private authService: SocialAuthService) { }

  homePage() {
    if (this.location.path() !== '/') {
      this.router.navigate(["/"]);
    }
  }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      userName: ['', Validators.required],
      userPassword: ['', Validators.required]
    });

    //google auth
    this.authService.authState.subscribe((user) => {
      this.user = user;
      this.loggedIn = (user != null);
      console.log(user.name);
    });

  }

  onSubmit() {
    if (this.loginForm.valid) {
      const userCredentials: UserLogin = {
        userName: this.loginForm.value.userName,
        userPassword: this.loginForm.value.userPassword,
      };

      //check for login credentials with server side 
      lastValueFrom(this.userSvc.login(userCredentials)).then(
        (response) => {
          
          if (response.userName === userCredentials.userName) { //username match
            const encodedPassword = SHA256(userCredentials.userPassword).toString(); //encode password
            if (response.userPassword === encodedPassword) { //password match
              console.log("password match: userPass:" + response.userPassword + " encodedPass: " + encodedPassword);
              this.onSubmitError = false;
              this.userSvc.setLoginStatus(true); //set emit status to true
              // localStorage.setItem("token", response.token); //store user in local storage
              this.router.navigate(["/"]);
            }
            else { //password dont match
              this.errorResp = "<strong> password </strong> does not match with <strong>user</strong>: " + userCredentials.userName;
              this.onSubmitError = true;
            }
          } else { //username not found
            this.errorResp = "<strong>User</strong>: " + userCredentials.userName + " does not exists!";
            this.onSubmitError = true;
          }

        }
      ).catch(
        (error) => {
          console.log("Error logging in: ", error);
        }
      );
    }
  }
}

