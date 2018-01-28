import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {ProductDetailComponent} from './components/product-detail.component';
import {ProductOverviewComponent} from './components/product-overview.component';

const productRoutes: Routes = [
  {path: 'products', component: ProductOverviewComponent},
  {path: 'product/:id', component: ProductDetailComponent}
];

@NgModule({
  imports: [
    RouterModule.forChild(productRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class ProductRoutingModule {}
