import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, tap } from "rxjs";
import { User } from "../models/user";

@Injectable({
    providedIn: 'root'
  })
  export class UserService {
  
    constructor(private http:HttpClient) { }

    getUser(userId: number): Observable<User>
    {
        return this.http.get<User>(`http://localhost:8080/user?id=${userId}`).pipe(
            tap((response)=> console.table(response)))
    }

  }