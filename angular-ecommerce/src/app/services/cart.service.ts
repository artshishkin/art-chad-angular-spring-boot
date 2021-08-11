import {Injectable} from '@angular/core';
import {CartItem} from "../common/cart-item";
import {Subject} from "rxjs";
import {CartStatusDto} from "../common/cart-status-dto";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private cart: Map<number, CartItem> = new Map<number, CartItem>();

  cartStatusSubject: Subject<CartStatusDto> = new Subject<CartStatusDto>();
  cartItemsSubject: Subject<CartItem[]> = new Subject<CartItem[]>();

  constructor() {
  }

  addToCart(cartItem: CartItem) {
    if (!this.cart.has(cartItem.id)) {
      this.cart.set(cartItem.id, cartItem);
      this.updateCartItemsSubject();
    } else {
      let existingItem = this.cart.get(cartItem.id)!;
      existingItem.quantity++;
    }

    this.computeCartTotals();
  }

  computeCartTotals() {

    let totalPriceValue: number = 0;
    let totalQuantityValue: number = 0;

    for (let item of this.cart.values()) {
      totalPriceValue += item.quantity * item.unitPrice;
      totalQuantityValue += item.quantity;
    }
    console.log(`Total price: ${totalPriceValue.toFixed(2)}, total quantity: ${totalQuantityValue}`);

    this.cartStatusSubject.next({totalPrice: totalPriceValue, totalQuantity: totalQuantityValue})
  }

  private getCartItems(): CartItem[] {
    return Array.from(this.cart.values());
  }

  decrementQuantity(cartItem: CartItem) {

    cartItem.quantity--;

    if (cartItem.quantity <= 0)
      this.remove(cartItem);
    else
      this.computeCartTotals();
  }

  remove(cartItem: CartItem) {
    this.cart.delete(cartItem.id);
    this.updateCartItemsSubject();
    this.computeCartTotals();
  }

  updateCartItemsSubject() {
    this.cartItemsSubject.next(this.getCartItems());
  }
}
