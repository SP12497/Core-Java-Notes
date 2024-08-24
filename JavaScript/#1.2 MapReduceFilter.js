/*
Map: 
    - The map function is used to transform each element of an array according to a specified callback function
      and returns a new array with the results of applying the callback function to each element.
Reduce: 
    - The reduce function is used to reduce an array to a single value 
      (eg., summing all elements, finding the maximum value, concatenating strings, etc.) 
      by applying a callback function to each element of the array, accumulating a result along the way.
Filter: 
    - The filter function is used to create a new array with all elements 
      that pass a test implemented by the provided callback function.
*/

const arr = [5, 8, 3, 4];
console.log("Map");
function binary(x) {
    return x.toString(2);
}

const output = arr.map(binary); // callback function need to return value.
console.log(output);

console.log("double:", arr.map(x => x * 2));

console.log("----------Filter----------");
// callback function should return condition ie true and false.
function isOdd(x) {
    return x % 2 === 1;
}
console.log("odd array:", arr.filter(isOdd));
console.log("greater than 3 array:", arr.filter(x => x > 3));


console.log("----------Reduce----------");

// reduce works same like this function:
function findSum(arr) {
    let sum = 0;    // sum = acc (accumulator), 0=> initial value
    for (let i = 0; i < arr.length; i++) {
        sum += arr[i];  // arr[i] => current value (curr)
    }
    return sum; // return final value of accumulator.
}

console.log(findSum(arr));

console.log("reduce sum", arr.reduce((acc, curr) => {
    acc = acc + curr;
    return acc;
}, 0));

// -------

function findMax(arr) {
    let max = 0;    // sum = acc (accumulator), 0=> initial value
    for (let i = 0; i < arr.length; i++) {
        if (arr[i] > max) {
            max = arr[i];
        }
    }
    return max; // return final value of accumulator.
}

console.log("findMax:", findMax(arr));

console.log("reduce findMax:", arr.reduce((acc, curr) => {
    // console.log(acc, curr);
    if (curr > acc) {
        acc = curr;
    }
    return acc;
}));

console.log("my reduce", arr.reduce((acc, curr) => {
    return acc < curr ? curr : acc;
}, 0));


// --------Test---------
console.log("______________________");

const users = [
    { firstName: "Sagar", lastName: "Patil", age: 26 },
    { firstName: "John", lastName: "Cena", age: 46 },
    { firstName: "Roman", lastName: "Reigns", age: 38 },
    { firstName: "Brock", lastName: "Lesnar", age: 46 },
]

const fullNameArr = users.map(obj => obj.firstName + " " + obj.lastName);
console.log("fullNameArr:", fullNameArr);

const olderThan30 = users.filter(obj => obj.age > 30);
console.log("olderThan30:", olderThan30);

const firstNamesOlderThan30 = users.filter(obj => obj.age > 30).map(obj => obj.firstName);
console.log("firstNamesOlderThan30:", firstNamesOlderThan30);

// Count of same ages
// acc => {26:1, 46:2, 38:1}
const countOfAge = users.reduce((acc, curr) => {
    if (acc[curr.age]) {
        acc[curr.age] = ++acc[curr.age];
    } else {
        acc[curr.age] = 1;
    }
    return acc;
}, {});

console.log("countOfAge:", countOfAge);

// Q. Get last name of all the users whose age is greater that 20... using Reduce.

const lastNameOlderThan30 = users.reduce((acc, curr) => {
    if (curr.age > 30)
        acc.push(curr.lastName);
    return acc;
}, []);

console.log("reduce lastNameOlderThan30:", lastNameOlderThan30);