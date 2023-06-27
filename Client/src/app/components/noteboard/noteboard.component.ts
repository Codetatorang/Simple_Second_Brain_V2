import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-noteboard',
  templateUrl: './noteboard.component.html',
  styleUrls: ['./noteboard.component.scss']
})
export class NoteboardComponent {
  @Input() collapsed = false;
  @Input() screenWidth = 0;

  getBodyClass(): string {
    let styleClass = '';
    if(this.collapsed && this.screenWidth > 768) {
      styleClass = 'body-trimmed';
    } else if(this.collapsed && this.screenWidth <= 768 && this.screenWidth > 0) {
      styleClass = 'body-md-screen'
    }
    return styleClass;
  }
}
