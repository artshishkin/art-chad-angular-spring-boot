import {Injectable} from '@angular/core';
import {Observable, of} from "rxjs";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Country} from "../common/country";
import {map} from "rxjs/operators";
import {State} from "../common/state";

@Injectable({
  providedIn: 'root'
})
export class MyShopFormService {

  private baseUrl = `${environment.backendServerUrl}`;
  private countriesUrl = `${this.baseUrl}/countries`;

  constructor(private httpClient: HttpClient) {
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

  getCountries(): Observable<Country[]> {
    return this.httpClient
      .get<CountriesGetResponse>(this.countriesUrl)
      .pipe(map(data => data._embedded.countries));
  }

  getStates(countryId: number): Observable<State[]> {
    let statesUrl = `${this.countriesUrl}/${countryId}/states`;
    return this.httpClient.get<StatesGetResponse>(statesUrl)
      .pipe(map(data => data._embedded.states));
  }
}

interface CountriesGetResponse {
  _embedded: {
    countries: Country[];
  }
}

interface StatesGetResponse {
  _embedded: {
    states: State[];
  }
}
