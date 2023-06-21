import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { LoginService } from './service/login.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoutguardService implements CanActivate {

  constructor(private loginService: LoginService, private router: Router){}

  
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
  

    if(this.loginService.isUserLoggedIn())
    {
return true;
    }
    
    this.router.navigate(['login']);

    return false;

  }
}
