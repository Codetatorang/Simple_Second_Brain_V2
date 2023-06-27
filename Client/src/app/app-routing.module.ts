import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './components/signup/signup.component';
import { LoginComponent } from './components/login/login.component';
import { NoteboardComponent } from './components/noteboard/noteboard.component';

const routes: Routes = [
  {path:"signup", component: SignupComponent},
  {path:"login", component: LoginComponent},
  {path:"board", component: NoteboardComponent},
  {path:"**", redirectTo:"/", pathMatch:"full"}, //unmatch default redirect to home
  //!todo: disallow nagivation to login or sign up when logged in
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
