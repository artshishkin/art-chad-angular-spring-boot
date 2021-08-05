import {SalesPerson} from './sales-person';

describe('SalesPerson', () => {
  it('should create an instance', () => {
    expect(new SalesPerson('Art', 'Shyshkin', 'd.art.shishkin@gmail.com', 123.45)).toBeTruthy();
  });
});
