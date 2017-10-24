import {Http} from "@angular/http";
import SysProperties from "../app.properties";
import {Inject} from "@angular/core";

export class AppPropertiesService
{
    http: Http;
    props: Object;
    private sysProperties:SysProperties;

    constructor(@Inject(Http) http:Http, @Inject(SysProperties) sysProperties:SysProperties) {
        this.http = http;
        this.sysProperties = sysProperties;
    }

    getProperty(name: string){
        let prop = this.sysProperties[name];
        return prop;
    }

    setProperties(properties:Object){
        this.props = properties;
    }
}


