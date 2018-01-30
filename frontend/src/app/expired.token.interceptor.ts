import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import 'rxjs/add/observable/throw'
import 'rxjs/add/operator/catch';
import {Observable} from "rxjs/Observable";

export class ExpiredTokenInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).catch((error, caught) => {
      // intercept the response error and displace it to the console
      console.log("Error Occurred");
      console.log(error.error.error);
      // return the error to the method that called it
      return Observable.throw(error);
    }) as any;
  }
}
