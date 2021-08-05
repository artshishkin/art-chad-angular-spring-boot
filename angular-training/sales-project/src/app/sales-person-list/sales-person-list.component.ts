import {Component, OnInit} from '@angular/core';
import {SalesPerson} from "./sales-person";

@Component({
  selector: 'app-sales-person-list',
  templateUrl: './sales-person-list.component.html',
  styleUrls: ['./sales-person-list.component.css']
})
export class SalesPersonListComponent implements OnInit {

  salesPersonList: SalesPerson[] = [];

  constructor() {
  }

  ngOnInit(): void {
    this.salesPersonList.push(
      new SalesPerson('Art', 'Shyshkin', 'art@shyshkin.com', 123.45),
      new SalesPerson('Kate', 'Shyshkina', 'kate@shyshkin.com', 234.56),
      new SalesPerson('Arina', 'Shyshkina', 'arina@shyshkin.com', 345.67),
      new SalesPerson('Nazar', 'Shyshkin', 'nazar@shyshkin.com', 456.78)
    );
  }

}
