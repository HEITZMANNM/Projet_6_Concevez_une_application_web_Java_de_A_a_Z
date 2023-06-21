import { Component } from '@angular/core';
import { Bankaccount } from '../models/bankaccount';
import { BankaccountService } from '../service/bankAccount.service';
import { TransactionService } from '../service/transaction.service';
import { ToastrService } from 'ngx-toastr';
import { User } from '../models/user';
import { Router } from '@angular/router';
import { TransactionDTO } from '../models/transactionDTO';

import { TransactionBankaccountDTO } from '../models/transactionBankaccountDTO';
import { UserService } from '../service/user.service';


@Component({
  selector: 'app-transaction-bank-account',
  templateUrl: './transaction-bank-account.component.html',
  styleUrls: ['./transaction-bank-account.component.css']
})
export class TransactionBankAccountComponent {

  user!: User;
  userSenderId!:number;
  ibanSelected!: string;
  bankaccountSelected!: Bankaccount;
  listOfBankAccount!: Bankaccount[];
  amount!: number;
  description!: string;
  origin:boolean = false;
  transaction!: TransactionBankaccountDTO;

  constructor(public bankaccountService: BankaccountService, public transactionService: TransactionService, private toastrService: ToastrService, public router: Router, private userService: UserService)
  {}
   ngOnInit(){

    this.user = JSON.parse(sessionStorage.getItem("user")|| '{}');

   this.chargeListOfBankaccount();

  }

  chargeListOfBankaccount()
  {
    
    this.bankaccountService.getBankAccountByUser(this.user).subscribe(
    data => { this.listOfBankAccount = data
    }
  ) 
  }

  inputValueToBankAccountSelected(bankaccount: Bankaccount)
  {
    this.ibanSelected = bankaccount.iban;
    this.bankaccountSelected = bankaccount;
  }

  send()
  {
    console.log("valeur de origin")
    console.log(this.origin)
  
    this.transaction = new TransactionBankaccountDTO(this.description,this.amount, this.bankaccountSelected, this.origin);


    if(this.bankaccountSelected != null && this.amount!= null)
    {
      
    if(this.amount < JSON.parse(sessionStorage.getItem("user")|| '{}').balance)
    {
      this.toastrService.success('Your buddy will be happy !')
      this.transactionService.sendBankaccount(this.transaction).subscribe(data => {this.chargeListOfBankaccount()})
    }
    else{
      this.toastrService.error('You dont have enought money !')
    }
    
    }
  else
  {
  this.toastrService.error('Please complete the elements !')
  }


    this.router.navigate(['transaction']);
  
}


  }


