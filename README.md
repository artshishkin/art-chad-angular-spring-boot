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
    
##### 36. Creating a New Angular Component - Write Some Code - Part 1        
    
1. Create new Angular project
    -  `ng new sales-project`
    -  routing -> no
    -  CSS
    -  Start project and open
        -  `ng serve --open`
2.  Update main template page
    -  `app.component.html`
3.  Generate a new component
    -  stop server - `Ctrl+C`
    -  `ng generate component sales-person-list`             
4.  Add new component selector to app template page
    -  sales-person-list.component.ts -> copy `selector`
    -  app-sales-person-list
    -  insert selector `into app.component.html`    
5.  Generate SalesPerson class
    -  `ng generate class sales-person-list/SalesPerson`

####  Section 8: Angular Crash Course - Bootstrap CSS, Conditionals and Formatting

#####  40. Integrating Angular and Bootstrap CSS

1.  Get links for remote Bootstrap files
    -  visit `http://getbootstrap.com`
    -  Get Started
    -  Starter template
        -  copy template for meta "Viewport" and link for Bootstrap CSS 
2.  Add links to index.html
3.  Apply Bootstrap CSS styles in template
    -  `app.component.html`
    -  wrap with `<div class="container">`
    -  for h1 add margins (top and bottom) - `mt-3 mb-3`
4.  Apply Bootstrap CSS styles in HTML Table (sales-person-list.component)
    -  duplicate html -> sales-person-list-bootstrap.component.html
    -  add styles
5.  Update TypeScript component to reference Bootstrap HTML template
    -  sales-person-list.component.ts
    -  change templateURL        
    
#####  42. Angular Conditionals and Formatting

1.  Add a column with condition
    -  Met quota
2.  Format sales Volumes
    -  use Angular pipe `currency`
    -  ` | currency:'USD'`

#####  43. Bonus: Angular Conditionals: How to highlight table row

[Angular Conditionals: How to highlight table row](https://github.com/darbyluv2code/fullstack-angular-and-springboot/blob/master/bonus-content/angular-crash-course/02-ngIf-highlight-entire-row/02-ngif-highligh-entire-row.md)

####  Creating Angular Module in IDEA

1.  Create new module
    -  File -> New -> Module ->
    -  JavaScript -> Angular CLI 
2.  Add component `sales-person-list`
    -  `app` -> Alt+Insert -> Angular Schematic -> component -> `sales-person-list`
3.  Add new class
    -  `sales-person-list` ->  Alt+Insert -> Angular Scematic -> class -> SalesPerson

####  Docker images for backend and frontend

-  [How to Dockerize Angular App?](https://dzone.com/articles/how-to-dockerize-angular-app)
    -  create `Dockerfile`
    -  create `.dockerignore`
    -  `docker build -t artarkatesoft/fullstack-angular-ecommerce .`

-  [Dynamically set Angular Environment Variables in Docker](https://pumpingco.de/blog/environment-variables-angular-docker/)

####  Section 11: eCommerce Project - Integrating Online Shop Template

#####  66. Angular Project - Install Bootstrap CSS Styles with npm

-  `cd angular-ecommerce`
-  `npm install bootstrap`
-  install fontawesome-free
    -  `npm install @fortawesome/fontawesome-free`

####  Add caching for product-category endpoint

-  [spring-data-rest-with-cache](https://stackoverflow.com/questions/51591121/spring-data-rest-with-cache)
-  start redis-cli in docker
    -  `docker exec -it redis redis-cli`

-  performance **increased** from 640 requests/sec to 2540 req/sec -> ~ **4 times**

####  Section 16: eCommerce Project - Pagination

#####  109. Angular Project - Pagination - Install ng-bootstrap

-  `cd angular-ecommerce`
-  `ng add @angular/localize`
-  `npm install @ng-bootstrap/ng-bootstrap`

            