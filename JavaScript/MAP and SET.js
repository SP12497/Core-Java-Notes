/*
Method	Description
const map = new Map();
map.set(key, value)	Adds a key-value pair to the map.
map.get(key)	Retrieves the value associated with the key.
map.has(key)	Checks if a key exists in the map.
map.delete(key)	Removes a key-value pair from the map.
map.clear()	Removes all key-value pairs.
map.size	Returns the number of key-value pairs.
forEach((value, key)=>{})	Executes a provided function once per key-value pair.


const set = new Set();
set.add(value)	Adds a value to the set.
set.has(value)	Checks if a value exists in the set.
set.delete(value)	Removes a value from the set.
set.clear()	Removes all values from the set.
set.size	Returns the number of values.
forEach((value)={})	Executes a provided function once for each value in the set.

*/




// -----------
const map = new Map();

// Add key-value pairs
map.set('name', 'Alice');
map.set('age', 25);

// Access values
console.log(map.get('name')); // Output: Alice

// Check existence
console.log(map.has('age')); // Output: true

// Iterate over entries
map.forEach((value, key) => {
    console.log(`${key}: ${value}`);
});

// Output size
console.log(map.size); // Output: 2

// Delete a key
map.delete('age');
console.log(map.has('age')); // Output: false

// Clear the map
map.clear();
console.log(map.size); // Output: 0


// _____________________
const set = new Set();

// Add values
set.add(1);
set.add(2);
set.add(3);
set.add(2); // Duplicate, will not be added

console.log(set); // Output: Set { 1, 2, 3 }

// Check existence
console.log(set.has(2)); // Output: true

// Iterate over values
set.forEach(value => {
    console.log(value);
});

// Output size
console.log(set.size); // Output: 3

// Delete a value
set.delete(2);
console.log(set.has(2)); // Output: false

// Clear the set
set.clear();
console.log(set.size); // Output: 0
