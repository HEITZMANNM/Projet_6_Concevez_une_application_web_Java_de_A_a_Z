

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';
import { tap } from 'rxjs';
import { NewUser } from '../models/newUser';



@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) { }

  user!: User;
  iter!: number;



  authenticate(mail : string, password: string) {
      return this.http.get<User>(`http://localhost:8080/userByEmailAndPassword?email=${mail}&password=${password}`)
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem("user")
    return !(user === null)
  }

  logout() {
    sessionStorage.removeItem("user")
  }

  addNewUser(user: NewUser)
  {
    return this.http.post(`http://localhost:8080/user`, user).pipe(
      tap((response)=> console.table(response)));
  }

}