const http = require('http');
/*
example 1:
    server object/class have all the methods and attributes of EventEmitter class.
    when someone call http://localhost:3000, it will execute this listner:
*/
// const server = http.createServer();
// server.on('connection', (socket) => {       // register a listener for the 'connection' event. // connection is predefined event.
//     console.log("New connection");
// });


// ----------------
// Example 2:

const server = http.createServer((req, res) => {    // register a listener for the 'request' event. // request is predefined event.
    if (req.url === '/') {
        res.write("This is homepage");
        res.end();
    }

    if (req.url === '/api/courses') {
        // http://localhost:3000/api/courses
        res.write(JSON.stringify(["Hindi", "English", "Marathi"]))
        res.end();
    }
});


// ----------------

server.listen(3000);

console.log('Listining on port 3000...');