/**
 * Middleware function that logs a message to the console.
 * @param {Object} req - The request object.
 * @param {Object} res - The response object.
 * @param {Function} next - The next middleware function.
 */

module.exports.logger = function (req, res, next) {
    console.log("logging...");
    next(); // If we don't call this function, the request will not proceed to the next middleware or route handler.

};