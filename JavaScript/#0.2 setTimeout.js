console.log("Start");

setTimeout(function cb() {
    console.log("Callback");
}, 500);


let startDate = new Date().getTime();
let endDate = startDate;
while (endDate < (startDate + 10000)) {    // execute for 10 sec.
    endDate = new Date().getTime();
}

console.log("after", (endDate - startDate) / 1000 , "sec wait.");
console.log("While Expired.");
// Once while loops blocking main thread for 10 sec by Global Execution stack. Once GE done, then callback will get pick from callback stack and executed.

function cb() {
    console.log('cb fun');
};
setTimeout(cb, 0); // This will defer the code. 
// means It wont execute immediatly. Once Global Execution is completed then this will get executed.
// Use case: When we have to execute some code but its not priority (dont want to block the thread), then we can use.

console.log("End");
/* Output:
Start
after 10 sec wait.
While Expired.
End
Callback
cb fun
*/