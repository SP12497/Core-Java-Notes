/*
What is Nodejs?
    - Node.js is an open-source, cross-platform, JavaScript runtime environment that executes JavaScript code outside a web browser.
    - Node.js lets developers use JavaScript to write command line tools and for server-side scripting—running scripts server-side to produce dynamic web page content before the page is sent to the user's web browser.
    - Single threaded, event-driven, non-blocking I/O model.
    - Huge library of open-source packages available. Huge community support.
    
REPL:
    - Read, Eval, Print, Loop
    - Node.js comes with a built-in REPL environment. It performs the following tasks:
        - Read: Reads user's input, parses the input into JavaScript data-structure, and stores in memory.
        - Eval: Takes and evaluates the data structure.
        - Print: Prints the result.
        - Loop: Loops the above command until the user presses ctrl-c twice.
    - REPL is used to test simple JavaScript expressions.
    - Practical: Open terminal and type "node" to start REPL.
    - "_": It contains the result of the last expression.
      eg. 2 + 2 ans is 4 
          _ + 1 ans is 5

Run Node.js file:
    - Create a file with .js extension.
    - Write JavaScript code in the file.
    - Open terminal and type "node filename.js" to run the file.
    - The file will be executed and the output will be displayed in the terminal.
    - Practical: Create a file named "app.js" and write console.log("Hello World") in it. Run the file using "node app.js".

readline module: (Read Input From User)
    - Java: Scanner class, C: scanf(), Python: input() function
    - Readline module is used to read input from the user.
    - It is a built-in module in Node.js.
    - Practical: Create a file named "readline.js" and write the following code in it.
*/
        const readline = require('readline');
        const rl = readline.createInterface({
            input: process.stdin,
            output: process.stdout
        });
        rl.question('Enter a number: ', (num) => {
            console.log(`You entered: ${num}`);
            rl.close();         // emit close event to close the interface
        });
        rl.on('close', () => {  // We can listen to the close event to perform some action on close event.
            console.log('Readline interface closed.');
            process.exit(0);    // exit the process
        });

/*
fs module:
    - File System module is used to perform file operations.
    - It is a built-in module in Node.js.
    - Practical: Create a file named "file.js" and write the following code in it.
    - We can perform the following operations using the fs module:
        - Read file         : fs.readFile()     |    fs.readFileSync()
        - Write file        : fs.writeFile()    |    fs.writeFileSync()
        - Append file       : fs.appendFile()   |    fs.appendFileSync()
        - Delete file       : fs.unlink()       |    fs.unlinkSync()
        - Rename file       : fs.rename()       |    fs.renameSync()
        - Create directory  : fs.mkdir()        |    fs.mkdirSync()
        - Remove directory  : fs.rmdir()        |    fs.rmdirSync()
        - Read directory    : fs.readdir()      |    fs.readdirSync()
        - etc...
*/
const fs = require('fs');
// Read file:
fs.readFile('/Node/samplefile.txt', 'utf8', (err, data) => {   // read file asynchronously
    if (err) {
    console.error(err);
    return;
    }
    console.log(data);
});

// fs.readFileSync(path, encoding);  // is synchronous and blocks execution until finished
let textIn = fs.readFileSync('/Node/samplefile.txt', 'utf8');   // read file synchronously
console.log(textIn);

// Write File:
// fs.writeFile(path, data, callback);
fs.writeFile('/Node/samplefile.txt', textIn + '\nHello World!', (err) => {   // write file asynchronously
    if (err) {
        console.error(err);
        return;
    }
    console.log('File written successfully.');
});


/*
Routing:
    - Routing defines the way in which the client request are handled by the application endpoints.
    - We can make our application to respond to different URLs with different response using Routing.
    - Routing basically means implementing different actions for different URLs.
    - Url Patterns:
        - File based url pattern: 
            - http://localhost:3000/about.html
        - Resource based url pattern: http://localhost:3000/user     || /user/123
            - Route Parameter:
                - www.example.com/books/:category/:language
                www.example.com/books/programming/javascript
                req.url => /books/programming/javascript
                req.params.category => programming
                req.params.language => javascript
            - Query String / Query Parameter:
                - www.example.com/books?category=programming&language=javascript
                - req.query.category, req.query.language
------------------------------
Architecture of NODE JS (C++):
    - V8 JavaScript Engine: (C++ ann JS)
    - Libuv: (C++) => Event Loop, File System, Networking
        - Event Loop => Handles asynchronous operations
        - Thread Pool => Handles file system operations
    - HTTP Parser: (C) => Parse HTTP requests and responses
    - C-ares: (C)   => Handles DNS queries
    - OpenSSL: (C)  => Provides SSL support
    - Zlib: (C)    => Provides support for gzip compression
    
- Node.js uses the V8 JavaScript Engine to execute code.
- Node.js follows a single-threaded, event-driven, non-blocking I/O model.
- Node.js provides a set of built-in modules to perform different operations.
- Node.js uses the libuv library to handle asynchronous operations.
- Node.js uses the event-driven architecture to perform non-blocking I/O operations.
- Node.js uses the CommonJS module system to organize code.
- Node.js uses the npm package manager to manage packages.
- Node.js uses the Express.js framework to build web applications.
- Node.js uses the MongoDB database to store data.

Process:
    - program in running state is called process.
    - Every process has One Main Thread.
    - Node.js is single threaded which uses this Main Thread.

Node js process:
    - Main Thread: Single Threaded
    - Event Loop: Handles asynchronous operations
    - Thread Pool: 
        - all heavy operations like file system operations, network operations are not handled by the main thread. They are handled by the thread pool.
        - By default, the thread pool size is 4. We can increase or decrease the size of the thread pool upto 128.
        - Advantage: Heavy operations handled by thread pool, so main thread is free to handle other operations.
        - In node.js, we can not create multiple threads. We can only create child processes.

Event Loop: refer "Event Loop.js" file.
*/