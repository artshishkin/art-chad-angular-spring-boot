import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  checkoutFormGroup: FormGroup;

  constructor(private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {

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
