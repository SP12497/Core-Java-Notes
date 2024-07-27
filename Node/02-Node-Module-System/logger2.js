const EventEmiter = require('events');

var url = 'http://mylogger.io/log';

// create a class which extents EventEmiter
class Logger2 extends EventEmiter {
    log(message) {
        console.log(message);
        this.emit('messageLogged2', { id: 1, url });
    }
}

module.exports = Logger2;