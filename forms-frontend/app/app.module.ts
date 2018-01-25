import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule}   from '@angular/forms';
import {Http, HttpModule} from '@angular/http';
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
import {AuthConfig, AuthHttp} from 'angular2-jwt';
import {TOKEN_NAME} from "./auth/auth.constant";
import {LoginComponent} from "./login.component";
import {AuthenticationService} from "./auth/authentication.service";
import {UserService} from "./auth/user.service";
import { CookieService } from 'ngx-cookie-service';

export function authHttpServiceFactory(http: Http) {
    return new AuthHttp(new AuthConfig({
        headerPrefix: 'Bearer',
        tokenName: TOKEN_NAME,
        globalHeaders: [{'Content-Type': 'application/json'}],
        noJwtError: false,
        noTokenScheme: true,
        tokenGetter: (() => localStorage.getItem(TOKEN_NAME))
    }), http);
}

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
        PageNotFoundComponent,
        LoginComponent
    ],
    providers: [
        {provide: AuthHttp, useFactory: authHttpServiceFactory, deps: [Http]},
        HeroService,
        AuthenticationService,
        UserService,
        CookieService
    ],
    bootstrap: [ AppComponent ]
})
export class AppModule { }