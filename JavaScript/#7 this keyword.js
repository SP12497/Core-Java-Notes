/* 
https://roadsidecoder.hashnode.dev/javascript-interview-questions-this-keyword-output-based-scope-implicit-binding-etc

Node:
    - Has global object. (var b)
    - Has module scope (this.a)
Chrome/browser:
    - Has window object. (this)
- In Node.js, the this keyword inside a module refers to the module scope, not the global scope. 
  Each module in Node.js has its own scope, and this within that scope refers to the module itself.
- In a browser environment, the this keyword typically refers to the global object, which is window. 
  So, in the browser, this often represents the window scope.

- What is Module?
    - In Node.js, a module is a separate JavaScript file that encapsulates functionality and variables. 
      It is a way to organize code into reusable components, making it easier to manage and maintain large projects.
    - Each module in Node.js has its own scope, meaning that variables, functions, and other declarations 
      made within a module are scoped to that module and not accessible from other modules unless explicitly exported.
    - Node.js uses the CommonJS module system, where each file is treated as a separate module. 
      Modules can export functionality using the module.exports or exports object, and 
      other modules can import this functionality using the require() function.
Binding:
    Implicit Binding: using dot (.) notation
        obj.getName()   
    Explicit Binding: using call, bind and apply.  (#8 CallBindApply8.js)
    Global Binding
    Arrow Functions:
*/

// 'this' keyword in JS (Implicit Binding)
console.log(this); // {}, module scope defult is empty object
this.a = 5; // node: module scope   // browser: window object
b = 6;      // node: global scope   // browser: window object
console.log(this.a);    // node: 5          // browser: 6
console.log(this.b);    // node: undefined  // browser: 6
console.log("window object:", this);
//---
console.log("__________________________________");
function getParams() {
    console.log("getParams", this.a); // node: undefined // chrome: 5// still its points to its global obj, ie window obj
}
getParams();

const getParams1 = () => {
    console.log("arrow:", this.a); // still its points to its global obj, ie window obj
}
getParams1()

console.log("__________________________________");
// But both arrow and regular have difference
let user = {
    name: "sagar",
    age: 26,
    getDetails() {
        console.log("getDetails", this); // here this function variable will points to its immeadiate parent object ie, name, age, getDetails, chileObj.
    },
    chileObj: {
        newName: "Patil",
        getInnerDetails() { // regular function
            console.log("inner attribute:", this.newName, ",global attibute:", this.name);
        }
    }
}
user.getDetails();
user.chileObj.getInnerDetails(); //inner attribute: Patil ,global attibute: undefined

console.log("__________________________________");
//Arrow:
// console.log("Arrow this keyword");
let userArrow = {
    name: "Sagar",
    age: 26,
    getDetails() {
        const nestedArrow = () => console.log("getDetails=>", this.name); // name will work
        nestedArrow(); // => parent normal function // getDetails() parent space => userArrow space.
    },
    arrowDetails: () => {
        console.log("arrowDetails=>", this); // parent of scope in regular function this
        // => parent normal function //(no parent function so global space (out of userArrow))
    },
    childObj: {
        newName: "Patil",
        getInnerDetails: () => {
            console.log("Arrow=> inner attribute:", this.newName, ",global attibute (parent of reguler function this):", this.name, "this:", this);
            // => parent normal function //(no parent function (all are attributes) so global space (out of userArrow))
        }
    }
}
// take parent regular function outer value
userArrow.getDetails()
userArrow.arrowDetails();
userArrow.childObj.getInnerDetails();

console.log("__________________CLASS________________");
// Class
class User {
    age = 30;
    constructor(name) {
        this.name = name; // this = parent regular function outer value
    }
    getName() {
        console.log("class name:", this.name, this.age);
    }
}
const userObj = new User("Sagar");
userObj.getName();

console.log("__________________Q1________________");
// Q1 Output
var globalVar = 12345;
this.myThisVar = "######"
function makeUser() {
    return {
        name: "Sagar",
        ref: this   // Node: global scope, not module scope // browser: window obj
    }
}
let userMake = makeUser();
console.log("makeUser 1:", userMake.ref.myThisVar); // node: undefined // chrome: #####
console.log("makeUser 1.2:", userMake.ref.globalVar); // node: 12345 // chrome: 12345
console.log("makeUser 2:", userMake.ref);

console.log("___________________Q2_______________");
// Q2. How to get name from above function using this
const makeUser2 = {
    name: "Yogesh",
    age: 26,
    getName() {
        return this.name;
    },
    ref() {
        return this;
    }
}

console.log("makeUser2:", makeUser2.getName());
console.log("makeUser2:", makeUser2.ref().age);

console.log("__________________________________");
const user4 = {
    name: "Rohit",
    logMessage() {
        console.log(this.name);
    }
}
// call:
setTimeout(user4.logMessage, 1000); // pass only function structure as callback //ans: undefined // window object
//concept: logMessage is callback so setTimeout will copy only the logMessage function into setTimeout
//          so after 1 sec, setTimeout won't access of "user" class, so function executed independently, 
//          and user parent is not there so, it points to window object.
// how to fix this?
setTimeout(() => {
    user4.logMessage(); // Rohit // pass a function which will call user4.logMessage, so in this case, setTimeout will not copy logMessage()
}, 1000);

console.log("__________________Callback 1________________");
var length = 4; // this should be at the start of the file
this.c = 11;
function callback() {
    console.log("callback:", this.length, ", global:", length, ", c:", this.c);
}
const object = {
    length: 6,
    myMethod(fn) {
        fn();   //callback: undefined , c: 6
    }
}
object.myMethod(callback); // node: undefined 4 undefined / broswer: 4 4 11


console.log("__________________Callback by Arguments________________");
const object2 = {
    length: 6,
    myMethod(fn) {
        arguments[0](); // node and browser: call by args, this=args, so this.length = args.length = 3; length = global = 4; this.c = not present in args
    }
};

object2.myMethod(callback, 6, 7);

console.log("__________________Q6________________");
// Q6. Implement this code
// const result = calc.add(10).multiply(5).substract(30).add(10);
// console.log("result", result.total);
// const calc = {
//     total: 0,
//     add: function (num) {
//         this.total += num;
//         return calc;
//     },
//     multiply(num) {
//         this.total *= num;
//         return calc;
//     },
//     subtract: function (num) {
//         this.total -= num;
//         return calc;
//     }
// }
const calc = {
    _total: 0,
    add: function (num) {
        this._total +=num;
        return calc;
    },
    multiply: function (num) {
        this._total *=num;
        return calc;
    },
    subtract: (num) => {
        calc._total -=num;
        return calc;
    }
}
const result = calc.add(10).multiply(5).subtract(30).add(10);
console.log("result", result._total);