
043 Introduction Express:
=========================
- mkdir express-demo
- cd express-demo
- npm init --yes     <!-- create package.json file -->
- npm i express

044 Building Your first web server:
===================================
index.js file

045 Nodemon:
============
Nodemon is a useful tool for automatically restarting your Node.js application whenever changes are detected. To install Nodemon globally, run the following command:
```
npm i -g nodemon
```

Once installed, you can use Nodemon to run your Node.js file by replacing the `node` command with `nodemon`. For example:
```
nodemon <filename>
```

With Nodemon, there is no need to manually restart the server every time you make changes to your code. Nodemon will automatically detect the changes and restart the server for you.

Remember to include the necessary script in your `package.json` file to start your application with Nodemon:

```json
"scripts": {
    "start": "nodemon <filename>"
}
```
Now, you can simply run `npm start` to start your server with Nodemon.


046 Environment Variable:
=========================
- process.env
- process.env.port
eg. 
    - const port = - process.env.port || 300;
    - app.listen(port); // currently we dont have env variable so server will run on 3000.

How to set env varible?
    - MAC: export PORT =5000
    - WINDOWS: set PORT =5000   <!-- note: no space between "=5000" -->
