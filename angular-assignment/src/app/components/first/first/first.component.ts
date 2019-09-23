import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-first',
  template: '<p>Hello from first component</p>',
 // templateUrl: './first.component.html',
  styleUrls: ['./first.component.scss']
})
export class FirstComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
