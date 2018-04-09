import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import 'rxjs/add/observable/throw'
import 'rxjs/add/operator/catch';
import {Observable} from "rxjs/Observable";
import {Injectable, Injector} from "@angular/core";
import {LoginService} from "./modules/login/login.service";
import {Router} from "@angular/router";
import * as Rx from 'rxjs/Rx';


@Injectable()
export class ErrorInterceptor implements HttpInterceptor {


  constructor(private injector: Injector, private router: Router) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(req).catch((error) => {

      if (error.status === 401) {
        if (error.url.includes("token")) {
          this.router.navigate(['login']);
        } else {
          console.log("Refresh token in interceptor");
          let loginService = this.injector.get(LoginService);
          loginService.refreshAccessToken().subscribe(res => {
            console.log("returned");
          });
        }
      }

      return Rx.Observable.empty();
    }) as any;
  }
}
