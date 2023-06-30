import { Component, OnInit, } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { lastValueFrom } from 'rxjs';
import { JokesApiService } from 'src/app/services/jokes-api.service';

@Component({
  selector: 'app-jokes',
  templateUrl: './jokes.component.html',
  styleUrls: ['./jokes.component.scss']
})
export class JokesComponent implements OnInit {
  JokeQueryForm!: FormGroup
  displayJoke: boolean = false;
  joke!: any;
  jokeDisplayDiv!: string;

  constructor(private fb: FormBuilder, private jokeService: JokesApiService) { }

  ngOnInit(): void {
  }

  createForm() {
    this.JokeQueryForm = this.fb.group({
      radioAnyCustom: '',
      checkBoxCustomOptions: { value: '', disabled: true },
      checkBoxpart: { value: '' },
    });
  }

  onSubmit() {
    lastValueFrom(this.jokeService.apicall()).then(
      (response) => {
        this.joke = response;
        if (this.joke.type == "single") {
          this.displayJoke = true;
          console.log("the joke: ", this.joke.joke);
          this.jokeDisplayDiv = this.joke.joke;
        } else if (this.joke.type == "twopart") {
          this.displayJoke = true;
          this.jokeDisplayDiv = "<Strong>Setup joke</Strong>: "+this.joke.setup + "<br>" + "<Strong>Delivery joke</Strong>:" + this.joke.delivery;
          console.log("the joke part 1:", this.joke.setup);
          console.log("the joke part 2:", this.joke.delivery);

        }
      }
    ).catch((error) => {
      console.log("error occured", error);
    })

  }
}
