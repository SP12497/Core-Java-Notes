// https://roadsidecoder.hashnode.dev/javascript-interview-questions-call-bind-and-apply-polyfills-output-based-explicit-binding
// https://youtu.be/VkmUOktYDAU?si=PuEJYa4vQP3TDwv-

console.log("______________#1______________");
// Q. What is call?
var obj = { name: "Sagar" };

function sayHello() {
    console.log("this.name:", this.name);
}
sayHello();             // undefined
sayHello.call(obj);     // Sagar // call(this object, List<arguments>)

function sayHello2(age, city) {
    console.log("this.name:", this.name, ". and my age is:", age, ". and my city is:", city);
}

sayHello2.call(obj, 26, "CallNDB");

// Q. What is apply?
// apply is same as call but its takes all the arguments in form of an array.
sayHello2.apply(obj, [26, "ApplyNDB"]);

// Q. What is bind?
// instead of calling a function, it will return a reusable function that we call call later.
const bindedFunction = sayHello2.bind(obj);
bindedFunction(55, "BindCity");
bindedFunction(55, "BindNdb");
// Output based Questions:
// Q1. 

var person = { name: "Yogesh" }
function sayHi(age) {
    return `${this.name} is ${age}`;
}

console.log(sayHi.call(person, 44)); // log message
console.log(sayHi.bind(person, 44)); // log function itself

console.log("______________#2______________");
// Q2
const age = 10;
var person2 = {
    name: "Sagar",
    age: 20,
    getAge: function () {
        return this.age;
    }
}
var person = { age: 30 };
console.log("getAge()", person2.getAge());                          // 20

console.log("getAge.call()", person2.getAge.call());                // undefined
console.log("getAge.call()", person2.getAge.call(person));          // 30

console.log("getAge.apply()", person2.getAge.apply());              // undefined
console.log("getAge.apply()", person2.getAge.apply(person));        // 30

console.log("getAge.bind()", person2.getAge.bind(person));          // [Function: bound getAge]
console.log("IIFE getAge.bind()", person2.getAge.bind()());         // undefined
console.log("IIFE getAge.bind()", person2.getAge.bind(person2)());  // 20

console.log("______________#3______________");
const arr1 = ['a', 'b', 'c'];
const numsArr = [1, 2, 3];

/*
result.push.apply(arr1, numsArr);
console.log(result);                // undefined
console.log(arr1);                  // [ 'a', 'b', 'c', 1, 2, 3 ]
console.log(numsArr);               // [ 1, 2, 3 ]
*/
arr1.push.apply(arr1, numsArr);
console.log(arr1);              // [ 'a', 'b', 'c', 1, 2, 3 ]
console.log(numsArr);              // [ 1, 2, 3 ]

const arr3 = ['a', 'b', 'c'];
numsArr.push.call(arr3, numsArr);
console.log(arr3);              // [ 'a', 'b', 'c', [ 1, 2, 3 ] ]
console.log(numsArr);              // [ 1, 2, 3 ]

const arr4 = ['a', 'b', 'c'];
numsArr.push.call(arr4, ...numsArr);
console.log(arr4);              // [ 'a', 'b', 'c', 1, 2, 3 ]
console.log(numsArr);              // [ 1, 2, 3 ]


console.log(Math.max(numsArr));                     // NaN
console.log(Math.max.apply(null, numsArr));         // 3
console.log(Math.max(...numsArr));                  // 3
console.log("______________#4______________");
// Q3.
// status = "global"; // for chrome
this.status = "global";
setTimeout(function () {
    const status = "local";

    const data = {
        status: "object scope",
        getStatus() {
            return this.status;
        }
    }
    console.log("getStatus()", data.getStatus()); // "object scope"
    console.log("getStatus.call(this)", data.getStatus.call(this)); // on chrome: "global"  // node: undefined // Html Javascript: "global"
    // Inside the setTimeout callback, this does not point to global or have a status property in Node.js, so data.getStatus.call(this) returns undefined
}, 0);


global.status = "global"; // Setting status on global object
setTimeout(function () {
    const status = "local";

    const data = {
        status: "object scope",
        getStatus() {
            return this.status;
        }
    }
    console.log("global getStatus()", data.getStatus()); 
    console.log("global getStatus.call(global)", data.getStatus.call(global)); // Bind explicitly to global
}, 0);


console.log("______________#5______________");
const animals = [
    { species: "Lion", name: "King" },
    { species: "Whale", name: "Queen" },
];

function printAnimal(i) {
    this.print = function () {
        console.log("#" + i, this.species, ":", this.name);
    }
    this.print();
}

for (let i = 0; i < animals.length; i++) {
    printAnimal.call(animals[i], i);
}
// #0 Lion : King
// #1 Whale : Queen

console.log("______________#6______________");
function f() {
    console.log(this);  // node: global obj | browser: Window object
}

let user = {
    // g: f.bind({name:'sagar'})    // this will point to passed obj {name:'sagar'}
    g: f.bind(null)     // f() will point to f() this scope, ie window object
};

user.g();

console.log("__________________Types of Bindings________________");
console.log("__________________1. Implicit Bindings________________");
// If a function is called as a method of an object, this inside the function refers to that object.
const obj1 = {
    name: "John",
    greet() {
        console.log(`Hello, ${this.name}!`);
    }
};

obj1.greet(); // Output: Hello, John!

console.log("__________________2. Explicit Bindings________________");
// You can explicitly bind a function to a specific context using methods like call(), apply(), or bind().

function greet() {
    console.log(`Hello, ${this.name}!`);
}

const obj2 = { name: "Alice" };

greet.call(obj2); // Output: Hello, Alice! // this=obj2
greet.apply({ name: "Bob" }); // Output: Hello, Bob!   // this=obj3

const boundGreet = greet.bind(obj2);    // // this=obj2 and return obj3 binded function.
boundGreet(); // Output: Hello, Alice!

console.log("__________________3. Global Binding__________________");
function greet2() {
    console.log(`Global browser Hello, ${this.name} - ${name}!`);
}
const name = "World";

greet2(); // Output: Hello, World - World! (in browsers)    // node: undefined - World!

console.log("__________________4. Arrow Functions__________________");
const obj4 = {
    name: "Alice",
    greet: () => {
        console.log(`Hello, ${this.name} - ${obj4.name} - ${name}!`);   // this =: obj4
    }
};

obj4.greet(); // Node: Hello, undefined - Alice - World! // Browser: Hello, - Alice - World!
