/*
"lecture notes" + "mongodb" + site:.edu + filetype:pdf
-- https://youtube.com/playlist?list=PLA3GkZPtsafZydhN4nP0h7hw7PQuLsBv1&si=qL6l_KkT1edrthvI

SQL (MySQL):
    - RDBMS is a relational database management system and works on relational database.
    - It stores data in form of entity as table.
    - It uses SQL to query database.

NoSQL (MongoDB):
    - Its a non-relational, document-oriented database management system and works 
      database management system and works on document-bases database.
    - MongoDB stores data in form of documents.
    - MongoDB uses BSON to query database.

WHAT IS BSON:
    - https://www.educba.com/json-vs-bson/
    - Binary JavaScript Object Notation. (Binary JSON)
    - Mongo DB store data in BSON format.

Terminologies:
    - MySQL Terminologies : Mongo Terminologies
    - ACID Transactions: ACID Transactions
    - TABLE : Collection (Array of objects.)
    - ROW   : Document (object)
    - COLUMN: Field (fields of object)
    - Secondary Index: Secondary Index
    - JOINs: Embedded documents, $lookup & $graphLookUp
    - Group By: Aggregation Pipeline
    - Triggers: No triggers
    - Foreign Key: No Foreign Key
    - Concurrency: Optimistic Locking

CAP Theorem:
    1. Consistency: 
        - All nodes have same data at same time.
        - All users must see the same set of data at any given time.
        - In NoSQL, very important since most of the time data is distributed across systems.
    2. Availability: 
        - System should be always ON (service guaranteed).
        - System remains available for read/write requests for all clients.
        - Every request gets a response on success/failure.
    3. Partition Tolerance: 
        - System continues to work despite network partition.
        - System continues to even when different distributed hardware stops functioning.

ACID Properties:
    - Atomicity:
        - All or nothing.
        - If one part of transaction fails, whole transaction fails.
        - Achieve at transaction level or document/row level.
            - transaction level Example: Transfer money from one account to another account.
            - documents level Example: 
                1. Suppose, document/row have 50+ fields, and 25 fields are updated/inserted/deleted, and 26th field update fails, then all 25 fields should be rollbacked.
                2. If we are inserting 10 documents, and 5th document insertion fails, 4 documents should be remain inserted and 5th and later (5-10) documents will be failed to insert.
                   Hence, it provides atomicity at a document level (fields level) not at a collection level.
    - Consistency:
        - Data should be in consistent state before and after transaction.
        - Data should be in valid state (state means data should be in valid format) after transaction.
    - Isolation:
        - Transaction should be isolated from other transactions.
        - Transaction should not affect other transactions. 
        - e.g. If One account is sending money to two different accounts, then both transactions should be isolated from each other.
    - Durability:
        - Once transaction is committed, it should be permanent.
        - Data should be saved even if system crashes or restarts.

MongoDB limitations:
    - Collection (Table) Size Limit: NA
    - Document (row) Size Limit: 16MB
    - Nested Document Level: 100 levels (nested documents)
    - Index Limit: 64 indexes per collection
    - Field, Database, Collection Name Limit: 64 characters

Install and Run Mongo DB:
    - Install MongoDB Community Edition. Update Environment Variables.
    - Install MongoDB Shell.    Update Environment Variables.
    - Start MongoDB Server on Command Prompt: mongod
    - Start MongoDB Shell on Command Prompt: mongosh / mongo
*/

-- Databases commands
    -- 1. Show Databases: Note: it will shows only those databases which have collections.
        show dbs;
        show databases
    -- 2. Create Database:
        use mydb;   -- Create if absent and use database.
        db.createCollection("students");    -- Create collection in current database.
    -- 3. Drop Database:
        db.dropDatabase();          -- Drop current database.
        db.dropDatabase("mydb");    -- Drop 'mydb' database.
        db.dropDatabase('db');      -- Drop current database.
    -- 4. Switch Database:
        use mydb;   -- Switch to 'mydb' database.
    -- 5. Current Database:
        db;         -- Show current database.
    -- 6. Rename Database:
        -- Not possible.
        -- Create new database and copy data from old database to new database.
            -- Open Command Prompt:
            mongodump --db oldDbName --out /path/to/backup          -- # Step 1: Dump the old database
            mongorestore --db newDbName /path/to/backup/oldDbName   -- # Step 2: Restore the old database to a new database
            db.dropDatabase('oldDbName');                           -- # Step 3: Drop the old database

-- Collections commands
    -- 1. Show Collections:
        show collections;
        show tables;
    -- 2. Drop Collection:
        db.students.drop();         -- Drop 'students'
    -- 3. Create Collection:
        db.stud.insertOne({"name":"sa"});     -- Implicit Creation
        db.createCollection("students");      -- Explicit Creation
        db.createCollection("cappedCollection", { capped: true, size: 5242880, max: 5 }); -- Create capped collection.
            -- if 6th document is inserted, then 1st document will be removed.
            -- Capped collections are fixed-size collections that automatically overwrite the oldest documents when they reach their size limit.
            -- In this example, 'cappedCollection' is created with a maximum size of 5 MB (5242880 bytes) and a maximum of 5 documents.
        -- Creating a Collection with Document Validation:
        db.createCollection("validatedCollection", {
            validator: {
                $jsonSchema: {
                    bsonType: "object",
                    required: ["name", "age"],
                    properties: {
                        name: {
                            bsonType: "string",
                            length: { minimum: 1 },
                            description: "must be a string and is required"
                        },
                        age: {
                            bsonType: "int",
                            minimum: 0,
                            description: "must be an integer greater than or equal to 0 and is required"
                        }
                    }
                }
            }
        })
    -- 4. Rename Collection:
        db.students.renameCollection("stud");  -- Rename 'students' to 'stud'
        
    /* Node.js connection:
        const MongoClient = require('mongodb').MongoClient;
        const url = "mongodb://localhost:27017/";

        MongoClient.connect(url, function(err, db) {
            if (err) throw err;
            const dbo = db.db("myDatabase");
            dbo.createCollection("myNodeCollection", function(err, res) {
                if (err) throw err;
                console.log("Collection created!");
                db.close();
            });
        });
    */


