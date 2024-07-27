const fs = require('node:fs');

/*
https://nodejs.org/docs/latest/api/fs.html

file system contains each method is sync and async form.
async methods require callback function.x
*/

const files = fs.readdirSync('./'); // read all the files of the current folder.
console.log("sync files");

fs.readdir('./', function(err, files) {
    if(err) console.log("async error:", err);
    if(files) console.log("async files:", files);   // read all the files of the current folder.
});

