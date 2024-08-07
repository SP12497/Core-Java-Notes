// https://roadsidecoder.hashnode.dev/javascript-interview-questions-promises-and-its-polyfills
console.log("__________________Synchronous Code________________");
console.log("start");
console.log("message1");
console.log("end");

console.log("__________________Asynchronous Code________________");
console.log("2 start");
setTimeout(() => {
    console.log("2 Async setTimeout code called");
}, 0)

function plzSubscribe(name, cb) {
    setTimeout(() => {
        cb(`2 Subscribe to ${name}`);
        return 10;
    }, 500);
};

function likeTheVideo(vdo, cb) {
    setTimeout(() => {
        cb(`2 Thanks for like the ${vdo}`);
    }, 200);
};
const result = plzSubscribe("SPWorld", function (message) { console.log(message) }); // called second because time
likeTheVideo("Javascript", function (message) { console.log(message) });    // called first
console.log(result);    // undefined
console.log("2 end");

/* Output:
2 start
undefined
2 end
2 Async setTimeout code called
2 Thanks for like the Javascript
2 Subscribe to SPWorld
*/

function shareTheVideo(vdo, cb) {
    setTimeout(() => {
        cb(`3 Thanks for sharing the ${vdo}`);
    }, 200);
};

console.log("______CallBack Hell/Pyramid Of Doom______");
// Below pyramid like structure also called as: Pyramid Of Doom.
// Below Concept called as: CallBack Hell.
// How to all functions execute sequentially?
// solution:    
plzSubscribe("SPWorld", function (message) {
    console.log(message);
    likeTheVideo("JS Course", function (message) {
        console.log(message);
        shareTheVideo("JS Course", function (message) {
            console.log(message);
            shareTheVideo("JS Course", function (message) {
                console.log(message);
            })
        })
    })
})
