/*
Promise:
    - Promises in JavaScript are objects that represent the eventual completion or failure of an asynchronous operation and its resulting value.
    - They provide a way to handle asynchronous operations in a more elegant and structured manner compared to traditional callback-based approaches.
    - A Promise is in one of three states:
        pending: initial state, neither fulfilled nor rejected.
        fulfilled (resolved): meaning that the operation was completed successfully.
        rejected: meaning that the operation failed.
    - A promise is created using the "Promise" constructor, 
      which takes a function called the "executor" as its argument. 
      The executor function typically performs some asynchronous operation and 
      calls either the resolve() function to fulfill the promise or the reject() function to reject it.
    - Methods of promises:
        1. then(onFulfilled, onRejected) :
            - The then() method is used to specify what to do when the promise is fulfilled (resolved successfully) or rejected. 
            - It takes two optional callback functions as arguments: onFulfilled and onRejected. 
              The onFulfilled function is called when the promise is fulfilled, and 
              the onRejected function is called when the promise is rejected.
        2. catch(onRejected) : 
            - The catch() method is used to specify what to do when the promise is rejected. 
            - It is equivalent to calling then(undefined, onRejected). 
            - It takes a single callback function as an argument, which is called when the promise is rejected.
        3. finally(onFinally) : 
            - The finally() method is used to specify what to do regardless of whether the promise is fulfilled or rejected. 
            - It takes a single callback function as an argument, which is called when the promise is settled (either fulfilled or rejected).
        4. Promise.resolve(value) : 
            - Returns a promise that is resolved with the given value.
        5. Promise.reject(reason) : 
            - Returns a promise that is rejected with the given reason (usually an error).
    - Promise Combinators:
        1. Promise.all()
        2. Promise.allSettled()
        3. Promise.race()
        4. Promise.any()
*/

function importantAction(username) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve(`Subscribe to ${username}`);
        }, 1000)
    })
}

function likeTheVideo(video) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve(`Liked  the ${video}`);
        }, 1000)
    })
}

function shareTheVideo(video) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve(`Shared the ${video}`);
        }, 1000)
    })
}

function deleteTheVideo(video) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            reject(`Failed to delete the ${video}`);
        }, 900)
    })
}

function downloadTheVideo(video) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            reject(`Failed to download the ${video}`);
        }, 1000)
    })
}

async function asyncImportantAction(username) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve(`Subscribe to ${username}`);
        }, 1000)
    })
}

async function asyncShareTheVideo(video) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve(`Shared the ${video}`);
        }, 800)
    })
}

function asyncDeleteTheVideo(video) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            reject(`Failed to delete the ${video}`);
        }, 1000)
    })
}



//--- resolve
// importantAction("SP World").then((message) => {
//     console.log("resolved:", message);
// }).catch((error) => {
//     console.log(error);
// })

//--- reject
// deleteTheVideo("JS Course").then((message) => {
//     console.log("_______________reject_________________");
//     console.log(message);
// }).catch((error) => {
//     console.error("catch:", error);
// })

//--- chain of promises
// importantAction("SP World").then((message) => {
//     console.log("resolved:", message);
//     likeTheVideo("JS Course").then((message) => {
//         console.log("resolved:", message);
//         shareTheVideo("JS Course").then((message) => {
//             console.log("resolved:", message);
//             deleteTheVideo("JS Course").then((message) => {
//                 console.log("resolved:", message);
//             }).catch((error) => {
//                 console.log("rejected delete", error);
//             });
//         }).catch((error) => {
//             console.log(error);
//         });
//     }).catch((error) => {
//         console.log(error);
//     });
// }).catch((error) => {
//     console.log("rejected -", error);
// });

//--- remove pyramid structure and multiple catch
// importantAction("SP World")
//     .then((message) => {
//         console.log(message);
//         return likeTheVideo("JS Course");
//     })
//     .then((message) => {
//         console.log(message);
//         return shareTheVideo("JS Course");
//     })
//     .then((message) => {
//         console.log(message);
//         return deleteTheVideo("JS Course");
//     })
//     .then((message) => {
//         console.log("delete resolve", message);
//     })
//     .catch((message) => {
//         console.log("catch -", message);
//     });

