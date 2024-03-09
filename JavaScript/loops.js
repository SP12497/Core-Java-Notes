console.log("_________________For in : loop object_________________");
// for in loop with object:
const user2 = {
    name: "Sagar",
    age: 24,
    isTotallyAwesome: true
}
// used to iterate the object
for (key in user2) {
    console.log(key, ":", user2[key]);
}