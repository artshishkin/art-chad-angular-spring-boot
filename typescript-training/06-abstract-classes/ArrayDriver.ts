import {Shape} from "./Shape";
import {Circle} from "./Circle";
import {Rectangle} from "./Rectangle";

let circle = new Circle(3, 4, 5);
let rectangle = new Rectangle(1, 2, 3, 4);

let shapes: Shape[] = [];
shapes.push(circle, rectangle);

shapes.forEach(sh => {
    console.log(sh.getInfo());
    console.log(`Area: ${sh.calculateArea()}`);
    console.log();
});
