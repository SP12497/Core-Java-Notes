/*
Window:
Window:
    Document:
        HTML:
            HEAD:
                title:
                    - text node
                meta:
                    - attribute
        Body:
            div:
                - attribute
                h1:
                    - text node
                    - attribute
                p:
                    - text node
                    - attribute

*/

// open on page on browser: run below commands:
// https://en.wikipedia.org/wiki/Sundar_Pichai
console.log(window);
console.log(window.document);
console.log(document);  // same as window.document
console.log(document.baseURI);

console.log(document.links);    // return HTMLCollection .. same as array
console.log(document.links[2]);

console.log(document.getElementById('firstHeading'));   // id=firstHeading
document.getElementById('firstHeading').innerHTML = "<h1> SP world! </h1>";
