# art-chad-angular-spring-boot
 Full Stack: Angular and Java Spring Boot - Tutorial from Chad Darby and Harinath Kuntamukkala (Udemy)

####  Section 4: TypeScript Crash Course - Fundamentals

#####  7. Creating our first TypeScript application

1.  Install tsc
    -  `npm install -g typescript`
2.  Transpile (translate/compile) code
    -  `tsc myhelloworld.ts`
3.  Run
    -  `node myhelloworld.js`

#####  9. Defining Variables

1.  Even with errors `tsc` will compile JS
2.  Add flag `-noEmitOnError`
    -  `tsc -noEmitOnError sample-types.ts` 


####  Section 5: TypeScript Crash Course - Creating Classes

##### 16. Defining Accessors

-  `Accessors are only available when targeting ECMAScript 5 and higher`
-  To fix that error add flag
    -  `-t es5`
    -  `tsc -noEmitOnError -t es5 Customer.ts`
