import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Purchase} from "../common/dto/purchase";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  private purchaseUrl = `${environment.backendServerUrl}/api/checkout/purchase`;

  constructor(private httpClient: HttpClient) {
  }

  placeOrder(purchase: Purchase): Observable<any> {
    return this.httpClient.post<Purchase>(this.purchaseUrl, purchase)
  }

}
