import {Injectable} from '@angular/core';
import {JwtHelper} from 'angular2-jwt';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {TOKEN_AUTH_PASSWORD, TOKEN_AUTH_USERNAME, TOKEN_NAME} from '../../app.constants';
import {Observable} from "rxjs/Observable";

@Injectable()
export class LoginService {

  static AUTH_TOKEN = '/iam/oauth/token';

  jwtHelper: JwtHelper = new JwtHelper();
  accessToken: string;
  isAdmin: boolean;

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string) {

    console.log("log in!");

    const body = `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}&grant_type=password`;

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Basic ' + btoa(TOKEN_AUTH_USERNAME + ':' + TOKEN_AUTH_PASSWORD),
        'withCredentials': 'true'
      })
    };

    this.http.post(LoginService.AUTH_TOKEN, body, httpOptions).subscribe(res => {
        console.log(res);
      },
      error => {
        console.log('error');
      });
  }

  refreshAccessToken() : Observable<{}> {

    console.log("refreshing!");

    const body = `grant_type=refresh_token`;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Basic ' + btoa(TOKEN_AUTH_USERNAME + ':' + TOKEN_AUTH_PASSWORD),
        'withCredentials': 'true'
      })
    };

    return this.http.post(LoginService.AUTH_TOKEN, body, httpOptions);
  }

  processToken(accessToken: string) {
    console.log('access token received');
    const decodedToken = this.jwtHelper.decodeToken(accessToken);
    console.log(decodedToken);

    this.isAdmin = decodedToken.authorities.some(el => el === 'ADMIN_USER');
    this.accessToken = accessToken;

    localStorage.setItem(TOKEN_NAME, accessToken);
  }

  logout() {
    this.accessToken = null;
    this.isAdmin = false;
    localStorage.removeItem(TOKEN_NAME);
  }

  isAdminUser(): boolean {
    return this.isAdmin;
  }

  isUser(): boolean {
    return this.accessToken && !this.isAdmin;
  }
}
