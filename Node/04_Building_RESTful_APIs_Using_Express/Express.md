``` javascript
Express: 
    - Express is a free and open-source web application framework for Node.js.
    - Express is a fast, unopinionated, and minimalist web framework for Node.js. 
    - It provides a simple and flexible way to build web applications and APIs.

const express = require('express');
const app = express();
app.listen(3000, () => console.log('Listening on port 3000...'));

Here are some important methods and functionalities in Express:
1. `express()` 
    - Creates an Express application.
    - This function returns an instance of the Express application that can be used to configure routes and middleware.
    - For example, `const express = require('express'); const app = express(); creates a new Express application.
    - `app` is an instance of the Express application that can be used to define routes and middleware.

2. `app.use()` 
    - Mounts middleware functions in the application request processing pipeline.
    - It is used to handle tasks such as parsing request bodies, logging, and more.
    - app.use(express.json()); // used to get the request body in request object in JSON format. By default, req.body will be undefined.

3. `app.get(path, callback)` 
    - Handles HTTP GET requests for the specified path. 
    - The callback function is executed when a GET request is made to the specified path.

4. `app.post(path, callback)` 
    - Handles HTTP POST requests for the specified path.
    - The callback function is executed when a POST request is made to the specified path.

5. `app.put(path, callback)` 
    - Handles HTTP PUT requests for the specified path.
    - The callback function is executed when a PUT request is made to the specified path.

6. `app.delete(path, callback)` 
    - Handles HTTP DELETE requests for the specified path. 
    - The callback function is executed when a DELETE request is made to the specified path.

7. `app.listen(port, callback)` 
    - Starts the Express application and listens for incoming requests on the specified port.
    - For example, `app.listen(3000, () => console.log('Listening on port 3000...'))`.

8. `req.params` 
    - An object containing the route parameters extracted from the URL.
    - For example, if the URL is `/api/courses/id`, `req.params` will contain `{ id: 'id' }`.
      and if the requested url is `/api/courses/1`, `req.params` will be `{ id: '1' }`.

9. `req.query` 
    - An object containing the query parameters extracted from the URL.
    - For example, if the URL is `/api/courses?id=1&name=math`, `req.query` will contain `{ id: '1', name: 'math' }`.

10. `res.send(data)` 
    - Sends the specified data as the response to the client.
    - For example, `res.send('Hello World')` sends the string 'Hello World' as the response.
    - `res.status(200).send('<h4>Hello...</h4>')`
    - send method by default response in text/html format. We cant use to send JSON.
    -  `res.status(200).json({ key: "value"})`

10.2. `res.json()`:
    - `res.status(200).json({ key: "value"})`
    - used to send json data as response.

11. `res.end([data])`
    - Ends the response process and sends the response to the client.
    - The optional `data` parameter can be used to send the last chunk of data to the client.
    - This method is typically used to end the response when no more data needs to be sent.
    - For example, `res.end('Hello World')` can be used to end a response after sending the string 'Hello World'.

    - The main difference between `res.send()` and `res.end()` is that `res.send()` is used to send data as the response to the client, while `res.end()` is used to end the response process and send the response to the client. 
    - When using `res.send()`, you can pass any type of data as an argument, such as strings, objects, or arrays, and it will be automatically converted to the appropriate format (e.g., JSON). 
    - On the other hand, `res.end()` is typically used when you want to manually control the response and send the data in chunks. You can optionally pass data as an argument to `res.end()` to send the last chunk of data to the client. 
    - It's important to note that once `res.end()` is called, no further data can be sent in the response.

11. `res.status(code)` 
    - Sets the HTTP status code of the response.
    - For example, `res.status(404)` sets the status code to 404 (Not Found).

12. `res.writeHead(statusCode, headers)` 
    - Sets the status code and headers for the response.
    - The `statusCode` parameter is a number representing the HTTP status code.
    - The `headers` parameter is an object containing the response headers.
    - This method is commonly used to set the status code and headers before sending the response body.
    - For example, `res.writeHead(200, { 'Content-Type': 'text/html' })` sets the status code to 200 (OK) and the 'Content-Type' header to 'text/html'.

