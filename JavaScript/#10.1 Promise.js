// https://www.youtube.com/redirect?event=video_description&redir_token=QUFFLUhqbHl6ZnNGazNNYVJpUHI3YnJlajZwUHlLWktSd3xBQ3Jtc0tscUM0VVJmYkNXT25XZXFaWDRqbkNnRnNMejZ1Z3VGaUJ6cGNHZ1NOaHpJcmRyN0lJdnlIVG1paHlrcjJlTUpaVDB3ZjlwSjV3dnRNYlkxeVRlTnBtZDhMTDMxSDQ4bmltOGVKTUdhU2NMcDF2WXBvOA&q=https%3A%2F%2Froadsidecoder.hashnode.dev%2Fjavascript-interview-questions-promises-and-its-polyfills&v=HaJdoFp2OEc
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
    }, 500);
};

function likeTheVideo(vdo, cb) {
    setTimeout(() => {
        cb(`2 Thanks for like the ${vdo}`);
    }, 200);
};
const result = plzSubscribe("SPWorld", function (message) { console.log(message) }); // second because time
likeTheVideo("Javascript", function (message) { console.log(message) });    // first
console.log(result);    // undefined
console.log("2 end");

function shareTheVideo(vdo, cb) {
    setTimeout(() => {
        cb(`3 Thanks for sharing the ${vdo}`);
    }, 200);
};

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

/*
Promise:
A Promise is in one of these states:
    pending: initial state, neither fulfilled nor rejected.
    fulfilled/resolved: meaning that the operation was completed successfully.
    rejected: meaning that the operation failed.
Methods of promises:
    resolve()
    reject()
    then()
    catch()
*/

let val = Promise.resolve("Hi");
console.log("Promise.resolve:", val); // Promise { 'Hi' }
