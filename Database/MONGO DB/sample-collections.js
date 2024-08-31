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
        { title: "MEAN", price: 400, isFramework: true },
    ]
);

db.students.insertMany(
    [
        {
            name: "Bharati",
            age: 30,
            grade: "A+",
            Gender: "Female",
            bio: "I am a student of Computer Science",
            subjects: ["Node", "Angular", "MongoDB"]
        },
        {
            name: "Sagar",
            age: 20,
            grade: "A",
            Gender: "Male",
            bio: "I am a student of Computer Engineering",
            subjects: ["MEAN", "Django", "Node"]
        },
        {
            name: "Mayur",
            age: 22,
            grade: "B",
            Gender: "Male",
            bio: "I am a student of Mechanical Engineering",
            subjects: ["Java"]
        },
        {
            name: "Rohan",
            age: 21,
            grade: "B+",
            Gender: "Male",
            bio: "I am a student of Civil Engineering",
            subjects: ["MEAN", "JavaScript", "Django"]
        },
        {
            name: "Diya",
            age: 24,
            grade: "C",
            Gender: "Female",
            bio: "I am a student of Electrical Engineering",
            subjects: ["MEAN", "Java"]
        },
        {
            name: "Nilesh",
            age: 25,
            Gender: "Male",
            grade: "C+",
            bio: "I am a student of Electronics Engineering",
            subjects: ["Angular", "", "Mongoose"]
        },
        {
            name: "Kamlesh",
            age: 22,
            grade: "D",
            Gender: "Male",
            bio: "I am a student of Chemical Engineering",
            subjects: ["Spring", "JavaScript", "Django"]
        },
        {
            name: "Yogita",
            age: 48,
            grade: "D+",
            Gender: "Female",
            bio: "I am a student of Civil Engineering",
            subjects: ["MERN", "Express"]
        },
        {
            name: "Sagar",
            age: 68,
            grade: "E",
            Gender: "Male",
            bio: "I am a student of Software Engineering",
            subjects: ["MEAN", "Spring", "Express"]
        },
        {
            name: "Gopal",
            age: 35,
            grade: "E+",
            Gender: "Male",
            bio: "I am a student of Mechatronics Engineering",
            subjects: ["Mongoose", "Express", "MERN"]
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

db.employees.insertMany( 
    [
        {
            name: "Sagar",
            Hobbies: ["Cricket", "Football", "Chess"],
            Address: { city: "Pune", state: "MH" },
            Experience: [
                { company: "Google", duration: 1 },
                { company: "Microsoft", duration: 3.6 },
                { company: "Amazon", duration: 1.5 }
            ]
        },
        {
            name: "Suraj",
            Hobbies: ["Handball", "Football", "Badminton"],
            Address: { city: "Mumbai", state: "MH" },
            Experience: [
                { company: "Tavant", duration: 1.5 },
                { company: "Infosys", duration: 2.6 }
            ]
        },
        {
            name: "Nilesh",
            Hobbies: ["Cricket", "Football", "Chess"],
            Address: { city: "Nandurbar", state: "MH" },
            Experience: [
                { company: "Tata", duration: 1.5 },
                { company: "Wipro", duration: 2.6 },
                { company: "HCL", duration: 1.9 }
            ]
        },
        {
            name: "Ravi",
            Hobbies: ["Kabaddi", "Table Tennis", "Swimming"],
            Address: { city: "Jalgaon", state: "MH" },
            Experience: [
                { company: "TCS", duration: 2.5 },
                { company: "Accenture", duration: 3.6 }
            ]
        }
    ]
);
