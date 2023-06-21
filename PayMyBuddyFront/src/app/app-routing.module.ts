import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AppComponent } from './app.component';
import { TransactionComponent } from './transaction/transaction.component';
import { ProfileComponent } from './profile/profile.component';
import { ContactComponent } from './contact/contact.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { NewTransferComponent } from './new-transfer/new-transfer.component';
import { NewUserComponent } from './new-user/new-user.component';
import { TransactionBankAccountComponent } from './transaction-bank-account/transaction-bank-account.component';
import { RoutguardService } from './rout-guard.service';

const routes: Routes = [

  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent, canActivate:[RoutguardService]},
  {path: 'transaction', component: TransactionComponent, canActivate:[RoutguardService]},
  {path: 'profile', component: ProfileComponent,canActivate:[RoutguardService]},
  {path: 'contact', component: ContactComponent, canActivate:[RoutguardService]},
  {path: 'transaction/newTransfer', component: NewTransferComponent, canActivate:[RoutguardService]},
  {path: 'transaction/transactionBankAccount', component: TransactionBankAccountComponent, canActivate:[RoutguardService]},
  {path: 'login/newUser', component: NewUserComponent},
  {path: '**', component: PageNotFoundComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
