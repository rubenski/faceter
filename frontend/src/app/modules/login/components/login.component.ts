import {Component} from '@angular/core';
import {Credentials} from './credentials';
import {LoginService} from '../login.service';

@Component({
  moduleId: module.id,
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  private model = new Credentials();
  private submitted = false;


  constructor(private loginService: LoginService) {
  }

  onSubmit() {
    this.submitted = true;
    this.loginService.login(this.model.username, this.model.password);
  }
}
