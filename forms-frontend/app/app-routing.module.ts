import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DashboardComponent} from './dashboard.component';
import {HeroesComponent} from './heroes.component';
import {HeroDetailComponent} from './hero-detail.component';
import {ProductComponent} from "./products/product.component";
import {ItemFormComponent} from "./itemform/itemform.component";
import {PageNotFoundComponent} from "./not-found.component";
import {LoginComponent} from "./login.component";


const routes: Routes = [
    {path: '', redirectTo: '/dashboard', pathMatch: 'full'},
    {path: 'dashboard', component: DashboardComponent},
    {path: 'detail/:id', component: HeroDetailComponent},
    {path: 'heroes', component: HeroesComponent},
    {path: 'products', component: ProductComponent},
    {path: 'item/:type', component: ItemFormComponent},
    {path: 'login', component: LoginComponent},
    {path: '**', component: PageNotFoundComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
