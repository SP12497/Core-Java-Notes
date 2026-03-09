- Generic Types:
    - let names: Array<string> = ["Alice", "Bob", "Charlie"]; // Generic type with Array<T>
    - function identity<T>(arg: T): T { return arg; } // Generic function

    - type DataStore = {
        [key: string]: string | number; // Index signature
    }
    datastore.id = 123; // Valid
    datastore.isPresent = true; // Error: Type 'boolean' is not assignable to type 'string | number'.

    - "T" is just a placeholder for the generic type. You can use any letter or name, but "T" is commonly used to represent a generic type.
        type DataStore<T> = {
            [key: string]: T; // Index signature with generic type
        }
        const stringStore: DataStore<string> = {};
        const stringOrNumberStore: DataStore<string | number> = {};

- With Functions:
    - function merge(a: any, b: any) {
        return [a, b];
    }
    const ids = merge(1,3);
    here, the issue is, ids is of type any[]. ids not aways the actual type of the merged values. 

    - solution is to use generics:
    function merge<T>(a: T, b: T): [T, T] {
        return [a, b];
    }
    const ids1 = merge<number>(1, 3);
    // Type: [number, number]

    const ids2 = merge(1, 3);
    // Type inferred: [number, number]
    
    const mixed = merge<string | number>("Hello", 42);
    // Type inferred: [string | number, string | number]

    - function merge<T, U>(a: T, b: U): [T, U] {
        return [a, b];
    }
    const mixed = merge("Hello", 42);
    // Type: [string, number]

    const mixed2 = merge("Hello", 42);

    merge<string, number>("Hello", 42);
    // Also valid

- Generics & Constraints:
    - extends object: This constraint ensures that the generic type T must be an object. This is useful when you want to create a function that works with objects and you want to ensure that the input is of the correct type.
    - function mergeObj<T extends object>(a: T, b: T) {
        return { ...a, ...b };
    }
    const merged = mergeObj({ name: "Sagar" }, { age: 30 });
    // { name: string; age: number; }

- Generic Classes:
    class User<T> {
        constructor(public id: T) {}
    }
    const user = new User('aa');

- Generic Interfaces:
    interface Repository<T> {
        getById(id: T): T;
        save(item: T): void;
    }
    class UserRepository implements Repository<number> {
        getById(id: number): number {
            // Implementation
            return id;
        }
        save(item: number): void {
            // Implementation
        }
    }