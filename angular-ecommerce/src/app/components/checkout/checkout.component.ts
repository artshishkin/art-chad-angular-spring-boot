import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {CartStatusDto} from "../../common/cart-status-dto";
import {CartService} from "../../services/cart.service";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  checkoutFormGroup: FormGroup;
  cartTotals: CartStatusDto;

  constructor(private formBuilder: FormBuilder,
              private cartService: CartService) {
  }

  ngOnInit(): void {

    this.cartService.cartStatusSubject.asObservable()
      .subscribe(data => this.cartTotals = data);
    this.cartService.computeCartTotals();

    let customer: FormGroup = this.formBuilder.group({
      firstName: [''],
      lastName: [''],
      email: ['']
    });

    let shippingAddress: FormGroup = this.formBuilder.group({
      country: [''],
      street: [''],
      city: [''],
      state: [''],
      zipCode: ['']
    });

    let billingAddress: FormGroup = this.formBuilder.group({
      country: [''],
      street: [''],
      city: [''],
      state: [''],
      zipCode: ['']
    });

    let creditCard: FormGroup = this.formBuilder.group({
      cardType: [''],
      nameOnCard: [''],
      cardNumber: [''],
      securityCode: [''],
      expirationMonth: [''],
      expirationYear: ['']
    });

    this.checkoutFormGroup = this.formBuilder.group({
      customer,
      shippingAddress,
      billingAddress,
      creditCard
    });

  }

  onSubmit() {
    console.log('Customer purchased order');
    console.log(this.checkoutFormGroup.get('customer')?.value);
    console.log(`Email address is ${this.checkoutFormGroup.get('customer')?.value.email}`);
    console.log(this.checkoutFormGroup.get('shippingAddress')?.value);
    console.log(this.checkoutFormGroup.get('creditCard')?.value);
  }

  copyShippingAddressToBillingAddress(event: MouseEvent) {
    if ((<HTMLInputElement>event.target)?.checked) {
      this.checkoutFormGroup.controls.billingAddress
        .setValue(this.checkoutFormGroup.controls.shippingAddress.value);
      console.log(``)
    } else {
      this.checkoutFormGroup.controls.billingAddress.reset();
    }

  }
}