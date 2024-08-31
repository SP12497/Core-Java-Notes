/*
#1 What is Temporal Dead Zone?
    - refers to the period between the creation of a variable (using let or const) and 
      its declaration being processed by the JavaScript engine.
    -  During this temporal dead zone, accessing the variable results in a ReferenceError.
        ->
            console.log(a); // accessing a before declaration // This is in temporal dead zone. // a is present in scripts scope.
            let a = 1;  // Temporal DZ ended.
*/

// console.log(a);  // ReferenceError: Cannot access 'a' before initialization // a is in temporal dead zone
// let a = 5;

// console.log(x);     // ReferenceError: x is not defined

// console.log(b); // present in global scope with undefined
// var b = 3; 
console.log("\n\n---------------------")
console.log("____________");
// let c = 5;
// let c = 4;  // SyntaxError: Identifier 'c' has already been declared
console.log("\n\n---------------------")
console.log("____________");
// const d ;   //SyntaxError: 'const' declarations must be initialized.
const d = 5;
// d = 11;  // TypeError: Assignment to constant variable.

console.log("\n\n---------------------")
console.log("______#2 Scopes______");
// #2 Scopes:
// What is block scope?
// Scopes: global scope: { script scope: { block scope: { block scope: {}}}}

let f = 11;     // script scope
let h = 12;     // script scope
{
    var e = 10;     // present in global scope
    let f = 20;     // block scope      // shodowing: hides parent 'f' variable.
    const g = 30;   // block scope
    // var h = 10;  // SyntaxError: Identifier 'h' has already been declared // explain: var will create global scope, but h is already present in script scope, so scope should not cross the boundry.
    console.log("this is the block scope");
    {
        const g = 40;
    }
}
console.log(e, f); // 10 11
// console.log(g);  // ReferenceError: g is not defined

console.log("\n\n---------------------")
console.log("__________#3 typeof__________");

let score = "33";
console.log(typeof score);      // string
console.log(typeof (score));    // string

// ---------------------
// Number() -- // convert into number
// Number.parseInt(score); // convert into number
// Number.parseFloat(score); // convert into number

Number("123");  // 123
Number("123.45");  // 123.45
Number("123.45.67");  // NAN
Number("a123b");  // NAN
Number("0x123");  // 291    // Hexadecimal
Number("0b101");  // 5      // Binary
Number("0o123");  // 83     // Octal
Number(null);  // 0
Number(undefined);  // NAN
Number(true);  // 1
Number(false);  // 0


Number.parseInt("123");  // 123
Number.parseInt("123.45");  // 123
Number.parseInt("123.45.67");  // 123
Number.parseInt("a123b");  // NAN
Number.parseInt("123b");  // 123
Number.parseInt(null);  // NAN
Number.parseInt(undefined);  // NAN
Number.parseInt(true);  // NAN
Number.parseInt(false);  // NAN


console.log("---------#4 Falsy and Truthy Values---------");
/*
falsy values:
    falsy values are those which, when evaluated in a Boolean context, are considered false. These include:
        false: The boolean value false.
        0: The number zero.
        -0: Negative zero, which behaves the same as zero.
        0n: The BigInt representation of zero.
        "" or '': Empty strings.
        null: The absence of any value or object.
        undefined: A variable that has been declared but not yet assigned a value.
        NaN: Represents "Not-a-Number," a result of undefined or unrepresentable numerical operations.
*/
Boolean(false);   // false
Boolean(0);       // false
Boolean(-0);      // false
Boolean(0n);      // false // 0n: BigInt
Boolean("");      // false
Boolean(null);    // false
Boolean(undefined); // false
Boolean(NaN);     // false
/*
Truthy values:
    truthy values are all values that are not falsy. They evaluate to true in a Boolean context. These include:
        Non-empty strings: Any string with at least one character, including strings that might seem falsy like '0', 'false', ' ', etc.
        Non-zero numbers: Any number that is not zero, including both positive and negative numbers (e.g., 1, -1, 3.14).
        Non-zero BigInt: BigInt values that are not 0n (e.g., 1n, -1n).
        Objects: Any object, including arrays ([]), plain objects ({}), and functions (function(){}).
        The boolean value true: This is inherently truthy.
*/

Boolean(true);     // true
Boolean("hello");  // true
Boolean("0");      // true
Boolean("false");  // true
Boolean(" ");      // true
Boolean(1);        // true
Boolean(-1);       // true
Boolean(3.14);     // true
Boolean({});       // true
Boolean([]);       // true
Boolean(function () { }); // true
Boolean(1n);       // true
Boolean(-1n);      // true


console.log("\n\n---------------------")
console.log("_____conversion/typecasting_____");
let fname = "Sagar", lname = " Patil";
console.log(fname + lname);

