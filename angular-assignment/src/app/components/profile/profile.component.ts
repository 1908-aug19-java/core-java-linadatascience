import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  users =[{
    id: 24,
    name: 'Paul',
    age: 25,
    email: 'paultop@gmail.com'
  }, {
    id:25,
    name:'Amily',
    age:23,
    email:'amily123@hotmail.com'
  },{
    id:26,
    name:'Meggie',
    age:22,
    email:'lucky456@hotmail.com'
  }]
  buttonMsg:string = '';
  condition:boolean;  
  
  constructor() { }

  ngOnInit() {
    this.condition=true;
  }
  
  // if(condition){
  //   this.buttonMsg =  'hide';
  // } else{
  //   this.buttonMsg = 'show';
  // }
  changeCondition(){
    this.condition = !this.condition;
    if(this.condition){
      this.buttonMsg = 'hide';
    }else{
      this.buttonMsg = 'show';
    }
  //  console.log(this.buttonMsg);
  }

}
