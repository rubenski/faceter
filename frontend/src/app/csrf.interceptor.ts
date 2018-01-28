import {
  HttpHandler, HttpHeaderResponse, HttpInterceptor, HttpProgressEvent, HttpRequest, HttpResponse,
  HttpSentEvent, HttpUserEvent
} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {CookieService} from 'ngx-cookie-service';
import {Injectable} from '@angular/core';

@Injectable()
export class CsrfInterceptor implements HttpInterceptor {

  constructor(private cookieService: CookieService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpSentEvent | HttpHeaderResponse |
    HttpProgressEvent | HttpResponse<any> | HttpUserEvent<any>> {
    const csrfToken = this.cookieService.get('X-CSRF-TOKEN');
    console.log('test');
    console.log(csrfToken);
    const authReq = req.clone({headers: req.headers.set('CSRF-TOKEN', csrfToken)});
    return next.handle(authReq);
  }

}
