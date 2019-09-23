import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-highlight',
  templateUrl: './highlight.component.html',
  styleUrls: ['./highlight.component.scss']
})
export class HighlightComponent implements OnInit {

  colors:string[] = ["red", "yellow",'green', 'blue'];
  selectedColor: string ='';

  constructor() { }

  ngOnInit() {
  }

}
