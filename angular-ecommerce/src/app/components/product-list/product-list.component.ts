import {Component, OnInit} from '@angular/core';
import {ProductService, ProductsGetResponse} from "../../services/product.service";
import {Product} from "../../common/product";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list-grid.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  private DEFAULT_CATEGORY_ID: number = 1;
  private DEFAULT_CATEGORY_NAME: string = 'Books';

  products: Product[] = [];
  currentCategoryId: number = this.DEFAULT_CATEGORY_ID;
  private previousCategoryId: number = this.DEFAULT_CATEGORY_ID;

  currentCategoryName: string = this.DEFAULT_CATEGORY_NAME;
  keyword: string | null;

  // new properties for pagination
  pageNumber: number = 1;
  pageSize: number = 10;
  totalElements: number = 0;

  constructor(private productService: ProductService,
              private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.listProducts();
    });
  }

  listProducts() {

    const isSearchByKeyword: boolean = this.route.snapshot.paramMap.has('keyword');

    if (isSearchByKeyword) {
      this.handleSearchProducts();
    } else {
      this.handleListProducts();
    }

  }

  private handleListProducts(): void {

    console.log("handleListProducts()");
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

    //
    // Check if we have a different category than previous
    // Note: Angular will reuse a component if it is currently being used
    //

    // if we have a different category id than previous
    // then set pageNumber back to 1

    if (this.previousCategoryId != this.currentCategoryId) {
      this.pageNumber = 1;

    }
    this.previousCategoryId = this.currentCategoryId;

    console.log(`currentCategoryId = ${this.currentCategoryId}, page = ${this.pageNumber}`);

    this.productService
      .getProductListPaginate(this.currentCategoryId, this.pageNumber - 1, this.pageSize)
      .subscribe(this.processResult());
  }

  private processResult() {
    return (data: ProductsGetResponse) => {
      this.products = data._embedded.products;
      this.pageNumber = data.page.number + 1;
      this.pageSize = data.page.size;
      this.totalElements = data.page.totalElements;
    };
  }

  private handleSearchProducts(): void {
    this.keyword = this.route.snapshot.paramMap.get('keyword');
    this.productService.searchProducts(this.keyword).subscribe(data => this.products = data);
  }

  updatePageSize(pageSize: number) {
    this.pageNumber = 1;
    this.pageSize = pageSize;
    this.listProducts();
  }
}
