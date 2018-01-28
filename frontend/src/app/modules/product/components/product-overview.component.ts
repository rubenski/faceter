import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import 'rxjs/add/operator/switchMap';
import {Observable} from 'rxjs/Observable';
import {ProductService} from '../product.service';

@Component({
  moduleId: module.id,
  templateUrl: './product-overview.component.html',
  styleUrls: ['./product-overview.component.css']
})
export class ProductOverviewComponent implements OnInit {

  private products$: Observable<{}>;
  private selectedId: number;


  ngOnInit(): void {
    this.route.params.subscribe(params => {
      console.log(params);
      this.selectedId = Number(params.get('id'));
      this.service.findForMe();
    });
  }

  constructor(private service: ProductService,
              private route: ActivatedRoute) {
  }
}
