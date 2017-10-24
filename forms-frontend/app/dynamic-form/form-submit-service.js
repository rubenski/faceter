"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};
Object.defineProperty(exports, "__esModule", { value: true });
const core_1 = require("@angular/core");
const http_1 = require("@angular/http");
require("rxjs/add/operator/map");
let FormSubmitService = class FormSubmitService {
    constructor(http) {
        this.http = http;
    }
    submitForm(form) {
        var headers = new http_1.Headers();
        headers.append("Content-Type", "application/json");
        alert("pop");
        this.http.put("http://localhost:883/forms/api/entity", JSON.stringify(form), {
            headers: headers
        }).map((res) => res.json()).subscribe(data => console.log(data), err => this.handleError(err), () => console.log('Authentication Complete'));
    }
    handleError(error) {
        console.log("err: " + error);
    }
    createUrl() {
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
};
FormSubmitService = __decorate([
    core_1.Injectable(),
    __param(0, core_1.Inject(http_1.Http)),
    __metadata("design:paramtypes", [http_1.Http])
], FormSubmitService);
exports.default = FormSubmitService;
//# sourceMappingURL=form-submit-service.js.map