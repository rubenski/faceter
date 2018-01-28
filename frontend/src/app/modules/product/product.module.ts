import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';

import {ProductDetailComponent} from './components/product-detail.component';
import {ProductOverviewComponent} from './components/product-overview.component';
import {ProductService} from './product.service';
import {ProductRoutingModule} from './product-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ProductRoutingModule
  ],
  declarations: [
    ProductDetailComponent,
    ProductOverviewComponent
  ],
  providers: [ProductService]
})
export class ProductModule {
}
