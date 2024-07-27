// route from 058_custom_middleware file:
// Topic: 067 Structuring Express Applications:
// ============================================

const express = require('express');

// Create a new instance of the Express application:
// const app = express();  // This will create a new app object, separate from the parent file's app object.

// Create a new instance of the Express Router:
const router = express.Router(); // This will create a new router object that is associated with the parent app object ie. 058_custom_middleware.js express app object.

const students = [
    { id: 1, name: "Marathi" },
    { id: 2, name: "Hindi" },
    { id: 3, name: "English" },
];

router.get('/', (req, res) => {
    res.send(students)
});
router.get('/:id', (req, res) => {
    res.send(students[parseInt(req.params.id)])
});
router.post('/', (req, res) => {
    const student = {
        id: students.length + 1,
        name: req.body.name
    }
    students.push(student);
    res.send(student);
});

module.exports = router;
