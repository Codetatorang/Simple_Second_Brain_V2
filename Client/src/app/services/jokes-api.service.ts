import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class JokesApiService {

  // apiUrl:string = "https://api.chucknorris.io/jokes/random";
  private apiUrl:string = "https://v2.jokeapi.dev/";
  constructor(private http:HttpClient) { }

  apicall(){
    return this.http.get(this.apiUrl + "joke/Any");
  }
}
