``` typescript
- Typescript is a Javascript Superset.
- Install: npm install -g typescript
- Generate js file from ts file: tsc calculator.ts
- Add in HTML: <html> <head> <script src="calculator.js" defer> </script> </head> </html>
- https://github.com/mschwarzmueller/understanding-typescript-resources
- Node.js to run javascript code: node calculator.js

# Typescrip Essentials:
## Basic Build-in Types
    let userName: string;
    userName = 'Sagar';
    let userAge = 28;       // inference type : auto set as number
    userAge = '50'; // error: userAge is number type // solution: mark userAge as 'any' type.   // let userAge: any = 28;
    function add(a: number, b = 5) {return a + b;}
    let age1: any = 11;
    let age2: string | number = 33; // Union Types
## Object & Array Types
### Array:
    let users: (string | number)[]; // Union Types
    users = [1, 'SP'];
    let users: Array<string | number>;  // Generic type, provides more features
    // Tuple Array: 
        // - It could also be a fixed-length array with more than two elements. Typescript tuples can be of any length needed.
    let possibleResults: [number, number];
    possibleResults = [1, -1];
    // possibleResults = [1, -1, 1];    // error: lenght is only 2
### Object:
    // Inference Type Object: When you initialize an object with values, TypeScript automatically infers its shape and the types of its properties based on the assigned values. 
    let user = { name: 'Sagar', age: 28};   // inference object type
    let user: { name: string; age: number | string; } = { name: 'Sagar', age: 28};   // inference object type

    let val: {} = 'name';   // type '{}' doesn't mean object, it means any value except null or undefined.
    // let val: {} = null;  // error.
    val = 1;
    // val = undefined;    // error
    let obj = {};  // this is object type.

    const someObj = {   // number and string are valid keys
        0: 'test',
        'name': 'test'
    }

    How to create reference of Object type? => Use Record.
    let data = Record<string, number | string>; // key: string, value: number | string
    data = {
        entry1: 1,
        entry2: '2'
    }
### Enum:
    enum Role {     // default numbering
        Admin, Editor, Guest            // 0, 1, 2
    }
    enum Role1 {    // sequence start from assignment
        Admin=2, Editor, Guest            // 2, 3, 4
    }
    enum Role2 {
        Admin= 'Admin', Editor= 'Editor', Guest='Guest'            // Admin, Editor, Guest      // Need to assing for each..
    }
    let userRole: Role = Role.Admin;
    userRole = 1;   // Editor
    // userRole = 4;   // error: invalid number.

### Literal Types:
    // - literal types allow you to specify exact, specific values that a variable can hold, rather than a general type like string or number.
    let userRole: 'admin' | 'editor' | 'guest' = 'admin';
    userRole = 'editor';
    let possibleResults: [ 1 | -1 , -1 | 1];    // tuple with literal type  // tuple: [,]    // literal type: 1|-1  and -1 | 1
    possibleResults = [1, -1];   // ✅
    possibleResults = [1, 1];   // ✅
    // possibleResults = [-2, 3];   // not allowed

    let possibleResults: [1, -1] | [-1, 1]; // union (|) of 2 tuple of [number literal type]
    possibleResults = [1, -1];   // ✅
    // possibleResults = [1, 1];   // not allowed
    // possibleResults = [-2, 3];   // not allowed

## Custom Types
    type MyNumber = number;
    type Role = 'admin' | 'editor' | 'guest';
    let userRole: Role = 'admin';
    type User = {
        name: string;
        age: number;
        permission: string[];
    }

## Function Types:
    function add(a: number, b: number) { return a: b;}
    function add(a: number, b: number): number { return a: b;}  // :number => its returns value type
    function log(message: string): void { console.log(message); }
    // Never type:
    // - Never function will never complete and never return a value.
    // - Warning: it will crash your program, if not properly handled.
    function logAndThrow(message: string): never {
        console.log(message);
        throw new Error(message);
    }
    // Function Type:
    function performJob1(cb: (msg: string) => void) { cb('Job Done'); }
    function performJob2(cb: Function) { cb('Job Done'); }
    function performJob2(cb: (msg: (string) => void) { cb('Job Done'); }
    const log = (msg: string) => { console.log(msg); }
    performJob1(log);
    performJob2(log);

    type User = {
        name: string;
        greet: () => string;
    }
    let user: User = {
        name: 'Sagar',
        greet() {
            return this.name;
        }
    }

## Special Types:
    // # null or undefined:
    let a: null;
    a = null;
    // a = 'sp'; // error: only null allowed.
    let b: null | string;
    b = 'sp';

    let c = undefined | number;

    // # '!' operator:
    <input id="user-name">
    const inputEl = document.getElementById('user-name');   // this returns either 'HTMLElement | null'
    const inputEl = document.getElementById('user-name')!;   // tell TS that, this value will never be null. 
    // Add this if we are sure that, this line wont return null. If its returned null and we didnt handle value properly, then this will return RunTimeException.
    console.log(inputEl.value);
    console.log(inputEl!.value); // throw runtime exception if its null.
    console.log(inputEl?.value);    // Optional Chaining (?) - JS feature

    // AS: use for type-casting or changing the type.
    const inputEl = document.getElementById('user-name') as HTMLInputElement | null;    // by default type is (HTMLElement | null) - HTMLElement is generic type

    // # unknown type:
    // - the unknown type represents a value that can be anything, similar to any. However, unlike any, unknown is type-safe.
    // - It requires you to perform type checking or type assertions before you can operate on a value of type unknown. This ensures that you handle the value appropriately based on its actual type.
    function process(val: any) {
        val.log();      // Even if log method is missing, this will not show compile time error.
    }
    function process(val: unknown) {
        // val.log();      // if log method is missing, this will show compile time error.
        // To use unknown type, must to check each value before use:
        if(
            typeof val === 'object' &&
            !! val &&
            'log' in val &&
            typeof val.log === 'function'
        ) {
            val.log();
        }
    }

    // Optional: (Optional Parameter or property)
    function generateError(msg?: string) {
        throw new Error(msg);
    }
    generateError();
    generateError('exception occured.');

    // Nullish Coalescing: ( ?? ) - js feature
    // ignores only 'null' and 'undefined'
    // || : ignores all falsy values. (null, undefined, '', false, 0, ...)
    const val1 = null || undefined || false || '';  // ans: ''
    const val1 = null ?? undefined ?? false ?? '';  // ans: false
    const val1 = null || undefined || '' || false;  // ans: false
    const val1 = null ?? undefined ?? '' ?? false;  // ans: ''



----------------------------------------------------------
Section 3: The TypeScript Compiler (and its Configuration)
----------------------------------------------------------
tsconfig.json:
- How to create tsconfig.json file?
    - tsc --init
    - this will create a tsconfig.json file in the current directory.
- Types in tsconfig.json:
    - Projects:
        - this property is used to specify the root files and the compiler options required to compile the project.
        - eg, incremental: true
    - Language and Environment:
        - this property is used to specify the language and environment in which the code is written.
        - eg, target: 'es2016'  // target version of ECMAScript which the code will be compiled to on browser.
            module: 'commonjs'
            lib: ['es2016', 'dom']  // standard library
    - Modules:
        - how imports and exports are handled in the code.
        - eg, module: 'commonjs' / 'NodeNext'  // module system used in the code.
            rootDir: './src'  // root directory of the source files.
            moduleResolution: 'node'  // how modules are resolved.
            baseUrl: './src'  // base url for module resolution.
            paths: { '*': ['*'] }  // path mapping for module resolution.
    - Javascript Support:
        - allowJs: true  // allow javascript files to be compiled.
    - Emit:
        - this property is used to specify the output of the compiled code.
        - eg, outDir: './dist'  // output directory for the compiled code. Compile file storage location
            sourceMap: true  // generate source maps for the compiled code.
            declaration: true  // generate declaration files for the compiled code.
    - Type Checking:
        - this property is used to specify the type checking options for the code.
        - eg, strict: true  // enable all strict type checking options.
            noImplicitAny: true  // raise error on expressions and declarations with an implied 'any' type.
                function add(a: number, b) { return a + b; }  // error: b is implicitly 'any' type.
            noImplicitThis: true  // raise error on 'this' expressions with an implied 'any' type.
            alwaysStrict: true  // parse in strict mode and emit "use strict" for each source file.  
    - Code Quality Checks:
        - this property is used to specify the code quality checks for the code.
        - eg, noUnusedLocals: true  // report errors on unused local variables.
            noUnusedParameters: true  // report errors on unused parameters.
            noFallthroughCasesInSwitch: true  // helps you detect switch cases without break or return.

    - Execute code which accept tsconfig rules:
        - folder structure:
          tsconfig.json
          /src/app.ts
          /dist     // compiled JS Code will be stored here.
        - Execution:
            - tsc /src/app.ts // trigger typescript code specific file: this line won't execute by tsconfig.json rules.
            - tsc // trigger typescript code:  this line will execute by tsconfig.json rules.
            - tsc --watch // this line will execute by tsconfig.json rules and watch for changes in the code.
- How to use Javascript features in Typescript?
    - /src/app.ts:
        - import fs from 'node:fs';     // error, its js module feature, node js require package.json module feature.
        - Solution:
            - create package.json file: 
                - npm init -y
            - update package.json file:
                - "type":"module"
            - Install Dependency:
                - npm install @types/node --save-dev 
                - this package contains type declations to understand node.js apis

=========
Section 6: Classes and Interfaces
- Class:
    - class User  {
        name: string;   // way 1: property creating, by default 'public'
        public hobbies: string[] = [];
        private classroom: string = '2C';
        readonly hobbies: string[] = [];

        protected department: string = 'IT';

        constructor(name: string, public contact: number, public div: string = 'B', private std: number) { // // way 2: property creating 'contact'
            this.name = name;
            // this.age = 5;    // this way of property creating not allowed in ts, its only allowed in JS.
        }

        display() {
            console.log(this.name, this.contact, this.div, this.std, this.classroom);
        }
    }

    const sagar = new User('Sagar', 222, 'A', 10);
    sagar.contact = 333;    // contact is public
    // sagar.std = 9;   // error std is private, only accessible within the class.
    // sagar.hobbies = ['reading', 'swimming'];    // not allowed
    sagar.hobbies.push('reading');  // works: here, we are not assigning new value
    ------
- Getters and Setters:
    class User {
        static organization = 'HM'; // static property
        static greet() {
            return console.log("hello");
        }

        constructor(private _firstName: string, private _lastName: string) {
        }

        get fullName() {
            return `${this._firstName} ${this._lastName}`;
        }

        set fullName(name: string) {
            if(name.trim().length === 0) {
                throw new Error('Invalid name');
            }
            const parts = name.split(' ');
            this._firstName = parts[0];
            this._lastName = parts[1];
        }
    }
    const sagar = new User('Sagar', 'P');
    // console.log(sagar.fullName());  // error: its not method '()'
    console.log(sagar.fullName);
    sagar.fullName = 'Sagar Pa';    // Setter
    console.log(sagar.fullName);

    console.log(User.organization); // class level, available for all objects.
    User.greet();       // static method.

- Inheritance:
    class Exployee extends User {
        // Note: only public and protected properties of User class are accessible in Employee class.
        constructor(public jobTitle: string) {
            super();    // call default constructor
            // super('Sagar', 'P'); // call parameterized constructor.
            super.firstName = 'Sagar';  // access parent class properties.
            super.department = 'HR';    // protected property
            super.std = 12;    // private property of parent class is not accessible in child class.
        }
    }

- Abstract Class:
    abstract class Shape {
        constructor(public name: string) {}

        // Concrete method (has implementation)
        describe(): string {
            return `This is a ${this.name}`;
        }

        // Abstract method (must be implemented by child class)
        abstract calculateArea(): number;
    }

    // Child class
    class Circle extends Shape {
        constructor(public radius: number) {
            super("Circle");
        }

        calculateArea(): number {
            return Math.PI * this.radius * this.radius;
        }
    }

    // const s = new Shape("Test"); // error: cant create object of Abstract class
    const circle = new Circle(5);   // works.

- Interfaces:
    - Object type definitions and contracts that can be implemented by classes.

    - Interface Defination:
        interface Authenticatable {
            email: string
            password: string;

            login: void;
            logout: void;
        }
        interface Authenticatable {     // declaration merging: 
            role: string
        }

        // now Authenticatable have 3 property:  email, password and role.
        // declaration merging benefits:
            // - if first Authenticatable is coming from any library and we have to add our properties also to the same.
            - we cant do same thing using type aliases: type Authenticatable
        - Another way:
            interface AuthenticatableAdmin extends Authenticatable {
                role: 'admin' | 'superadmin';
            }

    - Usage as Object Type:
        let authUser: Authenticatable;
        authUser = {
            email: 'test@example.com',
            password: 'abc',
            role: 'dev',
            login() {
                console.log("User logged in");
            },
            logout() {
                console.log("User logged out");
            }
        };
    - Implementation as Contract:
        class User implements Authenticatable {
            constructor (public email: string, public password: string, public role: string) {
            }
            login() {
                console.log("User logged in");
            },
            logout() {
                console.log("User logged out");
            }
        }

    - creating a function which required object having login method:
        - function authenticate(user: {login(): void}) {}
        - function authenticate(user: Authenticatable) {}
- type aliases:
    type SumFn = (a: number, b: number) => number; // function type
    // OR
    interface SumFn {
        (a: number, b: number): number;
    }
 
    let sum: SumFn; // making sure sum can only store values of that function type
    
    sum = (a, b) => a + b; // assigning a value that adheres to that function type

-----