import {Component, OnInit} from '@angular/core';
import {HeroService} from "./hero.service";
import {Hero} from "./hero";

@Component({
    moduleId: module.id, // this somehow inserts the 'app' context into the url resolution for the template url
    selector: 'my-dashboard',
    templateUrl: 'dashboard.component.html',
    styleUrls: [ 'dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {

    heroes: Hero[] = [];

    constructor(private heroService: HeroService) { }

    ngOnInit(): void {
        this.heroService.getHeroes()
            .then(heroes => this.heroes = heroes.slice(0, 4));
    }
}
