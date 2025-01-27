/*
#28 Event Loop:
--------------
redirected from: Architecture of NODE JS (C++) in ./notes.js
- The event loop is a fundamental concept in JavaScript that ensures non-blocking, asynchronous operations.
- It enables JavaScript, which has a single-threaded runtime, to execute multiple tasks like handling events, performing I/O operations, and running timers without blocking the main thread.
Execution Flow:
Suppose: we are doing async operation like reading file, then we can use callback function to handle the response.
- eg. fs.readFile('file.txt', (err, data) => {});
    - Reading file is a heavy task. So read file operation is added to the thread pool. Main thread is free, it will perform other operations.
    - Event loop checks the thread pool for the completed operation.
    - When the operation is completed, the callback function is added to the callback queue in the event loop.
    - callback function will wait for Main thread to be free.
    - When Main thread is free, it will take the callback function from the callback queue and execute it.
    - Async Operation -> Thread Pool -> Event Loop (Callback Queue) -> Main Thread

LIBUV:
    - Event loop is heart of Node.js application.
    - Event Loop Phases:
        - All Phases callbacks are executed in the order in which they are added to the event loop.
            eg. suppose, callbacks are present in phase 1, the event loop will execute all callbacks of phase 1 first, then it will move to phase 2 and execute all callbacks of phase 2.
                now, if new callbacks are added to phase 1. Event loop will complete all callbacks of phase 2 first, then it will go to next phase ie. phase 3, and so on.
                Once all phases are completed, it will again start from phase 1.
                -> Phase 1-> 2 -> 3 -> 4 -> 5 -> 6 -> 1 -> ....
        Phase 1. Timers: setTimeout(), setInterval()
        Phase 2. Pending Callbacks: Execute I/O callbacks deferred to the next loop iteration.
        Phase 3. Idle, prepare: Only used internally.
        Phase 4. Poll: Retrieve new I/O events; execute I/O related callbacks (almost all of the code execution). 
        Phase 5. Check: setImmediate() callbacks are invoked here
        Phase 6. Close Callbacks: Some close callbacks, like socket.on('close', () => {})
    - Microtask Queue: Promises, Object.observe(), MutationObserver
        - This is special queue for promises.
        - Once the current phase is completed by the event loop, it will check for Microtask Queue.
            eg. Phase 1 -> Phase 2 -> (now, callback functions are added to Microtask Queue oe Next Tick Queue) Execute Microtask Queue/ Next Tick Queue -> Phase 3 -> Phase 4 -> Phase 5 -> Phase 6 -> Phase 1 -> ....
        - callback function to resolve promises.
    - Next Tick Queue: 
        - callback functions are attached to the next tick queue. 
        - eg. process.nextTick(function callback() {});

Event Loop Phases by Procademy Youtube Channel:
    - Phase 1: Expired timers               // setTimout(), setInterval()
    - Phase 2: I/O Tasks and polling        // readFile, readStream
    - Phase 3: setImmediate() callbacks     // setImmediate
    - Phase 4: Closed callbacks             // fs.close, socket.on('close')
    - Microtasks Queue (High Priority)      // Promises (.then, .catch, .finally)
    - Next Tick Queue  (High Priority)      // process.nextTick(callback)
*/

// ------------------------------
// #29 NODE JS Event Loop in Practice:
const fs = require('fs');

// Example : 1
/*
console.log("Program Started");

//setImmediate():
//  - As per node defination, 'setImmediate' function is always complete execution before any timeout functions.
//  - But, there is a know bug, setTimout(0) is in Phase 1 and setImmediate() is in Phase 3. So, setImmediate() is not always executed after setImmediate().
//    It depends on the phase in which they are added to the event loop.

setImmediate(() => {    // Phase 3
    console.log("Stored setImmediate callback in Phase 3.");
}); 

// Note: first, readFile is added to the thread pool. Once read operation is completed, callback function is added to the callback queue of phase 2 in the event loop.
fs.readFile('samplefile.txt', (err, data) => {  // Phase 2, this operation will take some time to complete and add callback to the event loop.
    console.log("readFile completed in Phase 2.");
});

setTimeout(() => {  // Phase 1
    console.log("Stored setTimeout callback in Phase 1.");
}, 0);


console.log("Program Ended");
*/
/*
Otput:
    Program Started
    Program Ended
    Stored setTimeout callback in Phase 1.
    Stored setImmediate callback in Phase 3.
    readFile completed in Phase 2.
*/


// ---------------------------
// Example 2:
console.log("Program Started");
fs.readFile('samplefile.txt', (err, data) => {
    console.log("readFile completed in Phase 2.");  // Phase 2 is in progress

    setTimeout(() => {
        console.log("Stored setTimeout callback in Phase 1.");
    });

    setImmediate(() => {
        console.log("Stored setImmediate callback in Phase 3.");    // Phase 2 -> Next Tick Queue -> Phase 3 -> Phase 4 -> Phase 5 -> Phase 6 -> Phase 1 -> ....
    });

    process.nextTick(() => {        // Picked once current phase (any between Phase 1 to 4) ie. Phase 2 is completed.
        console.log("Stored in Next Tick Queue.");
    });
    
});

console.log("Program Ended");
/*
Output:
    Program Started
    Program Ended
    readFile completed in Phase 2.
    Stored in Next Tick Queue.
    Stored setImmediate callback in Phase 3.
    Stored setTimeout callback in Phase 1.
*/
