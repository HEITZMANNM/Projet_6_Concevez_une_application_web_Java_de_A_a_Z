import { Component } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {


  logOff(){

    sessionStorage.clear();
    sessionStorage.removeItem('user');
    sessionStorage.removeItem('userLogged');

  }

}
