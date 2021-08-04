class Customer {

    constructor(private _firstName: string,
                private _lastName: string) {
    }

    get firstName(): string {
        return this._firstName;
    }

    set firstName(value: string) {
        this._firstName = value;
    }

    get lastName(): string {
        return this._lastName;
    }
}

let customer = new Customer('Kate', "Shyshkin");
customer.firstName = 'Art';

console.log(`customer.firstName: ${customer.firstName}`);
console.log(`customer.lastName: ${customer.lastName}`);


