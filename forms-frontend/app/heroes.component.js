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
const hero_service_1 = require("./hero.service");
const router_1 = require("@angular/router");
let HeroesComponent = class HeroesComponent {
    constructor(router, heroService) {
        this.router = router;
        this.heroService = heroService;
    }
    getHeroes() {
        this.heroService.getHeroes().then(heroes => this.heroes = heroes);
    }
    ngOnInit() {
        this.getHeroes();
    }
    onSelect(hero) {
        this.selectedHero = hero;
    }
    gotoDetail() {
        this.router.navigate(['/detail', this.selectedHero.id]);
    }
    add(name) {
        name = name.trim();
        if (!name) {
            return;
        }
        this.heroService.create(name)
            .then(hero => {
            this.heroes.push(hero);
            this.selectedHero = null;
        });
    }
    delete(hero) {
        this.heroService
            .delete(hero.id)
            .then(() => {
            this.heroes = this.heroes.filter(h => h !== hero);
            if (this.selectedHero === hero) {
                this.selectedHero = null;
            }
        });
    }
};
HeroesComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        selector: 'my-heroes',
        templateUrl: 'heroes.component.html',
        styleUrls: ['heroes.component.css'],
        providers: [hero_service_1.HeroService]
    }),
    __metadata("design:paramtypes", [router_1.Router,
        hero_service_1.HeroService])
], HeroesComponent);
exports.HeroesComponent = HeroesComponent;
//# sourceMappingURL=heroes.component.js.map