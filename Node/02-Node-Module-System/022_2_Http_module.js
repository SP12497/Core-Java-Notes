const http = require('http');

const server = http.createServer();

server.listen(3000, () => { console.log("server is listening port 3000..."); });

server.on('connection', (socket) => {   // listen to connection event
    console.log("New connection...");
});

/*
Observer pattern:
- The observer pattern is a behavioral design pattern that 
  allows an object (known as the subject) to maintain a list of its dependents (known as observers) 
  and notify them automatically of any state changes. 
- This pattern promotes loose coupling between the subject and its observers, 
  allowing for easy addition or removal of observers without affecting the subject's code.
*/
server.on('request', (req, res) => {    // listen to request event
    if (req.url === '/') {
        res.write("This is homepage");
        res.end();
    }

    if (req.url === '/api/courses') {
        res.write(JSON.stringify(["Hindi", "English", "Marathi"]));
        res.end();
    }
});
