/*
EVENT: A signal that something has happened
https://nodejs.org/docs/latest/api/events.html
*/

const EventEmiter = require('events');
const emitter = new EventEmiter();
/*
- events return class EventEmitter.
- EventEmiter is a class, which contains properties and functions.
*/

/**
 * Register a listener for the 'messageLogged' event.
 * @event messageLogged
 * @callback listener
 * @memberof EventEmitter
 * @param {Object} args - The arguments passed to the listener.
 */
emitter.on('messageLogged', function () {
    console.log("listener called###");
});

/**
 * Emit the 'messageLogged' event.
 * @event messageLogged
 * @memberof EventEmitter
 */
emitter.emit('messageLogged');


/*
Note: 
- Emitting the event before registering the listener will throw an error. 
- To avoid the error, register the listener before emitting the event.
*/
emitter.on('messageLogged2', function (args) {
    console.log("listener 2 called: ", args);
})

emitter.emit('messageLogged2', {id:1, url: "www.google.com"});

emitter.on('messageLogged3', function (id, url) {
    console.log("listener 3 called: ", id, url, arguments);
})
emitter.emit('messageLogged3', 3, "www.youtube.com");
