/*
1. for loop
2. for of:
    - Purpose: Iterates over iterable objects (arrays, strings, maps, sets, etc.).
    - Returns the values of the iterable.
    - for (const val of arr)
        ["", "", ""]
        [{}, {}, {}]
        "iterate me"
        Map
3 for in :
    - Purpose: Iterates over enumerable properties of an object.
    - Returns the keys (property names) of the object.
4 forEach:
    - Purpose: Executes a provided function once for each array element.
    - Applied to arrays and does not directly return values or keys.
5. for await...of Loop:
    - Purpose: Used to iterate over async iterables or promises.
    - Usage: Helpful when dealing with asynchronous data streams or async generators.
    - for await (variable of asyncIterable) {}
*/

console.log("====== 2. for loop ======");
for (let i = 0; i < 5; i++) {   // initialization; condition; increment
    console.log(i); // Logs 0, 1, 2, 3, 4
}

console.log("====== 2. for of ======");
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
    // entry[0] , entry[1]
}

for (const [key, value] of map) {
    // console.log(key, ":-", value);
}

console.log("====== 3. For In loop ======");
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

// array:
const programmings = ["js", "java", "py"];  // array keys start from 0
for (const key in programmings) {            // works
    // console.log(key, ":-", programmings[key]);   // 0 :- js, 1 :- java, 2 :- py
}

// map:
console.log("---MAP---");
for (const key in map) {     // Wont work: map is not iterable. and it wont throw error as well.
    console.log(key);
}

// ----------
console.log("====== 4. forEach ======");
// - inbuild available in array prototypes.
// - It does not return any value.
programmings.forEach(function (item) {
    // console.log(item);
});

programmings.forEach(item => {
    // console.log(item);
});

programmings.forEach((item, index, programmingArray) => {
    console.log(item, index, programmingArray);
});

[10, 20, 30].forEach((num, idx, arr) => {
    console.log(`Value: ${num}, Square: ${num * num}`); // Logs value and its square
    console.log(`Index: ${idx}, Previous Value: ${arr[idx - 1] || 'None'}`); // Logs current index and previous value
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
    // console.log(item.languageName);
})

var value = myCoding.forEach((item) => {
    return item;
})
console.log(value); // undefined

// If you want to return a value back, then use Filter, Map or Reduce.
const myNums = [3, 4, 6, 2, 6, 7, 3];
const newNum = [];
myNums.forEach(item => {
    if (item > 4) {
        newNum.push(item);
    }
})
console.log(newNum);

let newNum2 = myNums.filter(item => item > 4);
console.log(newNum2);



console.log("===== 5. for await...of Loop =====");
// for await (variable of asyncIterable) {}
asyncIterableExample();

async function asyncIterableExample() {
    const asyncIterable = {
        [Symbol.asyncIterator]() {
            let i = 0;
            return {
                next() {
                    if (i < 3) {
                        return Promise.resolve({ value: i++, done: false });
                    }
                    return Promise.resolve({ done: true });
                }
            };
        }
    };

    for await (const num of asyncIterable) {
        console.log(num); // Logs 0, 1, 2
    }
}
