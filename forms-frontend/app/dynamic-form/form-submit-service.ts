

import {Injectable, Inject} from "@angular/core";
import {Http, Headers, Response} from "@angular/http";
import 'rxjs/add/operator/map'

@Injectable()
export default class FormSubmitService {

    private http:any;

    constructor(@Inject(Http) http:Http) {
        this.http = http;
    }

    public submitForm(form) {

        var headers = new Headers();
        headers.append("Content-Type", "application/json");
        alert("pop");

        this.http.put("http://localhost:883/forms/api/entity", JSON.stringify(form), {
            headers: headers
        }).map((res:Response) => res.json()).subscribe(
            data => console.log(data),
            err => this.handleError(err),
            () => console.log('Authentication Complete')
        );
    }

    private handleError(error){
        console.log("err: " + error);
    }

    private createUrl(){

        //var apiContext = this.appPropertiesService.getProperty("forms-api-context");
        //var host = this.appPropertiesService.getProperty("host");
        //var port = this.appPropertiesService.getProperty("port");
        //var protocol = this.appPropertiesService.getProperty("protocol");
        //console.log("api context: " + apiContext);
        //var url = protocol + "://" + host + ":" + port + apiContext + "config/" + id;
        //console.log("config url: " + url);
        //return url;

        return "";
    }


}