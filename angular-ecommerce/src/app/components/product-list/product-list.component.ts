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

  products: Product[] = [];
  currentCategoryId: number | null;
  private previousCategoryId: null | number;

  currentCategoryName: string | null;
  keyword: string;
  private previousKeyword: string;


  // new properties for pagination
  pageNumber: number = 1;
  pageSize: number = 5;
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

      this.currentCategoryId = +(this.route.snapshot.paramMap.get('id'))!;
      this.currentCategoryName = this.route.snapshot.paramMap.get('name');

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
    this.keyword = this.route.snapshot.paramMap.get('keyword')!;
    if (this.previousKeyword != this.keyword) {
      this.pageNumber = 1;
    }
    this.previousKeyword = this.keyword;

    this.productService
      .searchProductsPaginate(this.keyword, this.pageNumber - 1, this.pageSize)
      .subscribe(this.processResult());
  }

  updatePageSize(pageSize: number) {
    const firstProductOnPageIdx: number = (this.pageNumber - 1) * this.pageSize;
    this.pageNumber = Math.floor(firstProductOnPageIdx / pageSize) + 1;
    this.pageSize = pageSize;

    this.listProducts();
  }
}
