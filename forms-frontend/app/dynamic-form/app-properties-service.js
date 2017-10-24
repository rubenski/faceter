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
const http_1 = require("@angular/http");
const app_properties_1 = require("../app.properties");
const core_1 = require("@angular/core");
let AppPropertiesService = class AppPropertiesService {
    constructor(http, sysProperties) {
        this.http = http;
        this.sysProperties = sysProperties;
    }
    getProperty(name) {
        let prop = this.sysProperties[name];
        return prop;
    }
    setProperties(properties) {
        this.props = properties;
    }
};
AppPropertiesService = __decorate([
    __param(0, core_1.Inject(http_1.Http)), __param(1, core_1.Inject(app_properties_1.default)),
    __metadata("design:paramtypes", [http_1.Http, app_properties_1.default])
], AppPropertiesService);
exports.AppPropertiesService = AppPropertiesService;
//# sourceMappingURL=app-properties-service.js.map