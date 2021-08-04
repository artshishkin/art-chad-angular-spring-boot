import {Shape} from "./Shape";

export class Circle extends Shape {

    constructor(x: number, y: number, private _radius: number) {
        super(x, y);
    }

    getInfo(): string {
        return super.getInfo() + `, radius = ${this._radius}`;
    }

}