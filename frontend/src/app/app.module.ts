import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AppComponent} from './app.component';
import {FormsModule} from '@angular/forms';
import {HomeComponent} from './modules/home/components/home.component';
import {PageNotFoundComponent} from './modules/shared/components/pagenotfound.component';
import {AppRoutingModule} from './app-routing.module';
import {ProductModule} from './modules/product/product.module';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LoginModule} from './modules/login/login.module';
import {CsrfInterceptor} from './csrf.interceptor';
import {CookieService} from 'ngx-cookie-service';
import {DynamicFormModule} from './modules/dynamicform/dynamic.form.module';
import {ExpiredTokenInterceptor} from "./expired.token.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    CommonModule,
    FormsModule,
    ProductModule,
    DynamicFormModule,
    LoginModule,
    HttpClientModule,
    AppRoutingModule // AppRoutingModule must be last, because it contains the most generic route matching patterns
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: CsrfInterceptor,
      multi: true
    }, {
      provide: HTTP_INTERCEPTORS,
      useClass: ExpiredTokenInterceptor,
      multi: true
    },
    CookieService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
