import { Component, OnInit } from '@angular/core';
import { People } from 'src/app/models/People';


@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit {

  peopleArr: People[] = [{'firstName':'paul', 'lastName':'armstrong', 'email':'paul_armstrong@gmail.com', 'birthday': new Date('09/22/1982')},
  {'firstName':'annie', 'lastName':'braden', 'email':'annie_braden@gmail.com', 'birthday': new Date('11/18/1985')},
  {'firstName':'eric', 'lastName':'trump', 'email':'eric_trump@gmail.com', 'birthday': new Date('06/26/1983')},
  {'firstName':'maggie', 'lastName':'walker', 'email':'maggie_walker@gmail.com', 'birthday': new Date('09/15/1984')}]
  
  condition: boolean = true;

  constructor() { }

  ngOnInit() {
  }
  changeCondition(){
    this.condition = !this.condition;
  }
}
