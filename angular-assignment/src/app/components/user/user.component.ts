import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/User';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  users: User[]=[];
 
  constructor(private userService: UserService) { }

  ngOnInit() {
  }
  getAllUsers(){
    this.userService.getUsers().subscribe((allUsers)=>{
      this.users = allUsers;
    });
   // return this.users;
  }

}
