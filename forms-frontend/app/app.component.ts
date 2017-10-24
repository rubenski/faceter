import {Component} from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'my-app',
    styleUrls: ['app.component.css'],
    template: `
   <h1>{{title}}</h1>
    <nav>
     <a routerLink="/dashboard" routerLinkActive="active">Dashboard</a>
     <a routerLink="/heroes" routerLinkActive="active">Heroes</a>
     <a routerLink="/products" routerLinkActive="active">Products</a>
     <a [routerLink]="['/item', 'payment']" routerLinkActive="active">Item</a>
   </nav>
   <router-outlet></router-outlet>
 `
})
export class AppComponent {
    title = 'Faceter';
}