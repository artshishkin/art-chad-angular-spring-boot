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

  constructor(private productService: ProductService,
              private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.listProducts();
    });
  }

  private listProducts() {

    const categoryId = this.route.snapshot.paramMap.get('id');
    this.currentCategoryId = (categoryId != null) ? +categoryId : 1;

    this.productService.getProductList(this.currentCategoryId).subscribe(data => this.products = data);
  }
}
