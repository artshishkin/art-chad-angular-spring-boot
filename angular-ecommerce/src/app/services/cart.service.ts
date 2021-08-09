import {Injectable} from '@angular/core';
import {CartItem} from "../common/cart-item";
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private cart: Map<number, CartItem> = new Map<number, CartItem>();
  totalPrice: Subject<number> = new Subject<number>();
  totalQuantity: Subject<number> = new Subject<number>();

  constructor() {
  }

  addToCart(cartItem: CartItem) {
    if (!this.cart.has(cartItem.id)) {
      this.cart.set(cartItem.id, cartItem);
    } else {
      let existingItem = this.cart.get(cartItem.id)!;
      existingItem.quantity++;
    }

    this.computeCartTotals();
  }

  private computeCartTotals() {

    let totalPriceValue: number = 0;
    let totalQuantityValue: number = 0;

    for (let item of this.cart.values()) {
      totalPriceValue += item.quantity * item.unitPrice;
      totalQuantityValue += item.quantity;
    }
    console.log(`Total price: ${totalPriceValue.toFixed(2)}, total quantity: ${totalQuantityValue}`);

    this.totalPrice.next(totalPriceValue);
    this.totalQuantity.next(totalQuantityValue);
  }
}
