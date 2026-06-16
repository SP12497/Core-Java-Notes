Decorators:
    - MetaProgramming code that interacts with other code.

Typescript Supports 2 kinds of Decorators: 
    - ECMAScript Decorators (Stage 3):
        - Build-into JavaScript in the future.
        - Can be used without Typescript.
    - Experimental Decorators (Legacy):
        - Previously planned implementation
        - Did not make it into JS
        - only supported in Typescript with the `experimentalDecorators` flag.
        - Requires 'experimentalDecorators' flag to be enabled in tsconfig.json

- Types of Decorators:
    - Class Decorators
    - Method Decorators
    - Field Decorators
    - Getter Decorators
    - Setter Decorators

- Class Decorators:
    - A function that takes a class constructor as an argument and can modify or replace the class definition.
    - Example:
        ```typescript

        function logger<T extends new (...args: any[]) => any> (
            target: T,
            ctx: ClassDecoratorContext
        ) {
            console.log('Class Decorator called on:', target);
            console.log('Context:', ctx);

            return class extends target {       // anonymous class that extends the original class
                age = 25;
            };
        }

        @logger                 // Decorator applied to the Person class
        class Person { 
            name: 'Max';
            constructor(name: string) {
                this.name = name;
            }

            greet() {
                console.log('Hi, I am ' + this.name);
            }
        }

        const max = new Person('Max');
        console.log(max);  // Person { name: 'Max', age: 25 }

        // Output:
        // Class Decorator called on: [class Person]
        // Context: { kind: 'class', name: 'Person', ... }


        ```

- Method Decorators:
    - A function that takes the target object, the name of the method, and a property descriptor as arguments. It can modify the method's behavior or replace it entirely.
    - Example:
        ```typescript

        function autobind(
            target: (...args: any[]) => any,
            ctx: ClassMethodDecoratorContext
        ) {
           ctx.addInitializer(function(this: any) {  // Gives access of the class constructor of the method belongs to.
            this[ctx.name] = this[ctx.name].bind(this);  // Bind the method to the class instance   // this equals to the class instance(max in this case)

            return function(this: any) {    // optional: replaces the original method with a new one
                console.log('Executing original method');
                // target();        // Call the original method without name binding
                target.apply(this);
            }
           });
        }

        @logger                 // Decorator applied to the Person class
        class Person { 
            name: 'Max';
            constructor(name: string) {
                this.name = name;
            }

            <!-- constructor() {
                this.greet() = this.greet.bind(this);       // bind this object to the greet method
            } -->

            @autobind
            greet() {
                console.log('Hi, I am ' + this.name);
            }
        }

        const max = new Person('Max');
        const greet = max.greet;
        greet();  // Error: Cannot read property 'name' of undefined
        // Solution: either bind the method in the constructor / use an arrow function to define the method / use a method decorator to automatically bind the method

        ```


- Field Decorators:
    - A function that takes the target object, the name of the field, and a property descriptor as arguments. It can modify the field's behavior or replace it entirely.
    - Example:
        ```typescript

        function fieldLogger(
            target: undefined,  // always undefined, because this code is executed before the class is fully defined, so there is no target object yet.
            ctx: ClassFieldDecoratorContext
        ) {
            // return 'SP'; // Error: A field decorator cannot return a value.
            return (initialValue: any) => {
                console.log(initialValue);  // Max
                return 'SP';  // The field will be initialized with 'SP' instead of the original value 'Max' // this function is executed by JS once the class is fully defined.
            }
        }

        @logger                 // Decorator applied to the Person class
        class Person { 

            @fieldLogger
            name: 'Max';

            constructor(name: string) {
                this.name = name;
            }

            <!-- constructor() {
                this.greet() = this.greet.bind(this);
            } -->

            @autobind
            greet() {
                console.log('Hi, I am ' + this.name);
            }
        }

        const max = new Person('Max');
        const greet = max.greet;
        greet();  // Output: Hi, I am SP

        ```


- Decorators Factories:
    - A decorator factory is a function that returns a decorator function. It allows you to pass parameters to the decorator, making it more flexible and reusable.
    - Example:
        ```typescript

        function replacer<T>(
            initValue: T
        ) {
            function replacerDecorator(
            target: undefined,
            ctx: ClassFieldDecoratorContext
            ) {
                return (initialValue: any) => {
                    console.log(initialValue);  // Max
                    return initValue; 
                }
            }
        }

        @logger                 // Decorator applied to the Person class
        class Person { 

            @replacer('SP')
            name: 'Max';

            constructor(name: string) {
                this.name = name;
            }

            <!-- constructor() {
                this.greet() = this.greet.bind(this);
            } -->

            @autobind
            greet() {
                console.log('Hi, I am ' + this.name);
            }
        }

        const max = new Person('Max');
        const greet = max.greet;
        greet();  // Error: Cannot read property 'name' of undefined
        // Solution: either bind the method in the constructor / use an arrow function to define the method / use a method decorator to automatically bind the method

        ```