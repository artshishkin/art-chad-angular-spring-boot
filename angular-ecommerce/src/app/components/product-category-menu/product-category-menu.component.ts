import {Component, OnInit} from '@angular/core';
import {ProductCategory} from "../../common/product-category";
import {ProductService} from "../../services/product.service";

@Component({
  selector: 'app-product-category-menu',
  templateUrl: './product-category-menu.component.html',
  styleUrls: ['./product-category-menu.component.css']
})
export class ProductCategoryMenuComponent implements OnInit {

  productCategories: ProductCategory[];

  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {
    this.fetchProductCategories();
  }

  private fetchProductCategories() {

    //fetch data from backend -> use productService like we did with productService
//    this.productService.getProductList(this.currentCategoryId).subscribe(data => this.products = data);
  }

}
