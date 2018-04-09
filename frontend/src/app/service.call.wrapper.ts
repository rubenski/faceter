import 'rxjs/add/operator/catch';
import {Observable} from "rxjs/Observable";
import {LoginService} from "./modules/login/login.service";
import {Injectable} from "@angular/core";
import {HttpResponse} from "@angular/common/http";

@Injectable()
export class ServiceCallWrapper {

  constructor(private loginService: LoginService) {
  }

  callWithAutoTokenRefresh(o: Observable<{}>): Observable<{}> {

    return o.map(result => {
      return Observable.of(result);
    })
      .catch(error => {
        if (error.status == 401) {
          console.log("Received 401");
          this.loginService.refreshAccessToken().subscribe(result => {
            return o;
          });
        }

        throw new Error("Got 401 and could not refresh token");
      });
  }
}
