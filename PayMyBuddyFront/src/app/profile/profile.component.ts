import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { Bankaccount } from '../models/bankaccount';
import { BankaccountService } from '../service/bankAccount.service';
import { Observable } from 'rxjs';
import { ToastrService } from 'ngx-toastr';
import { BankaccountToAdd } from '../models/bankaccountToAdd';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{

  user! : User;
  bankAccountList!: Bankaccount[];

  inputValue!: string;
  ibanSelected!:string;
  newIban!: string;
  newBic!: string;
  newPassword!:string;
  status: string = "actif";

  bankaccountToAdd!: BankaccountToAdd;
  

  constructor(public bankAccountService: BankaccountService, private toastrService: ToastrService)
{
}
 
ngOnInit(): void {
  this.getBankAccounts();
}


getBankAccounts()
{
  this.user = JSON.parse(sessionStorage.getItem("user")|| '{}');

  return this.bankAccountService.getBankAccountByUser(this.user).subscribe(data =>{
    this.bankAccountList = data});
}

deleteIban()
{
  
  return this.bankAccountService.deleteBankAccount(this.ibanSelected).subscribe(
    data => {this.getBankAccounts();
      
   
    if(this.ibanSelected != null)
    {
      this.toastrService.success('Iban deleted !')
    }
    else
    {
      this.toastrService.error('Please select an Iban !')
    }

    location.reload();
  });

  
}

addIban()
{
this.bankaccountToAdd = new BankaccountToAdd(this.newIban, this.newBic, this.status);


  return this.bankAccountService.addNewIban(this.bankaccountToAdd, this.user).subscribe(
    data => {this.getBankAccounts(); 
      
this.newBic ="";
this.newIban= "";

    });
    
    
   
}

modify()
{
this.user.password = this.newPassword;

sessionStorage.clear();

sessionStorage.setItem("user", JSON.stringify(this.user));


return this.bankAccountService.updateUser(this.user).subscribe(
  data => {this.getBankAccounts();
    
    this.toastrService.success('Password Modified !')

    location.reload();

  });


}

inputValueToIbanSelected(iban: string){

  this.ibanSelected=iban;

}

}
