// use mydb;

db.books.insertMany(
    [
        { title: "Node", price: 200, isFramework: true },
        { title: "Java", price: 400 },
        { title: "Spring", price: 500, isFramework: true },
        { title: "React", price: 300, isFramework: true },
        { title: "Angular", price: 375, isFramework: true },
        { title: "JavaScript", price: 100 },
        { title: "Python", price: 50 },
        { title: "Django", price: 125, isFramework: true },
        { title: "MongoDB", price: 200 },
        { title: "Express", price: 525, isFramework: true },
        { title: "Mongoose", price: 350, isFramework: true },
        { title: "MERN", price: 1000, isFramework: true },
        { title: "MEAN", price: 700, isFramework: true },
    ]
);

db.students.insertMany(
    [
        {
            name: "Sagar",
            age: 20,
            grade: "A+",
            subjects: ["Math", "Physics", "Computer Science"]
        },
        {
            name: "Suraj",
            age: 21,
            grade: "A",
            subjects: ["English", "Physics", "Computer Science"]
        },
        {
            name: "Rohit",
            age: 22,
            grade: "B",
            subjects: ["Data Structure"]
        },
        {
            name: "Rohan",
            age: 23,
            grade: "B+",
            subjects: ["Account", "Geography", "Hindi"]
        },
        {
            name: "Raj",
            age: 24,
            grade: "C",
            subjects: ["Physics", "Computer Science"]
        },
        {
            name: "Nilesh",
            age: 25,
            grade: "C+",
            subjects: ["Hindi", "", "Geography"]
        },
        {
            name: "Rajesh",
            age: 26,
            grade: "D",
            subjects: ["Geometry", "Physics", "Account"]
        },
        {
            name: "Yogesh",
            age: 27,
            grade: "D+",
            subjects: ["Math", "Hindi"]
        },
        {
            name: "Rahul",
            age: 28,
            grade: "E",
            subjects: ["Account", "Physics", "Computer Science"]
        },
        {
            name: "Rahul",
            age: 29,
            grade: "E+",
            subjects: ["Geography", "Geometry", "Account"]
        }
    ]
);

db.products.insertMany(
    [
        {
            showroom: "Chroma",
            products: [
                { name: "Laptop", quantity: 10 },
                { name: "Mobile", quantity: 20 },
                { name: "Tablet", quantity: 30 }
            ]                
        },
        {
            showroom: "Vijay Sales",
            products: [
                { name: "Laptop", quantity: 15 },
                { name: "Tablet", quantity: 35 }
            ]                
        },
        {
            showroom: "Reliance Digital",
            products: [
                { name: "Laptop", quantity: 20 },
                { name: "Mobile", quantity: 30 },
            ]                
        },
        {
            showroom: "D-Mart",
            products: [
                { name: "Laptop", quantity: 25 },
                { name: "Mobile", quantity: 35 },
                { name: "Tablet", quantity: 45 }
            ]
        }
    ]
);
