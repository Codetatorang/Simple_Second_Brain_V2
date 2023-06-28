import { Injectable, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
// import { User, UserLogin } from 'src/app/models/user.model';
// import { JwtHelperService } from '@auth0/angular-jwt';
import { BehaviorSubject, Observable } from 'rxjs';
import { UserLogin, User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {
  private apiUrl = 'simplesecondbrain.up.railway.app/user'
  apiUrlwithHTTPs = "https:" + this.apiUrl;

  loginStatusChanged: EventEmitter<boolean> = new EventEmitter<boolean>();
  private isLoggedin: boolean = false;
  private storageKey = 'loginStatus';
  statusInStorage!: string;

  // private readonly jwtHelper!: JwtHelperService;

  constructor(private http: HttpClient) {
    this.isLoggedin = this.getLoginStatusFromStorage();
    // this.jwtHelper = new JwtHelperService();
  }

  getLoginStatus(): boolean {
    return this.getLoginStatusFromStorage();
  }

  setLoginStatus(status: boolean) {
    this.isLoggedin = status;
    this.loginStatusChanged.emit(this.isLoggedin);
    this.setLoginStatusToStorage();
  }

  private setLoginStatusToStorage() {
    localStorage.setItem(this.storageKey, this.isLoggedin.toString());
  }

  private getLoginStatusFromStorage(): boolean {
    if(null !== localStorage.getItem(this.storageKey)){
      const status = localStorage.getItem(this.storageKey);
      return status ? status === 'true' : false;
    }
    return false;
  }

  login(user: UserLogin): Observable<User> {
    return this.http.post<User>(this.apiUrlwithHTTPs + "/login", user, { withCredentials: true });
  }

  public isAuthenticated(): boolean {
    const token = localStorage.getItem("token");
    // return !!token && !this.jwtHelper.isTokenExpired(token);
    return false;
  }

  logout() {
    //code in later
  }


  //! register
  createUser(user:User):Observable<User>{
    return this.http.post<User>(this.apiUrlwithHTTPs + "/createuser", user, {withCredentials: true});
  }

}
