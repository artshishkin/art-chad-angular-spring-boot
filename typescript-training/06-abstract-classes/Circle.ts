import {Shape} from "./Shape";

export class Circle extends Shape {

    constructor(x: number, y: number, private _radius: number) {
        super(x, y);
    }

    getInfo(): string {
        return `Circle: ${super.getInfo()}, radius=${this._radius}`;
    }

    calculateArea(): number {
        return Math.PI * Math.pow(this._radius, 2);
    }

}