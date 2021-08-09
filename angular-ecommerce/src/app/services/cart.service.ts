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
    for (let item of this.cart.values()) {
      console.log(`${item.toString()}`);
    }
  }
}
