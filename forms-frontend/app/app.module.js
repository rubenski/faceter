"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
const core_1 = require("@angular/core");
const platform_browser_1 = require("@angular/platform-browser");
const forms_1 = require("@angular/forms");
const http_1 = require("@angular/http");
const app_routing_module_1 = require("./app-routing.module");
const app_component_1 = require("./app.component");
const dashboard_component_1 = require("./dashboard.component");
const heroes_component_1 = require("./heroes.component");
const hero_detail_component_1 = require("./hero-detail.component");
const hero_service_1 = require("./hero.service");
const dynamic_form_question_component_1 = require("./dynamic-form/dynamic-form-question.component");
const dynamic_form_component_1 = require("./dynamic-form/dynamic-form.component");
const product_component_1 = require("./products/product.component");
const itemform_component_1 = require("./itemform/itemform.component");
const not_found_component_1 = require("./not-found.component");
const angular2_jwt_1 = require("angular2-jwt");
const auth_constant_1 = require("./auth/auth.constant");
const login_component_1 = require("./login.component");
const authentication_service_1 = require("./auth/authentication.service");
const user_service_1 = require("./auth/user.service");
const ngx_cookie_service_1 = require("ngx-cookie-service");
function authHttpServiceFactory(http) {
    return new angular2_jwt_1.AuthHttp(new angular2_jwt_1.AuthConfig({
        headerPrefix: 'Bearer',
        tokenName: auth_constant_1.TOKEN_NAME,
        globalHeaders: [{ 'Content-Type': 'application/json' }],
        noJwtError: false,
        noTokenScheme: true,
        tokenGetter: (() => localStorage.getItem(auth_constant_1.TOKEN_NAME))
    }), http);
}
exports.authHttpServiceFactory = authHttpServiceFactory;
let AppModule = class AppModule {
};
AppModule = __decorate([
    core_1.NgModule({
        imports: [
            platform_browser_1.BrowserModule,
            forms_1.FormsModule,
            http_1.HttpModule,
            app_routing_module_1.AppRoutingModule,
            forms_1.ReactiveFormsModule
        ],
        declarations: [
            app_component_1.AppComponent,
            dashboard_component_1.DashboardComponent,
            hero_detail_component_1.HeroDetailComponent,
            heroes_component_1.HeroesComponent,
            dynamic_form_component_1.DynamicFormComponent,
            dynamic_form_question_component_1.DynamicFormQuestionComponent,
            product_component_1.ProductComponent,
            itemform_component_1.ItemFormComponent,
            not_found_component_1.PageNotFoundComponent,
            login_component_1.LoginComponent
        ],
        providers: [
            { provide: angular2_jwt_1.AuthHttp, useFactory: authHttpServiceFactory, deps: [http_1.Http] },
            hero_service_1.HeroService,
            authentication_service_1.AuthenticationService,
            user_service_1.UserService,
            ngx_cookie_service_1.CookieService
        ],
        bootstrap: [app_component_1.AppComponent]
    })
], AppModule);
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map