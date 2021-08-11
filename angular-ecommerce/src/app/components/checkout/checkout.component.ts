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

    this.checkoutFormGroup = this.formBuilder.group({
      customer
    });

  }

  onSubmit() {
    console.log('Customer purchased order');
    console.log(this.checkoutFormGroup.get('customer')?.value);
    console.log(`Email address is ${this.checkoutFormGroup.get('customer')?.value.email}`);
  }

}
