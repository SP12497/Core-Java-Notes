const express = require('express')
const { logger } = require('./middleware/logger');

const app = express();

// 057 Custom middleware
app.use(logger);
app.use(function (req, res, next) {
    console.log("authenticating...");
    next();
})

// 058 Build in middleware:
// ========================
app.use(express.json());

// pass arrays and complex object using url encoded format:
// postman Body-> x-www.form-unlencoded => key:name value:Sagar
// POST: localhost:3000/api/courses
// will get name data inside body.
app.use(express.urlencoded({ extended: true })); //key=value&key=value => {key:value, key:value}

// read all static asset from the folder like images, other static data.
// http://localhost:3000/readme.txt
app.use(express.static('./public')); // express.static(<folderName>)


const courses = [
    { id: 1, name: "Marathi" },
    { id: 2, name: "Hindi" },
    { id: 3, name: "English" },
];


app.get('/api/courses', (req, res) => {
    res.send(courses)
});

app.post('/api/courses', (req, res) => {
    const course = {
        id: courses.length + 1,
        name: req.body.name
    }
    courses.push(course);
    res.send(course);
});

const port = process.env.PORT || 3000;
app.listen(port, () => console.log(`Listing on port ${port}...`));

// 060 Third Party Middleware:
// ==============================
// https://expressjs.com/en/resources/middleware.html
// const morgan = require('morgan')
// app.use(morgan('tiny')) // help to log the request
// const morgan = require('helment')
// app.use(helmet())

// 061 Environments:
//============================
console.log(`NODE_ENV: ${process.env.NODE_ENV}`); // Node.js environment variable
console.log(`app: ${app.get('env')}`);  // express environment variable // if NODE_ENV is not present then by default returns 'development'

if (app.get('env') === 'development') {
    console.log("only dev services are enabled");
}
// - set NODE_ENV=production


// 062 Configuration:
// environment specific configuration
// - npm i config
// create config folder with <NODE_ENV>.json files 
const config = require('config');
console.log('Application Name:', config.get('name'));
console.log('Application Name:', config.get('mail.host'));
/*
- don't store passwords in configuration

---------
- better option to store passwords in environment variables.
- set app_password=1234
- custom-environment-variables.json: mappkng of configuration settings to an environment variables
    - "password": "app_password"
*/
console.log('Mail Password:', config.get('mail.password'));


// 063 Debugging:
//===============
// - npm i debug
const startUpDebugger = require('debug')('app:startup');
const dbDebugger = require('debug')('app:db');
if (app.get('env') === 'development') {
    startUpDebugger("only dev services are enabled...");
}
dbDebugger('Connected to the database...')
/*
RUN:
    step 1: 
        - set DEBUG=app:startup         // only startup related debug will print
        - set DEBUG=app:db              // only db related debug will print
        - set DEBUG=app:db,app:startup  // both debug will print
        - set DEBUG=app:*               // all debug will print
    step 2:
        - node <filename> // run the project/file
*/

/*
064 Templating Engines:
    - In all the api's, we were returning json data, 
      but in so some scenario's, we have to return data in HTML
    - To return api data in HTML, we need templating Engines.
    - There are various templating Engines are available, some popular are:
        - Pug
        - Mustache
        - EJS
    - eg. app.set('view engine', 'pug'); // express in internally load this engine
*/
app.set('view engine', 'pug')   // 
app.set('views', './src/views'); // path where we stored the templates // its optional

app.get('/template/pub', (req, res) => {
    res.render('index', { title: "Pug Express App", message: "Hi.. This is Sagar!" })
});


// 067 Structuring Express Applications:
const students = require('./routes/student');
const home = require('./routes/home');
app.use('/api/students', students); // all routes of students will start with /api/students routes to students file routes.
app.use('/', home); // note: this "/" should be at the end, because it will match all routes.

const { myLogger } = require('./middleware/myMiddleware');
app.use(myLogger); // This won't work, this line should be before all routes
