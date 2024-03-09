console.log(".\#10Promise.js");
const thenable = {
    then: function (callback) {
        setTimeout(()=> callback(2), 1000)
    }
}

const value = await thenable;
console.log(value);