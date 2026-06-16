- typeof:
    let userName = 'Max';
    console.log(typeof userName); // string // this is JavaScript typeof

    type UserName = typeof userName;    // this is TypeScript typeof, it creates a new type based on the value of userName
    console.log(UserName); // 'Max'

- create type from object:
    const settings = {
        theme: 'dark',
        fontSize: 14,
        languages: ['en', 'fr', 'de'],
    };

    type Settings = typeof settings;

    // Settings is now a type that has the same structure as the settings object:
    Settings becomes {
        theme: string;
        fontSize: number;
        languages: string[];
    }

    function loadData(s: typeof settings) {}
    function loadData(s: Settings) {}

- Create typeof from function:
    function sum(a: number, b: number) {
        return a + b;
    }
    function subtract(a: number, b: number) {
        return a - b;
    }
    
    type SumFn = typeof sum;
    type SubtractFn = typeof subtract;
    
    function performMathAction(cb: SumFn | SubtractFn) {
        // some code...
    }

- Indexed Access Types:
    - get type of a property from an object type:
    type AppUser = {
        name: string;
        permissions: {
            id: string;
            title: string;
            desc: string;
        }
    }
    type Permissions = AppUser['permissions']; // returns array of object ofPermissions type // [{ id: string; title: string; desc: string; }]
    type Permission = Permissions[number]; // return only object of permissions type // { id: string; title: string; desc: string; }
    - 
    type Names = string[];
    type Name = Names[number]; // string
-------
KeyOf:
    type User = { name: string; age: number; };
    type UserKeys = keyof User; // 'name' | 'age'

    let validKey: UserKeys;
    validKey = 'name'; // valid
    validKey = 'dept'; // Error: Type '"dept"' is not assignable to type 'UserKeys'

    function getProp<T extends object, K extends keyof T>(obj: T, key: K): T[K] {
        return obj[key];
    }

    const val = getProp({ name: 'Alice', age: 30 }, 'name'); // Type: string

-----
- Mapped Types:
    - create new type by transforming properties of an existing type:

    type Operations = {
        add: (a: number, b: number) => number;
        subtract: (a: number, b: number) => number;
    }

    type Results<T> = {
        [Key in keyof Operations]: number; // Transform each property to number
    }

    <!-- Similar to:
    type Results = {
        add: number;
        subtract: number;
    } -->

    let mathOperations: Operations = {
        add: (a, b) => a + b,
        subtract: (a, b) => a - b,
    };

    let mathResults: Results<Operations> = {    // All the properties are required and of type number
        add: mathOperations.add(5, 3), // 8
        subtract: mathOperations.subtract(5, 3), // 2
    };

- Readonly:
    - example 1:
        type Operations = {
            readonly add: (a: number, b: number) => number;
            readonly subtract: (a: number, b: number) => number;
        }

        type Results<T> = {
            [Key in keyof Operations]: number; // by default, this properties are also readonly because they are derived from Operations type
        }

        let mathResults: Results<Operations> = {
            add: mathOperations.add(5, 3), // 8
            subtract: mathOperations.subtract(5, 3), // 2
        };

        mathResults.add = 10; // Error: Cannot assign to 'add' because it is a read-only property.
    
    - example 2:
        type Operations = {
            readonly add: (a: number, b: number) => number;
            readonly subtract: (a: number, b: number) => number;
        }

        type Results<T> = {
            - readonly [Key in keyof Operations]: number; // Remove readonly modifier from properties
        }

        mathResults.add = 10; // works
    
    - example 3:
        type Operations = {
            add: (a: number, b: number) => number;
            subtract: (a: number, b: number) => number;
        }

        type Results<T> = {
            readonly [Key in keyof Operations]: number; // Add readonly modifier to properties
        }

        mathResults.add = 10; // Error: Cannot assign to 'add' because it is a read-only property.

    - example 4:
        type Operations = {
            readonly add: (a: number, b: number) => number;
            readonly subtract: (a: number, b: number) => number;
        }

        type Results<T> = {
            readonly [Key in keyof Operations]: number; // Add readonly modifier to properties
        }

        mathResults.add = 10; // Error: Cannot assign to 'add' because it is a read-only property.

