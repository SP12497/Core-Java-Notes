// Q1:
// console.log("start")
// const promise1 = new Promise((resolve, reject) => {
//     console.log(1);
//     resolve(2);
// });

// promise1.then((res) => {
//     console.log(res);
// });
// console.log("end");
// Output: start 1 end 2


// Q2:
// console.log("start")
// const promise2 = new Promise((resolve, reject) => {
//     console.log(1);
//     resolve(2);
//     console.log(3);
// });

// promise2.then((res) => {
//     console.log(res);
// })
// console.log("end")

// Output: start 1 3 end 2

// Q 2.2
// console.log("start")
// const promise1 = new Promise((resolve, reject) => {
//     console.log(1);
//     console.log(3);
// });

// promise1.then((res) => {
//     console.log(res);
// })
// console.log("end")

// Output: start 1 3 end

// Q3
// console.log("start")
// const fn = () =>
//     new Promise((resolve, reject) => {
//         console.log(1);
//         resolve("success")
//         console.log(3);
//     });

// console.log("middle");
// fn().then((res) => {
//     console.log(res);
// })
// console.log("end");

// Output: start middle 1 3 end success

// Q4:

// function job() {
//     return new Promise((resolve, reject) => {
//         // resolve();
//         reject();
//     })
// }

// let promise = job();
// promise
//     .then(function () {
//         console.log("success 1");
//     })
//     .then(function () {
//         console.log("success 2");
//     })
//     .then(function () {
//         console.log("success 3");
//     })
//     .catch(function () {
//         console.log("catch 1");
//     })
//     .then(function () {
//         console.log("success 4");
//     });

// Output: reject()=> catch1 success4
// Output: resolve()=> success1 success2 success3 success4


// Q5:

function job(state) {
    return new Promise(function (resolve, reject) {
        if (state) {
            resolve("success");
        } else {
            reject("error");
        }
    });
}

// job(true)
//     .then(function (data) {
//         console.log("then1:", data);
//         return job(false);
//     })
//     .catch(function (error) {
//         console.log("catch1:", error);
//         return "Error Caught";
//     })
//     .then(function (data) {
//         console.log("then2:", data);
//         return job(true);
//     })
//     .catch(function (error) {
//         console.log("catch2:", error);
//     })
//     .then(function (data) {
//         console.log("then3:", data);
//         return job(true);
//     });

// Output: then1: success  | catch1: error  |  then2: Error Caught  |  then3: success

// job(true)
//     .then(function (data) {
//         console.log(data);
//         return job(true);
//     })
//     .then(function (data) {
//         if(data !== "victory")
//             throw "defeat";
//         return job(true);
//     })
//     .catch(function (error) {
//         console.log(error);
//         return job(false);
//     })
//     .then(function (data) {
//         console.log(data);
//         return job(true);
//     })
//     .catch(function (error) {
//         console.log(error);
//         return "Error caught"
//     })
//     .then(function (data) {
//         console.log(data);
//         return new Error("test");   // Not Returning a promise.
//     })
//     .then(function (data) {
//         console.log("Success:", data.message);
//     })
//     .catch(function (error) {
//         console.log("Error:", error.message);
//     });

// Output: success | defeat | error | Error caught | success: test

// Q7: Promise Chaining:
// const firstPromise = new Promise((resolve, reject) => {
//     resolve("first promise success");
// })

// const secondPromise = new Promise(function (resolve, rejected) {
//     resolve(firstPromise);
// })

// secondPromise
//     .then((res) => {
//         return res; // function
//     })
//     .then((res) => {
//         console.log(res);
//     });
