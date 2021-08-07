import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../common/product";
import {map} from "rxjs/operators";
import {environment} from "../../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = `${environment.backendServerUrl}/api/products`;

  constructor(private httpClient: HttpClient) {
  }

  getProductList(categoryId: number): Observable<Product[]> {

    // need to build URL based on category id
    const searchUrl = `${this.baseUrl}/search/findByCategoryId?id=${categoryId}`;

    return this.httpClient
      .get<GetResponse>(searchUrl)
      .pipe(map(response => response._embedded.products));
  }
}

interface GetResponse {
  _embedded: {
    products: Product[];
  }
}
