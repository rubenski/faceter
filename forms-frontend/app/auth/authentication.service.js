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
Object.defineProperty(exports, "__esModule", { value: true });
const core_1 = require("@angular/core");
const http_1 = require("@angular/http");
require("rxjs/add/operator/map");
const ngx_cookie_service_1 = require("ngx-cookie-service");
const auth_constant_1 = require("./auth.constant");
let AuthenticationService = AuthenticationService_1 = class AuthenticationService {
    constructor(http, cookieService) {
        this.http = http;
        this.cookieService = cookieService;
    }
    login(username, password) {
        const body = `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}&grant_type=password`;
        const headers = new http_1.Headers();
        headers.append('Content-Type', 'application/x-www-form-urlencoded');
        headers.append('Authorization', 'Basic ' + btoa(auth_constant_1.TOKEN_AUTH_USERNAME + ':' + auth_constant_1.TOKEN_AUTH_PASSWORD));
        return this.http.post(AuthenticationService_1.AUTH_TOKEN, body, { headers, withCredentials: true })
            .map((res) => {
            let contentLengthHeader = res.headers.get('Content-Length');
            let setCookieHeader = res.headers.get('set-cookie');
            if (res.json().access_token) {
                this.cookieService.set('test', 'Hello World');
                return {
                    token: res.json().access_token,
                    csrf: res.headers
                };
            }
            return null;
        });
    }
};
AuthenticationService.AUTH_TOKEN = '/logssssin/oauth/token';
AuthenticationService = AuthenticationService_1 = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http, ngx_cookie_service_1.CookieService])
], AuthenticationService);
exports.AuthenticationService = AuthenticationService;
var AuthenticationService_1;
//# sourceMappingURL=authentication.service.js.map