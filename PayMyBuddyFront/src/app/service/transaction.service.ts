import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Transaction } from '../models/transaction';
import { TransactionDTO } from '../models/transactionDTO';
import { Observable, tap } from 'rxjs';
import { TransactionBankaccount } from '../models/transactionBankaccount';
import { TransactionBankaccountDTO } from '../models/transactionBankaccountDTO';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor(private http:HttpClient) { }


getTransactions(userId: number): Observable<Transaction[]>
{
  return this.http.get<Transaction[]>(`http://localhost:8080/transactionByUserId?userId=${userId}`).pipe(
    tap((response)=> console.table(response))
  )
}

getTransactionBankaccountByUserId(userId: number): Observable<TransactionBankaccount[]>
{
  return this.http.get<TransactionBankaccount[]>(`http://localhost:8080/transactionsWithBankByUserId?userId=${userId}`).pipe(
    tap((response)=> console.table(response))
  )
}

send(transactionDTO: TransactionDTO)
{
  return this.http.post(`http://localhost:8080/transaction`, transactionDTO).pipe(
      tap((response)=> console.table(response)));
}

sendBankaccount(transactionBankaccountDTO: TransactionBankaccountDTO)
{
  return this.http.post(`http://localhost:8080/transactionBankaccount`, transactionBankaccountDTO).pipe(
    tap((response)=> console.table(response)));
}

}
