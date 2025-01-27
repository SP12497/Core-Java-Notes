const fs = require('node:fs');

/*
https://nodejs.org/docs/latest/api/fs.html

file system contains each method is sync and async form.
async methods require callback function.
*/

const files = fs.readdirSync('./'); // read all the files of the current folder.
console.log("sync files");

fs.readdir('./', function(err, files) {
    if(err) console.log("async error:", err);
    if(files) console.log("async files:", files);   // read all the files of the current folder.
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
// const fs = require('fs');
// Read file:
// fs.readFile(path, encoding, callback);
fs.readFile('/Node/samplefile.txt', 'utf8', (err, data) => {   // read file asynchronously, utf8 is encoding, will get callback once file read is completed.
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


