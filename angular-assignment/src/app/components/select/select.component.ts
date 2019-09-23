import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.scss']
})
export class SelectComponent implements OnInit {

  colors:string[]=['red', 'green', 'yellow', 'blue'];
  animals:string[]=['dog', 'cat', 'fish','bird'];
  days: string[]= ['Monday','Tuesday','Wednesday','Thurseday','Friday','Saturday','Sunday'];
  
  typeSelected:string='';


  constructor() { }

  ngOnInit() {
  }
  
}
