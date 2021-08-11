import {Injectable} from '@angular/core';
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MyShopFormService {

  constructor() {
  }

  getCreditCardMonths(startMonth: number): Observable<number[]> {

    let months: number[] = [];

    for (let i = startMonth; i <= 12; i++) {
      months.push(i);
    }
    return of(months);
  }

  getCreditCardYears(): Observable<number[]> {

    let years: number[] = [];
    let year: number = new Date().getFullYear();

    for (let i = 0; i < 10; i++) {
      years.push(year++);
    }
    return of(years);
  }

}
