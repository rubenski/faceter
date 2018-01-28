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
const angular2_jwt_1 = require("angular2-jwt");
const auth_constant_1 = require("./auth.constant");
let UserService = class UserService {
    constructor() {
        this.jwtHelper = new angular2_jwt_1.JwtHelper();
    }
    login(accessToken) {
        console.log("access token received");
        const decodedToken = this.jwtHelper.decodeToken(accessToken);
        console.log(decodedToken);
        this.isAdmin = decodedToken.authorities.some(el => el === 'ADMIN_USER');
        this.accessToken = accessToken;
        localStorage.setItem(auth_constant_1.TOKEN_NAME, accessToken);
    }
    logout() {
        this.accessToken = null;
        this.isAdmin = false;
        localStorage.removeItem(auth_constant_1.TOKEN_NAME);
    }
    isAdminUser() {
        return this.isAdmin;
    }
    isUser() {
        return this.accessToken && !this.isAdmin;
    }
};
UserService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [])
], UserService);
exports.UserService = UserService;
//# sourceMappingURL=user.service.js.map