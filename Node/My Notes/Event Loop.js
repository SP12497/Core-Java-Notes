/*
redirected from: Architecture of NODE JS (C++) in ./notes.js
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
    - Event Loop Phases:
        - All Phases callbacks are executed in the order in which they are added to the event loop.
        Phase 1. Timers: setTimeout(), setInterval()
        Phase 2. Pending Callbacks: Execute I/O callbacks deferred to the next loop iteration.
        Phase 3. Idle, prepare: Only used internally.
        Phase 4. Poll: Retrieve new I/O events; execute I/O related callbacks (almost all of the code execution). 
        Phase 5. Check: setImmediate() callbacks are invoked here
        Phase 6. Close Callbacks: Some close callbacks, like socket.on('close', ...)
    - Microtask Queue: Process.nextTick(), Promises, Object.observe(), MutationObserver
        - callback function to resolve promises.
    - Next Tick Queue: 
        - callback functions are attached to the next tick queue. 
        - It is executed after the current operation is completed. 
        - eg. process.nextTick(function callback() {});

- The Microtask Queue and Next Tick Queue have more priority.
- If Phase 3 of event loop is under execution, Once Phase 3 is completed, it will check for Microtask Queue and Next Tick Queue.
- if Microtask Queue and Next Tick Queue are not empty, it will execute them first.
- Once Microtask Queue and Next Tick Queue are completed. It will go to Phase 4.
- Event loop is heart of Node.js application.

Event Loop Phases by Procademy Youtube Channel:
    - Phase 1: Expired timers 
    - Phase 2: I/O Tasks and polling
    - Phase 3: setImmediate() callbacks
    - Phase 4: Closed callbacks
    - Phase 5: Microtasks Queue (High Priority)
    - Phase 6: Next Tick Queue  (High Priority)
*/


const fs = require('fs');

/*
// Example : 1
console.log("Program Started");

// Note: setImmediate() is always complete execution before any timeout.
// But, there is know bug, setTimout(0) is not always executed after setImmediate(). 
setImmediate(() => {
    console.log("Stored setImmediate callback in Phase 3.");
}); 

// Note: first, readFile is added to the thread pool. Once read operation is completed, callback function is added to the callback queue of phase 2 in the event loop.
fs.readFile('samplefile.txt', (err, data) => {
    console.log("readFile completed in Phase 2.");
});

setTimeout(() => {
    console.log("Stored setTimeout callback in Phase 1.");
});


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
//Example 2:
/*
console.log("Program Started");
fs.readFile('samplefile.txt', (err, data) => {
    console.log("readFile completed in Phase 2.");

    setTimeout(() => {
        console.log("Stored setTimeout callback in Phase 1.");
    });

    setImmediate(() => {
        console.log("Stored setImmediate callback in Phase 3.");
    }); 
    
});

console.log("Program Ended");
*/
/*
Output:
    Program Started
    Program Ended
    readFile completed in Phase 2.
    Stored setImmediate callback in Phase 3.
    Stored setTimeout callback in Phase 1.
*/

// ---------------------------
// Example 3:
console.log("Program Started");
fs.readFile('samplefile.txt', (err, data) => {
    console.log("readFile completed in Phase 2.");

    setTimeout(() => {
        console.log("Stored setTimeout callback in Phase 1.");  // push to thread pool and return immediately callback to event loop.
    });

    setImmediate(() => {
        console.log("Stored setImmediate callback in Phase 3."); // push to thread pool and return immediately callback to event loop.
    });

    process.nextTick(() => {    // At this point, All 4 phases are empty in event loop. So, it will execute nextTick() callback first.
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