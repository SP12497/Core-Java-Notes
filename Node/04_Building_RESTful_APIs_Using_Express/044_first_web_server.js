// refer: readme.md file
// next file:  ./src/058_custom_middleware.js
const express = require('express')
const Joi = require('joi'); // joi retuns class, so Joi J should be capital.

const app = express();
app.use(express.json());  // JSON.parse(req.body); // middleware to parse JSON object. // without this, req.body will be undefined.

const courses = [
    { id: 1, name: "Marathi" },
    { id: 2, name: "Hindi" },
    { id: 3, name: "English" },
];

app.get('/', (req, res) => {
    res.send('Hello World')
})

app.get('/api/courses', (req, res) => {
    res.send(courses)
})

// app.listen(3000, () => console.log("Listing on port 3000..."));

// 046: Environment Variables:
//cmd :  set PORT=5000
const port = process.env.PORT || 3000;
app.listen(port, () => console.log(`Listing on port ${port}...`));

// 047 Route Parameters:
app.get('/api/posts/:year/:month', (req, res) => {
    // http://localhost:3000/api/posts/2034/JAN
    res.send(req.params); // { "year": "2034", "month": "JAN" }
    // res.send(req.params.year);
    // res.send(req.params.month);

    // query Param:
    // http://localhost:3000/api/posts/2034/JAN?sortBy=name
    res.send(req.query);  // {"query": "name"}
});

// 048 Handling HTTP GET Request:
app.get('/api/courses/:id', (req, res) => {
    const course = courses.find(c => c.id === parseInt(req.params.id));
    if (!course) return res.status(404).send("The course with the given ID was not found.");
    res.send(course);
});

// 049 Handling POST request:
// ==========================
app.post('/api/courses', (req, res) => {
    /* 
    req.body always is in JSON format and express does not parse it automatically. 
    we have to let express know, parse it always:
        - app.use(express.json());  // JSON.parse(req.body);
    */
    const course = {
        id: courses.length + 1,
        name: req.body.name
    }
    courses.push(course);
    res.send(course);
});

// 051 Input Validations:
// ======================
app.post('/api/coursesValidation', (req, res) => {
    if (!req.body.name || req.body.name.length) { // manual validations or we can use joi in next example
        return res.status(400).send('Name is required and should be minimum 3 characters.');
    }

    const course = {
        id: courses.length + 1,
        name: req.body.name
    }
    courses.push(course);
    res.send(course);
});

// - npm i joi // refer: joi.md file
app.post('/api/coursesJoi', (req, res) => {
    const schema = Joi.object({
        name: Joi.string().min(3).required()
    });

    const { error, value } = schema.validate(req.body);
    if (error) {
        const errorMessage = error.details.map(d => d.message).join(', ');
        return res.status(400).json({ error: errorMessage });
    }
    const course = {
        id: courses.length + 1,
        name: req.body.name
    }
    courses.push(course);

    res.send(course);
});

//052 Handling HTTP PUT Request:
//==============================
// PUT: localhost:3000/api/courses/1  => { "name": "Kannada" }
app.put('/api/courses/:id', (req, res) => {
    const course = courses.find(c => c.id === parseInt(req.params.id));
    if (!course) return res.status(404).send("The course with the given id was not found.");

    const { error } = validateCourse(req.body);
    if (error) {
        return res.status(400).send(error.details[0].message)
    }

    course.name = req.body.name;
    res.send(course);
});

function validateCourse(course) {
    const schema = Joi.object({
        name: Joi.string().min(3).required()
    })//.validate(course);
    return schema.validate(course);
}

// DELETE: localhost:3000/api/courses/1
app.delete('/api/courses/:id', (req, res) => {
    const course = courses.find(c => c.id === parseInt(req.params.id));
    if (!course) return res.status(404).send("The course with the given ID was not found.");

    const index = courses.indexOf(course);
    courses.splice(index, 1);
    res.send(course);
});
