import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import 'rxjs/add/operator/switchMap';
import {DynamicFormService} from '../dynamic.form.service';
import 'rxjs/add/observable/interval'
import 'rxjs/add/observable/of'
import 'rxjs/add/operator/retry';
import 'rxjs/add/operator/map';
import {LoginService} from "../../login/login.service";

@Component({
  moduleId: module.id,
  templateUrl: './dynamic.form.component.html',
  styleUrls: ['./dynamic.form.component.css']
})
export class DynamicFormComponent implements OnInit {

  private form$: Object;
  private selectedId: number;

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      console.log(params);
      console.log('dyn forms');

      let source2 = this.dynamicFormService.findForMe().catch((error) => {
        console.log("my error " + error.status);
        if (error.status == 401) {
          console.log("refresh access token")
          this.loginService.refreshAccessToken().subscribe((result) => {
            console.log("refresh result: " + result);

          }, (err) => console.log(err));
        }
        return null;
      }).retry(1);

      source2.subscribe(
        value => this.form$ = value,
        error => console.log("error occurred: " + error)
      );
    });
  }

  constructor(private dynamicFormService: DynamicFormService,
              private route: ActivatedRoute,
              private loginService: LoginService) {

  }
}
