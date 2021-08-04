class Customer {

    firstName: string;
    lastName: string;

    constructor(theFirst: string, theLast: string) {
        this.firstName = theFirst;
        this.lastName = theLast;
    }
}

let customer = new Customer('Kate',"Shyshkina");
customer.firstName = 'Art';
customer.lastName = 'Shyshkin';

console.log(`customer: ${customer}`);
console.log(`customer.firstName: ${customer.firstName}`);
console.log(`customer.lastName: ${customer.lastName}`);


