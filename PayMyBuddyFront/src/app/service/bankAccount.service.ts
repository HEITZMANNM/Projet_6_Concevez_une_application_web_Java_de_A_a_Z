import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';
import { LoginService } from './login.service';
import { Observable, tap } from 'rxjs';
import { Bankaccount } from '../models/bankaccount';
import { BankaccountToAdd } from '../models/bankaccountToAdd';



@Injectable({
  providedIn: 'root'
})
export class BankaccountService {


    constructor(public http: HttpClient){}

    getBankAccountByUser(user: User): Observable<Bankaccount[]>{

        return this.http.get<Bankaccount[]>(`http://localhost:8080/bankaccountByUserInfo?userId=${user.id}`).pipe(
            tap((response)=> console.table(response))
          )
    }

    deleteBankAccount(iban: string)
    {
return this.http.delete(`http://localhost:8080/bankaccount?iban=${iban}`).pipe(
  tap((response)=> console.table(response))
)
    }


    addNewIban(bankaccountToAdd: BankaccountToAdd, user: User)
    {
    

      return this.http.post(`http://localhost:8080/bankaccount?userId=${user.id}`, bankaccountToAdd).pipe(
        tap((response)=> console.table(response))
      );
    }

    updateUser(user:User)
    {
      return this.http.put(`http://localhost:8080/user`, user).pipe(
        tap((response)=> console.table(response))
      )
    }

}