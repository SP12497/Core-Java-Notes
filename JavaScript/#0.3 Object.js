// Object Literals"

const mySym = Symbol("key1")
var jsuser = {
    name: "Sagar", age: 26,
    workingDays: ["Monday", "Tuesday"],
    "full name": "Sagar Patil",
    [mySym]: "myKey1", // access symbol
    // mySym : "myKey1", // it wont access symbol
    isActive: true,
};

console.log(jsuser);    // here, u can check the symbol.
console.log(jsuser.age);
console.log(jsuser["isActive"]);
console.log(jsuser["full name"]);
console.log(typeof jsuser[mySym]);
console.log(jsuser[mySym]); // myKey1 // This is the actual way to access synbol, We cant get symbol using dot notation
console.log("mySym:", jsuser.mySym);    // undefined

// Object.freeze(jsuser);
// jsuser.age = 50;    // wont update the age. also it wont throw error.
// console.log(jsuser.age); // 26

jsuser.greeting = function () {
    console.log("Hello JS User");
}
jsuser.greetingName = function () {
    console.log(`Hello ${this.name}`);
}

console.log(jsuser.greeting());         // Hello JS User
console.log(jsuser.greetingName());     // Hello Sagar


console.log("________________");
// const tinderUser = new Object();
const tinderUser = {};
tinderUser.Id = "123abc";
tinderUser.name = "Sagar";
tinderUser.isLoggedIn = false;
// console.log(tinderUser);

const regularUser = {
    name: "sagar",
    address: {
        zipcode: 123
    }
}
console.log(regularUser.address.zipcode);
console.log(regularUser["address"].zipcode);

console.log(regularUser?.["address"]?.zipcode);// Optional chaining (?.)

const obj1 = { 0: "a", 1: "b" };
const obj2 = { 2: "a", 3: "c" };

console.log({ obj1, obj2 });    // { obj1: { '0': 'a', '1': 'b' }, obj2: { '2': 'a', '3': 'c' } }

// Object.assign(target, source)
console.log(Object.assign(obj1, obj2)); // { '0': 'a', '1': 'b', '2': 'a', '3': 'c' }
console.log(Object.assign({}, obj1, obj2)); // try to make target as {}, to identify target // { '0': 'a', '1': 'b', '2': 'a', '3': 'c' }
console.log({ ...obj1, ...obj2 });  // same as Object.assign()

console.log("__________");
console.log(Object.keys(jsuser));   // array of keys
console.log(Object.values(jsuser)); // array of values
console.log(Object.entries(jsuser)); // array of key-value pair

console.log(tinderUser.hasOwnProperty("name")); // true
console.log(tinderUser.hasOwnProperty("test")); // false

console.log("____Object Destructuring____");

const { age, isActive: isAvailable } = jsuser;

console.log(age, isAvailable);


if(Object.keys({jsuser}).length = 0) {
    console.log("object is empty.");
}

