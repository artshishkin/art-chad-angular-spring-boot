import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {OrderHistory} from "../common/order-history";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class OrderHistoryService {

  private orderUrl = `${environment.backendServerUrl}/orders`;

  constructor(private httpClient: HttpClient) {
  }

  getOrderHistory(email: string): Observable<GetResponseOrderHistory> {
    const orderHistoryUrl = `${this.orderUrl}/search/by-customer-email?email=${email}&sort=dateCreated,DESC`;
    return this.httpClient.get<GetResponseOrderHistory>(orderHistoryUrl);
  }

}

interface GetResponseOrderHistory {
  _embedded: {
    orders: OrderHistory[];
  }
}
