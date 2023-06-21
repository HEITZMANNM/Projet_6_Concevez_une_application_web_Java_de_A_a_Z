import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { LoginService } from '../service/login.service';
import { ContactService } from '../service/contact.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit{

  user!: User;
  

  listOfFriends!: User[];


inputValue!: string;

  

  constructor(public loginService: LoginService, public contactService: ContactService, public router: Router)
{
 
}
  ngOnInit(): void {
    
this.chargeListOfFriends();
    
  }


chargeListOfFriends()
{

this.user =  JSON.parse(sessionStorage.getItem("user")|| '{}');

console.log(this.user);

this.contactService.getFriends(this.user.email).subscribe(
  data => { this.listOfFriends = data


  }
)

}

deleteFriend(friend: User){

this.contactService.deleteFriend(parseInt(this.user.id), parseInt(friend.id)).subscribe(
  data => {this.chargeListOfFriends();
    
  })
  
}




addFriend(){

  this.contactService.addNewFriend(parseInt(this.user.id), this.inputValue).subscribe(
    data => {this.chargeListOfFriends();
      
    })

    location.reload();
}

}
