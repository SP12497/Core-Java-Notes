const user = {
    name: "Sagar",
    age: 26,
    "like this video": true,
}
user.name = "Suraj"
// user."like this video" // error
console.log("like this video:", user["like this video"]);

console.log("__________________#1________________");
// 1. Deleting Object Properties:
console.log(delete user.age);       // true
delete user["like this video"];
console.log(user);
// delete local variable? possible?
// delete is only used to delete the attributes of object,  we can't delete global/local variable and object
const funUser = (function (a) {
    console.log("a=>", delete a);    // false // Deleting Variables: The delete operator can’t delete a variable
    const stud = { address: "addreess", zip: 123 }
    delete stud;   // false
    delete stud.address; // true
    return { a, stud };
})(5);

console.log(funUser);       // { a: 5, stud: { zip: 123 } }

let arr = [1,2,3,4];
delete arr;     // false
delete arr[2];  // delete will not reindex the array or update its length, which may leave undefined holes in the array. For removing elements from an array, it’s recommended to use methods like pop(), shift(), or splice() instead.
console.log(arr);       // [ 1, 2, <1 empty item>, 4 ]

// Deleting Variables: The delete operator can’t delete a variable
var x = 3;
console.log(delete x);  // false

nonDeclareVariable = "aa";
console.log("nonDeclareVariable", delete nonDeclareVariable);   // true


console.log("__________________#2________________");
const property = "first name";
const nameValue = "Sagar";

const user1 = {
    [property]: nameValue
}
console.log("user1:", user1);

console.log("__________________#3________________");
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

console.log("__________________#4________________");
//Q1. What's the output?
const obj = {
    key1: "one",
    key2: "two",
    key1: "Three", // this will update the "key1"
}
console.log("same key in initialization:", obj);    // same key in initialization: { key1: 'Three', key2: 'two' }
console.log("same key in initialization:" + obj);   // same key in initialization:[object Object] 
console.log(`same key in initialization:${obj}`);   // same key in initialization:[object Object] 
console.log(`same key in initialization:${JSON.stringify(obj)}`);   // same key in initialization:{"key1":"Three","key2":"two"}

console.log("__________________#5________________");
// Q2. Create a function multiplyByTwo(obj), that multiplies
// Double all numeric property values of nums object.
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

console.log("__________________#6________________");
//Q3. output?
const a = {}
const b = { key: "b" }
const c = { key: "c" }
a[b] = 123;
a[c] = 456;

console.log(a[b]); // ans?
console.log(a);
/* Concept:
    we can't store object as a key
    object will get convert into string as '[object Object]'
    a['[object Object]'] = 123;
    a['[object Object]'] = 456;
    Ans is: 456
*/
// to store object as keys:
const oo = {}
oo[JSON.stringify(b)] = 123;
oo[JSON.stringify(c)] = 123;
console.log(oo);            // { '{"key":"b"}': 123, '{"key":"c"}': 123 }


console.log("__________________#7________________");
// Q4. JSON
const strUser = JSON.stringify(user);
console.log("strUser:", strUser);
console.log("parse strUser:", JSON.parse(strUser));
// localStorage.setItem("strUser", strUser); // it will store string object in chrome local storage
// sessionStorage.setItem("user", user); // it will store '[object Object]' in chrome session storage

console.log("__________________#8________________");
// Q6. spread operator 
console.log("...INDIA:", [..."INDIA"]);     // ...INDIA: [ 'I', 'N', 'D', 'I', 'A' ]
console.log("...INDIA:", ...[..."INDIA"]);  // ...INDIA: I N D I A

const admin = { isAdmin: true, ...user2 };
console.log(admin);

console.log("__________________#9________________");
// Q7.
const settings = {
    username: "sagar",
    password: "12345678",
    isValid: true
}

console.log("settings with replaces:", JSON.stringify(settings, ["username", "isValid"])); // ignore password:// output: {"username":"sagar","isValid":true}
console.log("settings with function:", JSON.stringify(settings, (key, value) => key === "password" ? undefined : value));   // ignore password, // output: {"username":"sagar","isValid":true}
console.log("settings separated by $$:", JSON.stringify(settings, ["username", "isValid"], "$$")); 
/*  -- by default separated by comma. comma is replaced by $$.
settings separated by $$: {
$$"username": "sagar",
$$"isValid": true
}
*/

