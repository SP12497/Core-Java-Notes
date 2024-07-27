// https://nodejs.org/docs/latest/api/os.html

const _os = require('os');

console.log("cpus", _os.cpus()); // Returns an array of objects containing information about each logical CPU core.
console.log("freemem", _os.freemem()); // Returns the amount of free system memory in bytes.
console.log("arch", _os.arch()); // Returns a string identifying the operating system CPU architecture.
console.log("totalmem", _os.totalmem()); // Returns the total amount of system memory in bytes.
console.log("hostname", _os.hostname()); // Returns the hostname of the operating system.
console.log("platform", _os.platform()); // Returns a string identifying the operating system platform.
console.log("release", _os.release()); // Returns a string identifying the operating system release.
console.log("type", _os.type()); // Returns a string identifying the operating system name.