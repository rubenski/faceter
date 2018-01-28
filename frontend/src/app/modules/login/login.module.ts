import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {LoginComponent} from './components/login.component';
import {LoginService} from './login.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule
  ],
  declarations: [
    LoginComponent
  ],
  providers: [LoginService]
})
export class LoginModule {
}
