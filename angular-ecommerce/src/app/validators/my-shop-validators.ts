import {FormControl, ValidationErrors} from "@angular/forms";

export class MyShopValidators {


  static notOnlyWhitespace(formControl: FormControl): ValidationErrors | null {

    return (formControl.value?.trim().length === 0) ?
      {'notOnlyWhitespace': true} :
      null;
  }
}
