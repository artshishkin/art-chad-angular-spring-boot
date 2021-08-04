export class Customer {

    constructor(private _firstName: string,
                private _lastName: string) {
    }

    get firstName(): string {
        return this._firstName;
    }

    set firstName(value: string) {
        this._firstName = value;
    }

    set lastName(value: string) {
        this._lastName = value;
    }

    get lastName(): string {
        return this._lastName;
    }
}