console.log("1" + 2);   // 12
console.log(1 + "2");   // 12   // number get converted into string
console.log("1" + 2 + 2);// 122
console.log(1 + 2 + "2");//32

console.log(+"1" + 2 + 2);// 5  // + sign used to number conversion
console.log(true);  // true
console.log(+true); // 1
console.log(+""); // 0

console.log(+null); // 0
console.log(3 + null);  // 3
console.log(3 + undefined);  // NaN
console.log(+ undefined);  // NaN

let no1, no2, no3;
no1 = no2 = no3 = 2 + 2;
console.log(no1, no2, no3);


console.log("\n\n---------------------")
console.log("____Comparison______");
console.log("2" > 1);   // true
console.log("02" > 1);  // true
console.log("2" == 2);  // true
console.log("2" === 2);  // false // values are same but data types are different

console.log("null > 0: ", null > 0);  // false
console.log(null == 0);     // false
console.log(null >= 0);     // true // here, null is falsy value get converted into 0.
console.log(null < 1);     // true // here, null get converted into 0.
console.log(null === null); // true
// undefined always false for above 3 scenario's

/*
null == undefined // true
null > 0 // false
null < 0 // false
null == 0 // false
null >= 0 // true
null !== 0 // true
*/

console.log("Strict check:");
console.log("2" == 2);  // true
console.log("2" === 2); // false

// ----------------------------------------
/* Data Types:
    - 7 Primitive:  // store on Stack Memory
        - String
        - Number
        - Boolean
        - null
        - undefined
        - Symbol: (Symbol(111))
        - BigInt: (123n)
    - Reference (Non-Primitive);    // store on heap memory and reference store on stack.
        - Array: [1,2,"c",null]
        - Object: {name:"sagar", gender: "male"}
        - Functions: function(){}
        - ...
// Typescript:
    const num:number = 10;
*/


console.log("\n\n---------------------")
console.log("______Symbol_______");
/*
Symbol is a unique and immutable data type that is primarily used to create unique identifiers for objects.
Even if two Symbol values are created with the same description, they are guaranteed to be unique.
*/

const id = Symbol('123');
const anotherId = Symbol('123');
console.log("id:", id, "anotherId:", anotherId, "===:", id === anotherId);  // id: Symbol(123) anotherId: Symbol(123) ===: false
const student = { name: "sagar", age: 11 }
console.log(student === student);   // true
console.log(student == Symbol(student));   // false
console.log(student != Symbol(student));   // true

console.log("\n\n---------------------")
console.log("______bigint_______");
const bigNumer = 34256636454556634343434343n;
console.log(bigNumer);
console.log(typeof bigNumer);   // bigint

console.log("\n\n---------------------")
console.log("________String________");
let num = 1;
let strNum = String(num);
console.log(typeof (strNum));           // string
console.log(String(null));              // 'null'
console.log(typeof (String(null)));     // string
console.log(String(undefined));         // 'undefined'
console.log(String(true));              // 'true'


const myname = "sagar";
const repoCount = 50;
console.log(`Hello ${myname}, repo count is: ${repoCount}`);

console.log(myname[0]);     // s

const mystr = new String("abcdef");
console.log(mystr[0]);      // a
console.log(mystr.__proto__);
console.log(mystr.length);  // 6
console.log(mystr.toUpperCase());
console.log(mystr.charAt(3));   // d

console.log(mystr.indexOf("d")); // 3
console.log(mystr.indexOf("t")); // -1  // not present

console.log(mystr.substring(0, 4)); // abcd
console.log(mystr.substring(2, 4)); // cd
console.log(mystr.substring(-2, 4)); // abcd

console.log("slice:", mystr.slice(0, 4)); // abcd
console.log(mystr.slice(-4, 4)); // cd
// a  b  c  d  e  f
// -6 -5 -4 -3 -2 -1
// 0  1  2  3  4  5

// slice vs splice
console.log("slice vs splice");
// Syntax: 
// array.slice(startIndex, endIndex) // endIndex is optional // return slice of data.   // returns empty array if startIndex > endIndex
// array.splice(startIndex, deleteCount, item1, item2, item3, ...) // deleteCount is optional // return deleted items
const myarray = new Array(1, 2, 3, 4, 5);
console.log(myarray);      // [ 1, 2, 3, 4, 5 ]
console.log(myarray.slice(1, 3));   // [ 2, 3 ] // return slice of data.
console.log(myarray);  // [ 1, 2, 3, 4, 5 ]

console.log(myarray.splice(1, 3)); // [ 2, 3, 4 ] // return slice of data and delete slice from original data
console.log(myarray);  // [ 1, 5 ]

