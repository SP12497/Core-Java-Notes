module.exports.myLogger = function (req, res, next) {
    console.log("my middleware...");
    next(); // if we dont call, it wont go to next function like route or next middleware.
};