
/*
Middleware:
  - Middleware is a function or a set of functions that are executed in a sequential manner 
    during the processing of an HTTP request in an Express application.
  - It sits between the incoming request and the final route handler, 
    allowing for additional processing, modifications, or error handling.
  - Middleware functions have access to the request (req) and response (res) objects,
    as well as the next function, 
    which is used to pass control to the next middleware function or route handler in the chain.
  - Middleware functions can be used for various purposes, 
    such as logging, authentication, authorization, error handling, and more. 
    They provide a way to modularize and organize the code, making it easier to manage and maintain.

Use Case:
- Logging: Middleware can be used to log information about incoming requests, such as the request method, URL, and timestamp.
- Authentication: Middleware can be used to check if a user is authenticated before allowing access to certain routes.
- Authorization: Middleware can be used to check if a user has the necessary permissions to access a particular resource.
- Error Handling: Middleware can be used to handle errors and send appropriate error responses to the client.

Example:
*/

// Middleware to log incoming requests
app.use((req, res, next) => {
  console.log(`[${new Date().toISOString()}] ${req.method} ${req.url}`);
  next();
});

// Middleware to check if user is authenticated
const authenticate = (req, res, next) => {
  if (req.session.user) {
    next();
  } else {
    res.status(401).send('Unauthorized');
  }
};

// Route handler that requires authentication
app.get('/protected', authenticate, (req, res) => {
  res.send('Welcome to the protected route');
});


/* _____________________________
Using next() Method in Express Middleware
1. Calling next() to Pass Control:
   - next() without arguments.

2. Passing an Error to Error-Handling Middleware:
   next(err) with an error argument.

3. Skipping to the Next Route Handler:
   next('route') to skip to the next route handler.

4. Using next() in Route-Level Middleware:
   Route-specific middleware using next().


____________________________________

Using next() Method in Express Middleware
1 Calling next() to Pass Control:
    - next() without arguments:
*/
app.use((req, res, next) => {
  console.log('Middleware executed');
  next(); // Passes control to the next middleware or route handler
});

/*
2 Passing an Error to Error-Handling Middleware:
next(err) with an error argument
*/
app.use((req, res, next) => {
  const err = new Error('Something went wrong');
  next(err); // Passes control to the error-handling middleware
});

/*
3. Skipping to the Next Route Handler:
next('route') to skip to the next route handler:
*/
app.get('/example', (req, res, next) => {
  if (someCondition) {
    next('route'); // Skip remaining middleware in this route. Passes control to the next route handler
  } else {
    next();
  }
}, (req, res) => {
  res.send('This will be skipped if someCondition is true');
});

/*
4. Using next() in Route-Level Middleware:
  Route-specific middleware using next():
*/
const middleware = (req, res, next) => {
  console.log('Route-specific middleware');
  next(); // Passes control to the next middleware or route handler
};

app.get('/route', middleware, (req, res) => { // All Middlewares is called before the route handler
  res.send('Response from route handler');  // Response from route handler
});

// ```