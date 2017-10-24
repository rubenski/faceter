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
            not_found_component_1.PageNotFoundComponent
        ],
        providers: [hero_service_1.HeroService],
        bootstrap: [app_component_1.AppComponent]
    })
], AppModule);
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map