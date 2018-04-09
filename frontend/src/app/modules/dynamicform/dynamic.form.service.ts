import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ServiceCallWrapper} from "../../service.call.wrapper";
import {Observable} from "rxjs/Observable";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class DynamicFormService {


  constructor(private http: HttpClient, private serviceCallWrapper: ServiceCallWrapper) {

  }

  findForMe() : Observable<{}> {
    return this.http.get('/forms/api/definition/payment', httpOptions);
  }

  findProduct(formUniqueName: string) {
    return this.http.get('/forms/api/definition/' + formUniqueName, httpOptions);
  }
}
