Express: 
    - Express is a fast, unopinionated, and minimalist web framework for Node.js. 
    - It provides a simple and flexible way to build web applications and APIs.

Here are some important methods and functionalities in Express:
1. `express()` 
    - Creates an Express application.
    - This function returns an instance of the Express application that can be used to configure routes and middleware.
    - For example, `const app = express()` creates a new Express application.
    - `app` is an instance of the Express application that can be used to define routes and middleware.

2. `app.use()` 
    - Mounts middleware functions in the application's request processing pipeline.
    - It is used to handle tasks such as parsing request bodies, logging, and more.

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

11. `res.status(code)` 
    - Sets the HTTP status code of the response.
    - For example, `res.status(404)` sets the status code to 404 (Not Found).

12. `Joi` 
    - A powerful schema description language and data validator for JavaScript.
    - It is commonly used for input validation in Express applications.

13. `res.render(viewName, paramsObject)`
    - Renders a view template specified by `viewName` and sends the rendered HTML to the client.
    - The `paramsObject` is an optional object that can be used to pass data to the view template.
    - This method is commonly used to dynamically generate HTML pages using template engines like EJS or Pug.
    -   app.set('view engine', 'pug')
        app.set('views', './src/views');
        app.get('/template/pub', (req, res) => {
            res.render('index', { title: "Pug Express App", message: "Hi.. This is Sagar!" })
        });

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