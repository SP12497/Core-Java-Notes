//1. for of
["", "", ""]
[{}, {}, {}]

const arr = [1, 2, 3, 4, 5];
for (const val of arr) {
    // console.log(val);
}

const greetings = "Hello Sagar";
for (const greet of greetings) {
    // console.log(greet);
}

// Map:
// Keys are unique. 
// insertion order is fix.

const map = new Map();
map.set("IN", "India"); // returns: { 'IN' => 'India' }
map.set("USA", "United States of America"); // returns: { 'IN' => 'India', 'USA' => 'United States of America' }
map.set("Fr", "Franch");
map.set("IN", "India");

// console.log(map);

for (const entry of map) {
    // console.log(entry);
}

for (const [key, value] of map) {
    // console.log(key, ":-", value);
}

// ----------
// 2. For In loop:
const student = {
    name: "sagar",
    "age": 50
}

// for(const stud of student) {    // TypeError: student is not iterable
//     console.log(stud);
// }

for (const key in student) {
    // console.log(key, ":-", student[key]);
}

// arr:
const programmings = ["js", "java", "py"];  // array keys start from 0
for (const key in programmings) {            // works
    // console.log(key, ":-", programmings[key]);
}

// map:
for (const key in map) {     // Wont work: map is not iterable. and it wont throw error as well.
    console.log(key);
}


// 3. forEach:
// - inbuild available in array prototypes.
// - It does not return any value.
programmings.forEach(function (item) {
    // console.log(item);
});

programmings.forEach(item => {
    // console.log(item);
});

programmings.forEach((item, index, arr) => {
    // console.log(item, index, arr);
});

// Array of objects:
const myCoding = [
    {
        languageName: "Java",
        languageFileExt: "java"
    },
    {
        languageName: "Python",
        languageFileExt: "py"
    },
    {
        languageName: "JavaScript",
        languageFileExt: "js"
    },
]

myCoding.forEach((item) => {
    console.log(item.languageName);
})

var value = myCoding.forEach((item) => {
    return item
})
console.log(value); // undefined

// If you want to return a value back, then use Filter, Map or Reduce.
const myNums = [3,4,6,2,6,7,3];
const newNum = [];
myNums.forEach(item=> {
    if(item>4) {
        newNum.push(item);
    }
})
console.log(newNum);

let newNum2 = myNums.filter(item=> item>4);
console.log(newNum2);
