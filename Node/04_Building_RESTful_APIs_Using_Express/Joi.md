JOI:
    - npm i joi
    - Joi is a powerful validation library for JavaScript that allows you to easily validate and sanitize user input.
    - It provides a simple and declarative API for defining validation rules.
    - Joi Object:
        const Joi = require('joi');
        const schema = Joi.object({
                name: Joi.string().min(3).required()
        });

Here are some important functionalities of Joi:
1. Data Validation:
   - Joi provides a wide range of validation methods to validate different types of data, such as strings, numbers, dates, objects, arrays, etc.
   - Example: `Joi.string().min(3).required()` validates that a string is at least 3 characters long and is required.

2. Error Handling:
   - Joi automatically generates detailed error messages when validation fails.
   - Example: `const { error, value } = schema.validate(data);` returns an error object if validation fails, which contains detailed error messages.

3. Object Validation:
   - Joi allows you to validate complex objects with nested properties.
   - Example: `Joi.object({ name: Joi.string().required(), age: Joi.number().min(18) })` validates an object with a required `name` property and an optional `age` property that must be at least 18.

4. Array Validation:
   - Joi provides methods to validate arrays and their elements.
   - Example: `Joi.array().items(Joi.string())` validates an array where each element must be a string.
   - Example: `Joi.array().min(1).max(5)` validates an array with at least 1 and at most 5 elements.
   - Example: `Joi.array().unique().items(Joi.string()).min(1).max(5).required().label('My Custom Label').error(new Error('My Custom Error'))` 
      validates an array with unique string elements, at least 1 and at most 5 elements, and is required. It also provides a custom label and error message. 

5. Custom Validation:
   - Joi allows you to define custom validation rules using the `Joi.extend()` method.
   - Example: `Joi.extend((joi) => ({ type: 'custom', validate(value, helpers) { ... } }))` defines a custom validation rule.
   - Use cases: You can use custom validation rules to validate complex data structures, such as phone numbers, email addresses, URLs, etc.

6. Sanitization:
   - Joi can also be used to sanitize user input by removing unwanted characters or formatting data.
   - Example: `Joi.string().trim().lowercase()` trims leading and trailing whitespace and converts the string to lowercase.

These are just a few examples of what you can do with Joi. 
It provides many more features and options for data validation and sanitization. 
Refer to the Joi documentation for more details: https://joi.dev/