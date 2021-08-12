import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {CartTotalsDto} from "../../common/cart-totals-dto";
import {CartService} from "../../services/cart.service";
import {MyShopFormService} from "../../services/my-shop-form.service";
import {Country} from "../../common/country";
import {State} from "../../common/state";
import {MyShopValidators} from "../../validators/my-shop-validators";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  private EMAIL_PATTERN: string = '^[a-zA-Z0-9_.%+-]+@[a-zA-Z0-9-]+\\.[a-z]{2,4}$';

  checkoutFormGroup: FormGroup;
  cartTotals: CartTotalsDto;

  creditCardMonths: number[];
  creditCardYears: number[];

  countries: Country[] = [];
  states: Map<string, State[]> = new Map<string, State[]>();

  constructor(private formBuilder: FormBuilder,
              private cartService: CartService,
              private myShopFormService: MyShopFormService) {
  }

  ngOnInit(): void {

    this.reviewCartDetails();

    this.populateCreditCardDateArrays();

    this.populateCountries();

    this.initCheckoutFormGroup();

  }

  private initCheckoutFormGroup() {
    let customer: FormGroup = this.formBuilder.group({
      firstName: new FormControl('',
        [Validators.required, Validators.minLength(2), MyShopValidators.notOnlyWhitespace]),
      lastName: new FormControl('',
        [Validators.required, Validators.minLength(2), MyShopValidators.notOnlyWhitespace]),
      email: new FormControl('', [Validators.required, Validators.pattern(this.EMAIL_PATTERN)])
    });

    let shippingAddress: FormGroup = this.formBuilder.group({
      country: new FormControl('', [Validators.required]),
      street: new FormControl('',
        [Validators.required, Validators.minLength(2), MyShopValidators.notOnlyWhitespace]),
      city: new FormControl('',
        [Validators.required, Validators.minLength(2), MyShopValidators.notOnlyWhitespace]),
      state: new FormControl('', [Validators.required]),
      zipCode: new FormControl('',
        [Validators.required, Validators.minLength(2), MyShopValidators.notOnlyWhitespace]),
    });

    let billingAddress: FormGroup = this.formBuilder.group({
      country: new FormControl('', [Validators.required]),
      street: new FormControl('',
        [Validators.required, Validators.minLength(2), MyShopValidators.notOnlyWhitespace]),
      city: new FormControl('',
        [Validators.required, Validators.minLength(2), MyShopValidators.notOnlyWhitespace]),
      state: new FormControl('', [Validators.required]),
      zipCode: new FormControl('',
        [Validators.required, Validators.minLength(2), MyShopValidators.notOnlyWhitespace]),
    });

    let creditCard: FormGroup = this.formBuilder.group({
      cardType: new FormControl('', [Validators.required]),
      nameOnCard: new FormControl('',
        [Validators.required, Validators.minLength(2), MyShopValidators.notOnlyWhitespace]),
      cardNumber: new FormControl('', [Validators.required, Validators.pattern('^\\d{16}$')]),
      securityCode: new FormControl('', [Validators.required, Validators.pattern('^\\d{3}$')]),
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

  get firstName() {
    return this.checkoutFormGroup?.get('customer.firstName')!;
  }

  get lastName() {
    return this.checkoutFormGroup?.get('customer.lastName')!;
  }

  get email() {
    return this.checkoutFormGroup?.get('customer.email')!;
  }

  get shippingAddressStreet() {
    return this.checkoutFormGroup?.get('shippingAddress.street')!;
  }

  get shippingAddressCountry() {
    return this.checkoutFormGroup?.get('shippingAddress.country')!;
  }

  get shippingAddressCity() {
    return this.checkoutFormGroup?.get('shippingAddress.city')!;
  }

  get shippingAddressState() {
    return this.checkoutFormGroup?.get('shippingAddress.state')!;
  }

  get shippingAddressZipCode() {
    return this.checkoutFormGroup?.get('shippingAddress.zipCode')!;
  }

  get billingAddressStreet() {
    return this.checkoutFormGroup?.get('billingAddress.street')!;
  }

  get billingAddressCountry() {
    return this.checkoutFormGroup?.get('billingAddress.country')!;
  }

  get billingAddressCity() {
    return this.checkoutFormGroup?.get('billingAddress.city')!;
  }

  get billingAddressState() {
    return this.checkoutFormGroup?.get('billingAddress.state')!;
  }

  get billingAddressZipCode() {
    return this.checkoutFormGroup?.get('billingAddress.zipCode')!;
  }

  get creditCardType() {
    return this.checkoutFormGroup?.get('creditCard.cardType')!;
  }

  get creditCardNameOnCard() {
    return this.checkoutFormGroup?.get('creditCard.nameOnCard')!;
  }

  get creditCardNumber() {
    return this.checkoutFormGroup?.get('creditCard.cardNumber')!;
  }

  get creditCardSecurityCode() {
    return this.checkoutFormGroup?.get('creditCard.securityCode')!;
  }

  private reviewCartDetails() {
    this.cartService.cartTotalsSubject.asObservable()
      .subscribe(data => this.cartTotals = data);
  }

  onSubmit() {
    console.log('Customer purchased order');

    if (this.checkoutFormGroup.invalid) {
      this.checkoutFormGroup.markAllAsTouched();
    }

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