13. `res.render(viewName, paramsObject)`
    - Renders a view template specified by `viewName` and sends the rendered HTML to the client.
    - The `paramsObject` is an optional object that can be used to pass data to the view template.
    - This method is commonly used to dynamically generate HTML pages using template engines like EJS or Pug.
    -   app.set('view engine', 'pug')
        app.set('views', './src/views');
        app.get('/template/pub', (req, res) => {
            res.render('index', { title: "Pug Express App", message: "Hi.. This is Sagar!" })
        });

14. `Joi` 
    - A powerful schema description language and data validator for JavaScript.
    - It is commonly used for input validation in Express applications.


These are just a few examples of the methods and functionalities provided by Express. 
Express is a versatile framework with many more features and options for building web applications and APIs.

In this section will learn:
    - Middleware
    - Configuration
    - Debugging
    - Templating Engines


# 05_Express_Advance_Topics:
057 Middleware:
===============
Request Process pipeline:
    - Request
        - Middeleware : 1: express.json(), 2: route()
            - Response
Go To: 04_Building_RESTful_APIs_Using_Express\express-demo\advance\058_custom_middleware.js


- app.use() : 
    - add middleware function to the request processing pipeline.
    - app.use(express.json());  
        - internally it will attach incoming request data to req.body and do parsing like JSON.parse(req.body);
        - middleware to parse JSON object.
        - without this, req.body will be undefined.  eg. console.log(req.body); // undefined
    - app.use((req, res, next) => {
        console.log('Logging...');
        next();
    });

Route Handlers:
    app.get('/api/v1/movies', getAllMovies); // function getAllMovies(req, res) { res.send(movies); }
    app.get('/api/v1/movies/:id', getMovie);
    app.post('/api/v1/movies', createMovie);
    app.patch('/api/v1/movies/:id', updateMovie);
    app.delete('/api/v1/movies/:id', deleteMovie);

    - Transforming the above code into app.route():
    app.route('/api/v1/movies')
        .get(getAllMovies)
        .post(createMovie);
    app.route('/api/v1/movies/:id')
        .get(getMovie)
        .patch(updateMovie)
        .delete(deleteMovie);

---------
#42 Mounting Routes:
    - Benefits:
        - Modularize the code. (Code written in separate files)
        - Easy to maintain.
        - Easy to understand.
        - Easy to test.
- controller.js:
    const express = require('express');
    // const app = express();
    const movieRouter = express.Router();
    movieRouter.route('/')
            .get(getAllMovies)
            .post(createMovie);

    movieRouter.route('/:id')
        .get(getMovie)
        .patch(validateBody, updateMovie)   // #45 Chaining multiple middleware functions
        .delete(deleteMovie);
    
    validateBody = (req, res, next) => {    // middleware function
        if (!req.body.name || req.body.name.length < 3) {
            return res.status(400).send('Name is required and should be minimum 3 characters.');
        }
        next();
    }
- app.js:
    const movieRouter = require('./controller');
    app.use('/api/v1/movies', movieRouter);    // middleware to mount the router at specific path.


#44 param middleware:
    - this middleware is executed when a specific parameter is present in any URL.
    - localhost:3000/api/v1/movies/1
    router.param('id', (req, res, next, value) => {
        console.log(`Movie id is: ${value}`);
        let movie = movies.find(m => m.id === parseInt(value));
        if (!movie) 
            return res.status(404).send('Movie not found');
        next();
    });

#46 Serving static files:
    - ./public/index.html
    app.use(express.static('./public'));  // register all public files to be served as static files.
    - test: http://localhost:3000/index.html

#47 Environment variables:
    - Node.js: process.env.NODE_ENV
        - set NODE_ENV=production
    - Express: app.get('env')
        - app.get('env') === 'production'
