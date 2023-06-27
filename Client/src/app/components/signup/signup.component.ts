import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { faUser, faKey, faEnvelope, faCircleUser } from '@fortawesome/free-solid-svg-icons';
import { UserAuthService } from 'src/app/services/user-auth.service';
import { User } from 'src/app/models/user.model';
import { Location } from '@angular/common';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit{
  createUserForm!: FormGroup;
  onSubmitError!: boolean;
  errorResp!: string;

  // icons
  username_icon = faUser;
  password_icon = faKey;
  accname_icon = faCircleUser;
  email_icon = faEnvelope;

  constructor(private userSvc: UserAuthService,
    private router: Router,
    private location: Location,
    private fb: FormBuilder){}


  ngOnInit(): void {
    this.createUserForm = this.fb.group({
      userName: this.fb.control('', [Validators.required, Validators.minLength(3), Validators.maxLength(30)]),
      userPassword: this.fb.control('', [Validators.required]),
      accName: this.fb.control('', [Validators.required, Validators.minLength(3), Validators.maxLength(30)]),
      email: this.fb.control('', [Validators.email, Validators.required]),
    });
  }

  homePage() {
    if (this.location.path() !== '/') {
      this.router.navigate(["/"]);
    }
  }

  onSubmit(): void {
    if (this.createUserForm.valid) {
      const user: User = {
        id: 0,
        userName: this.createUserForm.value.userName,
        userPassword: this.createUserForm.value.userPassword,
        accName: this.createUserForm.value.accName,
        email: this.createUserForm.value.email
      };

      lastValueFrom(this.userSvc.createUser(user)).then(
        (response) => {
          if (response.userName === user.userName && response.email === user.email) {
            this.errorResp = "Username '" + response.userName + "' has been taken" + "<br>"
              + "Email '" + response.email + "' has been taken";
            console.log(this.errorResp);
            this.onSubmitError = true;
            return;
          }
          else if (response.userName === user.userName){
            this.errorResp = "Username '" + response.userName + "' has been taken";
            console.log(this.errorResp);
            this.onSubmitError = true;
            return;
          }
          else if (response.email === user.email) {
            this.errorResp = "Email '" + response.email + "' has been taken";
            console.log(this.errorResp);
            this.onSubmitError = true;
            return;
          }
          else
            console.log("Account for " + response.userName + " created successfully");
            this.onSubmitError = false;
            return this.homePage();
        }).catch(
          (error) => {
            console.log("Error creating account: ", error);
          }
        );
    }
    else {
      this.errorResp = "Please fill in all fields";
      this.onSubmitError = true;
      console.log("Invalid form");
      return;
    }
  }

  
}
