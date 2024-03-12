/*
What is Temporal Dead Zone?
    - refers to the period between the creation of a variable (using let or const) and 
      its declaration being processed by the JavaScript engine.
    -  During this temporal dead zone, accessing the variable results in a ReferenceError.
        console.log(a); // This is in temporal dead zone. // a is present in scripts scope.
        let a = 1;  // Temporal DZ ended.
*/

// console.log(a);  // ReferenceError: Cannot access 'a' before initialization // a is in temporal dead zone
// let a = 5;
// console.log(x);     // ReferenceError: x is not defined
// console.log(b); // present in global scope with undefined
// var b = 3; 
console.log("____________");
// let c = 5;
// let c = 4;  // SyntaxError: Identifier 'c' has already been declared
console.log("____________");
// const d ;   //SyntaxError: Identifier 'c' has already been declared
const d = 5;
// d =11;  // TypeError: Assignment to constant variable.
console.log("____________");
// What is block scope?
// Scopes: global scope: { script scope: { block scope: { block scope: {}}}}

let f = 11;     // scipt scope
let h = 12;     // scipt scope
{
    var e = 10;     // present in global scope
    let f = 20;     // block scope      // shodowing: hides parent 'f' variable.
    const g = 30;     // block scope
    // var h = 10; // error: var will create global scope, but h is already present in scipt scope, so scope should not cross the boundry.
    console.log("this is the block scope");
    {
        const g = 40;
    }
}
console.log(e, f); // 10 11

console.log("____________________");

let score = "33";
console.log(typeof score);
console.log(typeof (score));

let scoreNum = Number(score);
console.log("scoreNum", typeof scoreNum);

let scoreInvalid = "33qa";
// let scoreInvalid = null;
// let scoreInvalid = undefined;
console.log("scoreInvalid:", Number(scoreInvalid)); // NAN
let isBool = true;
console.log("isBool", Number(isBool));  // 1 // false: 0

// "33" => 33
// "33sr", null, undefined => NAN
// true => 1, false=> 0

/*
What is false values:
    - false: The boolean value false.
    - 0: The number zero.
    - -0: Negative zero, which is treated as falsy (although technically -0 === 0).
    - 0n: BigInt zero.
    - "" or '': Empty strings.
    - null: The absence of any value or object.
    - undefined: The value assigned to uninitialized variables or non-existent properties.
    - NaN: The special value representing "Not-a-Number".
Truthy values are all other values that are not falsy. These include:
    - Any non-empty string ('hello', '0', 'false', ' ', etc.)
    - Any non-zero number (positive or negative)
    - Objects (including arrays and functions) ([], {}, function(){})
    - The boolean value: true

false == 0; // true
false == "";// true
0 == '';   // true
*/


console.log("---------Boolean---------");

let num = "1";
console.log(Boolean(num));
console.log(Boolean("abc"));
console.log(Boolean(0));
console.log(Boolean(undefined));


console.log("________String________");
let strNum = String(num);
console.log(strNum);
console.log(typeof (strNum));
console.log(typeof (String(null)));
console.log(String(undefined));
// console.log(Str);

console.log("_____String to Number conversion_____");
let str1 = "Sagar", str2 = " Patil";
console.log(str1 + str2);

console.log("1" + 2);   // 12
console.log(1 + "2");   // 12
console.log("1" + 2 + 2);// 122
console.log(1 + 2 + "2");//32
console.log(+"1" + 2 + 2);// 5
console.log(true);  // true
console.log(+true); // 1
console.log(+""); // 0

console.log(3 + null);  // 3
console.log(3 + undefined);  // NaN
console.log(+ undefined);  // NaN

let no1, no2, no3;
no1 = no2 = no3 = 2 + 2;
console.log(no1, no2, no3);


console.log("____Comparison______");
console.log("2" > 1);   // true
console.log("02" > 1);  // true

console.log("null: \n", null > 0);  // false
console.log(null == 0);     // false
console.log(null >= 0);     // true // here, null get converted into 0.
// undefined always false for above 3 scenario's

console.log("Strict check:");
console.log("2" == 2);
console.log("2" === 2);

/* Data Types:
    - 7 Primitive:  // store on Stack Memory
        - String
        - Number
        - Boolean
        - null
        - undefined
        - Symbol
        - BigInt
    - Reference (Non-Primitive);    // store on heap memory and reference store on stack.
        - Array
        - Object
        - Functions
        - ...
// Typescript:
    const num:number = 10;
*/

