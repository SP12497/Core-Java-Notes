/* What is Closure:
    - Closure means that an inner function always has access to the varibale of its
      outer function, even after the router function has returned.
    - closure gives you access to an outer function scope 
    from an inner function.
    - In JS, closures are created every time a function is created, at function creation time.
    
# Lexical Scope:
    - In JavaScript, lexical scope is the concept of determining the scope of a variable based on its declaration. 
    - This means that the scope of a variable is determined by the block of code in which it is declared, 
      not by the block of code in which it is used.
    - Improved maintainability: Lexical scope makes your code more maintainable by making it easier to change variables without affecting other parts of your code. 
      This is because variables are only visible within their own scope, so changes to a variable in one scope will not affect variables in other scopes.
    - https://cleverzone.medium.com/lexical-scope-in-javascript-929789101dab#:~:text=3%20min%20read,in%20which%20it%20is%20used.
*/

//global scope
var username = "global username"
function Subscribe() {
    // inner scope 1
    console.log("global scope:", username);
    var name = "Roadside Coder"; // local scope
    function displayName() { // displayName is called as closure
        // inner scope 2 as local scope
        var innerScope = "inner local scope";
        console.log("local scope:", innerScope);
        console.log("lexical scope:", name);
        console.log("global scope:", username);
    }
    displayName();
    // console.log("innerScope:", innerScope); // this lexical scope not allowed.
}
Subscribe();

console.log("__________________________________");
// example 2
function makeFun() {
    var name = "Sagar";
    function displayName(num) {
        console.log(name, num);
    }
    return displayName;
}

makeFun()(5);   // call: way 1
const innerFun = makeFun();
innerFun(10);   // call: way 

console.log("__________________________________");
/* #2. Closure scope chain : (in nested functions)
    - Every closure has three scopes:
        1. own scope where variables defined between its curly braces (local scope)
        2. Outer Function's variables
        3. Global variable
*/

var g = 10;
function sum(a) {
    return function (b) {
        return function (c) {
            return g + a + b + c;
        }
    }
}
console.log("closure sum is:", sum(1)(2)(3)); // 16

console.log("__________________________________");
// Que 1: Shadowing
let count = 0;
(function printCount() {
    if (count === 0) {
        let count = 1; // shodowing // hide parent variable, by creating local variable.
        console.log("if count:", count);
    }
    console.log("printCount:", count);
})();
// ans: 1, 0

console.log("__________________________________");
//Q2. Write a function that would allow you to do this

// const createBase = num1 => num2 => num2 + num1; // variable so, we have to declare before use.
var createBase = num1 => num2 => num2 + num1;

var addSix = createBase(6);
console.log("10:", addSix(10)); // 16
console.log("21:", addSix(21)); // 27
// function createBase(num) {
//     // db work .. 10 
//     return (innerNum) => console.log("add Num:", num + innerNum);
// }
// function createBase(num1) {
//     return num2 => num2 + num1;
// }

console.log("__________________________________");
// Q3. Time Optimization:
function find(index) {
    let a = [];
    for (let i = 0; i < 1000000; i++) {
        a[i] = i * 1;
    }
    console.log(a[index]); // everytime, do initialization and then check.
}

function closureFind() {
    let a = [];
    for (let i = 0; i < 1000000; i++) {
        a[i] = i * 1;
    }

    return function (index) {   // intialization once on first time. only check everytime.
        console.log(a[index]);
    }
}

console.time("6");
// find(6); // 36 // taking 15.996ms
findIndex = closureFind();
findIndex(6); // 16.652ms
console.timeEnd("6");
console.time("12");
// find(12); //144 // 23.09ms
findIndex(12); //0.216ms
console.timeEnd("12");

console.log("__________________________________");
// Q. setTimeout with Var
for (var i = 0; i < 5; i++)
    console.log(i);     // 01234

function a() {
    for (var i = 0; i < 3; i++) {   //use let for : 0 1 2
        setTimeout(function log() {
            console.log(i); // 3 3 3
        }, i * 1000);
    }
}
// a() // 3 3 3
// print a output: 0 1 2, using var for loop?
// => using closure we can achieve

