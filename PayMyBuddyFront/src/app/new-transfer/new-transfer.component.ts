import { Component, OnInit } from '@angular/core';
import { ContactService } from '../service/contact.service';
import { User } from '../models/user';
import { TransactionService } from '../service/transaction.service';
import { ToastrService } from 'ngx-toastr';
import { TransactionDTO } from '../models/transactionDTO';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-new-transfer',
  templateUrl: './new-transfer.component.html',
  styleUrls: ['./new-transfer.component.css']
})
export class NewTransferComponent implements OnInit {

  user!: User;
  userId!:number;
  listOfFriends!: User[];
  firendSelected!: User;
  friendName!: string;
  amount!: number;
  description!: string;
  transactionDTO!: TransactionDTO;


  constructor(public contactService: ContactService, public transactionService: TransactionService, private toastrService: ToastrService, public router: Router, private userService: UserService) { }
  
  ngOnInit(): void {

    this.userId = JSON.parse(sessionStorage.getItem("user") || '{}').id;
    this.user = JSON.parse(sessionStorage.getItem("user") || '{}');

    this.chargeListOfFriends();

  }

  chargeListOfFriends() {

    this.contactService.getFriends(this.user.email).subscribe(
      data => {
        this.listOfFriends = data
      }
    )
  }

  inputValueToFriendSelected(friend: User) {
    this.friendName = friend.firstName + " " + friend.lastName;
    this.firendSelected = friend;
  }

  send() {
    if (this.firendSelected != null && this.amount != null) {


      this.transactionDTO = new TransactionDTO(this.description, this.amount, parseInt(this.user.id), parseInt(this.firendSelected.id));


      if (parseInt(this.user.balance) > this.amount) {
        this.transactionService.send(this.transactionDTO).subscribe(data => { this.chargeListOfFriends() })
        this.toastrService.success('Your buddy will be happy !')


      }
      else {
        this.toastrService.error('You dont have enought money !')
      }

    }
    else {
      this.toastrService.error('Please complete the elements !')
    }


    this.router.navigate(['transaction']);

    

  }
}
