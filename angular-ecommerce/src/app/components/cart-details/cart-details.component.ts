import {Component, OnInit} from '@angular/core';
import {CartService} from "../../services/cart.service";
import {CartItem} from "../../common/cart-item";
import {CartStatusDto} from "../../common/cart-status-dto";

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.css']
})
export class CartDetailsComponent implements OnInit {

  cartItems: CartItem[] = [];
  cartTotals: CartStatusDto;

  constructor(private cartService: CartService) {
  }

  ngOnInit(): void {
    this.listCartDetails();
  }

  private listCartDetails() {
    this.cartItems = this.cartService.getCartItems();
    this.cartService.cartStatusSubject.asObservable().subscribe(data => this.cartTotals = data);
    this.cartService.computeCartTotals();
  }

  incrementQuantity(cartItem: CartItem) {
    this.cartService.addToCart(cartItem);
  }

  decrementQuantity(cartItem: CartItem) {
    this.cartService.decrementQuantity(cartItem);
    this.cartItems = this.cartService.getCartItems();
  }
}