function b() {
    for (var i = 0; i < 3; i++) {

        function log(i) {
            setTimeout(function log() {
                console.log(i); // 012
            }, i * 1000);
        }

        log(i);
    }
}

b();

console.log("_______________ Clousure Private Counter _______________");
// Q5. How would you use a closure to create a private counter?

function counter() {
    var _counter = 0;   // private property
    function add(num) {
        _counter += num;
    }

    function retrive() {
        return "Counter:" + _counter;
    }

    return {
        add,
        retrive
    }
}

const myCounter = counter();
myCounter.add(5);
myCounter.add(10);
console.log(myCounter.retrive());

console.log("_______________ Module Pattern _______________");
// Q6. What is Module Pattern?
var Module = (function () {
    function privateMethod() {      // private method
        console.log("privateMethod");
    }

    return {
        publicMethod: function () {
            // can call privateMethod.
            console.log("publicMethod");
        }
    }
})();// call

Module.publicMethod();

console.log("______________________________");

// Q7. Make this run only once
let view;
function pleaseSubscribe(name) {
    view = "SPWorld";
    console.log("Thanks", name, "for subscribe", view);
}
pleaseSubscribe("Michel");
pleaseSubscribe("Michel");
pleaseSubscribe("Michel");

// solution:
function pleaseSubscribe2(name) {
    let _called = 0;
    return function () {
        if (_called > 0) {
            console.log(name, "already subscribe to", view);
            return;
        }
        view = "Sagar Patil";
        console.log("Thanks", name, "for subscribe", view);
        _called++;
    }
}

mySubscribe = pleaseSubscribe2("Aron");
mySubscribe();
mySubscribe();
mySubscribe();

console.log("________________Once Polyfill______________");
// if interviewer ask more generic function for call once?
// => Q.8 Once Polyfill
function once(fun, context) {
    let ran;
    return function () {
        if (fun) {
            ran = fun.apply(context || this, arguments); // .apply(this, [arg1,arg2])
            fun = null;
        }
        // console.log("ran called")
        return ran;
    }
}

const hello = once((a, b) => console.log("hello", a, b));
hello(1, 2); // will run only once
hello(3, 4); // not run
hello(5, 6); // not run

console.log("________________Memoize Polyfill______________");
// Q9. Implement Memoize Polyfill
const clumsyProduct = (num1, num2) => {
    for (let i = 0; i < 100000000; i++) { }
    return num1 * num2;
}

console.time("First clumsyProduct Call");
console.log(clumsyProduct(9555, 9777));
console.timeEnd("First clumsyProduct Call"); //First clumsyProduct Call: 72.298ms

console.time("Second clumsyProduct Call");
console.log(clumsyProduct(9555, 9777)); // Parameter are same. So second time it will taking almost time. How to reduce?
console.timeEnd("Second clumsyProduct Call"); //Second clumsyProduct Call: 82.025ms

function myMemoize(fn, context) {
    const res = {};
    return function (...args) {
        var argsCache = JSON.stringify(args); // argsCache = "[9555,9777]"  // store args as key, and ans as value.
        if (!res[argsCache]) {
            res[argsCache] = fn.call(context || this, ...args);
        }
        // console.log(res); // { '[9555,9777]': 93419235, '[8888,7777]': 69121976 }
        return res[argsCache];
    }
}

const memoizedClumzyProduct = myMemoize(clumsyProduct);

console.time("First Call");
console.log(memoizedClumzyProduct(9555, 9777));
console.timeEnd("First Call"); //First Call: 82.225ms

console.time("Second Call");
console.log(memoizedClumzyProduct(9555, 9777)); // Parameter are same. So second time it will take less time
console.timeEnd("Second Call"); //Second Call: 0.222ms

console.time("2 First Call");
console.log(memoizedClumzyProduct(8888, 7777));
console.timeEnd("2 First Call"); //First Call: 82.225ms

console.time("2 Second Call");
console.log(memoizedClumzyProduct(8888, 7777)); // Parameter are same. So second time it will take less time
console.timeEnd("2 Second Call");

console.time("prev First Call");
console.log(memoizedClumzyProduct(9555, 9777));
console.timeEnd("prev First Call");