console.log("slice vs substring:");
let str = "Hello, World!";
// substring(startIndex, endIndex): endIndex is optional
// slice(startIndex, endIndex): endIndex is optional
// split(startIndex, length): length is optional
console.log(str.substring(7, 12));  // Output: "World"
console.log(str.substring(12, 7));  // Output: "World" (swapped indices)
console.log(str.substring(-3, 5));  // Output: "Hello" (negative index treated as 0
console.log(str.substring(-6, -1)); // Output: ""

console.log(str.slice(7, 12));  // Output: "World"
console.log(str.slice(12, 7));  // Output: "" returns empty array if startIndex > endIndex
console.log(str.slice(-3, 5));  // Output: ""
console.log(str.slice(-6, -1)); // // Output: "World"

// trim():
console.log("   abc   ".trim());        // "abc"
console.log("   abc   ".trimStart());   // "abc   "
console.log("   abc   ".trimEnd());     // "   abc"


console.log("sagar.patil".replace(".", " "));   /// sagar patil
console.log("sagar.patil".includes(".p"));  // true

console.log("sagar.b.patil".split("."));    // [ 'sagar', 'b', 'patil' ]
console.log("sagar.b.patil".split(".", 2)); // [ 'sagar', 'b' ]
"sagar.b.patil".split("");      // [ 's', 'a', 'g', 'a', 'r', '.', 'b', '.', 'p', 'a', 't', 'i', 'l' ]
"sagar.b.patil".split("", 5);   // [ 's', 'a', 'g', 'a', 'r' ]

console.log("\n\n---------------------")
console.log("____Numbers____");
const score1 = 400;
const balance = new Number(100);
const otherNumber = 123.4567;
console.log(score1);    // 200
console.log(balance);   // [Number: 100]

console.log(balance === 100);   // false
console.log(balance == 100);   // true


console.log(balance.toString().length);
console.log(balance.toFixed(2)); // 100.00
console.log(otherNumber.toPrecision(3)); // 123
console.log(otherNumber.toPrecision(4)); // 123.5
console.log(otherNumber.toPrecision(2)); // 1.2e+2

var no = 1000000;
console.log(no.toLocaleString());    // us standart: 1,000,000
console.log(no.toLocaleString('en-IN'));    // Indian standard: 10,00,000

console.log(Number.MAX_VALUE);  // 1.7976931348623157e+308
console.log(Number.MIN_VALUE);  // 5e-324
console.log(Number.MAX_SAFE_INTEGER);    // 9007199254740991
console.log(Number.MIN_SAFE_INTEGER);    // -9007199254740991
console.log(Number.POSITIVE_INFINITY);  // Infinity
console.log(Number.NEGATIVE_INFINITY);  // -Infinity
console.log(Number.EPSILON);    // 2.220446049250313e-16
console.log(Number.NaN);    // NaN
console.log(NaN);           // NaN
console.log();

console.log("\n\n---------------------")
console.log("____Math____");
console.log(Math.abs(-4));      // 4
console.log(Math.abs(4));       // 4

console.log(Math.round(5.4));   // 5

console.log("ceil:", Math.ceil(3.9));   // 4
console.log(Math.ceil(4.1));   // 5
console.log(Math.ceil(4.0));   // 4

console.log("floor:", Math.floor(3.9));   // 3
console.log(Math.floor(4.9));   // 4
console.log(Math.floor(4.0));   // 4

console.log("max:", Math.max(4, 5, 6, 2));  // 6
console.log("min:", Math.min(4, 5, 6, 2));  // 2

console.log(Math.random()); // 0.00000 00000 00001 to 0.9999...
console.log(Math.random() * 10); // 0 to 10
console.log((Math.floor(Math.random() * 10)) + 1); // 1 to 10

const min = 10, max = 20;
console.log((Math.floor(Math.random() * (max - min + 1)) + min)); // 10 to 20



console.log("\n\n---------------------")
console.log("____Date______");
let date = new Date();

console.log(typeof date); // "object"
console.log(date);                  // 2024-03-11T22:11:49.013Z
console.log(date.toString());       // Tue Mar 12 2024 03:41:49 GMT+0530 (India Standard Time)
console.log(date.toDateString());   // Tue Mar 12 2024
console.log(date.toTimeString());   // 03:42:50 GMT+0530 (India Standard Time)
console.log(date.toLocaleString()); // 3/12/2024, 3:41:49 AM
console.log(date.toISOString());    // 2024-07-04T19:06:59.324Z
console.log(date.toISOString() > new Date("2023-07-04T19:06:59.324Z").toISOString());    // true

console.log(new Date("3/12/2024, 3:41:49 AM"));   // Tue Mar 12 2024 03:41:49 GMT+0530 (India Standard Time)
console.log(new Date(""));   // Invalid Date
let newDate = new Date("2024", 0, 26);  // month starts from 0 = January
let newDateTime = new Date(2024, "0", "26", "14", 55, "45");  // month starts from 0 = January
console.log("newDate:", newDate.toLocaleString());          // 1/26/2024, 12:00:00 AM
console.log("newDateTime:", newDateTime.toLocaleString());  // newDateTime: 1/26/2024, 2:55:45 PM

