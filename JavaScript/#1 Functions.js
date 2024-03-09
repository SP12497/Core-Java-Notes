// Q1. What is function Declarations?
function square(num) {
    return num * num;
}

// Q2. What is function expression?
// const square2 = anonymous function.
const square2 = function (num) {
    return num * num
}
square2(3);

// #3 First class function:
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