/*
Promise Combinators:
    - Promise combinators are utility functions provided by the Promise API 
      that allow you to perform common tasks with promises more easily. 
    - Some of the commonly used promise combinators are:
    1. Promise.all():
        - It will execute in concurrent, not parallely. (JS can't execute code in parallely, it can execute sequentially or concurrently.)
        - Takes an array of promises and returns a single promise that resolves 
          when all of the input promises have resolved, or rejects if any of the promises reject. 
        - It's useful for parallel execution of multiple asynchronous operations.
        - In short, it accepts multiple promises and resolves when all promises are resolve and returns resolved object. 
          If one of promise get rejected, while promise.all will get rejected, and returns rejected object.
    2. Promise.allSettled():
        - Takes an array of promises and returns a single promise that resolves 
          after all of the input promises have settled (either resolved or rejected), 
          providing an array of objects with information about each promise's outcome.
        - In short, it will always runs all the promises and returns the success result.
            No matter, promises are resolves or rejected.
        - result structure:
            [{ status: 'fulfilled', value: 'Subscribe to SP world' }, { status: 'rejected', reason: 'Failed to delete the MySQL' }, ...]
    3. Promise.race():
        - fist minimum time taken promise rejected => go to catch block
        - Takes an array of promises and returns a single promise that resolves or rejects 
          as soon as one of the input promises resolves or rejects. 
        - It's useful for implementing timeouts or racing between multiple asynchronous operations.
        - In short, it will select a promise which will take minimum time.
          if selected promise resolve then retuns success in then block
          else if selected promise rejected() then returns error in catch block
    4. Promise.any():
        - It almost same like race
        - fist minimum time taken rejected => go to next promise => if all promises are rejected => then go to catch block.
        - In short, it will select a promise which will take minimum time.
          if selected promise resolve then retuns success in then block
          else if selected promise rejected() then it will go to next minimum time taken promise
          if 2nd promise resolve the retuns success in then block
          else if go to next promise...
          if all the promises are rejected(), then finally it will go to catch block.
    
*/
// 1.
// Promise.all([
//     importantAction("SP world"),
//     likeTheVideo("Java"),
//     shareTheVideo("Java"),
// ])
//     .then(messages => {
//         console.log("resolved - " + messages);
//         console.log("resolved - " + typeof messages);   // object
//         console.log("resolved - " + messages[0]);

//     })
//     .catch(messages => console.log("rejected - " + messages));

//2.
async function myAsyncAllFunction() {
    try {
        // let results = await Promise.all([
        let [sub, share] = await Promise.all([
            asyncImportantAction("SP world"),
            asyncShareTheVideo("Java"),
            // asyncDeleteTheVideo("Java") // Reject will go to catch block.
        ]);
        // console.log(results[0]);
        // console.log(results[1]);
        console.log(sub);
        console.log(share);
    } catch (err) {
        console.log("rejected :", err);
    }
}
// myAsyncAllFunction();

// ... All Settled()
async function myAsyncAllSettledFunction() {
    try {
        let results = await Promise.allSettled([
            asyncImportantAction("SP world"),
            asyncShareTheVideo("Java"),
            deleteTheVideo("MySQL")
        ]);
        console.log(results);   // will get result of all, no matter resolve or rejected. 
        // [{ status: 'fulfilled', value: 'Subscribe to SP world' }, ...]
    } catch (err) {
        console.log("rejected :", err);
    }
}
// myAsyncAllSettledFunction();

// ... RACE()
async function myAsyncRaceFunction() {
    try {
        let results = await Promise.race([
            asyncImportantAction("SP world"),
            asyncShareTheVideo("Java"),
        ]);
        console.log(results);   // will only get result of asyncShareTheVideo, bcoz this resolved first.
    } catch (err) {
        console.log("rejected :", err);
    }
}
// myAsyncRaceFunction();

// ... ANY()
async function myAsyncAnyFunction() {
    try {
        let results = await Promise.any([
            asyncImportantAction("SP world"),   // 1000 ms
            asyncShareTheVideo("Java"),  // 800 ms
        ]);
        console.log(results);

        let results2 = await Promise.any([
            deleteTheVideo("SP world"),   // 900 ms : rejected
            asyncImportantAction("Java"),  // 1000 ms : resolved
        ]);
        console.log(results2);

    } catch (err) {
        console.log("rejected :", err);
    }

    await Promise.any([
        deleteTheVideo("SP world"),   // 1000 ms
        downloadTheVideo("Java"),  // 800 ms
    ]).then((message) => {
        console.log(message);
    }).catch((err) => {
        console.log("rejected :", err);
    })
}
// myAsyncAnyFunction();



// Q: Execute each Promise one by one recursively
function promRecurse(funcPromises) {
    if (funcPromises.length === 0) return;
    // const arr = [11,22,33]
    // const val = arr.shift(); // val = 11, arr = [22,33]
    const currPromise = funcPromises.shift();
    currPromise
        .then((res) => console.log(res))
        .catch((res) => console.log(res))

    promRecurse(funcPromises);
}

promRecurse([
    asyncImportantAction("SP world"),
    asyncShareTheVideo("Java"),
    deleteTheVideo("MySQL")
]);
