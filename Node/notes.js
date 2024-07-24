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
    - Practical: Create a file named "fs.js" and write the following code in it.
*/
        const fs = require('fs');
        fs.readFile('/Node/sampleinput.txt', 'utf8', (err, data) => {   // read file asynchronously
            if (err) {
            console.error(err);
            return;
            }
            console.log(data);
        });

        let textIn = fs.readFileSync('/Node/sampleinput.txt', 'utf8');   // read file synchronously
        console.log(textIn);

