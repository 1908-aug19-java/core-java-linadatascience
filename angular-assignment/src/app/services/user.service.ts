import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {
   
  url:string = "https://jsonplaceholder.typicode.com/users";

  constructor(private http: HttpClient) { };

  getUsers() : Observable<User[]>{
    return this.http.get<User[]>(this.url);
  }

  getUserById(id:number):Promise<User>{
    return this.http.get<User>(this.url+'/'+id).toPromise();
  }
}
