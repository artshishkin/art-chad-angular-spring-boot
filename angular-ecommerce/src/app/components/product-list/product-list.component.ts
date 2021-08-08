import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../services/product.service";
import {Product} from "../../common/product";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list-grid.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];
  currentCategoryId: number;
  currentCategoryName: string;

  private DEFAULT_CATEGORY_ID: number = 1;
  private DEFAULT_CATEGORY_NAME: string = 'Books';

  constructor(private productService: ProductService,
              private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.listProducts();
    });
  }

  private listProducts() {

    const hasCategoryId = this.route.snapshot.paramMap.has('id');

    if (hasCategoryId) {
      const categoryId = this.route.snapshot.paramMap.get('id');
      this.currentCategoryId = (categoryId != null) ? +categoryId : this.DEFAULT_CATEGORY_ID;

      const categoryName = this.route.snapshot.paramMap.get('name');
      this.currentCategoryName = categoryName != null ? categoryName : this.DEFAULT_CATEGORY_NAME;

    } else {

      this.currentCategoryId = this.DEFAULT_CATEGORY_ID;
      this.currentCategoryName = this.DEFAULT_CATEGORY_NAME;

    }

    this.productService.getProductList(this.currentCategoryId).subscribe(data => this.products = data);
  }
}
