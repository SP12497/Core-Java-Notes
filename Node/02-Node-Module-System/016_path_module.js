// Code With Mosh=> Node.js Tutorial for Beginners
// const path = require('path');  // first check 'path' in node modules if not present then check local module
const path = require('node:path'); // only check in node modules.
// const _os_module = require('./017_os_module');  // "./..." will only check local modules.
var pathObj = path.parse(__filename); // __filename is a global object in node js.  // eg. C:\D\SP\Code Practice\Node Js\Code with Mosh\02-Node-Module-System\016_path_module.js

console.log(pathObj);

// https://nodejs.org/docs/latest/api/path.html

/*
pathObj =>
{
  root: 'C:\\',
  dir: 'C:\\D\\SP\\Code Practice\\Node Js\\Code with Mosh\\02-Node-Module-System',
  base: '016_path_module.js',
  ext: '.js',
  name: '016_path_module'
}
*/