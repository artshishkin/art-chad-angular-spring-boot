import {Shape} from "./Shape";
import {Circle} from "./Circle";
import {Rectangle} from "./Rectangle";

let shape = new Shape(1, 2);
let circle = new Circle(3, 4, 5);
let rectangle = new Rectangle(1, 2, 3, 4);

let shapes: Shape[] = [shape, circle];
shapes.push(rectangle);

shapes.forEach(sh => console.log(`shape: ${sh.getInfo()}`));