-- CRUD:
        -- Create: insertOne(data, options), insertMany(data, options)
            db.students.insertOne({"name":"sa", "age": 23});
            db.students.insertMany([{"name":"sa", "age": 23}, {"name":"sa", "age": 23}]);
        -- Read: find(query, projection/options), findOne(query, projection)
            db.students.find();
            db.students.find({"name":"sa"});
            db.students.find({"name":"sa"}, {"name":1, "age":1});
            db.students.findOne({"name":"sa"});
        -- Update: updateOne(filter, update, options), updateMany(filter, update, options), replaceOne(filter, data, options)
            db.students.updateOne({"name":"sa"}, {$set:{"age": 24}});               -- Update only first matching document.
            db.students.updateMany({"name":"sa"}, {$set:{"age": 24}});              -- Update all matching documents.
            db.students.replaceOne({"name":"sa"}, {"name":"sa", "age": 24});
        -- Delete: deleteOne(filter, options), deleteMany(filter, options)
            db.students.deleteOne({"name":"sa"});
            db.students.deleteMany({"name":"sa"});


    -- 1. Inserting Data:
        -- insert() is deprecated.
        -- insertOne(data, options)
            db.students.insertOne({
                name: "John Doe",
                age: 20,
                grade: "Sophomore",
                subjects: ["Math", "Physics", "Computer Science"]
            })
        -- insertMany(data, options)
            -- 1. Inserting Multiple Documents:
            db.students.insertMany([
                {
                    name: "Jane Doe",
                    age: 21,
                    grade: "Junior",
                    subjects: ["Math", "Physics", "Computer Science"]
                },
                {
                    name: "Alice",
                    age: 22,
                    grade: "Senior",
                    subjects: ["Math", "Physics", "Computer Science"]
                }
            ]);

            -- 2. Ordered Insertion: 
                -- If ordered is set to 'true', the operation will stop after the first error.
                db.students.insertMany(
                    [
                        { _id: 1, name: "Sager", age: 22, grade: "Senior", subjects: ["Physics", "Philosophy"] },   -- inserted
                        { _id: 1, name: "Suraj", age: 18, grade: "Freshman", subjects: ["Math", "English"] },       -- failed
                        { _id: 2, name: "Yogesh", age: 18, grade: "Freshman", subjects: ["Math", "English"] },      -- Not executed So, not inserted
                    ],
                    { ordered: true }       -- Default is true.
                )
                -- Here, the operation will stop after the first error because the _id field is duplicated in the second document.
            -- 3, UnOrdered Insertion:
                -- If ordered is set to 'false', the operation will attempt to insert all documents, even if some fail.
                db.students.insertMany(
                    [
                        { _id: 1, name: "Sager", age: 22, grade: "Senior", subjects: ["Physics", "Philosophy"] },   -- inserted
                        { _id: 1, name: "Suraj", age: 18, grade: "Freshman", subjects: ["Math", "English"] },       -- failed
                        { _id: 2, name: "Yogesh", age: 18, grade: "Freshman", subjects: ["Math", "English"] },      -- inserted
                    ],
                    { ordered: false }       -- Default is true.
                )
        
    -- Updating Data:
        -- 1. updateOne(filter, update, options)
            -- Update only the first document that matches the filter.
            -- Update Operators (2nd argument):
                -- $set: Sets the value of a field in a document.
                -- $unset: Removes the specified field from a document.
                -- $inc: Increments the value of the field by the specified amount.
                -- $mul: Multiplies the value of the field by the specified amount.
                -- $rename: Renames a field.
                -- $min: Only updates the field if the specified value is less than the existing value.
                -- $max: Only updates the field if the specified value is greater than the existing value.
                -- $push: Adds an element to an array.
                -- $pull: Removes an element from an array.
                -- $pop: Removes the first or last element of an array.
            -- options (3rd argument):
                -- upsert: (default false / true) If no document matches the filter, a new document will be inserted. Default is false.
                -- bypassDocumentValidation: If true, allows the write to opt-out of document level validation.
                -- returnDocument:  (before/after) Determines whether the original or updated document is returned. Default is 'after'.
                -- collation: Specifies the collation to use for the operation.
                -- arrayFilters: Filters the array elements to update.
            -- Simple Update:
                db.students.updateOne(
                    { name: "John Doe" },           -- Filter
                    { $set: { grade: "Junior" } }   -- Update
                )           
                -- {name: "John Doe", age: 20, grade: "Junior", subjects: ["Math", "Physics", "Computer Science"]}
            -- Upsert: 
                -- If no document matches the filter, a new document will be inserted.
                db.students.updateOne(
                    { name: "Michael Smith" },
                    { $set: { age: 19, grade: "Freshman", subjects: ["Biology"] } },
                    { upsert: true }
                )
                -- default upsert is false
        -- updateMany(filter, update, options)
            -- Update all documents that match the filter.
                db.students.updateMany(
                    { grade: "Senior" },
                    { $set: { grade: "Graduated" } }
                )
            -- Upsert:
                -- If documents match the filter, they will be updated.
                -- If no documents match the filter, a new document will be inserted.
                db.students.updateMany(
                    { grade: "Senior" },
                    { $set: { grade: "Graduated" } },
                    { upsert: true }
                )
        -- replaceOne(filter, data, options)
            --  replaces the existing document for "Alice" with a new one.
                db.students.replaceOne(
                    { name: "Alice" },
                    { name: "Alice", age: 23, grade: "Graduated", subjects: ["Math", "Physics", "Computer Science"] }
                    -- , { upsert: true }  -- If no document matches the filter, a new document will be inserted.
                )
            
        -- findOneAndUpdate(filter, update, options)
            -- Find a document and update it in one atomic operation.
                db.students.findOneAndUpdate(
                    { name: "Alex Johnson" },
                    { $set: { age: 22 } },          -- Suppose, Before updating, Alex Johnson's age is 21.
                    { returnDocument: "before" }    -- Return the original document. age = 21
                    -- { returnDocument: "after" }  -- Return the updated document. age = 22
                )
        -- findOneAndReplace(filter, data, options)
            -- Find a document and replace it in one atomic operation.
                db.students.findOneAndReplace(
                    { name: "Alex Johnson" },
                    { name: "Alex Johnson", age: 23, grade: "Senior", subjects: ["Math", "Physics", "Computer Science"] },
                    { returnDocument: "after" }     -- Return the updated document.
                )

        -- Update with Array Operators:
            -- $push: Adds an element to an array. duplicates are allowed.
                db.students.updateOne(
                    { name: "Sagar" },
                    { $push: { subjects: "Statistics" } }   -- subjects: ["Math", "Physics", "Computer Science", "Statistics"]
                )
            -- $pull: Removes an element from an array. Removes all matching elements.
                db.students.updateOne(
                    { name: "Sagar" },
                    { $pull: { subjects: "Math" } }   -- subjects: ["Physics", "Computer Science", "Statistics"]
                )
            -- $addToSet: Adds an element to an array if it does not already exist. No duplicates.
                db.students.updateOne(
                    { name: "Sagar" },
                    { $addToSet: { subjects: "Statistics" } }   -- subjects: ["Computer Science", "Statistics", "Math"]   -- 'Statistics' is already present. So, no change. if missing, then added.
                )
            -- $pop: Removes the first or last element of an array.
                -- $pop: '-1' removes first element, '1' removes last element
                db.students.updateOne(
                    { name: "Sagar" },
                    { $pop: { subjects: 1 } }   -- subjects: ["Computer Science", "Statistics"] => ["Computer Science"]
                )
        -- Other Operators:
            -- $min: Only updates the field if the specified value is less than the existing value.
                db.students.updateOne(
                    { name: "Sagar" },
                    { $min: { age: 21 } }   -- age: if existing age is 20, then 20 => 20, if 22, then 22 => 21
                )
                -- sql: update students set age = 21 where name = "Sagar" and age < 21;
            -- $max: Only updates the field if the specified value is greater than the existing value.
                db.students.updateOne(
                    { name: "Sagar" },
                    { $max: { age: 21 } }   -- age: if existing age is 20, then 20 => 20 (no change) | if 22, then 22 => 21
                )
                -- sql: update students set age = 21 where name = "Sagar" and age > 21;
            -- $mul: Multiplies the value of the field by the specified amount.
                db.students.updateOne(
                    { name: "Sagar" },
                    { $mul: { age: 2 } }   -- age: 21 => 42     -- Multiply by 2  / Double the age
                )
                -- sql: update students set age = age * 2 where name = "Sagar";
            -- $unset: Removes the specified field from a document. (remove column)
                db.students.updateOne(
                    { name: "Sagar" },
                    { $unset: { grade: "" } }
                )
                -- sql: ALTER TABLE students DROP COLUMN grade;
            -- $currentDate: Sets the value of a field to the current date.
                db.students.updateOne(
                    { name: "Sagar" },
                    { $currentDate: { lastModified: true } }
                )
                -- sql: update students set lastModified = current_date where name = "Sagar";
            -- $rename: Renames a field.
                db.students.updateOne(
                    { name: "Sagar" },
                    { $rename: { grade: "percentage" } }
                )
                -- sql: ALTER TABLE students RENAME COLUMN grade TO percentage;
            -- $unset: Removes the specified field from a document.
                db.students.updateOne(
                    { name: "Sagar" },
                    { $unset: { grade: "" } }
                )
                -- sql: ALTER TABLE students DROP COLUMN grade;
            -- $inc: Increments the value of the field by the specified amount.
                db.students.updateOne( { name: "Sagar" }, { $inc: { age: 2 } }   -- age: 21 => 23 -- Increment by 2
                db.students.updateOne( {}, { $inc: { age: -2 } }   -- age: 21 => 19 -- Descrement by 2
                -- sql: update students set age = age + 2 where name = "Sagar";


    -- Deleting Data:
        -- deleteOne(filter, options)
            -- Delete the first document in the collection.
                db.students.deleteOne({})
            -- Delete the first document that matches the filter.
                db.students.deleteOne({ name: "Alice" })
                db.students.deleteOne({ name: "Alice" }, { writeConcern: { w: "majority" } })
        -- deleteMany(filter, options)
            -- Delete all documents:
                db.students.deleteMany({})
            -- Delete all documents that match the filter.
                db.students.deleteMany({ grade: "Graduated" })
        -- findOneAndDelete(filter, options)
            -- Find a document and delete it in one atomic operation.
                db.students.findOneAndDelete({ name: "Alice" })

    -- bulkWrite: 
        -- Perform a mixture of insert, update, and delete operations on the students collection.
        db.students.bulkWrite([
            {
                insertOne: {
                    document: {
                        name: "Emily Davis",
                        age: 20,
                        grade: "Sophomore",
                        subjects: ["Economics", "Sociology", "Statistics"]
                    }
                }
            },
            {
                updateOne: {
                    filter: { name: "John Doe" },
                    update: { $set: { grade: "Junior" } }
                }
            },
            {
                deleteOne: {
                    filter: { name: "Alex Johnson" }
                }
            }
        ])

    -- Read Operations:
        -- find(query, projection/options)
            -- Returns array of documents that match the query. If no documents match, an empty array is returned. (find() internally returns cursor/pointer)
            -- by default returns documents 20 documents at a time. Use cursor to iterate over all documents.

            -- Find all documents in the students collection.
                db.students.find()  -- [ {name: "John Doe", age: 20, grade: "Sophomore", subjects: ["Math", "Physics", "Computer Science"]}, ...+ 19 documents ]
            -- Find all documents where the grade is "Senior".
                db.students.find({ grade: "Senior" })
            -- Find all documents, where the age is greater than or equal to 20.
                db.students.find({ age: { $gte: 20 } })
                db.students.find({ age: { $gte: 20, $lt: 30 } })
            -- Find all documents, where the grade is "Senior" and the age is greater than or equal to 20.
                db.students.find({ grade: "Senior", age: { $gte: 20 } })
            -- Find all documents, where the grade is "Senior" or the age is greater than or equal to 20.
                db.students.find({ $or: [{ grade: "Senior" }, { age: { $gte: 20 } }] })
            -- Find all documents, where the grade is not equal to "Senior".
                db.students.find({ grade: { $ne: "Senior" } })
            -- Find all documents, where the grade is "Senior" and the age is greater than or equal to 20, but only return the name and age fields.
                -- 1 means include, 0 means exclude.
                db.students.find(
                    { grade: "Senior", age: { $gte: 20 } },
                    { name: 1, age: 1 }         -- Projection => { _id: "", name: "", age: "" }
                )
            -- Find all documents, where the grade is "Senior" and the age is greater than or equal to 20, but exclude the _id field.
                db.students.find(
                    { grade: "Senior", age: { $gte: 20 } },
                    { _id: 0 }        -- Projection => { name: "", age: "", grade: "", subjects: "" }
                )
            -- Find all documents, where the grade is "Senior" and the age is greater than or equal to 20, but only return the first 2 documents.
                db.students.find(
                    { grade: "Senior", age: { $gte: 20 } }
                ).limit(2)
            -- Find all documents, where the grade is "Senior" and the age is greater than or equal to 20, but skip the first 2 documents.
                db.students.find(
                    { grade: "Senior", age: { $gte: 20 } }
                ).skip(2)
            -- Find all documents, where the grade is "Senior" and the age is greater than or equal to 20, but only return the first 2 documents and exclude the _id field.
                db.students.find(
                    { grade: "Senior", age: { $gte: 20 } },
                    { _id: 0 }
                ).limit(2)
            -- Second Highest Age:
                -- sort: 1 for ascending, -1 for descending.
                db.students.find().sort({ age: -1 }).limit(1).skip(1)
            -- forEach: Iterate over all documents.
                db.students.find().forEach(function(doc) { print("Name: " + doc.name + ", Age: " + doc.age) })  -- JavaScript function
                db.students.find().forEach(doc => { if (doc.name) print(doc.name) });
                db.students.find().forEach(doc => { if (doc.name) printjson(doc) });
                let count = 1; db.students.find().forEach(doc => { if (doc.name) {print(count + ' ' + doc);} count++})  -- 1 [object Object], 2 [object Object], ...
                let count = 1;  db.students.find().forEach(doc => { if (doc.name) {printjson('' + count + ' ' + JSON.stringify(doc));} count++})  -- 1 {"_id": ObjectId("5f3d7b1b7b3b3b3b3b3b3b3b"), "name": "John Doe", "age": 20, "grade": "Sophomore", "subjects": ["Math", "Physics", "Computer Science"]}, ...
            -- get all documents, by default find() returns 20 documents.
                db.students.find().toArray()    -- returns all documents. ie more than 20 documents.
            -- Comparison Operators:
                -- $eq: Equal to
                    db.students.find({ age: { $eq: 20 } })
                    db.students.find({ age: 20 })
                    db.students.find({ subjects: 'Math' })  -- subjects is an array.
                    db.students.find({ subjects: { $eq: ['MEAN', 'Java'] } })  -- subjects contains only "Math" and "Physics" subjects. --- { subjects: [ 'MEAN', 'Java' ], ...}
                    db.students.find({ 'address.zip': 10001 })  -- address is an object.
                -- $ne: Not equal to
                    db.students.find({ age: { $ne: 20 } })
                -- $gt: Greater than | $gte: Greater than or equal to
                    db.students.find({ age: { $gt: 20 } })
                    db.students.find({ age: { $gte: 20 } })
                -- $lt: Less than | $lte: Less than or equal to
                -- $in: In    | $nin: Not in
                    db.students.find({ age: { $in: [20, 21] } })
                     -- Que: Find all students any subjects in "Math" and "Physics".
                    db.students.find({ subjects: { $in: ["MEAN", "JavaScript"] } })     -- matches any one subject.
                -- $all: All
                    -- Que: Find all students who have both "Math" and "Physics" subjects.
                    db.students.find({ subjects: { $all: ["JavaScript", "MEAN"] } })    -- Both subjects should be present. Order doesn't matter.
                -- Que: find the students who only have specified subjects and in same order.
                    db.students.find({ subjects: ["Node", "Angular", "MongoDB"] })      -- All subjects should be present in same order.

            -- Logical Operators:
                -- $and: Logical AND
                    db.students.find({ $and: [{ grade: "Senior" }, { age: { $gte: 20 } }] })
                    db.students.find({ grade: "Senior", age: { $gte: 20 } })    -- Works same as above.
                    -- Why $and is not required?
                        -- if we are adding multiple conditions on the same field, then $and is required.
                        db.students.find({ age: { $gt: 20}, age: { $lt: 30 } })  -- Error: checks for last condition only. So, $and is required.
                        db.students.find({ $and: [{ age: { $gt: 20}, age: { $lt: 30 } }]})  -- Works fine. Used in complex and or conditions.
                        db.students.find({ age: { $gt: 20, $lt: 30 } })  -- Works fine.
                -- $or: Logical OR
                    db.students.find({ $or: [{ grade: "Senior" }, { age: { $gte: 20 } }] })
                -- $not: Logical NOT
                    db.students.find({ grade: { $not: { $eq: "Senior" } } })
                -- $nor: Logical NOR
                    db.students.find({ $nor: [{ grade: "Senior" }, { age: { $gte: 20 } }] })
        -- findOne(query, projection)
            -- Returns the first document/object that matches the query.
            -- If no documents match, null is returned. (findOne() internally returns document)
            -- Difference between find() and findOne():
                -- find() returns cursor/pointer of array or empty, findOne() returns document or null.
            db.students.findOne({ name: "Sagar" })      -- {_id: 123, name: "Sagar", age: 26, grade: "Senior", subjects: ["Physics", "Philosophy"]}
            db.students.findOne({ name: "Sagar" }, { name: 1, age: 1 })     -- {name: "Sagar", age: 26}
        -- count(query, options)
            db.students.find({ grade: "Senior" }).count()       -- 2
            db.students.count({ grade: "Senior" })              -- 2
            db.students.count({ grade: "Senior" }, { limit: 20 })    -- 0 to 20
            -- Que: How many students have 3 subjects?
                db.students.find({ subjects: { $size: 3 } }).size()
                db.students.find({ subjects: { $size: 3 } }).count()        -- 7
            -- Que: How many students have more than 1 subject?
                db.students.find({ $and: [{ subjects: { $exists: true }}, { $expr: { $gt: [ {$size: "$subjects"} , 1]}}] }).size()   -- 9 -- $ represents the field value.
        -- size()
            db.students.find().size()       -- 0 to any number
            -- db.students.find().size().limit(2)   -- Error: limit is not a function
            -- db.students.size({name: "Sagar"})    -- Error: size is not a function
        -- distinct(field, query, options)
            -- Returns an array of distinct values for the specified field.
            db.students.distinct("grade")       -- ["Sophomore", "Junior", "Senior"]
            db.students.distinct("grade", { age: { $gte: 20 } })       -- ["Sophomore", "Junior", "Senior"]
            -- db.students.find({ age: { $gte: 20 } }).distinct("grade")  -- Error: distinct is not a function.

-- -----------------
-- Datatypes is Mongo DB:
    db.companyData.insertOne({
        name: "Amazon", 
        isFunded: true, 
        funding: 987654321, 
        employee: [
            {name: "Sagar", age: 26}, 
            {name: "Suraj", age: 29}
        ], 
        foundedOn: new Date(),                  -- current date and time
        foundedOnTimestamp: new Timestamp()     -- current timestamp
    });
    db.companyData.find()
    /*[
        {
            _id: ObjectId('66b787901a1097ff88228fb5'),
            name: 'Amazon',
            isFunded: true,
            funding: 987654321,
            employee: [ { name: 'Sagar', age: 26 }, { name: 'Suraj', age: 29 } ],
            foundedOn: ISODate('2024-08-10T15:30:24.557Z'),
            foundedOnTimestamp: Timestamp({ t: 1723303824, i: 1 })
        }
    ]*/
    -- Date: Date and Time
        -- Date: ISODate('2024-08-10T15:30:24.557Z')
        -- Timestamp: Timestamp({ t: 1723303824, i: 1 })
            -- i:1 represents the increment value. It is used to differentiate between two operations that occur in the same second.
            db.companyData.insertOne({name: "Amazon", foundedOn: new Date(), foundedOnTimestamp: new Timestamp() }) -- {name: "Amazon", foundedOn: ISODate('2024-08-10T15:30:24.557Z'), foundedOnTimestamp: Timestamp({ t: 1723303824, i: 1 })}
            db.student.insertOne({
                name: "Sagar",
                dobDate: ISODate("1997-08-15"),                 -- Just the date
                dobUTC: ISODate("1997-08-15T14:30:00Z"),        -- UTC date with time
                dobIST: new Date("1997-08-15T14:30:00+05:30")   -- IST date with time and timezone
            });
            db.student.find({dob: {$gte: new Date("1997-08-15")}})  -- Find all students who are born after 15th August 1997.
            db.student.aggregate([{$group: {_id: { $year: "$dob"}, count: {$sum: 1}, names: {$push: "$name"}} }])  -- Group by year and count number of students born in that year.
            -- $year, $month, $dayOfMonth, $dayOfWeek, $hour, $minute, $second, $millisecond, $week, $dayOfYear
            db.student.aggregate([{ $project: { name: 1, _id: 0, dob: { $dateToString: { format: "%Y-%m-%d %H:%M:%S:%L", date: "$dob" } } } }])     -- [ { name: 'Sagar', dob: '1997-08-15 00:00:00:000' } ]

-- Collections Validators:
    -- Create a collection with document validation.
        -- (optional) validationAction: "error" (default), "warn", "off"
        db.createCollection("book", {
            -- validationAction: "error",
            validator: {
                $jsonSchema: {
                    -- bsonType: "object",  -- Optional
                    required: ["title", 'price'],
                    properties: {
                        title: {
                            bsonType: 'string',
                            description: 'title must be a string and required'
                        },
                        price: {
                            bsonType: 'number',
                            description: 'price must be a number and required'
                        }
                    }
                }
            }
        });

        db.books.insertOne({title: "JavaScript", price: 100});  -- inserted
        db.books.insertOne({title: "JavaScript"});  -- Error: price must be a number and required
        db.books.insertOne({title: "JavaScript", price: "100"});  -- Error: price must be a number and required
        db.books.insertOne({title: "MongoDB", price: 200, author: "Sagar"});    -- inserted
    
    -- Update validator of collection:
        -- (optional) validationAction: "error" (default), "warn", "off"
        -- collMod: Collection Modify
        db.runCommand({
            collMod: "books",
            validationAction: "error",
            validator: {
                $jsonSchema: {
                    required: ["title", 'price', 'author'],
                    properties: {
                        title: {
                            bsonType: 'string',
                            description: 'title must be a string and required'
                        },
                        price: {
                            bsonType: 'number',
                            description: 'price must be a number and required'
                        },
                        author: {
                            bsonType: 'array',
                            description: 'author must be an array and required',
                            items: {
                                bsonType: 'object',
                                required: ['name', 'email'],
                                properties: {
                                    name: {
                                        bsonType: 'string'
                                    },
                                    email: {
                                        bsonType: 'string'
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }); 
    
        db.books.insertOne({title: "JavaScript", price: 100, author: [{name: "Sagar", email: "Sagar@b.com"}, {name: "Suraj", email: "suraj@g.com"}]});  -- inserted
        db.books.insertOne({title: "JavaScript", price: 100, author: [{name: "Sagar"}]});  -- Error: 'author must be an array and required'

/* -----------------
-- WriteConcern:
            - {writeConcern: {w: <value> , j: <boolean>, wtimeout: <number>}}
                db.books.insertOne({name: "MongoDB"}, { writeConcern: { w: 1, j: false, wtimeout: 0 } })
            - It is a critical concept that dictates the level of acknowledgment requested from MongoDB for write operations. 
            - It determines how MongoDB confirms the success of a write operation (insert, update, delete, etc.) to the client.
            - Key Components:
                1. wtimeout:
                    - Specifies the time limit (in milliseconds) for the write operation to complete. 
                    - If the write operation does not complete within the specified time, the operation will time out.
                    - Default is 0 (no timeout).
                2. w: (Write Concern) Number of nodes that must acknowledge the write operation before it is considered successful.
                    A. `w: 0` =>  (Unacknowledged Write)
                        - makes performance faster.
                        No acknowledgment. The operation is considered successful as soon as the message is sent.
                        Behavior: The client does not wait for any acknowledgment from the MongoDB server. The write operation is considered successful as soon as the client sends the request.
                        Use Case: Suitable for logging or telemetry data where occasional data loss is acceptable.
                    B. `w: 1` => (Acknowledged Write - Default)
                        Acknowledgment from the primary node.
                        Behavior: The primary node acknowledges the write after it has been written to its memory.
                        Use Case: SGeneral-purpose writes where a basic acknowledgment is sufficient, and data loss is not acceptable.
                    C. `w: 2` => (Acknowledgment from Specified Number of Nodes)
                        Behavior: The write is acknowledged after being written to the primary and the specified number of secondary nodes.
                        Use Case: Ensuring data is replicated to a certain number of nodes before considering the write successful.
                        Note: If the specified number exceeds the available replica set members, the operation will time out unless wtimeout is specified
                    D. `w: "majority"` => (Majority Acknowledgment)
                        Acknowledgment from the majority of nodes.
                        Behavior: The write is acknowledged after being written to the majority of the replica set members.
                        Use Case: Ensures high durability and fault tolerance, especially important in systems requiring strong data consistency.
                    E. `w: "tagSetName"` => (Acknowledgment from Nodes with Specified Tags)
                        Acknowledgment from nodes with specified tags.
                        Behavior: T Acknowledgment from members with specific tags. Useful in complex replica set configurations.
                        Use Case: Ensures that the write is acknowledged by nodes with specific characteristics, such as geographic location or hardware configuration.
                        Note: Requires appropriate replica set configuration.
                3. j: (Journal Acknowledgment) 
                        - Scenario:
                            Suppose you're inserting 100,000 records into a MongoDB collection.
                            Each record is committed, and MongoDB writes the record number to the journal to track the progress.
                            At record number 23,302, a power outage occurs. The journal helps MongoDB identify that record 23,302 failed, so when the database restarts, it can resume or recover from that point.
                            for (let i = 1; i <= 100000; i++) {
                                db.books.insertOne(
                                    { recordNumber: i, name: `Book ${i}`, author: `Author ${i}` },
                                    { writeConcern: { j: true } }  // Ensure journaling is enabled
                                );
                            }
                        - Specifies whether the write operation should be acknowledged only after the operation is committed to the journal (if journaling is enabled). 
                        - This provides durability by ensuring the write operation is saved to the journal on disk before acknowledging.
                            - false: (Default) The operation is acknowledged 'before' it is written to the journal. ( makes performance faster)
                            - true: The operation is acknowledged 'after' it is written to the journal.
                        - Use Case: Ensures that the write operation is durable and can be recovered in case of a failure.
*/
    db.books.insertOne({name: "MongoDB"}, { writeConcern: { w: 1, j: false, wtimeout: 0 } })    -- default
    db.books.insertOne({name: "MongoDB"}, { writeConcern: { w: 0, j: true, wtimeout: 10 } })    -- Unacknowledged Write, Journal Acknowledgment, Timeout after 10ms

-- -----------------
-- Mongo Import:    
    -- After mongo version 4.4, mongoimport is not available in the bin folder.
    -- download "MongoDB Command Line Database Tools Download" from the below link:
        -- https://www.mongodb.com/try/download/database-tools
    -- Import JSON file:
        -- 1. Open Command Prompt. Go to the directory where the JSON file is located.
        -- 2. Run the following command: (Note: do not run on mongo or mongosh shell)
        mongoimport --db databaseName --collection collectionName --file students.json --jsonArray
        mongoimport --db myDb --collection Users --file students.json --jsonArray 
                -- (-- drop): Drop the collection before importing the data.

-- -----------------
-- Element Query Operators:
    -- Take books data from sample-collections.js file.
        -- books => [{ title: "Node", price: 200, isFramework: true }, { title: "Java", price: 444 }]
    -- $exists: Matches documents that contain the specified field.
        db.books.find({ isFramework: true })                -- works
        db.books.find({ isFramework: { $exists: true } })   -- works        -- retuns Node document, ...
        db.books.find({ isFramework: { $exists: false } })  -- works        -- retuns Java document, ...
        db.books.find({ isFramework: false })               -- empty, because isFramework field is not present in the document.
    
    -- $type: Matches documents that have the specified type for the field.
        -- https://www.mongodb.com/docs/manual/reference/operator/query/type/#available-types
        db.books.find({ price: { $type: "number" } })       -- works
        db.books.find({ price: { $type: 16 } })             -- works (16 is for 32-bit integer)
        db.books.find({ price: { $type: 'int' } })          -- works
        db.books.find({ price: { $type: 'long' } })         -- empty, because price is not long type.
        db.students.find({ age: { $type: ["number", "string"] } })  -- Returns all documents where the age field is a number or a string.

    -- $expr: Allows the use of aggregation expressions within the query language.
        db.books.find({ $expr: { $gt: ["$price", 200] } })  -- Returns all documents where the price field is greater than 200.
        db.books.find({ $expr: { $eq: ["$title", "$author"] } })  -- Returns all documents where the title field is equal to the author field.
        db.books.find({ $expr: { $gt: [{ "$price", { $avg: "$price" }] } })  -- Returns all documents where the price field is greater than the average price.
    
    -- $regex: Matches documents that match the specified regular expression.
        db.books.find({ title: { $regex: "^S" } })  -- Returns all documents where the title field starts with the letter "S".
        db.books.find({ title: { $regex: /^S/ } })  -- Returns all documents where the title field starts with the letter "S".
        db.books.find({ title: { $regex: "a$", $options: "i" } })  -- Returns all documents where the title field ends with the letter "a" (case-insensitive).

    -- $mod: Matches documents where the field value is a multiple of a specified divisor.
        db.books.find({price: {$mod: [50, 0]}}).sort({price: 1})    -- Returns all documents where the price field is a multiple of 50.
        db.students.find({ age: { $mod: [5, 0] } })  -- Returns all documents where the age field is a multiple of 5. => 10 mod 5 = 0, 15 mod 5 = 0

    -- $where: Matches documents that satisfy the JavaScript expression.
        -- $where is deprecated in MongoDB 4.4. Use $expr instead.
        db.students.find({ $where: "this.age > 20" })  -- Returns all documents where the age field is greater than 20.

    -- $jsonSchema: Matches documents that satisfy the specified JSON schema.
        db.students.find({ $jsonSchema: { required: ["name", "age"], properties: { name: { bsonType: "string" }, age: { bsonType: "number" } } } })  -- Returns all documents where the name field is a string and the age field is a number.

    -- $comment: Adds a comment to a query.
        db.books.find({ title: "MEAN" }).comment("Find all documents where the title is MEAN")  -- Returns all documents where the title field is "MEAN" and adds a comment to the query.

    -- $elemMatch: ( Array.contains() )
        -- Matches documents that contain an array field with at least one element that matches all the specified query criteria.
        db.products.find({ products: { $elemMatch: { name: "Laptop", quantity: { $gt: 15 } } } })  -- Returns all documents where the products array contains an element with the name "Laptop" and a quantity greater than 15.
        db.students.find({ subjects: { $elemMatch: { $eq: "Math" } } })  -- Returns all documents where the subjects array contains the element "Math".
        db.students.find({ subjects: { $elemMatch: { $gt: 90 } } })  -- Returns all documents where the subjects array contains an element greater than 90.
        db.students.find({ subjects: { $elemMatch: { $gt: 90, $lt: 100 } } })  -- Returns all documents where the subjects array contains an element between 90 to 100.

/* -----------------
-- Update Nested Arrays:
    -- Take data from sample-collections.js file. 
        -- employees collection => 
        {
            name: "Sagar",
            Hobbies: ["Cricket", "Football", "Chess"],
            Address: { city: "Pune", state: "MH" },
            Experience: [
                { company: "Google", duration: 1 },
                { company: "Microsoft", duration: 3.6 },
                { company: "Amazon", duration: 1.5 }
            ]
        } */ 
  -- $elemMatch: ( Array.contains() )
    -- find all employees who have experience less than 2 years.
        db.employees.find({Experience: { $elemMatch: { duration: {$lt: 2} } } }) ;
    -- Add new field (bondCompleted: false) in all employees:
        --  first match of duration less than 2 years:  (only Google will be updated.)
            -- $: The positional $ operator identifies an element in an array to update without explicitly specifying the position of the element in the array.
            db.employees.updateMany( { Experience: { $elemMatch: { duration: { $lt: 2 } } } }, { $set: { 'Experience.$.bondCompleted': false } } );
        --  Any experience having less than 2 years, then update all experiences.
            -- (all experiences will be updated. ie Google, Microsoft, Amazon)  
            -- $[]: The all positional operator identifies an array element to update without explicitly specifying the position of the element in the array.
            db.employees.updateMany( { Experience: { $elemMatch: { duration: { $lt: 2 } } } }, { $set: { 'Experience.$[].bondCompleted': false } } );
        -- Only update the fields whose duration is less than 2 years.
            db.employees.updateMany( { Experience: { $elemMatch: { duration: { $lt: 2 } } } }, { $set: { 'Experience.$[exp].bondCompleted': false } }, { arrayFilters: [ { 'exp.duration': { $lt: 2 } } ] } );

    -- Arrays Update:
        -- $push: Adds an element to an array.
            db.employees.updateOne({name: "Sagar"}, {$push: {Experience: {company: "Meta", Duration: 3}}})
        -- $pull: Removes an element from an array.
            db.employees.updateOne({name: "Sagar"}, {$pull: {Experience: {company: "Meta"}}})
        -- $addToSet: Adds an element to an array if it does not already exist.
            db.employees.updateOne({name: "Sagar"}, {$addToSet: {Experience: {company: "Meta", Duration: 3}}})
        -- $pop: Removes the first or last element of an array.
            db.employees.updateOne({name: "Sagar"}, {$pop: {Experience: 1}})  -- Removes last element
            db.employees.updateOne({name: "Sagar"}, {$pop: {Experience: -1}})  -- Removes first element


/* -----------------
Index:
    - Indexes are used to improve the performance of queries.
    - Indexes are stored in B-tree data structure.
    - It Stores: 
        1. Field Name (on which index is created)
        2. Value of the field (on which index is created) (sorted)
        3. Pointer to the document (where the field value is stored)
    - Disadvantages:
        1. Indexes consume additional storage space.
        2. Indexes slow down write operations (insert, update, delete). Because, for each write operation, the index also needs to be updated to maintain consistency.
    - Types of Indexes:
        1. Single Field Index: Index on a single field. (name)
        2. Compound Index: Index on multiple fields.    (name, age)
        3. Multikey Index: Index on an array field.     (subjects : ["Math", "Physics", "Computer Science"])
        4. Text Index: Index on text fields.            (String: title, bio)
        5. Hashed Index: Index on hashed values.        (title: "hashed")
    - When to Use Indexes:
        1. Use indexes on fields that are frequently used in queries.
        2. Use indexes on fields that are used for sorting and grouping.
        3. Use indexes on fields that are used for joining collections.
        4. When the collection is large and the query performance is slow.
    - When Not to Use Indexes:
        1. Do not use indexes on fields that are rarely used in queries.
        2. Do not use indexes on fields that have a low cardinality (few unique values).
        3. Do not use indexes on fields that are frequently updated.
*/
    -- 1. Create Index:
        -- A. Create a single field index on the title field.
            db.books.createIndex({ title: 1 })  -- 1 for ascending sorting, -1 for descending
        -- B. Create a compound index on the title and price fields.
            db.books.createIndex({ title: 1, price: 1 })    -- first sort by title, then by price
            -- Test:
                db.books.find({ title: "Node", price: 200 }).explain()  -- 'IXSCAN' (Index Scan) will be used.
                db.books.find({ title: "Node" }).explain()  -- 'IXSCAN' (Index Scan) will be used.
                db.books.find({ price: 200 }).explain()  -- 'COLLSCAN' (Collection Scan) will be used. Because, price is not in sorting order.
                db.books.find({ price: 200, title: "Node" }).explain()  -- 'COLLSCAN' (Collection Scan) will be used. Because, price is not in sorting order.
        -- C. Create a multikey index on the subjects field.
            -- Multikey Index: Index on an array field.
            db.students.createIndex({ subjects: 1 })    -- create index for each element in the array. eg. MathIndex, PhysicsIndex, ...
        -- D. Create a text index on the title field.
            -- Text indexes can only be created on 'string' fields.
            -- Only one text index can be created per collection.
            db.students.createIndex({ name: "text", bio: "text" })    -- create index on multiple fields.
                -- $text: Performs a text search on the field.
                    db.students.find({ $text: { $search: "Computer" } }) -- Returns all documents where the title or bio field contains the word "Computer".
                    db.students.find({ $text: { $search: "Computer" }}, { myScore: { $meta: "textScore" } } ) -- Returns all documents where the title or bio field contains the word "Electronics" and returns the text score.
                    db.students.find({ $text: { $search: "Computer -Engineering" }}, { myScore: { $meta: "textScore" } } ) -- Returns all documents where the title or bio field contains the word "Electronics" but not "Engineering" and returns the text score.
        -- E. Create a hashed index on the title field.
            db.books.createIndex({ title: "hashed" })
    -- 2. List Indexes:
        db.books.getIndexes();
    -- 3. Drop Index:
        db.books.dropIndex("title_1")  -- title_1 is the name of the index.
    -- 4. Drop All Indexes:
        db.books.dropIndexes();
    -- 5. Explain Query:
        db.books.find({ title: "Node" }).explain()                  -- Returns the execution plan of the query.
        db.books.find({ title: "Node" }).explain("executionStats")  -- Returns the execution statistics of the query.
    -- 6. Add Constraints or Options to Index:
        -- unique: Ensures that the field values are unique.
            db.books.createIndex({ title: 1 }, { unique: true });   -- Avoids creating duplicate titles in the collection.
            -- Test: 
                db.books.insertOne({ title: "Node", price: 200 });  -- Works
                db.books.insertOne({ title: "Node", price: 200 });  -- Error: E11000 duplicate key error collection: myDb.books index: title_1 dup key: { title: "Node" }
        -- sparse: Ignores documents that do not contain the field.
            db.books.createIndex({ title: 1 }, { sparse: true })    -- Ignores documents that do not contain the title field. Avoids creating an index for null values.
        -- partialFilterExpression: Applies the index only to documents that match the specified filter.
            db.books.createIndex({ title: 1 }, { partialFilterExpression: { price: { $gt: 100 } } })
        -- expireAfterSeconds: Automatically deletes documents after the specified number of seconds.
            -- Note: The field must be a date type.
            db.books.createIndex({ createdAt: 1 }, { expireAfterSeconds: 3600 })  -- Deletes documents after 1 hour. (createdAt field must be a date type)
        -- Projection Covered Query:
            -- If the query only returns the fields that are indexed, MongoDB can use the index to satisfy the query.
                db.books.find({ title: "Node" }, { title: 1, _id: 0 }).explain()  -- 'IXSCAN' (Stage: PROJECTION_COVERED) -- only title field which is indexed is returned.
                db.books.find({ title: "Node" }, { price: 1, _id: 0 }).explain()  -- 'IXSCAN' (Stage: FETCH)    -- price field is not present in index, so fetch from the document.
        -- background: Creates the index in the background.
            -- by default "false": creates the index in the foreground.
                -- When creating an index in the foreground, the operation blocks/locks the collection until the index is created. This can impact the performance of the database.
            db.books.createIndex({ title: 1 }, { background: true }) -- Do not lock the collection while creating the index.
        -- Winning Plan:
            -- Winning Plan: The query optimizer selects the best plan to execute the query.
            -- If collection have multiple indexes on same field, then query optimizer selects the best index to execute the query.
                -- example: Index1: title_1, Index2: title_1_price_1
                db.books.find({ title: "Node" }).explain()  -- 'IXSCAN' (Index: title_1)
                db.books.find({ title: "Node", price: 200 }).explain()  -- 'IXSCAN' (Index: title_1_price_1) // explain shows winningPlan and rejectedPlans details.
            -- In case of Multiple Indexes:
                -- Mongo DB checks the performance of the query with all indexes and selects the best index.
                -- Then it stored winning plan in cache for future use.
                -- When the query is executed next time, it directly uses the winning plan from cache.
                -- Cache is reset after:
                    -- 1. Restart of the server.
                    -- 2. After 1000 write operations.
                    -- 3. Index is dropped/reset/modified.

/* -----------------
Aggregation:
    - It groups the data from multiple documents into a single document based on the specified expression.
    - db.collection.aggregate(pipeline, options)
        - pipeline: An array of aggregation stages.
        - options: Optional parameters.
*/
    -- 1. $match: Filters the documents based on the specified condition.
        db.books.aggregate([
            { $match: { title: "Node" } }
        ])
    -- 2. $project: Reshapes the documents based on the specified expression.
        -- db.books.find({}, { title: 1, price: 1 })  -- { title: "Node", price: 200 }
            db.books.aggregate([ { $project: { title: 1, price: 1 } } ])    -- { _id: 123, title: "Node", price: 200 }
        -- Exclude Fields:
            db.books.aggregate([ { $project: { _id: 0, title: 1, price: 1 } } ])    -- { title: "Node", price: 200 }
        -- Rename Fields:
            db.books.aggregate([ { $project: { _id: 0, bookTitle: "$title", bookPrice: "$price" } } ])    -- { bookTitle: "Node", bookPrice: 200 }
        -- Add New Fields:
            db.books.aggregate([ { $project: { title: 1, price: 1, discountPrice: { $subtract: ["$price", 10] } } } ])    -- { title: "Node", price: 200, discountPrice: 190 }
    
    -- 3. $group: Groups the documents based on the specified expression.
        -- $group: { _id: expression, field1: expression, field2: expression, ... }
            db.books.aggregate([
                { $group: { _id: "$price", name: { $push: "$title"}}}       -- [{ _id: 200, name: [ 'Node', 'MongoDB' ] }...]
            ])
            -- sql: select price, group_concat(title) from books group by price;
        -- $$ROOT: Represents the root document.
            db.books.aggregate([
                { $group: { _id: "$price", samePriceBooks: { $push: "$$ROOT"}}}       -- [{ _id: 200, samePriceBooks: [ { _id: 1, title: 'Node', price: 200, isFramework: true }, { _id: 2, title: 'MongoDB', price: 200 } ] }...]
            ])
        -- Give a count per age of male Students.
            -- $sum: increment count by 1 for each document.
            db.students.aggregate([
                { $match: { Gender: "Male" } },
                { $group: { _id: "$age", countOfStudents: { $sum: 1}}}
            ])
        -- Give a count per age of male Students. and sort them by count in desc manner:
            db.students.aggregate([
                { $match: { Gender: "Male" } },
                { $group: { _id: "$age", countOfStudents: { $sum: 1}}},
                { $sort: { countOfStudents: -1 } }
            ]);
        
    -- 4. $sort: Sorts the documents based on the specified field.
        db.books.aggregate([
            { $sort: { price: 1 } }
        ])
    -- 5. $limit: Limits the number of documents to return.
        db.books.aggregate([
            { $limit: 2 }
        ])
    -- 6. $skip: Skips the specified number of documents.
        db.books.aggregate([
            { $skip: 2 }
        ])
    -- 7. $unwind: Deconstructs an array field into multiple documents. ie [] => ...array elements (spread)
        db.students.aggregate([
            { $unwind: "$subjects" }
        ])
        -- document: { name: "Sagar", Hobbies: ["Cricket", "Football", "Chess"] }
        -- $unwind: { name: "Sagar", Hobbies: "Cricket" }, { name: "Sagar", Hobbies: "Football" }, { name: "Sagar", Hobbies: "Chess" }
        -- List all Hobbies:
            db.students.aggregate([
                { $unwind: "$subjects" },
                { $group: { _id: null, allSubjects: { $addToSet: "$subjects" } } }
            ])
    -- 8. $lookup: Performs a left outer join to another collection in the same database.
        -- Use for Joins.
        -- Students: { name: "Bharati", age: 30, grade: "A+", Gender: "Female", bio: "I am a student of Computer Science", subjects: ["Node", "Angular", "MongoDB"] }
        -- Books:{ title: "MongoDB", price: 200 },
        db.students.aggregate([
            {
                $lookup: { 
                    from: "books",              -- foreign (books) collection name
                    localField: "subjects",     -- local (students) collection foreign field name
                    foreignField: "title",      -- foreign (books) collection field name
                    as: "booksInfo"             -- alias name
                } 
            }
        ])
    -- 9. $out: Writes the resulting documents to a new collection.
        db.students.aggregate([
            { $out: "newCollection" }
        ])
    -- 10. $addFields: Adds new fields to the documents.
        db.books.aggregate([
            { $addFields: { total: { $add: ["$price", 100] } } }
        ])
    -- 11. $replaceRoot: Replaces the root document with the specified document.
        db.books.aggregate([
            { $replaceRoot: { newRoot: { title: "$title", price: "$price" } } }
        ])
    -- 12. $redact: Restricts the content of documents based on the specified condition.
        db.books.aggregate([
            { $redact: { $cond: { if: { $eq: ["$title", "Node"] }, then: "$$DESCEND", else: "$$PRUNE" } } }
        ])
    -- 13. $filter: Filters the elements of an array based on the specified condition.
        db.students.aggregate([
            { $project: { name: 1, filteredSubjects: { $filter: { input: "$subjects", as: "subject", cond: { $eq: ["$$subject", "Math"] } } } } }
        ])
    -- 14. Bucket: Categorizes documents into groups, called buckets.
        /*    syntax => {
                $bucket: {
                    groupBy: expression,
                    boundaries: [boundary1, boundary2, ...],
                    default: value,
                    output: {
                        outputField1: { $sum: expression1 },
                        outputField2: { $avg: expression2 }
                    }
                }
            }
        Que: Create 3 groups of students based on their age: 0-30, 30-40, greater than 40.
        */  
        db.students.aggregate([ 
            -- {$match: {Gender: "Male"}},
            { 
                $bucket: { 
                    groupBy: "$age", 
                    boundaries: [0, 30, 40], 
                    default: "greater than 40", 
                    output: { 
                        count: { $sum: 1 }, 
                        names: {$push: "$name"} 
                    } 
                } 
            }
        ])

/* -----------------
Authentication and Authorization:
    - Authentication: The process of verifying the identity of a user.
    - Authorization: The process of granting or denying access to resources based on the user's identity.
    - Authentication Methods:
        1. SCRAM-SHA-1: Salted Challenge Response Authentication Mechanism.
        2. SCRAM-SHA-256: Salted Challenge Response Authentication Mechanism.
        3. x.509: Certificate-based authentication.
        4. LDAP: Lightweight Directory Access Protocol.
        5. Kerberos: Network authentication protocol.
    - Authorization Methods:
        1. Role-Based Access Control (RBAC): Assigns roles to users and grants permissions based on the roles.
        2. Attribute-Based Access Control (ABAC): Grants permissions based on the attributes of the user, resource, and environment.
        3. Discretionary Access Control (DAC): Grants permissions based on the discretion of the owner.
        4. Mandatory Access Control (MAC): Grants permissions based on security labels.
    - Enable Authentication:
        -- 1. Start MongoDB with Authentication:
            mongod --auth
        -- 2. Connect to MongoDB:
            mongo -u admin -p admin --authenticationDatabase admin
        -- 3. Create a User:
            db.createUser({ user: "admin", pwd: "admin", roles: ["userAdminAnyDatabase"] })
        -- 4. Create a User with Custom Roles:
            db.createUser({ user: "admin", pwd: "admin", roles: ["readWrite", { role: "read", db: "books" }] })
        -- 5. List Users:
            db.getUsers()
        -- 6. Drop a User:
            db.dropUser("admin")
        -- 7. Drop All Users:
            db.dropAllUsers()
        -- 8. Update a User:
            db.updateUser("admin", { roles: ["readWrite", { role: "read", db: "books" }] })
        -- 9. Change Password:
            db.changeUserPassword("admin", "newPassword")
        -- 10. Enable Authorization:
            -- 1. Start MongoDB with Authorization:
                mongod --auth --keyFile keyfile
            -- 2. Connect to MongoDB:
                mongo -u admin -p admin --authenticationDatabase admin
            -- 3. Create a User with Custom Roles:
                db.createUser({ user: "admin",
    -- Roles:
        - read / readWrite
        - dbAdmin / dbOwner / userAdmin 
        - readAnyDatabase / readWriteAnyDatabase / userAdminAnyDatabase / dbAdminAnyDatabase
        - clusterAdmin / clusterManager / clusterMonitor / hostManager
        - root / backup / restore
*/
    -- Step 1: Update Configurations:
        -- add below configuration in this path: "C:\Program Files\MongoDB\Server\7.0\bin\mongod.cfg"
            security:
                authorization: enabled
        -- restart mongodb service.
    -- Step 2: Create admin user in admin database:
        use admin;
        db.createUser({ user: "admin", pwd: "1234", roles: ["userAdminAnyDatabase", "dbAdminAnyDatabase"] });
    -- Step 3: Connect to admin user:
        -- In Shell:
            db.auth("admin", "1234");  -- (username, password)
        -- In Command Prompt:
            mongo -u admin -p 1234 --authenticationDatabase admin
            db.logout();  -- logout from the current user.
    -- Step 3. Create read only user:
        use admin;
        db.createUser({user: "Sagar", pwd: "1234", roles: ["read"]});
        db.getUsers();  -- list all users.  
        db.createUser({user: "Suraj", pwd: "1234", roles: [{role: "readWrite", db: "books"}, {role: "read", db: "students", collection: "employees"}]});
    -- Get Current user details:
        db.runCommand({connectionStatus: 1});

/* -----------------
Replication and Sharding:
    - Replication: The process of synchronizing data across multiple servers to ensure data availability and fault tolerance.
    - Sharding: The process of distributing data across multiple servers to improve performance and scalability.
        Vertical Sharding: Splitting the data based on the type of data. (Increase the CPU, RAM, etc.)
        Horizontal Sharding: Splitting the data based on the range of data. (Increase the number of servers)
    
-- -----------------
Transaction:
    - A transaction is a set of operations that are executed as a single unit of work.
    - A transaction must follow the ACID properties:
        1. Atomicity: All operations in a transaction are executed or none.
        2. Consistency: The database remains in a consistent state before and after the transaction.
        3. Isolation: The intermediate state of the transaction is not visible to other transactions.
        4. Durability: The changes made by the transaction are permanent.
*/
    -- Start Transaction:
        session = db.getMongo().startSession();
        session.startTransaction();
    -- Execute Operations: 
        -- Transfer money from one account to another.
        var cust = session.getDatabase("bank").customers;
        cust.updateOne({ name: "Sagar" }, { $inc: { balance: -1000 } });
        cust.updateOne({ name: "Suraj" }, { $inc: { balance: 1000 } });        
    -- Commit Transaction:
        session.commitTransaction();
    -- Abort Transaction:
        session.abortTransaction();
    -- End Session:
        session.endSession();
    -- Replicate data to secondary database:
        db.getMongos().setSecondaryOk();
