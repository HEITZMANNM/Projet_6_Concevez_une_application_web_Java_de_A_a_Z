import { Component } from '@angular/core';
import { LoginService } from '../service/login.service';
import { Router } from '@angular/router';
import { User } from '../models/user';
import { ToastrService } from 'ngx-toastr';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
 

  constructor(public loginService: LoginService, private router: Router, private toastrService: ToastrService)
{
}
user!: User;


  form={
    mail: "",
    password: ""
  }


  handleLogin(email: string, mdp: string): void {
    

    this.loginService.authenticate(this.form.mail, this.form.password).subscribe(
      data =>{
        this.user = data
        if(this.user != null)
        {
          this.toastrService.success('Welcome !')
          this.router.navigate(['home'])
          sessionStorage.setItem("user", JSON.stringify(this.user))
          sessionStorage.setItem("userLogged", JSON.stringify(this.user))


        }
        else{
          this.toastrService.error('Invalid password or login !')
        }
        
      })
    }

    
    
}


