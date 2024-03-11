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