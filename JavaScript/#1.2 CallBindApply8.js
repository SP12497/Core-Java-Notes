// https://roadsidecoder.hashnode.dev/javascript-interview-questions-call-bind-and-apply-polyfills-output-based-explicit-binding
// Q. What is call?
var obj = { name: "Sagar" };

function sayHello() {
    console.log("this.name:", this.name);
}
sayHello();
sayHello.call(obj); // call(object, List<arguments>)

function sayHello2(age, city) {
    console.log("this.name:", this.name, ". and my age is:", age, ". and my city is:", city);
}
sayHello2.call(obj, 26, "CallNDB");

// Q. What is apply?
// apply is same as call but its takes all the arguments in form of an array.
sayHello2.apply(obj, [26, "ApplyNDB"]);

// Q. What is bind?
// instead of calling a function, it will return a reusable function that we call call later.
const bindFunction = sayHello2.bind(obj);
bindFunction(55, "BindCity");
bindFunction(55, "BindNdb");
// Output based Questions:
// Q1. 

var person = { name: "Yogesh" }
function sayHi(age) {
    return `${this.name} is ${age}`;
}

console.log(sayHi.call(person, 44)); // log message
console.log(sayHi.bind(person, 44)); // log function itself

console.log("______________________________");
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
console.log("getAge()", person2.getAge());
console.log("getAge.call()", person2.getAge.call(person));
console.log("getAge.apply()", person2.getAge.apply(person));
console.log("getAge.bind()", person2.getAge.bind(person));
console.log("IIFE getAge.bind()", person2.getAge.bind(person2)());

// Q3.

var status = "global"; // for chrome
setTimeout(() => {
    const status = "local";

    const data = {
        status: "object scope",
        getStatus() {
            return this.status;
        }
    }
    console.log("getStatus()", data.getStatus()); // "object scope"
    console.log("getStatus.call(this)", data.getStatus.call(this)); // on chrome: "global"
    // setTimeout expires immediatly, so its this object points to global space.
}, 0);

const animals = [
    { species: "Lion", name: "King" },
    { species: "Whale", name: "Queen" },
];

function printAnimal(i) {
    this.print = function () {
        console.log("#" + i, this.species, ":", this.name);
    }
}

for (let i = 0; i < animals.length; i++) {
    printAnimal.call(animals[i], i);
}


function f() {
    // alert( this ); // ?
    console.log(this);
}

let user = {
    // g: f.bind({name:'sagar'})    // this will point to passed obj {name:'sagar'}
    g: f.bind(null) // this will point to window object
};

user.g();
