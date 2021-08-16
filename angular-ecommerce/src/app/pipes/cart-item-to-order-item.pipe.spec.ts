import {CartItemToOrderItemPipe} from './cart-item-to-order-item.pipe';

describe('CartItemToOrderItemPipe', () => {
  it('create an instance', () => {
    const pipe = new CartItemToOrderItemPipe();
    expect(pipe).toBeTruthy();
  });
});
