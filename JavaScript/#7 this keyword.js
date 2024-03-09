// 'this' keyword in JS (Implicit Binding)
this.a = 5;
b = 6;
console.log(this.a);
console.log("window object:", this); // check on chrome
//---
function getParams() {
    console.log("getParams", this.a); // still its points to its global obj, ie window obj
}
getParams();

const getParams1 = () => {
    console.log("arrow:", this.a); // still its points to its global obj, ie window obj
}
getParams1()

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
//Arrow:

console.log("Arrow this keyword");
let userArrow = {
    name: "Sagar",
    age: 26,
    arrowDetails: () => {
        console.log("arrowDetails=>", this); // parent of scope in regular function this
        // => parent normal function //(no parent so global space)
    },
    childObj: {
        newName: "Patil",
        getInnerDetails: () => {
            console.log("Arrow=> inner attribute:", this.newName, ",global attibute (parent of reguler function this):", this.name, "this:", this);
            // => parent normal function //(no parent so global space)
        }
    },
    getDetails() {
        const nestedArrow = () => console.log("getDetails=>", this.name); // name will work
        nestedArrow(); // => parent normal function // getDetails() parent space => userArrow space.
    }
}
// take parent regular function outer value
userArrow.arrowDetails();
userArrow.childObj.getInnerDetails();
userArrow.getDetails()

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

// Q1 Output
var globalVar = 12345;
this.myThisVar = "######"
function makeUser() {
    return {
        name: "Sagar",
        ref: this
    }
}
let userMake = makeUser();
console.log("makeUser 1:", userMake.ref.myThisVar); // undefined
console.log("makeUser 2:", userMake.ref); // returns window object

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

//--------
const user4 = {
    name: "Rohit",
    logMessage() {
        console.log(this.name);
    }
}
// call:
setTimeout(user4.logMessage, 1000); // callback //ans: undefined // window object
//concept: logMessage is callback so setTimeout will copy only the logMessage function into setTimeout
//          so after 1 sec, setTimeout won't access of "user" class, so function executed independently, 
//          and user parent is not there so, it points to window object.
// how to fix this?
setTimeout(() => {
    user4.logMessage() // Rohit // pass a function which will call user4.logMessage, so in this case, setTimeout will not copy logMessage()
}, 1000);

//Q.

var length = 4; // this should be at the start of the file
function callback() {
    console.log("callback:", this.length, ", b:", this.b); //global space is window, so: 4
}
const object = {
    length: 6,
    myMethod(fn) {
        fn();   //callback: undefined , b: 6
    }
}
object.myMethod(callback) // 4

const object2 = {
    length: 6,
    myMethod(fn) {
        arguments[0](); // callback: 3 , b: undefined// arguments.length = 3
    }
}

object2.myMethod(callback, 6, 7);

// Q6. Implement this code
// const result = calc.add(10).multiply(5).substract(30).add(10);
// console.log("result", result.total);

const calc = {
    total: 0,
    add: function (num) {
        this.total += num;
        return calc;
    },
    multiply(num) {
        this.total *= num;
        return calc;
    },
    substract: function (num) {
        this.total -= num;
        return calc;
    }
}

const result = calc.add(10).multiply(5).substract(30).add(10);
console.log("result", result.total);