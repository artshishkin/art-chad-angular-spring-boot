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

#####  17. Configuring Compiler Options with tsconfig.json

1.  Try to transpile
    -  `tsc Customer.ts`
    -  `Customer.ts:11:9 - error TS1056: Accessors are only available when targeting ECMAScript 5 and higher.`
2.  Create template tsconfig file
    -  `tsc --init`
3.  Compile all the ts files in the folder
    -  `tsc`
    
####  Section 7: Angular Crash Course - Fundamentals

#####  31. Creating a New Angular Project

1.  Install Angular CLI
    -  `npm install -g @angular/cli`
2.  Test version
    -  `ng version`
3.  Help
    -  `ng help`
4.  Create first project
    -  cd to angular-training
    -  `ng new my-first-angular-project`
        -  routing -> No 
        -  stylesheet -> CSS
5.  Run the Application
    -  from my-first-angular-project
    -  `ng serve`
    -  go to `http://localhost:4200`                  
6.  Edit HTML Template file
    -  /src/app/app.component.html
    -  find `app is running`
    -  changes are automatically reloaded -> view in browser
    -  delete everything except this span  
    
        
    
    
    