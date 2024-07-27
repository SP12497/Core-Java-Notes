const EventEmiter = require('events');
const emitter = new EventEmiter();

var url = 'http://mylogger.io/log';

function log(message) {
    console.log(message);
    emitter.emit('messageLogged', { id: 1, url });
}

module.exports = log;