----
- Optional Mapping:
    - example 1:
        type Operations = {
            add: (a: number, b: number) => number;
            subtract: (a: number, b: number) => number;
        }

        type Results<T> = {
            [Key in keyof Operations]?: number; // ? makes all properties optional
        }
        let mathResults: Results<Operations> = {
            add: mathOperations.add(5, 3), // 8
            // subtract is optional
        };
    
    - example 2:
        type Operations = {
            add?: (a: number, b: number) => number;
            subtract?: (a: number, b: number) => number;
        }

        type Results<T> = {
            [Key in keyof Operations]: number; // All properties are required, but they can be optional in the original type
        }
        let mathResults: Results<Operations> = {
            add: mathOperations.add(5, 3), // 8
            // subtract is optional
        };
    
    - example 3:
        type Operations = {
            add: (a: number, b: number) => number;
            subtract: (a: number, b: number) => number;
        }

        type Results<T> = {
            [Key in keyof Operations]-?: number; // -? makes all properties required
        }
        let mathResults: Results<Operations> = {    
            add: mathOperations.add(5, 3), // 8     // required
            subtract: mathOperations.subtract(5, 3), // 2   // required
        };

----
Conditional Types:
    - Example 1:
        type StringArray = string[];
        
        - type ElementType1 = StringArray[number]; // string
        - type ElementType2<T extends any[]> = T[number]; // generic version   // support only array types
        - type ElementType3<T> = T extends any[] ? T[number] : T; // more flexible version, it checks if T is an array type before trying to access its elements, if T is not an array, it returns actual type.

        - Calling:
            let text = 1;
            type Result1 = ElementType1; // string
            
            type Result2 = ElementType2<StringArray>; // string
            type Result2 = ElementType2<typeof text>; // Error: Type 'number' does not satisfy the constraint 'any[]'.
            
            type Result3 = ElementType3<StringArray>; // string
            type Result4 = ElementType3<typeof text>; // number
    
    - Example 2:
        type FullNamePerson = { firstName: string; lastName: string; };
        type FullNameOrNothing<T> = T extends FullNamePerson ? string : never;
        
        // this function returns 'string | never'
        function getFullName<T extends Object>(person: T): FullNameOrNothing<T> {
            if('firstName' in person && 'lastName' in person && person.firstName && person.lastName) {
                return `${person.firstName} ${person.lastName}` as FullNameOrNothing<T>;        // type cast 'as'
            }

            throw new Error('firstName and lastName are required');
        }

        - calling:
        const name1 = getFullName({ firstName: 'John', lastName: 'Doe' }); // type: string
        const name2 = getFullName({ name: 'Alice' }); // Error: Type '{ name: string; }' does not satisfy the constraint 'Object'.

---
- infer keyword:
    - used in conditional types to infer a type variable from a type expression:

    function add(a: number, b: number) {    // this function returns number
        return a + b;
    }

    type AddFn = typeof add; // (a: number, b: number) => number

    - How to get only return type insted of whole function type? Ans: using infer keyword
    type ReturnValueType<T> = T extends (...args: any[]) => infer R ? R : never; // this conditional type checks if T is a function type, if it is, it infers the return type R and returns it, otherwise it returns never.

    type AddFnReturnValueType = ReturnValueType<AddFn>; // number

- Utility Types:
    1. ReturnType<T>: Extracts the return type of a function type T.
        - type AddFnReturnType = ReturnType<AddFn>; // number
        - Similar to above example, inbuild keyword to get function return type without creating custom conditional type:
    2. Parameters<T>: Extracts the parameter types of a function type T as a tuple.
        - type AddFnParameters = Parameters<AddFn>; // [number, number]
    3. Partial<T>: Makes all properties of type T optional.
        - type PartialUser = Partial<User>; // { name?: string; age?: number; }
    4. Required<T>: Makes all properties of type T required.
        - type RequiredUser = Required<PartialUser>; // { name: string; age: number; }