import { Component } from '@angular/core';
  
@Component({
    selector: 'page-404',
    template: `
    <div class='center'>
      <img src="https://img.freepik.com/vecteurs-libre/page-non-trouvee-illustration-du-concept_114360-1869.jpg"/>
      <h1>Hey, cette page n'existe pas !</h1>
      <a routerLink="/login" class="waves-effect waves-teal btn-flat">
        Retourner à l' accueil
      </a>
    </div>
  `
})
export class PageNotFoundComponent { }