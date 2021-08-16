import {Pipe, PipeTransform} from '@angular/core';
import {CartItem} from "../common/cart-item";
import {OrderItem} from "../common/dto/order-item";

@Pipe({
  name: 'cartItemToOrderItem'
})
export class CartItemToOrderItemPipe implements PipeTransform {

  transform(cartItem: CartItem, ...args: unknown[]): OrderItem {
    const orderItem = new OrderItem();
    orderItem.imageUrl = cartItem.imageUrl;
    orderItem.quantity = cartItem.quantity;
    orderItem.unitPrice = cartItem.unitPrice;
    orderItem.productId = cartItem.id;
    return orderItem;
  }

}
