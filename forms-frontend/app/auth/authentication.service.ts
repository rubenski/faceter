import {Injectable} from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';
import { CookieService } from 'ngx-cookie-service';

import {TOKEN_AUTH_PASSWORD, TOKEN_AUTH_USERNAME} from './auth.constant';

@Injectable()
export class AuthenticationService {
    static AUTH_TOKEN = '/logssssin/oauth/token';

    constructor(private http: Http, private cookieService: CookieService) {
    }

    login(username: string, password: string) {
        const body = `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}&grant_type=password`;

        const headers = new Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        headers.append('Authorization', 'Basic ' + btoa(TOKEN_AUTH_USERNAME + ':' + TOKEN_AUTH_PASSWORD));


        return this.http.post(AuthenticationService.AUTH_TOKEN, body, {headers, withCredentials : true})
            .map((res: any) => {

                let contentLengthHeader = res.headers.get('Content-Length');
                let setCookieHeader = res.headers.get('set-cookie');

                if (res.json().access_token) {

                    this.cookieService.set( 'test', 'Hello World' );

                    return {
                        token: res.json().access_token,
                        csrf: res.headers
                    };
                }
                return null;
            });
    }
}