console.log("__________________#10________________");
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

console.log("__________________#11________________");
// Destructing Assignment       // Object Destructing
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
const { name: myName, age, address: { zip, landmark: nearby } } = student;
console.log("myName:", myName);
console.log("age:", age);
console.log("zip:", zip);
console.log("address:", address);

console.log("__________________#12________________");
// Q. 10 Spread and Rest operator
// function getItems(fruitList, ...args, favoriteFruits) { //error: rest operator ...args always be last param
function getItems(fruitList, favoriteFruits, ...args) { // rest operator ...args always be last param
    return [...fruitList, ...args, favoriteFruits]; // spread operator ...args can be any where.
}
console.log(getItems(["banana", "apple"], "pear", "orange", "grapes"));
//ans: [ 'banana', 'apple', 'orange', 'grapes', 'pear' ]

console.log("__________________#13________________");
// Q. 11 Object referencing (Shallow Copy)?
let ca = user;
user.age = 30;
console.log("age:", ca.age, user.age);   // both object, reference is same.

// console.log("===", { a: 1 } === { a: 1 }); // false
console.log("==", { a: 1 } == { a: 1 }); // false

let person = { name: "Anshul" }
const members = [person];  // member[0] 
// person.name = null; this will affect the members
person = null // it wont affect the members
console.log(members); //[ { name: 'Anshul' } ]


console.log("__________________#14________________");
// Q14 Deep Copy
let aa = 10;
let bb = aa; // deep copy of variables.
aa = 20;
console.log("variables aa bb:", aa, bb);    // 20 10
//---
let aObj = { num: 10 }
let bObj = aObj;    // Shallow Copy.
aObj.num = 11;
console.log("aObj:", aObj);// aObj: { num: 11 }
console.log("bObj:", bObj);// bObj: { num: 11 }

aObj = { num: 15 }; // re-assign, it won't change the reference.
console.log("aObj:", aObj);// aObj: { num: 15 }
console.log("bObj:", bObj);// bObj: { num: 11 }

aObj = null;
console.log("aObj:", aObj);// aObj: null
console.log("bObj:", bObj);// bObj: { num: 11 }

aObj = { num: 11 }
aObj.num = 22
console.log("aObj:", aObj);// aObj: { num: 22 }
console.log("bObj:", bObj);// bObj: { num: 11 }

//---
cObj = { ...aObj } // deepCopy // Note: deep copy of parent elements (student.name), but shallow copy of child elements like zipcode (student.addess.zipcode),
aObj.num = 33;
console.log("aObj:", aObj);// aObj: { num: 33 }
console.log("cObj:", cObj);// cObj: { num: 22 }

const value = { number: 10 }
const multi = (x = { ...value }) => { // deep copy via default parameter
    console.log(x.number *= 2);    // modify the value.num
}

multi(); // no value so, it will take deep copy of default value, so original value wont change
multi();
multi(value); // shollow copy to args, so it will modify original value
multi(value);
multi({ ...value });    // spread object.
multi({ ...value });
// ans 20 20 20 40 80 80

console.log("__________________Shallow Deep Copy________________");
//Q 17: shallow deep copy.
let user3 = {
    name: "Sagar",
    age: 26,
    address: {
        zip: 123
    }
}
const fullyDeepCloned = structuredClone(user3); // Introduced in JS 17
const cloneUser2 = JSON.parse(JSON.stringify(user3));   // Fully Deep copy
const cloneUser1 = Object.assign({}, user3); // parent attributes are Deep copy. Shallow copy of nested objects (zip).
const cloneUser3 = { ...user3 } // parent attributes are Deep copy. Shallow copy of nested objects.
user3.name = "Name changed"
user3.address.zip = 999
console.log("structuredClone:", fullyDeepCloned);   // { name: 'Sagar', age: 26, address: { zip: 123 } }
console.log("cloneUser1:", cloneUser1);             // { name: 'Sagar', age: 26, address: { zip: 999 } }
console.log("cloneUser2:", cloneUser2);             // { name: 'Sagar', age: 26, address: { zip:123 } }
console.log("cloneUser3:", cloneUser3);             // { name: 'Sagar', age: 26, address: { zip: 999 } }

// TODO: Learn Object class properties.