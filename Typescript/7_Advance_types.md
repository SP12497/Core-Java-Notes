- Intersection Types
- Type Guards
- Discriminated Unions
- Function Overloads

## Intersection Types (&):
    - Intersection types allow you to combine multiple types into one. This is useful when you want to create a new type that has all the properties of the combined types.
    - By Using type:
        type FileData = {
            type: "file";       // Discriminant property
            path: string;
            content: string;
        }
        type DatabaseData = {
            type: "database";
            connectionString: string;
            query: string;
        }

        type AccessedDbData = FileData & DatabaseData;

        - As Object:
            const data: AccessedDbData = {
                type: "file",
                path: "/path/to/file",
                content: "File content",
                connectionString: "Database connection string",
                query: "SELECT * FROM table"
            }
        - As function:
            - by using in operator:
                function processData(data: AccessedDbData) {
                    if('path' in data) {    // FileData
                        console.log("Processing file data:", data.path);
                        return;
                    }
                    // DatabaseData
                    console.log("Processing database data:", data.connectionString);
                }
            - by using discriminated union:
                function processData(data: AccessedDbData) {
                    if(data.type === "file") {   FileData
                        console.log("Processing file data:", data.path);
                        return;
                    }
                }
    - By Using interface:
        interface FileData1 { ... }
        interface DatabaseData1 { ... }
        interface AccessedDbData1 extends FileData1, DatabaseData1 {}
        const data: AccessedDbData1 = { ... }

- Type Guards:
    - Type guards are a way to narrow down the type of a variable within a conditional block. This allows you to safely access properties or methods that are specific to that type.

    class User {
        constructor(public name: string) {}
        userInfo() { return `User: ${this.name}`; }
    }
    
    class Admin {
        constructor(public name: string) {}
        adminInfo() { return `Admin: ${this.name}`; }
    }

    - Common type guards include:
        - typeof: Used for primitive types (string, number, boolean).
        - instanceof: Used for class instances.
            function classIdentifier(obj: User | Admin) {
                if(obj instanceof User) {
                    console.log(obj.userInfo());
                    return;
                }
                obj.adminInfo();
            }
            
        - in: Used to check if a property exists in an object.
            function processData(data: AccessedDbData) {
                if('path' in data) {    // FileData
                    console.log("Processing file data:", data.path);
                    return;
                }
                // DatabaseData
                console.log("Processing database data:", data.connectionString);
            }

- "Outsourcing" Type Guards & Using Type Predicates:
    - You can create custom type guard functions that return a boolean value and use the "is" keyword to indicate the type they are checking for.

    function isUser(obj: any): obj is User {
        return obj instanceof User;
    }

    function classIdentifier(obj: User | Admin) {
        if(isUser(obj)) {
            console.log(obj.userInfo());
            return;
        }
        obj.adminInfo();
    }

- Function Overloads:
    - Function overloads allow you to define multiple function signatures for a single function implementation. This is useful when you want to provide different ways to call a function based on the types of the arguments.

    function add(a: number, b: number): number; // Overload signatures
    function add(a: string, b: string): string; // Overload signatures
    function add(a: any, b: any): any { // Implementation signature
        return a + b;
    }

    const sum = add(5, 10);          // sum is of type number
    const concatenated = add("Hello, ", "world!"); // 'Hello, world!'
- Index Type:
    - Index types allow you to create types that represent the keys of an object. This is useful for creating types that are based on the properties of an object.
    type DataStore = {
        [key: string]: string | number; // Index signature
    }
    const store: DataStore = {}
    store.id = 123; // Valid
    store.name = "Data Store"; // Valid
    // store.invalid = true; // Error: Type 'boolean' is not assignable to

- "Record" Type : similar to Index type.
    type DataStore = Record<string, string | number>;
    const store: DataStore = {}
    store.id = 123; // Valid
    

- Constant Types with "as const":
    - The "as const" assertion allows you to create a type that is a literal type, which means it can only have a specific value. This is useful for creating types that represent specific values or constants.
    
    let roles = ["admin", "user", "guest"] as const; // roles is of type readonly ["admin", "user", "guest"]
    roles.push("superadmin"); // Error: Property 'push' does not exist on type 'readonly values.
    const firstRole = roles[0]; // valid.

- 'satisfies' Operator:
   - const dataEntriesWithoutSatisfies: Record<string, number> = {    // Any string key is allowed, and its value must be a number.
        entry1: 10,
        entry2: 20,
    }

    console.log(dataEntriesWithoutSatisfies.entry3);    // 'undefined', no error at compile time.
    
    - const dataEntries = {
        entry1: 10,
        entry2: 20,
    } satisfies Record<string, number>;

    console.log(dataEntries.entry3);    // Error: Property 'entry3' does not exist on type 'Record<string, number>'.