// Object Literals"

const mySym = Symbol("key1")
const workingDaysKey = "workingDays";
var jsUser = {
    name: "Sagar", 
    age: 26,
    [workingDaysKey]: ["Monday", "Tuesday"],
    "full name": "Sagar Patil",
    [mySym]: "myKey1",
    isActive: true,
    "city": "ndb mh"
};

console.log(jsUser);    // here, u can check the symbol.
console.log(jsUser.age);
console.log("mySym:", jsUser.city);    // ndb mh
console.log("mySym:", jsUser["city"]);    // ndb mh
console.log(jsUser["isActive"]);
console.log(jsUser["full name"]);
console.log(typeof jsUser[mySym]);
console.log(jsUser[mySym]); // myKey1 // This is the actual way to access synbol, We cant get symbol using dot notation
console.log("mySym:", jsUser.mySym);    // undefined


// Object.freeze(jsuser);
// jsuser.age = 50;    // wont update the age. also it wont throw error.
// console.log(jsuser.age); // 26

jsUser.greeting = function () {
    console.log("Hello JS User");
}
jsUser.greetingName = function () {
    console.log(`Hello ${this.name}`);
}

console.log(jsUser.greeting());         // Hello JS User
console.log(jsUser.greetingName());     // Hello Sagar


console.log("________________");
// const instaUser = new Object();
const instaUser = {};
instaUser.Id = "123abc";
instaUser.name = "Sagar";
instaUser.isLoggedIn = false;
// console.log(instaUser);

const regularUser = {
    name: "sagar",
    address: {
        zipcode: 123
    }
}
console.log(regularUser.address.zipcode);
console.log(regularUser["address"].zipcode);

console.log(regularUser?.["address"]?.zipcode);// Optional chaining (?.)
console.log(regularUser["address"]["zipcode"]);

const obj1 = { 0: "a", 1: "b" };
const obj2 = { 2: "a", 3: "c" };

console.log({ obj1, obj2 });    // { obj1: { '0': 'a', '1': 'b' }, obj2: { '2': 'a', '3': 'c' } }

console.log("_____Object.assing(target, source)_____");
console.log(Object.assign(obj1, obj2)); // { '0': 'a', '1': 'b', '2': 'a', '3': 'c' }
console.log({ ...obj1, ...obj2 });  // { '0': 'a', '1': 'b', '2': 'a', '3': 'c' } // same as Object.assign()
console.log(Object.assign({}, obj1, obj2)); // try to make target as {}, to identify target // { '0': 'a', '1': 'b', '2': 'a', '3': 'c' }

let myObj = {}
console.log(Object.assign(myObj, obj1, obj2)); // { '0': 'a', '1': 'b', '2': 'a', '3': 'c' }    // try to make target as {}, to identify target // { '0': 'a', '1': 'b', '2': 'a', '3': 'c' }
console.log("myObj: ", myObj);      // { '0': 'a', '1': 'b', '2': 'a', '3': 'c' }


console.log("__________");
console.log(Object.keys(jsUser));   // returns array of keys
console.log(Object.values(jsUser)); // returns array of values
console.log(Object.entries(jsUser)); // array of key-value pair // [ [ 'name', 'Sagar' ],...]

console.log(instaUser.hasOwnProperty("name")); // true
console.log(instaUser.hasOwnProperty("test")); // false

console.log("____Object Destructuring____");

const { age, isActive: isAvailable } = jsUser;
console.log(age, isAvailable);

const { city, name, workingDays, ...restObj } = jsUser;
console.log(restObj);   // gives new object by omitting city, name, workingDays


if(Object.keys({jsuser: jsUser}).length = 0) {  // false
    console.log("object is empty.");
} else {
    console.log("object is non empty.");
}

