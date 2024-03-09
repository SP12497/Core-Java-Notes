const user = {
    name: "Sagar",
    age: 26,
    "like this video": true,
}
user.name = "Suraj"
console.log("like this video:", user["like this video"]);
delete user.age;
delete user["like this video"];
console.log(user);

// delete local variable? possible?
// delete is only used to delete the attributes of object,  we can't delete global/local variable
const funUser = (function (a) {
    delete a;   // not work also it will not throw error
    const stud = { address: "addreess", zip: 123 }
    delete stud;   // not work
    delete stud.address; // will work
    return { a, stud };
})(5);

console.log(funUser);

//---------------
const property = "firstName";
const nameValue = "Sagar";

const user1 = {
    [property]: nameValue
}
console.log("user1:", user1);

// for in loop with object:
const user2 = {
    name: "Sagar",
    age: 24,
    isTotallyAwesome: true
}
// used to iterate the object
for (key in user2) {
    console.log(key, ":", user2[key]);
}

//Q1. What's the output?
const obj = {
    key1: "one",
    key2: "two",
    key1: "Three", // this will update the key1
}
console.log("same key in initialization:", obj);

// Q2. Create a function multiplyByTwo(obj), that multiplies
// all numeric property values of nums by 2.

let nums = {
    a: 100,
    b: 200,
    title: "My nums"
}
multiplyByTwo(nums);
console.log("nums:", nums);
// solution:
function multiplyByTwo(obj) {
    for (key in obj) {
        if (typeof obj[key] === "number") {
            obj[key] *= 2;
        }
    }
}

//Q3. output?
const a = {}
const b = { key: "b" }
const c = { key: "c" }
a[b] = 123;
a[c] = 456;

console.log(a[b]); // ans?
console.log(a);
/* Concept:
    we cant store object as a key
    object will get convert into string as '[object Object]'
    a['[object Object]'] = 123;
    a['[object Object]'] = 456;
    Ans is: 456
*/

// Q4. 

const strUser = JSON.stringify(user)
console.log("strUser:", strUser);
console.log("parse strUser:", JSON.parse(strUser));
// localStorage.setItem("strUser", strUser); // it will store string object in chrome local storage
// localStorage.setItem("user", user); // it will store '[object Object]' in chrome local storage

// Q6. spread operator 
console.log("INDIA:", [..."INDIA"]); // [ 'I', 'N', 'D', 'I', 'A' ]

const admin = { isAdmin: true, ...user2 };
console.log(admin);

// Q7.
const settings = {
    username: "sagar",
    password: "12345678",
    isValid: true
}

console.log("settings:", JSON.stringify(settings, ["username", "isValid"])); // ignore password:// output: {"username":"sagar","isValid":true}

const shape = {
    radius: 10,
    diameter() {
        return this.radius * 2; // in regular fun, this points to current object
    },
    perimeter: () => 2 * Math.PI * this.radius, // in arrow fun, this points to window object
    getRadius: () => this.radius // in arrow fun, this points to window object
}
console.log(shape.diameter()); // 20
console.log(shape.perimeter()); // NAN
console.log(shape.getRadius()); // undefined

let student = {
    name: "Sagar",
    age: 26,
    address: {
        zip: 123456,
        landmark: "my landmark"
    }
}

const name = "existing name property";
const address = "my test address";
const { name: myName, age, address: { zip } } = student;
console.log("myName:", myName);
console.log("age:", age);
console.log("zip:", zip);
console.log("address:", address);

// Q. 10 Spread and Rest operator
// function getItems(fruitList, ...args, favoriteFruits) { //error: rest operator ...args always be last param
function getItems(fruitList, favoriteFruits, ...args) { // rest operator ...args always be last param
    return [...fruitList, ...args, favoriteFruits]; // spread operator ...args can be any where.
}
console.log(getItems(["banana", "apple"], "pear", "orange", "grapes"));
//ans: [ 'banana', 'apple', 'orange', 'grapes', 'pear' ]

// Q. 11 Object referencing?
let ca = user;
user.age = 30;
console.log(ca.age); // 20

console.log({ a: 1 } === { a: 1 }); // false
console.log({ a: 1 } == { a: 1 }); // false

let person = {name : "Anshul"}
const members = [person];  // member[0] 
person = null // it wont affect the members
// person.name = null; this will affect the members
console.log(members); //[ { name: 'Anshul' } ]


// Q14
let aa = 10;
let bb = aa; // deep copy
aa = 20;
console.log("bb:", bb);
    //---
let aObj = {num:10}
let bObj = aObj;
aObj.num = 11;
console.log("aObj:",aObj);// aObj: { num: 11 }
console.log("bObj:",bObj);// bObj: { num: 11 }
aObj = {num:15}; // re-assign, it won't change the reference.
console.log("aObj:",aObj);// aObj: { num: 15 }
console.log("bObj:",bObj);// bObj: { num: 11 }
aObj = null;
console.log("aObj:",aObj);// aObj: null
console.log("bObj:",bObj);// bObj: { num: 11 }
aObj = {num:11}
aObj.num = 22
console.log("aObj:",aObj);// aObj: { num: 22 }
console.log("bObj:",bObj);// bObj: { num: 11 }
//---
cObj = {...aObj} // deepCopy
aObj.num = 33;
console.log("aObj:",aObj);// aObj: { num: 33 }
console.log("cObj:",cObj);// cObj: { num: 22 }

const value = {number: 10}
const multi = (x = {...value}) => { //default deep copy value
    console.log(x.number *= 2);    // modify the value.num
}

multi(); // no value so, it will take deep copy of default value, so original value wont change
multi(); 
multi(value); // shollow copy to args, so it will modify original value
multi(value);
// ans 20 20 20 40

//Q 17: shallow deep copy.
let user3 = {
    name: "Sagar",
    age:26,
    address: {
        zip:123
    }
}
const closeUser1 = Object.assign({}, user); // This will not clone nested objects.
const closeUser2 = JSON.parse(JSON.stringify(user3));
const closeUser3 = {...user3}
user.name = "Patil"
user.zip = 456
console.log("closeUser1:", closeUser1);
console.log("closeUser2:", closeUser2);
console.log("closeUser3:", closeUser3);