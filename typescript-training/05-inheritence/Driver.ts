import {Shape} from "./Shape";
import {Circle} from "./Circle";

let shape = new Shape(1, 2);
let circle = new Circle(3, 4, 5);

console.log(`shape: ${shape.getInfo()}`);
console.log(`circle: ${circle.getInfo()}`);