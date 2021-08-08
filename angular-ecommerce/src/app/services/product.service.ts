import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../common/product";
import {map} from "rxjs/operators";
import {environment} from "../../environments/environment";
import {ProductCategory} from "../common/product-category";


@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = `${environment.backendServerUrl}/api`;
  private productsUrl = `${this.baseUrl}/products`;
  private categoryUrl = `${this.baseUrl}/product-category`;

  constructor(private httpClient: HttpClient) {
  }

  getProductList(categoryId: number): Observable<Product[]> {

    // need to build URL based on category id
    const searchUrl = `${this.productsUrl}/search/findByCategoryId?id=${categoryId}`;

    return this.fetchProducts(searchUrl);
  }

  searchProducts(keyword: string | null): Observable<Product[]> {

    const searchUrl = `${this.productsUrl}/search/findByNameContaining?name=${keyword}`;

    return this.fetchProducts(searchUrl);
  }

  private fetchProducts(searchUrl: string) {
    return this.httpClient
      .get<ProductsGetResponse>(searchUrl)
      .pipe(map(response => response._embedded.products));
  }

  getProductCategories(): Observable<ProductCategory[]> {
    return this.httpClient
      .get<CategoryGetResponse>(this.categoryUrl)
      .pipe(map(response => response._embedded.productCategory));
  }

  getProduct(productId: number): Observable<Product> {
    throw Error("NOT implemented yet");
  }
}

interface ProductsGetResponse {
  _embedded: {
    products: Product[];
  }
}

interface CategoryGetResponse {
  _embedded: {
    productCategory: ProductCategory[];
  }
}
