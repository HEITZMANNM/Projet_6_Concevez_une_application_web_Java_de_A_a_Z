import { Component } from '@angular/core';
import { LoginService } from '../service/login.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { NewUser } from '../models/newUser';

@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css']
})
export class NewUserComponent {

  firstName!: string;
  lastName!: string;
  email!: string;
  password!: string;

  newUser!: NewUser;

  constructor(public loginService: LoginService, private router: Router, private toastrService: ToastrService){}

  addNewUser()
  {
this.newUser = new NewUser(this.firstName, this.lastName, this.email, this.password);

    this.loginService.addNewUser(this.newUser).subscribe(
      data =>{
        this.router.navigate(['login'])
        if(this.newUser != null)
        {
          this.toastrService.success('Welcome !')
          this.router.navigate(['login'])
          //sessionStorage.setItem("user", JSON.stringify(this.newUser))
         // localStorage.setItem("userLocal", JSON.stringify(this.user));
         // this.loginService.user = this.user;

        }
        else{
          this.toastrService.error('Please complete the elements !')
        }
        
      })
    }

}
