import { Component, ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.scss']
})
export class NotesComponent {
  content:string = "";//initial content
  @ViewChild('container',{static:true}) containerRef!:ElementRef;
  divs:string[] = [];

  onContentChanged(target:EventTarget | null){

    const element = target as HTMLElement; 
    if(null !== element)
      this.content = element.innerHTML;

  }

  handleKeyPress(event:KeyboardEvent){
    if(event.key === "Enter"){
      event.preventDefault();
      this.createNewDiv();
    }
  }


  createNewDiv() {
    const container = document.getElementById("container");
    const newDiv =  document.createElement("div");
    newDiv.contentEditable = "true";
    newDiv.classList.add("block");
    // newDiv.setAttribute("class","block");
    this.containerRef.nativeElement.appendChild(newDiv);
    this.divs.push(newDiv.innerHTML);
    container?.appendChild(newDiv);
    console.log("new div created");
  }

  newDivText = '';
  divTexts: string[] = [];

  createDiv() {
    if (this.newDivText.trim() !== '') {
      this.divTexts.push(this.newDivText);
      this.newDivText = '';
    }
  }
}
