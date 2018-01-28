import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class ProductService {

  constructor(private http: HttpClient) {
  }

  findForMe() {
    return this.http.get('/forms/api/definition/payment', httpOptions);
  }

  findProduct(productId: number) {
    return this.http.get('/forms/api/definition/payment' + productId, httpOptions);
  }
}
