export abstract class Shape {

    protected constructor(private _x: number, private _y: number) {
    }

    getInfo(): string {
        return `x=${this._x}, y=${this._y}`;
    }

    abstract calculateArea(): number;

    get x(): number {
        return this._x;
    }

    set x(value: number) {
        this._x = value;
    }

    get y(): number {
        return this._y;
    }

    set y(value: number) {
        this._y = value;
    }
}