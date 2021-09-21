import {CartItem} from './cart-item';
import {Product} from "./product";

describe('CartItem', () => {
  it('should create an instance', () => {
    const product = new Product();
    product.id = 123;
    product.name = 'Ford';
    product.unitPrice = 12500;
    product.imageUrl = "http://localhost:4200/product/123/image";
    expect(new CartItem(product)).toBeTruthy();
  });
});
