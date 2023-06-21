import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { LoginService } from '../service/login.service';



@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  user : User| undefined;
  iter!: number;

  constructor(public login: LoginService)
{
}
  ngOnInit(): void {

    
      this.user = JSON.parse(sessionStorage.getItem("user")|| '{}');
      this.iter = this.login.iter;

}
}
