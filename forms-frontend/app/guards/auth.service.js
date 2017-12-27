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
const config_1 = require("../config");
let AuthService = class AuthService {
    constructor(http, config) {
        this.http = http;
        this.config = config;
        this.formsUrl = 'forms/api/config/';
        this.headers = new http_1.RequestOptions({ headers: new http_1.Headers({ "Content-Type": "application/json" }) });
    }
    authenticate(itemId) {
        return this.http.get(this.formsUrl + itemId, this.headers)
            .toPromise()
            .then(response => response.json())
            .catch(this.handleError);
    }
    handleError(error) {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }
    isLoggedIn() {
        return true;
    }
};
AuthService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http, config_1.default])
], AuthService);
exports.AuthService = AuthService;
//# sourceMappingURL=auth.service.js.map