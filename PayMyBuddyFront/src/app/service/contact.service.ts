import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';
import { LoginService } from './login.service';
import { Observable, tap } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class ContactService {

 

  constructor(private http:HttpClient, private login: LoginService) { }


  status!: string;


  getFriends(mail:string):Observable<User[]>{
      return this.http.get<User[]>(`http://localhost:8080/userFriends?email=${mail}`).pipe(
        tap((response)=> console.table(response))
      )
  }

  getUserByEmail(email:string):Observable<User>
  {
    console.log("nous entrons bien sur l'url get user by email")
    return this.http.get<User>(`http://localhost:8080/userByEmail?email=${email}`).pipe(
      tap((response)=> console.table(response))
    )


  }

  getUser(mail : string, password: string):Observable<User> {
    return this.http.get<User>(`http://localhost:8080/userByEmailAndPassword?email=${mail}&password=${password}`).pipe(
      tap((response)=> console.table(response))
    )
}

  


  deleteFriend(userId: number, friendId: number): Observable<any>
  {
    
   return this.http.delete(`http://localhost:8080/friend?userId=${userId}&friendId=${friendId}`);
   
  }

  addNewFriend(userId: number, friendEmail:string): Observable<any>
  {
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };


  

     return this.http.get(`http://localhost:8080/newFriend?userId=${userId}&friendEmail=${friendEmail}`).pipe(
      tap((response)=> console.table(response)));
  }

  
}