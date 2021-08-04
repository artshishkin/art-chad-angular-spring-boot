import {Shape} from "./Shape";
import {Circle} from "./Circle";
import {Rectangle} from "./Rectangle";

let shape = new Shape(1, 2);
let circle = new Circle(3, 4, 5);
let rectangle = new Rectangle(1, 2, 3, 4);

console.log(`shape: ${shape.getInfo()}`);
console.log(`circle: ${circle.getInfo()}`);
console.log(`rectangle: ${rectangle.getInfo()}`);