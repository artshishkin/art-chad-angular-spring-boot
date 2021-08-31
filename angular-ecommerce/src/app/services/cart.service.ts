import {Injectable} from '@angular/core';
import {CartItem} from "../common/cart-item";
import {BehaviorSubject, Subject} from "rxjs";
import {CartTotalsDto} from "../common/cart-totals-dto";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private cart: Map<number, CartItem> = new Map<number, CartItem>();

  cartTotalsSubject: Subject<CartTotalsDto> = new BehaviorSubject<CartTotalsDto>({totalPrice: 0, totalQuantity: 0});
  cartItemsSubject: Subject<CartItem[]> = new BehaviorSubject<CartItem[]>([]);

  storage: Storage = sessionStorage;

  constructor() {

    //read the data from storage
    const storedCartString = this.storage.getItem("storedCart");
    if (storedCartString != null) {
      this.cart = JSON.parse(storedCartString, CartService.reviver);
      this.updateCartItemsSubject();
      this.computeCartTotals();
    }
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

  private computeCartTotals() {

    let totalPriceValue: number = 0;
    let totalQuantityValue: number = 0;

    for (let item of this.cart.values()) {
      totalPriceValue += item.quantity * item.unitPrice;
      totalQuantityValue += item.quantity;
    }
    console.log(`Total price: ${totalPriceValue.toFixed(2)}, total quantity: ${totalQuantityValue}`);

    this.cartTotalsSubject.next({totalPrice: totalPriceValue, totalQuantity: totalQuantityValue})

    this.persistCartItems();
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

  private updateCartItemsSubject() {
    this.cartItemsSubject.next(this.getCartItems());
  }

  resetCart() {
    this.cart.clear();
    this.updateCartItemsSubject();
    this.computeCartTotals();
  }

  private persistCartItems() {
    this.storage.setItem("storedCart", JSON.stringify(this.cart, CartService.replacer));
  }

  private static replacer(key: string, value: any) {
    if (value instanceof Map) {
      return {
        dataType: 'Map',
        value: Array.from(value.entries()), // or with spread: value: [...value]
      };
    } else {
      return value;
    }
  }

  private static reviver(key: string, value: any) {
    if (typeof value === 'object' && value !== null) {
      if (value.dataType === 'Map') {
        return new Map(value.value);
      }
    }
    return value;
  }

}
