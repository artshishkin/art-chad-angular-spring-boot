let myBool: boolean = true;
let myNumber: number = 123.45;
let myString1: string = "Double quotes";
let myString2: string = 'Single quotes';

//let's break it
// myBool = 0;
// myNumber = "A";
// myString1 = false;

console.log(myBool);
console.log('My number is: ' + myNumber);
console.log(`String template1: ${myNumber}`);
console.log(`String template2: ${myString1} and ${myString2}`);

let anyVar: any;
anyVar = myBool;
console.log(`anyVar: ${anyVar}`)
anyVar = myNumber;
console.log(`anyVar: ${anyVar}`)
anyVar = myString1;
console.log(`anyVar: ${anyVar}`)

