import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './components/signup/signup.component';
import { LoginComponent } from './components/login/login.component';
import { NoteboardComponent } from './components/noteboard/noteboard.component';
import { NotesComponent } from './components/notes/notes.component';
import { loginredirectGuard } from './guards/loginredirect.guard';
import { JokesComponent } from './components/jokes/jokes.component';

const routes: Routes = [
  {path:"signup", component: SignupComponent},
  {path:"login", canActivate:[loginredirectGuard], component: LoginComponent},
  {path:"board", component: NoteboardComponent},
  {path:"notes", component: NotesComponent, outlet:"noteOutlet"},
  {path:"jokes", component: JokesComponent, outlet:"noteOutlet"},
  {path:"**", redirectTo:"/", pathMatch:"full"}, //unmatch default redirect to home
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
