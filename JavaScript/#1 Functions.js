// Q1. What is Function Statement (also known as Function Declaration)?
square(4);  // works // Function Statements are hoisted before start execution of file.
function square(num) {
    return num * num;
}
square(5);  // works

// Q2. What is Function Expression?
// square2(4); // hoisting: square2 undefined here. // TypeError: square2 is not a function.
const square2 = function (num) {
    return num * num
}
square2(3);

// Q: What is the diffence betwee Function statement and function expression?
// => Hoisting.

// Q. Anonymous Function:
// function () {};     // SyntaxError: Function statements require a function name  // here, its showing statements not expression

// Q. Named Function Expression?
const cube = function numCube(num) {
    console.log(numCube);
    return num * num * num;
}
// numCube(2); // ReferenceError: numCube is not defined
cube(3);

// Q: Arrow Function:
const cube2 = (num) => {
    return num * num * num;
}
const cube3 = num => num * num * num;

/*
#3 First class function (First Class Citizens):
https://www.geeksforgeeks.org/what-is-the-first-class-function-in-javascript/
    - A programming language is said to have First-class functions 
      if functions in that language are treated like other variables. 
    - So the functions can be assigned to any other variable or passed as an argument or can be returned by another function. 
      JavaScript treats function as a first-class citizen. 
    - This means that functions are simply a value and are just another type of object.
    - Usage of First-Class Function (Ability to do below things is called first class function.)
        - It can be stored as a value in a variable.
        - It can be returned by another function.
        - It can be passed into another function as a parameter.
        - It can also stored in an array, queue, or stack.
        - Also, It can have its own methods and property.
*/
var d = function (param1) {     // can be stored as a value in a variable
    return function xyz() {     // can be returned by another function

    }
}
d(square);  // can be passed into another function as a parameter




// ---------
function displaySquare(fn) {
    console.log("Square is:", fn(5));
}
displaySquare(square);

// -------------------------
// #4. IIFE Immediately invoke function expression
(function square3(num) {
    console.log("IIFE1:", num * num);
})(5);

//output based question
(function (x) {
    return (function (y) {
        console.log("IIFE2:", x);
    })(2);
})(1);

(function (x) {
    return (function (y) {
        console.log("IIFE3:", x);
    });
})(1)(2);

(function (x) {
    return function (y) {
        console.log("IIFE4:", x);
    };
})(1)(2);

// -------------------------
// Hoisting based que:
// # function scope - o/p based question:
for (let i = 0; i < 5; i++) { // for var, its will give output as 5,5,5,5,5
    setTimeout(function () {
        console.log("let i:", i); // 0,1,2,3,4
    }, i * 1000);
}

for (var i = 0; i < 5; i++) { // for var, its will give output as 5,5,5,5,5
    setTimeout(function () {
        console.log("var i:", i); // 0,1,2,3,4
    }, i * 1000);
}

// -------------------------
//# function hoisting:
hoistedFun();   //work
function hoistedFun() {
    console.log("hoisted function called.");
}

console.log(x); // undefined // variable is hoisted but not assigned the value // let/const x will throw error
var x = 5;

// output based que
var a = 21;
var funA = function () {
    console.log("funA:", a);
    var a = 20;
};
funA();
// ans: undefined: inner var a is in local scope and its hoisted in function.

let b = 21;
let funB = function () {
    // console.log("funB:",b); // throw error, inner b not hoistd in local scope.
    let b = 20;
};
funB();

// -------------------------
// # Params vs Arguments
// function square4(a) {} // a is params
// square4(5)   // 5 is argument

/* -------------------------
Spread vs Rest Operator:
    # fun(...Spread); vs fun(...rest){};
    spread on argument level.
    rest in on params level. it  always a last parameter
*/

function mul1(num1, num2) {
    console.log("mul1:", num1 * num2);
}
var arr = [5, 6];
var arr2 = [2, 3, 4];
mul1(...arr);   // ...arr is spread operation. 
mul1(...arr2);  // works, accept first 2 as params: num1=2, num2=3;

function mul2(...nums) {    // ...nums is rest operator
    console.log("mul2:", nums[0] * nums[1]);
}
mul2(...arr);

// o/p based que.
// const fn = (x, y, ...nums, z) => {} // error: rest operation should always last parameter.
const fn2 = (x, y, z, ...nums) => { console.log(x, y, z, nums); }
fn2(2, 3, 4, 5, 6, 7); // x=2, y=3, z=4, nums=[5,6,7]

/*
-------------------------
 callback function:
- a callback function is a function passed into another function as an argument,
which is then invoked inside the outer function to complete some kind of routing or action.

document.addEventListener("click", function (params) { console.log("This is callback function") });

-------------------------
Javascript new name is Ecma script.
# Arrow Function: introduce in ES6: 2015
*/
const addRegularFun = function (params) {
    return params;
}
const addArrowFun1 = (num1, num2) => {
    return num1 + num2;
}
const addArrowFun2 = (num1, num2) => num1 + num2;
const square5 = num => num * num; // single param

console.log("square5  no 6:", square5(6));

/* -------------------------
Differenct in Arrow and Regular fun
1. Syntax
2. Implicit "return" keyword:
    // return optional for arrow based on the which syntax are we using, () => num
3. arguments: 
    - we dont have arguments keyword in arrow function
*/
function fn3() {
    console.log("arguments:", arguments); // [Arguments] { '0': 5, '1': 10, '2': 15 }
    console.log(arguments[1]); // 10
}
fn3(5, 10, 15);

// 4. "this" keyword:
let user = {
    username: "Roadside Coder",
    arrowFun: () => {
        console.log("arrowFun Subscribe to ", this.username); // undefined, this points to global scope // this = parent of parent
    },
    reguarFun() {
        console.log("regularFun Subscribe to ", this.username); // "Roadside Coder" // this = parent
    }
}
user.arrowFun();
user.reguarFun();


// Constructur function:
function Counter() {
    var count = 0;
    this.incrementCounter = function () {
        count++;
        console.log(count);
    }
    this.decrementCounter = function () {
        count--;
        console.log(count);
    }
}

// var counter1 = Counter();   // error: fuction is not returning any value so counter1.incrementCounter() will throw error.
// counter1.incrementCounter();

var counter2 = new Counter();
counter2.incrementCounter();
counter2.incrementCounter();
counter2.decrementCounter();