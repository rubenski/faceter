import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs/Observable";

@Component({
  moduleId: module.id,
  selector: 'app-hero-form',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  ngOnInit(): void {
    console.log("home!")

  }

}
