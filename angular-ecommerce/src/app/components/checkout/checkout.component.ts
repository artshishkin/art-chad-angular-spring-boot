import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {CartStatusDto} from "../../common/cart-status-dto";
import {CartService} from "../../services/cart.service";
import {MyShopFormService} from "../../services/my-shop-form.service";
import {Country} from "../../common/country";
import {State} from "../../common/state";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  checkoutFormGroup: FormGroup;
  cartTotals: CartStatusDto;

  creditCardMonths: number[];
  creditCardYears: number[];

  countries: Country[] = [];
  states: Map<string, State[]> = new Map<string, State[]>();

  constructor(private formBuilder: FormBuilder,
              private cartService: CartService,
              private myShopFormService: MyShopFormService) {
  }

  ngOnInit(): void {

    this.cartService.cartStatusSubject.asObservable()
      .subscribe(data => this.cartTotals = data);
    this.cartService.computeCartTotals();

    this.populateCreditCardDateArrays();

    this.populateCountries();

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

      const shippingAddressStates = this.states.get('shippingAddress');
      this.states.set('billingAddress', shippingAddressStates ? shippingAddressStates : []);

    } else {
      this.checkoutFormGroup.controls.billingAddress.reset();
      this.states.set('billingAddress', []);
    }

  }

  private populateCreditCardDateArrays() {
    this.myShopFormService.getCreditCardYears().subscribe(data => this.creditCardYears = data);

    const startMonth = new Date().getMonth() + 1;
    this.myShopFormService.getCreditCardMonths(startMonth).subscribe(data => this.creditCardMonths = data);
  }

  handleMonthAndYears() {
    const selectedYear: number = +this.checkoutFormGroup.get("creditCard")?.value.expirationYear;
    const currentYear: number = new Date().getFullYear();
    const currentMonth = new Date().getMonth() + 1;

    const startMonth = (currentYear == selectedYear) ? currentMonth : 1;

    this.myShopFormService.getCreditCardMonths(startMonth).subscribe(data => {
      console.log(`Retrieved months: ${JSON.stringify(data)}`);
      this.creditCardMonths = data;
    });
  }

  private populateCountries() {
    this.myShopFormService.getCountries()
      .subscribe(data => this.countries = data);
  }

  getStates(formGroupName: string) {
    const formGroup = this.checkoutFormGroup.get(formGroupName)!;
    const selectedCountry: Country = formGroup.value.country;
    this.myShopFormService.getStates(selectedCountry.id).subscribe(states => {
      this.states.set(formGroupName, states);
      formGroup.get("state")?.setValue(states[0]);
    });
  }

}
