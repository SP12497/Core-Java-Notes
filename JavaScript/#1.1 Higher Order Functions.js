const radius = [2, 1, 3, 5];

const calculateArea = function (radius) {
    const output = [];
    for (let index = 0; index < radius.length; index++) {
        output.push(Math.PI * radius[index] * radius[index]);
    }
    return output;
}

const calculateCircumference = function (radius) {
    const output = [];
    for (let index = 0; index < radius.length; index++) {
        output.push(2 * Math.PI * radius[index]);
    }
    return output;
}

const calculateDiameter = function (radius) {
    const output = [];
    for (let index = 0; index < radius.length; index++) {
        output.push(2 * radius[index]);
    }
    return output;
}
console.log("calculateArea:", calculateArea(radius));
console.log("calculateCircumference:", calculateCircumference(radius));
console.log("calculateDiameter:", calculateDiameter(radius));

console.log("_____________________________");

/*
In above code, issue is code repeatability, and we are not reusing the code.
Same thing we can achieve in High Order Function.

High Order Function:
    - A higher-order function is a 
        function that can take other functions as arguments 
        or return functions as results. 
    - In other words, it either accepts functions as parameters, or it can return a function as its output.
    - in builds are: map reduce filter
*/

function calculate(fn, radius) {    // High Order Function
    const output = [];
    for (let index = 0; index < radius.length; index++) {
        output.push(fn(radius[index]));
    }
    return output;
}

function area(radius) {     // Callback Function
    return Math.PI * radius * radius;
}

function circumference(radius) {
    return 2 * Math.PI * radius;
}

console.log("area:", calculate(area, radius));
console.log("circumference:", calculate(circumference, radius));

console.log("_________________");

console.log("area using map", radius.map(area));
// Create same as map.

Array.prototype.calc = function (fn) {    // High Order Function as prototype.
    const output = [];
    for (let index = 0; index < this.length; index++) {
        output.push(fn(this[index]));
    }
    return output;
}

console.log("area using calc", radius.calc(area));