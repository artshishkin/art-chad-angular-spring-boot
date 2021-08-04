import {Shape} from "./Shape";

export class Rectangle extends Shape {

    constructor(x: number, y: number,
                private _width: number, private  _height: number) {
        super(x, y);
    }

    getInfo(): string {
        return super.getInfo() + `, width=${this._width}, height=${this._height}`;
    }

    get width(): number {
        return this._width;
    }

    set width(value: number) {
        this._width = value;
    }

    get height(): number {
        return this._height;
    }

    set height(value: number) {
        this._height = value;
    }
}
