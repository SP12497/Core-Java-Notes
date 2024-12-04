const EventEmiter = require('events');

// ------------
const emitter = new EventEmiter();
emitter.on('messageLogged', function () {
    console.log("listerner 1 called###");
})

const log = require('./logger1');
log('message'); // did not call the listener, because the listener is not registered yet in the logger1.js file.

// ------------
// create a class and call using same object:

const Logger = require('./logger2');
const loggerObj = new Logger(); // Logger class extends EventEmitter class. loggerObj have all the properties and functions of EventEmitter class.

loggerObj.on('messageLogged2', function () {    // register the listener in Logger class before calling the log function.
    console.log("listerner 2 called###");
});

loggerObj.log("message2")