var newDate2 = new Date("2024-01-25");  // YYYY-MM-DD
console.log(newDate2);  // 2024-01-25T00:00:00.000Z
let newDate3 = new Date("01-14-2024");  // MM-DD-YYYY // 2024-01-13T18:30:00.000Z
console.log(newDate3);

let myTimeStamp = Date.now();   // current time in mili sec.    // 1720120400329
console.log("myTimeStamp:", myTimeStamp);   // 1720120400329 ans in mili sec.
console.log(date.getTime());   // ans in mili sec.  // 1720120400313  
console.log(myTimeStamp - date.getTime()); // 6, ans in mili sec, time difference

console.log(Math.floor(Date.now() / 1000));    // 1710196046, convert into seconds

console.log(date.getMonth());   // 2

console.log(date.toLocaleString("default", {
    weekday: "long",
    // timeZone: ""
}));    // Tuesday


console.log("\n\n---------------------")
console.log("______Arrays______");

var myarr = [0, 1, 2, 3, 4]
var myHeros = ["Sagar", "Suraj"]
const myArrObj = new Array(1, 2, 3, 4, 5);

console.log(typeof myarr, typeof myArrObj); // object object

myarr.push(5);
myarr.push(6);
myarr.pop();    // Removes the last element from an array and returns it. || undefined if the array is empty.
console.log(myarr.unshift(9));  // 7 (returns length of array)   // add at start
// myarr.unshift(99,88,77);   // add at start
console.log(myarr); // [ 9, 0, 1, 2, 3, 4, 5 ]
console.log(myarr.shift());     // 9    // remove start/first element
console.log(myarr); // [ 0, 1, 2, 3, 4, 5 ]
myarr.unshift(2);   // add at start
console.log(myarr.includes(4)); // true
console.log(myarr.indexOf(4)); // 5

console.log(myarr.join());  // "2,0,1,2,3,4,5"

const marvel_heroes = ["thor", "Ironman", "SpiderMan"];
const dc_heroes = ["Superman", "Batman", "WonderWomen"];

// marvel_heroes.push(dc_heroes);
console.log(marvel_heroes[0]);          // thor
// console.log(marvel_heroes[3][1]);    // Java: ArrayIndexOutOfBound   // TypeError: Cannot read properties of undefined (reading '1')
console.log(marvel_heroes[0][1]);       // h

var all_heroes = marvel_heroes.concat(dc_heroes);
console.log(all_heroes);    // [ 'thor', 'Ironman', 'SpiderMan', 'Superman', 'Batman', 'WonderWomen' ]

all_heroes = [...marvel_heroes, ...dc_heroes];  // using spread operator

var depthArr = [1, 2, 3, [4, 5], 6, [7, 8, [9, 10, [11, 12]]], 13];
console.log("flat(1):", depthArr.flat(1)); // [ 1, 2, 3, 4, 5, 6, 7, 8, [ 9, 10, [ 11, 12 ] ], 13 ]
console.log("flat(1):", depthArr.flat(2)); // [ 1, 2, 3, 4, 5, 6, 7, 8, [ 9, 10, 11, 12 ], 13 ]
console.log("flat(Infinity):", depthArr.flat(Infinity)); // [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 ]


console.log((Array.isArray("Sagar")));  // false: its string
console.log((Array.from("Sagar")));     // [ 'S', 'a', 'g', 'a', 'r' ] // convert into array

console.log(Array.from({ name: "sagar" })); // [] // array of what? key or value?

var mark1 = 100;
var mark2 = 80;
var mark3 = 30;
console.log(Array.of(mark1, mark2, mark3)); // [ 100, 80, 30 ]

console.log("Nullish Coalescing Operator (??): ignore=> null, undefined");

let val1 = 5 ?? 10; // 5
val1 = null ?? 10;  // 10
val1 = undefined ?? null ?? 15 ?? 10;   // 15
console.log(val1);
val1 = undefined || null || 15 || 10;   // 15 // same we can do using OR operator
// null ?? undefined ?? null  => null
// null ?? undefined => undefined

console.log("Difference between OR(||) and Nullish Coalescing Operator (??)");
// Note: "||" ignore falsy values and "??" ignore null and undefined.
val1 = undefined ?? null ?? 0 ?? 11;  // 0 // it only ignores undefined and null.
console.log(val1);
val1 = undefined || null || 0 || 11;  // 11 // it only ignores all false values.
console.log(val1);


// multiline
var isPresent = true
if (isPresent) console.log("log1"), console.log("log2"), isPresent = false, console.log(isPresent);
// log1
// log2
// false
