var Customer = /** @class */ (function () {
    function Customer(theFirst, theLast) {
        this.firstName = theFirst;
        this.lastName = theLast;
    }
    return Customer;
}());
var customer = new Customer('Kate', "Shyshkina");
customer.firstName = 'Art';
customer.lastName = 'Shyshkin';
console.log("customer: " + customer);
console.log("customer.firstName: " + customer.firstName);
console.log("customer.lastName: " + customer.lastName);
