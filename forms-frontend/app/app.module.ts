import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule}   from '@angular/forms';
import { HttpModule }    from '@angular/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent }         from './app.component';
import { DashboardComponent }   from './dashboard.component';
import { HeroesComponent }      from './heroes.component';
import { HeroDetailComponent }  from './hero-detail.component';
import { HeroService }          from './hero.service';
import {DynamicFormQuestionComponent} from "./dynamic-form/dynamic-form-question.component";
import {DynamicFormComponent} from "./dynamic-form/dynamic-form.component";
import {ProductComponent} from "./products/product.component";
import {ItemFormComponent} from "./itemform/itemform.component";
import {PageNotFoundComponent} from "./not-found.component";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        AppRoutingModule,
        ReactiveFormsModule
    ],
    declarations: [
        AppComponent,
        DashboardComponent,
        HeroDetailComponent,
        HeroesComponent,
        DynamicFormComponent,
        DynamicFormQuestionComponent,
        ProductComponent,
        ItemFormComponent,
        PageNotFoundComponent
    ],
    providers: [ HeroService ],
    bootstrap: [ AppComponent ]
})
export class AppModule { }
