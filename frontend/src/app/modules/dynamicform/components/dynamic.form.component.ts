import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ParamMap} from '@angular/router';
import 'rxjs/add/operator/switchMap';
import {DynamicFormService} from '../dynamic.form.service';
import 'rxjs/Rx';

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
      this.dynamicFormService.findForMe().subscribe(
        value => this.form$ = value,
        error => console.log("WHAAAAAA ERROR")
      );
    });
  }

  constructor(private dynamicFormService: DynamicFormService,
              private route: ActivatedRoute) {
  }
}