const id = Symbol('123');
const anotherId = Symbol('123');
console.log("id:", id, "anotherId:", anotherId, "===:", id === anotherId);

const bigNumer = 34256636454556634343434343n;
console.log(bigNumer);
console.log(typeof bigNumer);   // bigint

console.log("______String_______");

const myname = "sagar";
const repoCount = 50;
console.log(`Hello ${myname}, repo count is: ${repoCount}`);

console.log(myname[0]);

const mystr = new String("abcdef");
console.log(mystr[0]);
console.log(mystr.__proto__);
console.log(mystr.length);
console.log(mystr.toUpperCase());
console.log(mystr.charAt(3));

console.log(mystr.indexOf("d")); // 3
console.log(mystr.indexOf("t")); // -1

console.log(mystr.substring(0, 4)); // abcd
console.log(mystr.substring(2, 4)); // cd
console.log(mystr.substring(-2, 4)); // abcd

console.log("slice:", mystr.slice(0, 4)); // abcd
console.log(mystr.slice(-4, 4)); // cd
// a  b  c  d  e  f
// -6 -5 -4 -3 -2 -1
// 0  1  2  3  4  5

console.log("   abc   ".trim());
console.log("   abc   ".trimStart());
console.log("   abc   ".trimEnd());


console.log("sagar.patil".replace(".", " "));
console.log("sagar.patil".includes(".p"));

console.log("sagar.b.patil".split("."));    // [ 'sagar', 'b', 'patil' ]
console.log("sagar.b.patil".split(".", 2)); // [ 'sagar', 'b' ]


console.log("____Numbers____");
const score1 = 400;
const balance = new Number(100);
const otherNumber = 123.4567;
console.log(score1);    // 200
console.log(balance);   // [Number: 100]

console.log(balance.toString().length);
console.log(balance.toFixed(2)); // 100.00
console.log(otherNumber.toPrecision(3)); // 123
console.log(otherNumber.toPrecision(4)); // 123.5
console.log(otherNumber.toPrecision(2)); // 1.2e+2

var no = 1000000;
console.log(no.toLocaleString());    // us standart: 1,000,000
console.log(no.toLocaleString('en-IN'));    // Indian standart: 10,00,000

console.log(Number.MAX_VALUE);  // 1.7976931348623157e+308
console.log(Number.MIN_VALUE);  // 5e-324
console.log(Number.NEGATIVE_INFINITY);  // -Infinity
console.log(Number.EPSILON);    // 2.220446049250313e-16

console.log("____Math____");
console.log(Math.abs(-4));      // 4
console.log(Math.abs(4));       // 4
console.log(Math.round(5.4));   // 5

console.log("ceil:", Math.ceil(3.9));   // 4
console.log(Math.ceil(4.1));   // 5
console.log(Math.ceil(4.0));   // 4

console.log("floor:", Math.floor(3.9));   // 3
console.log(Math.floor(4.1));   // 4
console.log(Math.floor(4.0));   // 4

console.log("max:", Math.max(4, 5, 6, 2));
console.log("min:", Math.min(4, 5, 6, 2));

console.log(Math.random()); // 0 to 1
console.log(Math.random() * 10); // 0 to 10
console.log((Math.floor(Math.random() * 10)) + 1); // 1 to 10

const min = 10, max = 20;
console.log((Math.floor(Math.random() * (max - min + 1)) + min)); // 10 to 20



console.log("____Date______");
let date = new Date();

console.log(typeof date); // "object"
console.log(date);                  // 2024-03-11T22:11:49.013Z
console.log(date.toString());       // Tue Mar 12 2024 03:41:49 GMT+0530 (India Standard Time)
console.log(date.toDateString());   // Tue Mar 12 2024
console.log(date.toTimeString());   // 03:42:50 GMT+0530 (India Standard Time)
console.log(date.toLocaleString()); // 3/12/2024, 3:41:49 AM

let newDate = new Date("2024", 0, 26);  // month starts from 0 = January
let newDateTime = new Date(2024, "0", "26", "14", 55, "45");  // month starts from 0 = January
console.log("newDate:", newDate.toLocaleString());          // 1/26/2024, 12:00:00 AM
console.log("newDateTime:", newDateTime.toLocaleString());  // newDateTime: 1/26/2024, 2:55:45 PM

