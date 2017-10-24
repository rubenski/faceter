"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
const core_1 = require("@angular/core");
const router_1 = require("@angular/router");
const dashboard_component_1 = require("./dashboard.component");
const heroes_component_1 = require("./heroes.component");
const hero_detail_component_1 = require("./hero-detail.component");
const product_component_1 = require("./products/product.component");
const itemform_component_1 = require("./itemform/itemform.component");
const not_found_component_1 = require("./not-found.component");
const routes = [
    { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
    { path: 'dashboard', component: dashboard_component_1.DashboardComponent },
    { path: 'detail/:id', component: hero_detail_component_1.HeroDetailComponent },
    { path: 'heroes', component: heroes_component_1.HeroesComponent },
    { path: 'products', component: product_component_1.ProductComponent },
    { path: 'item/:type', component: itemform_component_1.ItemFormComponent },
    { path: '**', component: not_found_component_1.PageNotFoundComponent }
];
let AppRoutingModule = class AppRoutingModule {
};
AppRoutingModule = __decorate([
    core_1.NgModule({
        imports: [router_1.RouterModule.forRoot(routes)],
        exports: [router_1.RouterModule]
    })
], AppRoutingModule);
exports.AppRoutingModule = AppRoutingModule;
//# sourceMappingURL=app-routing.module.js.map