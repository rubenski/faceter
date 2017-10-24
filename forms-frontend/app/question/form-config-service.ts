

import {Inject} from "@angular/core";
import {AppPropertiesService} from "../dynamic-form/app-properties-service";
import {Http, Response} from "@angular/http";


export default class FormConfigService {


    constructor(private appPropertiesService:AppPropertiesService, private http:Http) {}

    public findFormConfig(id:string) {
        return this.http.get(this.createUrl(id)).map((res:Response) => res.json());
    }

    private createUrl(id:string){
        var apiContext = this.appPropertiesService.getProperty("forms-api-context");
        var host = this.appPropertiesService.getProperty("host");
        var port = this.appPropertiesService.getProperty("port");
        var protocol = this.appPropertiesService.getProperty("protocol");
        console.log("api context: " + apiContext);
        var url = protocol + "://" + host + ":" + port + apiContext + "config/" + id;
        console.log("config url: " + url);
        return url;
    }


}