var newDate2 = new Date("2024-01-25");  // YYYY-MM-DD
console.log(newDate2);  // 2024-01-25T00:00:00.000Z
let newDate3 = new Date("01-14-2024");  // MM-DD-YYYY // 024-01-13T18:30:00.000Z
console.log(newDate3);

let myTimeStamp = Date.now();
console.log("myTimeStamp:", myTimeStamp);   // 1710196046602 ans in mili sec.
console.log(date.getTime());   // ans in mili sec.  
console.log(myTimeStamp - date.getTime()); // 6, ans in mili sec, time difference

console.log(Math.floor(Date.now() / 1000));    // 1710196046, convert into seconds

console.log(date.getMonth());   // 2

console.log(date.toLocaleString("default", {
    weekday: "long",
    // timeZone: ""
}));    // Tuesday


console.log("______Arrays______");

var myarr = [0, 1, 2, 3, 4]
var myHeros = ["Sagar", "Suraj"]
const myArrObj = new Array(1, 2, 3, 4, 5);

console.log(typeof myarr, typeof myArrObj); // object object

myarr.push(5);
myarr.push(6);
myarr.pop();
myarr.unshift(9);   // add at start
console.log(myarr); // [ 9, 0, 1, 2, 3, 4, 5 ]
myarr.shift();      // remove start/first element
console.log(myarr); // [ 0, 1, 2, 3, 4, 5 ]
myarr.unshift(2);   // add at start
console.log(myarr.includes(4)); // true
console.log(myarr.indexOf(4)); // 5

console.log(myarr.join());  // "2,0,1,2,3,4,5"

// slice vs splice
console.log("slice vs splice");
console.log(myArrObj);      // [ 1, 2, 3, 4, 5 ]
console.log(myArrObj.slice(1, 3));   // [ 2, 3 ] // return slice of code.
console.log(myArrObj);  // [ 1, 2, 3, 4, 5 ]

console.log(myArrObj.splice(1, 3)); // [ 2, 3, 4 ] // delete slice and return slice of code.
console.log(myArrObj);  // [ 1, 5 ]

const marvel_heroes = ["thor", "Ironman", "SpiderMan"];
const dc_heroes = ["Superman", "Batman", "WonderWomen"];

// marvel_heroes.push(dc_heroes);
// console.log(marvel_heroes[0]);
// console.log(marvel_heroes[3][1]);

var all_heroes = marvel_heroes.concat(dc_heroes);
console.log(all_heroes);    // [ 'thor', 'Ironman', 'SpiderMan', 'Superman', 'Batman', 'WonderWomen' ]

all_heroes = [...marvel_heroes, ...dc_heroes];

var depthArr = [1, 2, 3, [4, 5], 6, [7, 8, [9, 10, [11, 12,]]], 13];
console.log(depthArr.flat(1)); // [ 1, 2, 3, 4, 5, 6, 7, 8, [ 9, 10, [ 11, 12 ] ], 13 ]
console.log(depthArr.flat(Infinity)); // [ 1, 2, 3,  4,  5,  6, 7, 8, 9, 10, 11, 12, 13 ]


console.log((Array.isArray("Sagar")));  // false: its string
console.log((Array.from("Sagar")));     // [ 'S', 'a', 'g', 'a', 'r' ] // convert into array

console.log(Array.from({name:"sagar"})); // [] // array of what? key or value?
console.log(Array.from({name:"sagar"})); // [] // array of what? key or value?

var mark1 = 100;
var mark2 = 80;
var mark3 = 30;
console.log(Array.of(mark1, mark2, mark3));

console.log("Nullish Coalescing Operator (??): null undefined");

let val1 = 5 ?? 10; // 5
val1 = null ?? 10;  // 10
val1 = undefined ?? null ?? 15 ?? 10;   // 15
console.log(val1);
val1 = undefined || null || 15 || 10;   // 15 // same we can do using OR operator

console.log("Difference between OR(||) and Nullish Coalescing Operator (??)");

val1 = undefined ?? null ?? 0 ?? 11;  // 0 // it only ignores undefined and null.
console.log(val1);
val1 = undefined || null || 0 || 11;  // 11 // it only ignores all false values.
console.log(val1);


// multiline
var isPresent = true
if(isPresent) console.log("log1"), console.log("log2");;