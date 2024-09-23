/* 
Blog: https://roadsidecoder.hashnode.dev/javascript-interview-questions-currying-output-based-questions-partial-application-and-more
Currying in JS
    f(a,b) into f(a)(b) // f(a)(b) is a currying 
    - Currying is a function that takes one argument at a time 
      and returns a new function expecting the next argument. 
      It is a conversion of functions from callable as f(a,b,c) into callable as f(a)(b)(c).
*/

function f1(a) {
    return function (b) {
        return `${a} ${b}`
    }
}
console.log(f1(5)(6));

/*Q. Why should we use currying?
Following are the reasons why currying is good :
    - It makes a function pure which makes it expose to less errors and side effects.
    - It helps in avoiding the same variable again and again.
    - It is a checking method that checks if you have all the things before you proceed.
    - It divides one function into multiple functions so that one handles one set of responsibility.
*/
// Q

console.log("______________________________");
function evaluate(operation) {
    return (a) => {
        return (b) => {
            if (operation === "sum")
                return a + b;
            else if (operation === "multiply")
                return a * b;
            else if (operation === "divide")
                return a / b;
            else if (operation === "subtract")
                return a - b;
            else return "No / Invalid Operation Selected"
        }
    }
}

console.log(evaluate("sum")(5)(4));
console.log(evaluate("multiply")(5)(4));
console.log(evaluate("invalid")(5)(4));

console.log("______________Infinite Currying ________________");
// Q. Infinite Currying 
// sum(1)(2)(3)...(n)
// sum(1)(2) // 3
// sum(1)(2)(5) // 8

// normal:
// fixed, accept only 2 params.
function add(a) {
    return function (b) {
        return function () {
            return a + b;
        }
    }
}
console.log("normal currying:" + add(5)(2)());

// Infinite currying:
function add(a) {
    return function (b) {
        if (b) {
            // const c = a+b; return add(c);
            return add(a + b);
        }
        return a; // call using ()
    }
}

console.log("infinite currying:", add(5)(10)(15)());

const lambdaInfiniteCurrying = (a) => {
    const fun3 = (b) => {
        if (b) {
            // return lambdaInfiniteCurrying(a+b);
            a += b;
            return fun3;
        }
        return a;
    }
    return fun3;
}
console.log(lambdaInfiniteCurrying(10)(2)(2)());

console.log("______________________________");

// Q. Difference Currying vs Partial Application
// currying function: 3 arguments = 3 functions
// Partial Application:
function sum1(a) {
    return function (b, c) { // arity instead of 1, accepting 2 args, so reduce 1 functions
        return a + b + c;
    }
}

const add10 = sum1(10);
console.log(add10(4, 6));
console.log(add10(5, 9));
// OR
console.log(sum1(20)(5, 6));
/*
- We concluded that the above function named sum expected 3 arguments and had 2 nested functions (Partial Application) 
  unlike previous implementation where the function expected 3 arguments and had 3 nested functions.(currying)

- Partial application transforms a function into another function with smaller arity.
*/


console.log("______________________________");
// Q5: Manupulation DOM
// index.html
// function updateElementText(id) {
//     return function (content) {
//         document.querySelector('#' + id).textContent = content;
//     }
// }

// const updateHeader = updateElementText("heading") // initialize id once
// updateHeader("Hello Patil"); // and update text again and again


console.log("______________________________");
// Q6: curry() implementation
// Converts f(a,b,c) into f(a)(b)(c)
function curry(func) {
    return function curriedFunc(...args) {
        // console.log("length:",args.length, func.length);
        if (args.length >= func.length) {
            return func(...args); // call function
        } else {
            return function (...next) {
                return curriedFunc(...args, ...next); // pass current argument and next () argument
            }
        }
    }
}

const sum = (a, b, c, d) => a + b + c + d;      // func.length = 4
const totalSum = curry(sum); // sum have 4 param so func.length = 4
console.log("curry() implementation 1:", totalSum(1)(5)(6)(3)); //(1): args.length =1, (5): args.length =2, (6): args.length =3, (3): args.length =4
console.log("curry() implementation 2:", totalSum(1)(5)(6,3));

const val = [5, 3, 2, 6]
console.log("Spread Operator:", sum(...val));

const val2 = [5, 3]
const val3 = [2, 6]
console.log("Spread Operator:", sum(...val2, ...val3));
const val4 = 5
const val5 = 7
console.log("Spread Operator:", sum(...val2, val4, val5));
