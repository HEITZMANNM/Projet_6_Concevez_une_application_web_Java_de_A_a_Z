import { Component, OnInit } from '@angular/core';
import { ContactService } from '../service/contact.service';
import { User } from '../models/user';
import { TransactionService } from '../service/transaction.service';
import { Transaction } from '../models/transaction';
import { UserService } from '../service/user.service';
import { TransactionBankaccount } from '../models/transactionBankaccount';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit{

  user!: User;
  userId!: number;
  transactionList!: Transaction[];
  transactionWithBankList!: TransactionBankaccount[];


  constructor( public transactionService: TransactionService, public userService: UserService )
  {
   
  }
  ngOnInit(): void {

  
    this.refreshUser();

  }

  getTransactions(){
  
  return this.transactionService.getTransactions(this.userId).subscribe(data =>{
    this.transactionList = data});
  }

  getTransactionBankaccount(){

    return this.transactionService.getTransactionBankaccountByUserId(this.userId).subscribe(data =>{
      this.transactionWithBankList = data});
  }

  

  refreshUser() {
    this.user = JSON.parse(sessionStorage.getItem("user")|| '{}');
    this.userId = parseInt(this.user.id);

    return this.userService.getUser(this.userId).subscribe(data => {
      this.user = data;
  
      sessionStorage.removeItem("user");
      sessionStorage.setItem("user", JSON.stringify(data));

      this.getTransactions();
      this.getTransactionBankaccount();

      location.reload;
  
    });
  }

}
