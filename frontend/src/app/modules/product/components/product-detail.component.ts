import {Component, HostBinding, OnInit} from '@angular/core';
import {Router, ActivatedRoute, ParamMap} from '@angular/router';
import 'rxjs/add/operator/switchMap';
import {ProductService} from '../product.service';
import {Product} from '../product';
import {slideInDownAnimation} from '../../../animations';



@Component({
  moduleId: module.id,
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css'],
  animations: [slideInDownAnimation]
})
export class ProductDetailComponent implements OnInit {

  @HostBinding('@routeAnimation') routeAnimation = true;
  @HostBinding('style.display')   display = 'block';
  @HostBinding('style.position')  position = 'absolute';

  private product$ = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: ProductService
  ) {}

  ngOnInit() {
    // In this example, you retrieve the route parameter map from an Observable. That implies that the route parameter
    // map can change during the lifetime of this component. They might. By default, the router re-uses a component
    // instance when it re-navigates to the same component type without visiting a different component first. The
    // route parameters could change each time. Suppose a parent component navigation bar had "forward" and "back"
    // buttons that scrolled through the list of heroes. Each click navigated imperatively to the HeroDetailComponent
    // with the next or previous id. You don't want the router to remove the current HeroDetailComponent instance from
    // the DOM only to re-create it for the next id. That could be visibly jarring. Better to simply re-use the same
    // component instance and update the parameter. Unfortunately, ngOnInit is only called once per component
    // instantiation. You need a way to detect when the route parameters change from within the same instance. The
    // observable paramMap property handles that beautifully.
    //
    // https://angular.io/guide/router#setting-the-route-parameters-in-the-list-view
    //
    this.product$ = this.route.paramMap
      .switchMap((params: ParamMap) =>
        this.service.findProduct(Number(params.get('id'))));
  }

  gotoProducts(product: Product) {
    this.router.navigate(['/products', { id: product.id, foo: 'foo' }]);
  }
}
