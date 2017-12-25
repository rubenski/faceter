import {Injectable} from "@angular/core";
import {Headers, Http, RequestOptions} from '@angular/http';
import {ItemForm} from "../itemform/item-form";
import Config from "../config";

@Injectable()
export class AuthService {

    private formsUrl = 'forms/api/config/';
    private headers = new RequestOptions({headers: new Headers({"Content-Type": "application/json"})});

    constructor(private http: Http, private config: Config) {
    }

    authenticate(itemId: string): Promise<ItemForm> {
        return this.http.get(this.formsUrl + itemId, this.headers)
            .toPromise()
            .then(response => response.json())
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }

    isLoggedIn() {
        return true;
    }
}