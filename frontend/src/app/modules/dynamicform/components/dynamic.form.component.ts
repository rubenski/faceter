import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ParamMap} from '@angular/router';
import 'rxjs/add/operator/switchMap';
import {Observable} from 'rxjs/Observable';
import {DynamicFormService} from '../dynamic.form.service';

@Component({
  moduleId: module.id,
  templateUrl: './dynamic.form.component.html',
  styleUrls: ['./dynamic.form.component.css']
})
export class DynamicFormComponent implements OnInit {

  private form$: Observable<{}>;
  private selectedId: number;

  ngOnInit(): void {
    this.route.params.subscribe( params => {
      console.log(params);
      console.log('dyn forms');
      this.form$ = this.dynamicFormService.findForMe();
    });
  }

  constructor(private dynamicFormService: DynamicFormService,
              private route: ActivatedRoute) {
  }

}
