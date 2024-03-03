-- Login:
	-- mysql -u root -p
-- clear command line history :   \! cls

-- Databases:
	show databases;
    create database myDb;
    use myDb;
    drop database myDb; -- delete DB;
    select database()   -- selected database name.

/* 
==============
Normalization:
	- Process of reducing the redundancy of data.
	- Improves the Data Integrity.
	- used for:
		- used to eliminate repeated data.
		- ensure data dependency make some logical sence
		- Removes repeating groups from the table.
		- Create a separate table for each set of related data
		- Identify each set of related data with a primary key
		- eg. Student and Address should not be in single row, create seprate table
Normalization Types:
- 1st Normal form:
	- Each cell in a row contain single value.
- 2nd Normal form:
	- Table (non-prime attribute) should depends on Primary Key (prime attribute), And its should be unique, not null.
- 3rd Normal form:
	- There should be no transitive dependency for non-prime attributes.
	- Student and Address should no be in single row, create a seprate table.
	- Transitively dependency?
		- Suppose, C is depends on B and B is depends on A. Then transitively  C is depends of A.
	- All non prime attributes(sName, sAge), must be depends on prime attributes (sRollNo)


==============
ACID Properties.
1. Atomicity:
    - Atomicity ensures that a transaction is treated as a single unit of work that either completes entirely or has no effect at all.
    - Example: Consider a bank transfer where money is withdrawn from one account and deposited into another. 
      Atomicity ensures that if the deposit operation succeeds but the withdrawal operation fails (due to insufficient funds), 
      the entire transaction is rolled back, and neither account is affected.
2. Consistency:
    - Consistency ensures that a transaction transforms the database from one consistent state to another consistent state.
    - Example: In a database system enforcing a foreign key constraint, consistency ensures that 
      if a transaction attempts to insert a record with a foreign key referencing a non-existing primary key, 
      the transaction fails, maintaining the overall consistency of the database.
3. Isolation:
    - Isolation ensures that the execution of one transaction is isolated from the effects of other concurrently executing transactions.
    - Example: In a multi-user database system, isolation ensures that 
      if one transaction reads data while another transaction is updating the same data, 
      the first transaction sees a consistent view of the data, unaffected by the changes made by the second transaction until it's committed.
4. Durability:
    - Durability guarantees that once a transaction is committed, its effects are permanent and persist even in the event of system failures.
      Such as power outages or crashes. Committed changes must be stored permanently in non-volatile storage (e.g., disk) 
      and should not be lost even if the system fails.
    - Example: After a successful transaction to transfer funds between accounts, 
      durability ensures that the changes made to the account balances are persisted to disk. 
      Even if the database system crashes after the transaction is committed, the changes are not lost upon system recovery.

These examples illustrate how each ACID property ensures data integrity, consistency, and reliability in database transactions.

===============
-------------------------
Constraints:
    - rules or conditions applied to the data in a table.
    - help ensure data integrity and enforce certain conditions on the data being inserted, updated, or deleted in the table.
    - Some of the Constraints are:
        - Primary Key
        - Foreign Key
        - Unique Constraint
        - NOT NULL Constraint
        - Check Constraint

Integrity Constraits:
    - Database integrity refers to the accuracy, consistency, and reliability of data stored in a database. 
    - There are several types of integrity constraints that help maintain data integrity:
- 1. Entity Integrity:
    - In short, it states that primary key value can't be null.
    - Entity integrity ensures that each row (or record) in a table is uniquely identifiable. 
    - It is typically enforced by primary key constraints, which prevent duplicate or null values in the primary key column(s).
2. Referential Integrity:
    - In short, Foreign key value can be either available in reference table or must be null.
    - Referential integrity ensures the consistency of relationships between tables. 
    - It is enforced by foreign key constraints, which ensure that values in a foreign key column must match values in the corresponding primary key column of another table (or be NULL).
3. Domain Integrity:
    - Domain constraints can be defined as the definition of a valid set of values for an attribute.
    - Domain integrity ensures that values in a database adhere to defined data types, formats, and ranges. 
    - It is enforced by data type constraints, check constraints, and domain constraints.
    - eg. salary should be int or decimal and greater the 0.
4. User-Defined Integrity:
    - User-defined integrity constraints are custom rules specified by the database administrator or application developer 
      to ensure data consistency according to specific business rules.
    - These constraints are enforced through triggers, stored procedures, or application logic.
5. Semantic Integrity:
    - Semantic integrity ensures that the data stored in the database accurately represents real-world entities and relationships. 
    - It is maintained through proper database design, normalization, and business logic enforcement.

1. Domain Constraits	: on Attribute Condition/checks
2. Entity Integrity Constraits	: primary key null not allowed
3. Referential Integrity Constraits	: FK should be PK of other table or null
4. Key Constraits	: choose entity/column as Primary key which is unique and not null

By enforcing these integrity constraints, databases ensure data accuracy, consistency, and reliability, which are crucial for reliable and meaningful data storage and retrieval.
===============================
Literals :
    Literals are fixed values that are directly used in SQL statements. 
    They can be of various data types such as strings, numbers, dates, or boolean values. 
1. String Literals: Enclosed in single quotes (' ') or double quotes (" ").
    'Hello, World!'
    "MySQL"
2. Numeric Literals: Representing integer or floating-point numbers.
    123
    3.14
3. Date and Time Literals: Representing specific points in time.
    DATE '2022-03-14'
    TIME '12:30:00'
    TIMESTAMP '2022-03-01 12:30:00'
4. Boolean Literals: Representing true or false values.
    TRUE
    FALSE
5. NULL Literal: Represents a null value.
    NULL
6. Binary Literals: Representing binary data. Enclosed in single quotes (' ') followed by the letter b or B.
    b'1101'
    B'0010'
7. Hexadecimal Literals: Representing hexadecimal data. Enclosed in single quotes (' ') followed by the letter x or X.
    x'4A'
    X'FF'
*/

-- tables:
	show tables;	-- tables, views
    show full tables; -- tables, views with Table_type
	
    describe book;	-- show structure of book table
    desc book;
-- SHOW ----
    SHOW databases;
    show tables;
    SHOW FULL TABLES LIKE 'temp_%';
    show COLUMNS from book; -- same as: desc book;
    show INDEX from my_table;
    show CREATE TABLE my_table;
    show GRANTS FOR 'user'@'localhost';
    show VARIABLES;
    show STATUS;  -- db status
    show PROCESSLIST;
    show table status LIKE "book"; -- internal details of table;
-- explain:
    explain select * from book;
/*
Types of SQL Commands:
- There are 5 types of SQL commands:
1. DDL (Data Definition Language):
    - Used for defining and managing the structure of database objects.
        CREATE: Creates new database objects such as tables, views, indexes, etc.
        ALTER: Modifies the structure of existing database objects.
        DROP: Deletes existing database objects.
        TRUNCATE: Removes all rows from a table.
        RENAME: Renames a database object.
2. DML (Data Manipulation Language):
    - Used for managing data within database objects.
        INSERT: Adds new rows of data into a table.
        UPDATE: Modifies existing data in a table.
        DELETE: Removes existing rows from a table.
3. DQL (Data Query Language)
    - querying and retrieving data from a database.
        SELECT: Retrieves data from one or more tables.
4. DCL (Data Control Language):
    - Used for controlling access to data within the database.
        GRANT: Gives specific privileges to users or roles.
        REVOKE: Removes specific privileges from users or roles.
5. TCL (Transaction Control Language):
    - used for managing transactions within the database.
        COMMIT: Saves changes made during a transaction.
        ROLLBACK: Undoes changes made during a transaction.
        SAVEPOINT: Sets a point within a transaction to which you can later roll back.
        SET TRANSACTION: Specifies characteristics for the transaction.

============= 1. DDL =============
1. Create, 2. Alter, 3. Drop 4. Truncate
DDL 1. Create:
    Create Table, create Index, create View, create Trigger
*/
create table aa(id int(10) auto_increment primary key , Name varchar(20));
create table Book (
	bookId varchar(10) not null,
    bookName varchar(255) not null default 'No Book',
    bookCode varchar(10) UNIQUE,
    authorId varchar(10) not null,
    rackId varchar(10),
    category varchar(20),
    bookPrice Double,
    noOfPages int /* CHECK (noOfPages >= 10) */,
    bookStatus enum('A', 'NA') not null,
    bookLastIssueDate date,
    bookLastIssueTime time,
    
    Primary key (bookId),
    Foreign Key (authorId) References author(authorId),
    Foreign Key (rackId) References rack(rackId),
    CHECK (noOfPages >= 10)
); 

create table Rack (
	rackId varchar(10) not null primary key,
    rackCapacity int(20)
);

create table author (
	authorId varchar(10) not null primary key,
    authorName varchar(20)
);

create table newBook (
	bookId varchar(10) not null,
    bookName varchar(10) not null default 'No Book',
    authorId varchar(10) not null,
    rackId varchar(10)  ,
    category varchar(20),
    noOfPages int(10),
    bookStatus enum('A', 'NA') not null,
    bookLastIssueDate date,
    bookLastIssueTime time
);

-- How to create table from data of another table?
    create table Books As select * from book;
    create table employee_backup as select emp_id, emp_name from employee;
/*
-----------------------
DDL 2. Alter:
    used to change the structure of the table.
    - Clause: 2.1 Add, 2.2 Modify, 2.3 Drop, 2.4 Change, 2.5 Rename To */
    -- Add Column:
        alter table Rack add rackName varchar(20) after rackId;
        alter table rack add Column rackFloor int(10);
        alter table rack add rackFloor int(10);
        ALTER TABLE table_name ADD INDEX idx_name (column_name);
        ALTER TABLE table_name ADD UNIQUE INDEX idx_name (column_name);
    -- Modify Column:
        alter table Rack Modify Column rackFloor varchar(20) after bookName;
        alter table Rack Modify Column rackFloor varchar(20);
        alter table Rack Modify rackFloor varchar(20);
        alter table Rack Modify rackFloor varchar(22) not null; -- before applying not null, make sure rackFloor must be present in all existing records.

    -- Drop Column:
        alter table rack Drop Column rackFloor;
        alter table rack Drop rackFloor;
        ALTER TABLE table_name DROP INDEX idx_name;
    
    -- Change: rename column name:
        alter table rack change column rackFloor rackFloorNo int(20);
        alter table rack change rackFloor rackFloorNo int(20);
    -- RENAME column
        alter table department RENAME COLUMN dept_id to department_id;
    -- Rename: rename table name:
        alter table book1 Rename To book2;
-- DDL 5. Rename:
        Rename table newBook to newBook1;   -- only one command.
-- DDL 3. Drop:
    -- used to delete existing database objects such as tables, indexes, views, or databases themselves.
    drop table employee;
    drop table employee, department;
    DROP DATABASE database_name;
    DROP TABLE table_name;
    DROP INDEX index_name ON table_name;
    DROP VIEW view_name;
    DROP TRIGGER trigger_name;
    DROP FUNCTION function_name;
    DROP PROCEDURE procedure_name;
  -- create Primary Key
    -- add PK
    alter table newBook add Primary Key (bookId);
    alter table newBook2 add bookId int primary key; -- add new column
    alter table newBook modify bookId varchar(10) primary key;
    -- remove PK
    alter table newBook drop primary key;  -- only delete PK on bookId, not deleting the column.
    alter table book2 drop bookId; -- delete PK and column both
    -- alter table newBook modify bookId varchar(10) -- this will not remove PK.
  -- create foreign key.
    alter table newBook add foreign key (authorId) references author (authorId);
    alter table newBook add constraint Foreign Key (rackId) references Rack(RackId);
    -- remove FK:
    ALTER TABLE table_name DROP FOREIGN KEY fk_constraint_name;
/* DDL 4. Truncate:
    - use to delete all data but not the structure.
    - Once it delete, we cant rollback.
    - TRUNCATE is faster than DELETE.
    - TRUNCATE does not return the number of rows.
    - Truncate bypasses contraints and triggers.
    - Note on delete mechanism: it will drop the table and create a new structure, so deletion in TRUNCATE is faster than DELETE query.
*/  
    TRUNCATE TABLE newbook;
    TRUNCATE TABLE table1, table2, table3;
    -- Rack table PK is foreign key for Book;
        SET FOREIGN_KEY_CHECKS = 0;   -- disable foreign key check.
        TRUNCATE TABLE table_name1;
        TRUNCATE TABLE table_name2;
        SET FOREIGN_KEY_CHECKS = 1;
    -- truncate query also used inside stored procedure.

/*
============= DML =============
- Data Manipulation Language
    -  used to manage and manipulate data stored within database objects, such as tables. 
Types:
    1. Insert Into  : Adds new rows of data into a table.
    2. Update SET   : Modifies existing data in a table.
    3. delete FROM  : Removes existing rows from a table.

---------------
DML: 1. Insert ... Into:
    INSERT INTO table_name (column1, column2, column3) VALUES (value1, value2, value3), (value4, value5, value6), ...;
*/  
-- 2. Inserting Multiple Rows in a Single Statement:
    insert into rack values('r1', 14), ("r2", 55), ("r3", 65), ("r4", 77);
     insert into author (authorId) values ("a3"), ("a4");
-- 1. Inserting Single Rows:
    insert into author (authorId, authorName) values ("a2",'Robert');
    insert into books (bookId, bookName, bookCode, authorId, rackId, category, bookPrice, noOfPages, bookStatus, bookLastIssueDate, bookLastIssueTime) values ('b1', "Rich Dada Poor Dad", "a2h3", 'a2', 'r1', 'sci-fi', 434.4, 200, 'A', "2024/02/19", "10:08:55");
    INSERT INTO book 
        (bookId, bookName, bookCode, authorId, rackId, category, bookPrice, noOfPages, bookStatus, bookLastIssueDate, bookLastIssueTime) 
        VALUES 
        ('b1', "Rich Dada Poor Dad", "a2h3", 'a2', 'r1', 'sci-fi', 434.4, 200, 'A', "2024/02/19", "10:08:55"),
        ('b2', 'The secrets', 'nc32', 'a1', 'r4', 'adventure', 555, 400, 'NA', '2024-02-12', '01:08:55'),
        ('b3', 'MySQL', 'cd23', 'a2', 'r3', 'adventure', 1200, 100, 'A', '2023-02-19', '10:08:55'),
        ('b4', 'Java', 'df3d', 'a2', 'r2', 'adventure', 1200, 100, 'A', '2023-02-19', '10:08:55'),
        ('b5', 'JavaScript', 'cdsr', 'a3', 'r1', 'comedy', 2100, 220, 'NA', '2023-02-19', '04:03:25');

    INSERT INTO employees (id, name, salary) VALUES (1, 'John', 50000), (2, 'Jane', 60000);
-- 3. Inserting Data from Another Table:
    INSERT INTO employees_archive (id, name, salary) SELECT id, name, salary FROM employees WHERE hire_date < '2022-01-01';
    insert into student_archive (sId, sName) select sId, sName from students where sMarks > 40; -- AS allows only in create.
    insert into student_archive select sId, sName from students where sMarks > 40;  -- if student_archive table only have 2 columns, ie sId, sName.
/*
-------------------
DML 2: Update ... SET:
    -  modify existing data in a table. 
    - It is commonly used to change the values of one or more columns in existing rows based on specified conditions.
UPDATE table_name
    SET column1 = value1, column2 = value2, ...
        WHERE condition
            ORDER BY column_name
                LIMIT row_count;
UPDATE table_name
    SET column1 = new_value
        WHERE id IN (SELECT id FROM other_table WHERE condition);
*/  
-- 1. Update Multiple/all Rows:
    Update employees SET salary = salary*1.2;
-- 2. Update Specific Rows:
    UPDATE employees SET salary = salary * 1.1 WHERE department = 'IT';
    UPDATE students SET marks = marks * 1.1, fees = 4000 WHERE class = 8;
-- 3. Update from Another Table:
    -- IN is used to match multiple records. = equals sign only match a single recods.
    UPDATE employees SET department = 'HR' WHERE employee_id IN (SELECT employee_id FROM new_hires);
/*
-------------------
DML 3: DELETE ... FROM:
    - used to remove rows from a table based on specified conditions.
DELETE FROM table_name
    WHERE condition;
*/
-- 1. Delete All Rows:
    delete from Students; -- resulting in the loss of all data. So, always use where clause.
-- 2. Delete Rows Based on a Single Condition:
    delete from students where sId = 1;
    delete from employees where department = "IT";
-- 3. Delete Rows Based on Multiple Conditions:
    delete from employees where department="IT" and hire_date < '2020-01-01';

-- 4. Delete Rows Using Subqueries:
    delete from rack where rackId NOT IN (select rackId from book);
    delete from rack where rackId IN (select rackId from rackBackup);
-- 5. Delete Rows Based on NULL Values:
    delete from author where authorName IS NULL;

/* =============================================
============= DQL =============
- Data Query Language:
    - It is focused on retrieving data from the database.
    - It mainly consists of the SELECT statement, which allows you to query the database and retrieve the information you need.
SELECT column1, column2, ...
    FROM table_name
        WHERE condition
            GROUP BY column_name
                HAVING condition
                    ORDER BY column_name ASC|DESC
                        LIMIT row_count OFFSET offset_value;
SELECT: Specifies the columns to be retrieved from the database table.
FROM: Specifies the table from which to retrieve the data.
WHERE: Optional clause that filters rows based on specified conditions.
GROUP BY: Optional clause that groups rows with the same values into summary rows.
HAVING: Optional clause that filters groups based on specified conditions.
ORDER BY: Optional clause that sorts the result set based on specified column(s) in ascending (ASC) or descending (DESC) order.
LIMIT: Optional clause that limits the number of rows returned by the query.
OFFSET: Optional clause that specifies the number of rows to skip before starting to return rows from the query, its used on top of LIMIT.
*/
    SELECT first_name, last_name, salary
        FROM employees
        ORDER BY salary DESC
        LIMIT 10 OFFSET 5;

    SELECT * FROM employees WHERE employee_id NOT IN (101, 102, 103);

-- # Basic SELECT Queries:
-- 1. Select All Columns from a Table:
    select * from database_name.employees;
    select * from employees;
-- 2. Select Specific Columns from a Table:
    SELECT first_name, last_name FROM employees;
    SELECT first_name AS "first name", last_name AS lastName FROM employees; -- AS, display column name last_name as lastName
-- 3. Select Distinct Values from a Column:
    select Distinct departments from employees; -- return unique departments.
    select Distinct(dept_id) from employees; -- return unique departments.
    select count(distinct(city)) from customers;
-- 4. Select with WHERE Clause:
    select * from employees where department = "IT";
    -- select bookName from book where count(noOfPages) >200;  -- error: we can't use aggregate funcition in where clause.
-- Other:
    select * from member where sex = 'Male' AND status = 'Active' ;
    select * from member where sex = 'Male' OR status='Active';
    select * from Book where cost <= 200;  -- >=  == 	!=
    select * from Book where cost between 100 and 200;
    
-- # Intermediate SELECT Queries:
-- Select with Order By Clause:
    select * from students Order By sname;  -- by default ascending
    select * from students Order By sname ASC;
    select * from students Order By sname DESC;
-- Select with Limit Clause:
    select * from employees LIMIT 10; -- return first 10 records.
    -- skip first 10 rows, then display next 5 rows(11-15)
    SELECT * FROM employees LIMIT 10, 5;  -- offset:10, Limit: 5
    -- skip first 5 rows, then display next 10 rows(6-15)
    SELECT * FROM employees LIMIT 10 OFFSET 5; -- display row 6-15, skip first 5 rows.
    -- 2nd highest salary:
        select Distinct salary from employee order by salary DESC limit 1,1;
        select Distinct salary from employee order by salary DESC limit 1 offset 1;
-- Select with Group By Clause:
    -- Group by statement is used to group the rows that have the same value. 
    -- The Group by clause is used in conjunction with the SELECT statement and Aggregate function to group rows together by commonn column values.
    select avg(salary) from employees;  -- return average salary count.
    -- select department, avg(salary) from employees; -- error: Group by clause required on department.
    select department, avg(salary) from employees GROUP BY department;
    select subject, count(salary) as salaryCount, avg(salary) as "salary Average" from Teacher group by subject;
    select dept_id, avg(salary) from employee group by dept_id;
    /*
    -> there is small difference between distinct and group by;
    - We can apply DISTINCT to remove the duplicate records
    - Select group by can be used to get data from different columns and group into one or more column. This can also be applied with aggregate function.
    - WHERE Clause use on top of SELECT FROM/ INSERT INTO/ UPDATE SET and HAVING used on top of GROUP BY.
    */

--# Advanced SELECT Queries:
-- Select with Having Clause:
    -- Having we use on top of "Group By", select where groupby having.
    -- We can not use WHERE clause on top of GROUP BY.
    -- In having, we can use aggregate function, but in where clause, we can not.
    -- used to filter groups of rows returned by a GROUP BY clause.
    -- It allows you to specify conditions on aggregated data, similar to the WHERE clause, but for groups rather than individual rows.
    SELECT department, AVG(salary) FROM employees GROUP BY department HAVING AVG(salary) > 50000;
    --  select dept_id, avg(salary) from employee group by dept_id where salary > 20000; We can not use WHERE clause on top of GROUP BY.
    select category, avg(bookPrice) from book group by category having avg(bookPrice)>500;
    select authorId, category, avg(bookPrice) from book group by category, authorId having avg(bookPrice)>500;
    select authorId, category, avg(bookPrice) from book where bookPrice>500 group by authorId, category having avg(bookPrice)>1000;
-- Select with Subquery:
    SELECT first_name, last_name FROM employees WHERE department_id IN (SELECT department_id FROM departments WHERE location = 'New York');
-- Select with Aggregate Functions:
    SELECT AVG(salary), MAX(age), MIN(sales) FROM employees;
    -- we can not use aggregate functions in where clause:
        -- SELECT AVG(salary) from employees WHERE MAX(age) = 20;   ...Wrong
-- Union: Show all unique records of both table without duplicates means all are unique.
    -- TABLE LEFT, TABLE RIGHT : ALL LEFT + ALL RIGHT - DUPLICATE(LEFT=RIGHT)
    -- NOTE: Both table must have same number of columns.
    select bookname from allbooks UNION select bookname from book;  -- show only unique book names of from both table
-- Union ALL: Show all records from both table no matter its duplicate or not.
    -- ALL LEFT + ALL RIGHT
    select bookname from allbooks UNION ALL select bookname from book;  -- show only unique book names of from both table
-- INTERSECTION:- (A intersection B) -- same as INNER JOIN
    -- only show non-distinct values. 
    -- only MATCHING records from both table.
    -- LEFT = RIGHT
    -- Show only the data from allbooks table which is present in Book table.
    select bookname from allbooks where bookname IN (select bookname from book);
--  Minus :- 	NOT IN :
    -- only NON MATCHING records from both table.
    -- LEFT != RIGHT   -- ALL LEFT + ALL RIGHT - REMOVE ALL(LEFT = RIGHT)
    select bookname from allbooks where bookname NOT IN (select bookname from book); 
    SELECT column_list FROM table1  
		LEFT JOIN table2 ON condition  
			WHERE table2.column_name IS NULL; 

/*
-- JOINS --
There are 6 types of joins:
1. Join / Inner Join: Returns records that have matching values in both tables
2. Outer Join:
    2.1 LEFT Join: Returns all records from the left table, and the matched records from the right table
    2.2 RIGHT Join: Returns all records from the right table, and the matched records from the left table.
    2.3 (Not In MYSQL, Its in SQL) Full Join: Full join returns all rows from both tables. Return all matching and non-matching records from both tables.
3. CROSS JOIN: Cross join returns the Cartesian product of the two tables, i.e., it combines each row of the first table with every row of the second table.
4. SELF JOIN: Self join is used to join a table to itself. It is helpful when you want to compare rows within the same table.
Note: Below employee structure and data queries are available in "tables-for-joins.sql" file.
*/
--1. Join / Inner Join / Perfect Join:
    -- Inner Join = Fetches matching records only.
        SELECT e.emp_name, d.dept_name FROM employee e JOIN department d ON e.dept_id = d.dept_id;
    -- below queries are same:
        select b.bookName, a.authorName from Book b INNER JOIN Author a ON b.authorId = a.authorId;
        select b.bookName, a.authorName from Book b JOIN Author a ON b.authorId = a.authorId;
        select b.bookName, a.authorName from Book b JOIN Author a where b.authorId = a.authorId;
        -- JOIN keyword is optional
        select b.bookName, a.authorName from book b, author a where a.authorId = b.authorId;
-- 2. Left OUTER Join:
    -- left join = inner join + any additional records in the left table.
        -- if on condition will fail, then right table columns will show null.
        SELECT e.emp_name, d.dept_name FROM employee e LEFT OUTER JOIN department d ON e.dept_id = d.dept_id;
        SELECT e.emp_name, d.dept_name FROM employee e LEFT JOIN department d ON e.dept_id = d.dept_id;
        select b.bookName, a.authorName from Book b LEFT JOIN Author a ON b.authorId = a.authorId;
-- 3. Right OUTER Join:
    -- right join = inner join + any additional records in the right table (left table columns will be null).
        -- if on condition will fail, then left table columns will show null .
        SELECT e.emp_name, d.dept_name FROM employee e RIGHT OUTER JOIN department d ON e.dept_id = d.dept_id;
        SELECT e.emp_name, d.dept_name FROM employee e RIGHT JOIN department d ON e.dept_id = d.dept_id;
        select b.bookName, a.authorName from Book b RIGHT JOIN Author a ON b.authorId = a.authorId;

-- Problem: Fetch details of ALL emp, their manager, their department and the projects they work on.
    select e.emp_name, m.manager_name, d.dept_name, p.project_name 
    from Employee e Join Manager m ON e.manager_id = m.manager_id 
    left Join department d ON e.dept_id = d.dept_id 
    left JOIN projects p ON e.emp_id = p.team_member_id;

/* 4. FULL OUTER Join: (Not In MySQL)
   -- Full Join = Inner Join 
                + any additional records in the left table (right table columns will be null) 
                + any additional records in the right table (left table columns will be null)
  -- MySQL: Left Join + UNION + Right Join.
*/  SELECT e.emp_name, d.dept_name FROM employee e LEFT JOIN department d ON e.dept_id = d.dept_id 
        UNION SELECT e.emp_name, d.dept_name FROM employee e RIGHT JOIN department d ON e.dept_id = d.dept_id;
    SELECT e.emp_name, d.dept_name FROM employee e LEFT JOIN department d ON e.dept_id = d.dept_id 
        UNION ALL SELECT e.emp_name, d.dept_name FROM employee e RIGHT JOIN department d ON e.dept_id = d.dept_id 
        where e.dept_id IS NULL;
  -- SQL:
    SELECT e.emp_name, d.dept_name FROM employee e FULL OUTER JOIN department d ON e.dept_id = d.dept_id;
    SELECT e.emp_name, d.dept_name FROM employee e FULL JOIN department d ON e.dept_id = d.dept_id;

-- 5. CROSS JOIN:
    -- returns cartesian project. : Employee->6records, Department->4records = Total 24 records. (each record match each other)
    -- Cross Join don't need "ON"
    -- Here, no ON check so, employee and department dont have relationships, it each record of employee will match to each record of department.
    SELECT e.emp_name, d.dept_name FROM employee e CROSS JOIN department d; 
    -- Here, ON Check, so work as INNER JOIN.
    SELECT e.emp_name, d.dept_name FROM employee e CROSS JOIN department d ON e.dept_id = d.department_id;
-- -------------
-- Problem 2: Write a query to fetch the employee name and their corresponding department name.
-- Also make sure to display the company name and the company location corresponding to each employee.
    select e.emp_name, d.dept_name, c.company_name, c.location 
        from Employee e LEFT JOIN Department d ON e.dept_id = d.dept_id 
        CROSS JOIN Company c;
    select e.emp_name, d.dept_name, c.company_name, c.location 
        from Employee e LEFT JOIN Department d ON e.dept_id = d.dept_id 
        CROSS JOIN Company c where c.company_id = "C001";

-- 6. Natural Joins
    -- Natural Join don't need "ON"
    -- If 2 tables, sharing the same column name -> then it will do INNER JOIN.
        -- eg. Employee: dept_id, Department: dept_id
        SELECT e.emp_name, d.dept_name FROM employee e NATURAL JOIN department d;
    -- If 2 tables, sharing the different column name -> then it will do CROSS JOIN.
        -- eg. Employee: dept_id, Department: department_id
        alter table department rename column dept_id to department_id;
        SELECT e.emp_name, d.dept_name FROM employee e NATURAL JOIN department d;
    -- control goes to sql to choose the column, where the join should happen. So using natural joins are highly not recommended.

-- 7. self Join:
  -- there is no keyword as SELF.
  -- When we do join table to itself, then this called as self join.
  -- Problem: Write a query to fetch the child name and their age corresponding to their parent name and parent age.
  select child.name as child_name, child.age as child_age, parent.name as parent_name, parent.age as parent_age 
  from family as child JOIN family as parent 
  ON child.parent_id=parent.member_id;


/* =============================================
============= TCL =============
5. TCL (Transaction Control Language):
    - used for managing transactions within the database.
    1. COMMIT: Saves changes made during a transaction.
    2. ROLLBACK: Undoes changes made during a transaction.
    3. SAVEPOINT: Sets a point within a transaction to which you can later roll back.
    4. SET TRANSACTION: Specifies characteristics for the transaction.
    5. RELEASE SAVEPOINT: Releases a previously defined savepoint within a transaction.
    
*/
-- by default, Session is always in "Auto-Commite Mode".
    select @@autocommit; -- by default "1"
-- Practical 1:
    SET AUTOCOMMIT = 0; -- To run into Manual Mode.

    SAVEPOINT ONE;

	insert into author values('A011','Vikram','UK','Male'); 
	UPDATE author set nationality ='Rus'  where authorid = 'A011';
	SAVEPOINT TWO;

	UPDATE author set nationality ='USA'  where authorid = 'A011';	
	SAVEPOINT THREE;
	
    UPDATE author set nationality ='IND'  where authorid = 'A011';	
	SAVEPOINT FOUR;

	UPDATE author set nationality ='India'  where authorid = 'A011';
 	
	ROLLBACK TO THREE; -- UNDO query after the SAVEPOINT THREE
	-- ROLLBACK TO TWO;
    RELEASE SAVEPOINT TWO; -- Remove savepoint. Not queries.
	ROLLBACK TO ONE;
	Commit;  //permanently save all data 
-- Practical 2:
    START TRANSACTION;
    insert into family (member_id) values ("M1");
    insert into family (member_id) values ("M2");
    COMMIT; -- commited M1 and M2

    START TRANSACTION;
    insert into family (member_id) values ("M3");
    ROLLBACK; -- RollBack M3

    START TRANSACTION;
    insert into family (member_id) values ("M4");
    
    START TRANSACTION; -- auto commited M4
    insert into family (member_id) values ("M5");
    ROLLBACK; -- Rollback M5
    ROLLBACK; -- Nothing to rollback.

/* SET TRANSACTION:
    - used to specify characteristics for the current transaction. 
    - However, it's important to note that MySQL does not fully support the SET TRANSACTION statement with a wide range of transaction characteristics as some other database systems do.
    - MySQL primarily supports setting transaction isolation levels using the SET TRANSACTION statement. 
    - SET TRANSACTION ISOLATION LEVEL isolation_level;
    - Where isolation_level can be one of the following:
      1. READ UNCOMMITTED: Allows dirty reads, meaning a transaction may read data that has been modified by other transactions but not yet committed.
      2. READ COMMITTED: Prevents dirty reads by allowing a transaction to read only committed data.
      3. REPEATABLE READ: Ensures that a transaction can repeat the same read operation and retrieve the same data, even if other transactions modify the data in the meantime.
      4. SERIALIZABLE: Provides the highest level of isolation, ensuring that transactions are executed in a serializable manner, preventing phantom reads, non-repeatable reads, and dirty reads.
*/
    SET TRANSACTION ISOLATION LEVEL isolation_level;




/*
===============================================
-- How to Create a User?
    CREATE USER 'username'@'hostname' IDENTIFIED BY 'password';
    - 'username': This is the username you want to create.
    - 'hostname': This is the hostname from which the user will connect. 
        For example, 'localhost' means the user can only connect from the local machine,
        while '%' means the user can connect from any host.
    - 'password': This is the password for the user.
Basic User Creation:
    - This command creates a user named 'myuser' who can connect only from the local machine ('localhost') with the password 'mypassword'.
    -- After creating the user, you may need to grant privileges to the user to allow them to perform specific actions on databases and tables. This is typically done using the GRANT statement.
*/
-- Show current user:
    select user();
    select current_user();
-- Show all list of users:
    select user, host from mysql.user;
-- 1. Basic User Creation:
    create user 'user3'@'localhost';
    CREATE USER 'myuser'@'localhost' IDENTIFIED BY 'mypassword';
-- 2. Create User with No Password:
    CREATE USER 'username'@'hostname' IDENTIFIED BY '';
-- 3. Create User with SSL Requirements:
    CREATE USER 'username'@'hostname' REQUIRE SSL;
-- 4. Create User with SSL and X.509 Certificate Requirements:
    CREATE USER 'username'@'hostname' REQUIRE X509;
-- 5. Create User with Specific Resource Limits:
    CREATE USER 'username'@'hostname' WITH MAX_QUERIES_PER_HOUR 100 MAX_UPDATES_PER_HOUR 10;
    -- This creates a user with specific resource limits for queries and updates per hour.
-- 6. Create User with Account Expiry:
    CREATE USER 'username'@'hostname' WITH PASSWORD EXPIRE;
    -- This creates a user whose password will expire. You can also specify PASSWORD EXPIRE NEVER to disable password expiry.
-- 7. Create User with Password Expiry Policy:
    CREATE USER 'username'@'hostname' PASSWORD EXPIRE INTERVAL 90 DAY;
    -- This creates a user with a password expiry policy. Passwords must be changed within the specified interval.

-- ------------
-- Some other common commands related to user:
-- 1. DROP USER: Removes one or more existing database users.
    DROP USER 'username'@'hostname';
-- 2. ALTER USER: Modifies the properties of an existing user, such as the password or account lock status.
    ALTER USER 'username'@'hostname' IDENTIFIED BY 'new_password';
    ALTER USER 'username'@'hostname' Password expire;
    ALTER USER 'username'@'hostname' account lock;
-- 3. RENAME USER: Renames an existing user account to a new name.
    RENAME USER 'old_username'@'hostname' TO 'new_username'@'hostname';
-- 4. GRANT: Grants specific privileges to a user or group of users.
    GRANT SELECT, INSERT ON database.* TO 'username'@'hostname';
-- 5. REVOKE: Revokes previously granted privileges from a user or group of users.
    REVOKE SELECT, INSERT ON database.* FROM 'username'@'hostname';
-- 7. FLUSH PRIVILEGES: Reloads the grant tables in the MySQL server, applying any changes made using the GRANT or REVOKE statements.
    FLUSH PRIVILEGES;
-- 8. SHOW GRANTS: Displays the privileges granted to a specific user or group of users.
    SHOW GRANTS FOR 'username'@'hostname';
-- 9. SHOW GRANTS FOR CURRENT_USER(): Displays the privileges granted to the currently authenticated user.
    SHOW GRANTS FOR CURRENT_USER();
/*
===============================================
DCL (Data Control Language):
    - privilege: privilege is a permission given by DBA.
    - Privilege can be divided into 2 parts:
        1. System privileges : DDL
        2. Object Privileges : DQL, DML
    - Used for controlling access to data within the database.
    - MySQL provides 2 DCL commands to manupulate the privileges.
        1. GRANT: Gives specific privileges to users or roles.
        2. REVOKE: Removes specific privileges from users or roles.
    In Grant ALL, Truncate is not available.
What are the privileges are granted in GRANT ALL ?
-> In MySQL, the GRANT ALL command is used to grant all available privileges on a database or table to a user. 
    SELECT: Permission to read data from tables.
    INSERT: Permission to insert new rows into tables.
    UPDATE: Permission to modify existing rows in tables.
    DELETE: Permission to delete rows from tables.
    CREATE: Permission to create new databases and tables.
    DROP: Permission to drop (delete) existing databases and tables.
    ALTER: Permission to alter the structure of existing tables (e.g., add or drop columns).
    INDEX: Permission to create and drop indexes on tables.
    CREATE TEMPORARY TABLES: Permission to create temporary tables.
    CREATE VIEW: Permission to create views based on queries.
    SHOW VIEW: Permission to show the CREATE VIEW statement for views.
    CREATE ROUTINE: Permission to create stored procedures and functions.
    ALTER ROUTINE: Permission to alter existing stored procedures and functions.
    EXECUTE: Permission to execute stored procedures and functions.
    CREATE USER, RELOAD, PROCESS, REFERENCES: These are administrative privileges and are typically only granted to administrative users for managing the MySQL server and user accounts.
*/
-- GRANTS: (ON, TO)
    USE database_name; GRANT SELECT ON table_name TO 'username'@'hostname';
    GRANT ALL PRIVILEGES ON database_name.* TO 'username'@'hostname';
    GRANT ALL ON database_name.* TO 'username'@'hostname';
    GRANT SELECT, INSERT, UPDATE, DELETE ON database_name.* TO 'username'@'hostname';
    grant select, update(name,salary) ON mydb.emp TO 'u1'@'localhost';
        --  update emp set emp_name = "sagar", salary=333333., dept_id="D3" where emp_id = 1;
    GRANT EXECUTE ON PROCEDURE procedure_name TO 'username'@'hostname';
    GRANT CREATE, DROP ON database_name.* TO 'username'@'hostname';
    GRANT ALTER, CREATE VIEW ON database_name.* TO 'username'@'hostname';
    GRANT USAGE ON *.* TO 'username'@'hostname';
    -- GRANT OPTION: This command allows the user to grant privileges to other users. 
    -- It is often used in combination with other privileges to delegate the ability to grant access to databases or tables.
    GRANT SELECT ON database_name.* TO 'username'@'hostname' WITH GRANT OPTION;

-- REVOKE:  (ON, FROM)
    REVOKE ALL PRIVILEGES ON *.* FROM 'username'@'hostname';
    REVOKE SELECT, INSERT, UPDATE, DELETE ON database_name.* FROM 'username'@'hostname';
    REVOKE EXECUTE ON PROCEDURE procedure_name FROM 'username'@'hostname';
    REVOKE CREATE, DROP ON database_name.* FROM 'username'@'hostname';
    REVOKE ALTER, CREATE VIEW ON database_name.* FROM 'username'@'hostname';
    REVOKE USAGE ON *.* FROM 'username'@'hostname';
    REVOKE GRANT OPTION ON database_name.* FROM 'username'@'hostname';

/* ===================================================================
Views:
    - In SQL, a view is a virtual table based on the result-set of an SQL statement.
    - A view contains rows and columns, just like a real table. The fields in a view are fields from one or more real tables in the database.
    Advantages:
        - simplify complex query. (create view from joins and show all necessary data in single table.)
        - provide extra layer of security.  (we can skip password of employee and show employee details to other user's.)
    Disadvantages:
        - Performance decreases.
        - Dependency on table. (View dont have its own storage. Every time it will query table and show latest data.)
*/
-- show all view and tables views:
    show tables;
    show full tables;
-- create view:
    CREATE VIEW vemployee AS select emp_name from employee;
    CREATE OR REPLACE VIEW vemployee2 AS select emp_id, emp_name from employee;     -- AS is mandatory
    CREATE VIEW `employee department` AS SELECT e.emp_name, d.dept_name FROM employee e JOIN department d ON e.dept_id = d.dept_id;
    select * from `employee names`;
-- change view:
    ALTER VIEW vemployee AS select emp_id, emp_name, salary from employee;
    CREATE OR REPLACE VIEW vemployee AS select emp_id, emp_name from employee;
-- Renave view name:
    RENAME TABLE vemployee TO vemp;
-- Drop View:
    drop view vemployee3;
    drop view vemployee2, vemp;
-- Practical:
    insert into vemployee values ("E7","Yogesh");
    insert into employee (emp_id, emp_name, salary, dept_id, manager_id) values ("E8","Nilesh", 12345, "D2", "M2");
    -- Note: both E7 and E8 record will reflect in employee table and view vemployee table.
    -- Its proves that, Vies is just a representation, its dont have own storage, every time it will query its parent tables.
    Drop view vbook;

/* ==================================================================
-- Temporary Table:
    - A special type of table that allows you to store a temporary result set.
	   which you can reuse several times in a SINGLE SESSION.
    - These tables will get automatcally removed when the session ends.
    - Temporary table has its own storage.
    - If changes made in Temporary Table, it only reflect to Temporary table, wont reflect on Base table like view.
*/
-- Creating a Local Temporary Table:
    CREATE TEMPORARY TABLE temp_table_name (
        column1 datatype1,
        column2 datatype2,
        INDEX tIndex (column1)
    );
-- Create only structure, not data from other table:
    CREATE TEMPORARY TABLE tEmployee LIKE employee; 
    desc tEmployee;
-- Create only structure and data from other table:
    CREATE TEMPORARY TABLE tEmployee2 AS select emp_id, emp_name from employee; -- AS is not mandatory
    CREATE TEMPORARY TABLE tEmployee select emp_id, emp_name from employee;
    select * from tEmployee;
-- DROP:
    DROP TEMPORARY TABLE tEmployee; -- This is safe to use, if wont delete Table or view. 
    DROP TEMPORARY TABLE IF EXISTS tEmployee2;  -- No error, if present then delete
    DROP TABLE tEmployee2;
-- we cant see the table using (show full tables;)  
    SELECT * FROM INFORMATION_SCHEMA.INNODB_TEMP_TABLE_INFO\G
-- Practical:
    -- Session 1
    CREATE TEMPORARY TABLE local_temp_table (
        id INT,
        name VARCHAR(50)
    );
    INSERT INTO local_temp_table (id, name) VALUES (1, 'John'), (2, 'Alice');
    -- Query the temporary table
    SELECT * FROM local_temp_table; -- Returns data
    -- End of Session 1
    -- The local_temp_table is automatically dropped

/*
__________
Variable :
---------- 
    -  Variables are used to store data temporarily during the execution of SQL statements or stored procedures. 
    - Type of Variables:
        1. User-Defined Variables:
            - User-defined variables in MySQL start with the '@' symbol.
            - They are local to the session in which they are created.
        2. Local Variable:
            - The local variable is a strongly typed variable.
            - The scope of the local variable is in a stored program block in which it is declared.
            - MySQL uses the DECLARE keyword to specify the local variable. The DECLARE statement also combines a DEFAULT clause to provide a default value to a variable. 
            - initial value is NULL. 
            - It is mainly used in the stored procedure program.
            - eg:
        3. System Variables:
            - System variables are predefined by MySQL and control various aspects of the MySQL server's behavior. 
            - They are accessed using the @@ symbol.
        4. Function Variables:
            declare Joindate date;
            declare name varchar(50)
	Types of variables :
		- @Session Variable
		- Local variable :
			DECLARE variable_name datatype.
		- Input Variable	:
			IN variable_name datatype
		- Output Variable	: 
			OUT variable_name datatype
INTO :
------
	- The SELECT INTO statement copies data from one table into a new table or variable
	- select authorname INTO @ANAME from author where authorid ='A002';
	- SELECT * INTO CustomersBackupTable FROM CustomersTable;
	- INSERT INTO Customers (CustomerName, City, Country) SELECT SupplierName, City, Country FROM Suppliers;
*/
-- 1. User-Defined Variables:
    SET @num := 10;
    SELECT @num * 5;
    select max(salary) INTO @sal from employee;
    select @sal:=max(salary) INTO @SAL from employee;
    select * from employee where salary =@sal;
-- 2. Local Variable:
    DELIMITER //
    Create Procedure Test()  
    BEGIN  
        DECLARE A INT DEFAULT 100;  
        DECLARE B INT;  
        DECLARE C INT;  
        DECLARE D INT;  
        DECLARE E INT;          -- NULLL
        DECLARE F VARCHAR(20);  -- NULL
        SET B = 90;  
        SET C = 45;  
        SET D = A + B - C;  
        SELECT A, B, C, D, E, F;  
    END //  
    DELIMITER ;
    CALL Test();
    DROP PROCEDURE Test;
-- 3. System Variables:
    SHOW VARIABLES; -- check all the list of system variables pressent in database;
    show variables LIKE '%thread%';
    select @@thread_cache_size;

/* ===================================
FUNCTION :
- Function retun only one column of only one row. ie function returns only 1 value.
MySQL has many built-in functions.
There are 2 types:
1. Single Row Functions
	- Work on each individual row of a table and return result for each row.
	- For example, ucase(), round(), sqrt(), mode() etc. are single row functions.
2. Multiple Row (Aggregate) functions:
	- Work on multiple rows together and return a summary result for a group of rows.
	- For eg: sum(), avg(), count(), min()

	-> Function retun only one column value of a row.
	-> When to use...
		-> When multiple client applications are written in different language or
		   work on different platforms, but need to perform the same database operations.
*/
-- 1. Single Row Functions:
-- 1.1 String functions.
-- length():
    SELECT LENGTH("SQL Tutorial") AS LengthOfString;
-- char()
    select char(65); => 0x41		
    select char(65 using ASCII); -- 'A'  [65-90 A-Z] [97-122 a-z]
    select char(70,65,90 using ASCII); -- FAZ
-- concat
    select concat(name, " ", lastname) as "Full Name" from student; -- "Sagar Patil"
    select concat(concat(name, " is from "), city) from student; -- Sagar is from Nandurbar
-- lower/lcase
    select lower(name) from student;
    select lcase(name) from student;
-- upper/ucase
    select upper(name) from student;
    select ucase(name) from student;
-- substr(data, startIndex, stringLength):
    select substr('computer', 4,3);  -- put
    select substr(name, 4,3) from student;  -- put
-- trim: 
    select trim('   spaces   '); -- 'spaces'
    select ltrim('   spaces   '); -- 'spaces   '
    select rtrim('   spaces   '); -- '   spaces'
-- instr(data, searchData);
    select instr('computer', "mp"); -- 3
    select (name, "gar") from student;  -- sagar 3  -- Nilesh 0
-- length
    select length("Sagar"); -- 5
-- left/right
    select left("Sagar"); -- Sa
    select right("Sagar"); -- ar
-- mid
    select mid("Computer", 4,3); -- put

-- 1.2 Numeric functions:
    select mod(5,3);    -- modules => reminder 2

    select pow(2,6);    -- 2^6 = 64
    select power(2,6);    -- 2^6 = 64

    select round(15.195, 1); -- 15.2
    select round(15.195,-1); -- 20
    select round(13.195,-1); -- 10

    select sign(15); -- 1
    select sign(0); -- 0
    select sign(-15); -- -1

    select sqrt(25); -- 5

    select truncate(15.195, 1); -- 15.1 -- take 1 value after point.
    select truncate(15.195, 2); -- 15.19

-- 1.3 Time and Date"
    select curdate(); -- 2024-02-29
    select current_date(); -- 2024-02-29
    select current_date; -- 2024-02-29

    select now(); -- 2024-02-29 17:05:39
    select sysdate(); -- 2024-02-29 17:05:39
    -- difference between now and sysdate();
    select now(), sleep(5), now(); -- 2024-02-29 17:05:00  | 0 | 2024-02-29 17:05:00
    select sysdate(), sleep(5), sysdate(); -- 2024-02-29 17:05:00  | 0 | 2024-02-29 17:05:05

    select date('2024-02-29 17:05:39'); -- 2024-02-29
    select month('2024-02-29 17:05:39'); -- 2
    select monthname('2024-02-29 17:05:39'); -- February
    select day('2024-02-29 17:05:39'); -- 29
    select dayname('2024-02-29 17:05:39'); -- Thusday
    select year('2024-02-29 17:05:39'); -- 2024

    select dayofmonth('2024-02-29 17:05:39'); -- 18
    select dayofweek('2024-02-29 17:05:39'); -- 5  -- week starts from sunday
    select dayofyear('2024-02-29 17:05:39'); -- 60  -- week starts from sunday
    
-- ================================
-- CUSTOM FUNCTIONS:
-- Show list of functions and procedures:
    -- ROUTINE means Function and Procedure.
    SELECT ROUTINE_NAME, ROUTINE_TYPE FROM INFORMATION_SCHEMA.ROUTINES;
    SELECT ROUTINE_NAME, ROUTINE_TYPE FROM INFORMATION_SCHEMA.ROUTINES 
	    where ROUTINE_TYPE = 'FUNCTION' AND ROUTINE_SCHEMA = 'database_name';
-- 1. ParameterLess FUNCTION?
    DELIMITER $
    CREATE FUNCTION getFirstEmp()
    RETURNS varchar(50)
    BEGIN
        return (select emp_name from employee where emp_id="E1");
    END $
    DELIMITER ;
-- ERROR: This function has none of DETERMINISTIC, NO SQL, or READS SQL DATA in its declaration and binary logging is enabled (you *might* want to use the less safe log_bin_trust_function_creators variable)
-- Solution 1:
    SET GLOBAL log_bin_trust_function_creators = 1;
-- Solutionn 2:
    DELIMITER $
    CREATE FUNCTION getFirstEmp() RETURNS varchar(50)
    DETERMINISTIC NO SQL READS SQL DATA
    BEGIN
        return (select emp_name from employee where emp_id="E1");
    END $  
    DELIMITER ;

    -- -----------------------
    DELIMITER $
    CREATE FUNCTION emp_name_max_salary()
    RETURNS varchar(50)
    BEGIN
        DECLARE e_max INT;
        DECLARE e_name varchar(50);

        select MAX(salary) INTO e_max from employee;
        select emp_name INTO e_name from employee where salary = e_max;
        return e_name;
    END $
    DELIMITER ;
    -- call:
    select 'Max salary employee Name: ' + emp_name_max_salary();
    drop function emp_name_max_salary;

-- parameterized function:
    DELIMITER #
    CREATE FUNCTION getEmpName(empId varchar(20)) RETURNS varchar(50)
    BEGIN
        return (select emp_name from employee where emp_id= empId);
    END #
    DELIMITER ;
    select getEmpName('E3');



/* =========================================================
Strored Procedure:
    - A stored procedure in MySQL is a set of SQL statements that are stored in the database server 
        and can be called and executed repeatedly by client applications or other stored procedures.
    - It's stores in database catalog. Information_schema is an online catalog.
    - Required less Execution Time.Its decides on
			-> 1. Internet Speed, 2. Load on Server, 3. Progamming Structure. 
    - A stored procedure can be invoked by...
        -> CALL Keyword
        -> Triggers
        -> Other stored procedure
        -> Applications such as Java , Python , PHP
    - when a query triggered then it goes to 
			->Front end ->Back End -> Server -> Server resolve it -> Back end ->Front End.
            - above process will repeate in each query. In procedure, will gather all the queries and send to the server and reduces the time.
    - Stored procedures offer several advantages, including improved performance, code modularity, and enhanced security.
    - Stored Procedure did not retun any parameters. We can take the output in the form of parameters
    - Parameters are variables that allow you to pass values into the procedure when it is called
    - There are three types of parameters commonly used in stored procedures:
    1. IN Parameters: 
        - IN parameters are used to pass values into the stored procedure. 
        - They are read-only within the procedure, meaning their values cannot be modified by the procedure. 
        - IN parameters are typically used to provide input to the procedure.
    2. OUT Parameters: 
        - OUT parameters are used to return values from the stored procedure to the caller. 
        - They are declared in the parameter list with the OUT keyword. 
        - Inside the procedure, you can assign values to OUT parameters, which can then be accessed after the procedure call.
    3. INOUT Parameters: 
        - INOUT parameters are a combination of IN and OUT parameters. 
        - They allow passing values into the procedure and returning modified values back to the caller. 
        - INOUT parameters are specified with the INOUT keyword.
*/
-- 1. Creating a Stored Procedure:
    DELIMITER //
    CREATE PROCEDURE GetEmployeesByDepartment(IN department_id varchar(20), INOUT num INT, OUT emp_id INT)
    BEGIN
        SELECT * FROM employee WHERE dept_id = department_id;
        SET num = num + 1;
        SELECT COUNT(*) INTO emp_id FROM employee;
    END //
    DELIMITER ;
-- 2. Calling the Stored Procedure:
    SET @myCounter = 1;
    CALL GetEmployeesByDepartment("D1", @myCounter, @myCount);
    SELECT @myCounter, @myCount;
-- 3. Dropping a Stored Procedure:
    DROP PROCEDURE IF EXISTS GetEmployeesByDepartment;

-- Practice:
    -- ----
    CREATE PROCEDURE TransferFunds(IN sender_id INT, IN recipient_id INT, IN amount DECIMAL(10, 2))
    BEGIN
        START TRANSACTION;
        UPDATE accounts SET balance = balance - amount WHERE account_id = sender_id;
        UPDATE accounts SET balance = balance + amount WHERE account_id = recipient_id;
        COMMIT;
    END;
    CALL TransferFunds(01,02, 5000);

    -- ---- 
    -- DECIMAL(10,2);   -- 12345678.12  -- Total size 10 num, and after .2
    DELIMITER //
    ALTER PROCEDURE GetEmployeeSalary(IN empId varchar(20))
    BEGIN
        DECLARE salary DECIMAL(10,2);   -- 12345678.12  -- Total size 10 num, and after .2
        
        SELECT salary INTO salary FROM employee WHERE emp_id = empId;
        
        IF salary > 50000 THEN
            SELECT 'High Salary' AS message;
        ELSE
            SELECT 'Low Salary' AS message;
        END IF;
    END //
    DELIMITER ;
    CALL GetEmployeeSalary("E1");

    -- -------
    Delimiter /
    Create Procedure useSwitch( IN id varchar(10) , OUT mbrsts varchar(30))
    BEGIN 
        DECLARE yr int (10);
        SELECT year(doj) into yr from member where memberid = id;

        CASE
            WHEN yr>=2021 THEN SET mbrsts='Latest Member';
            WHEN yr=2020 THEN SET mbrsts='Last Year Member';		
            WHEN yr < 2020 THEN SET mbrsts='Old Member';		
        End case;
    END /
    Delimiter ;
-- Call : 
    CALL useSwitch('M005',@sts);
    Select @sts;


/* =========================================================
TRIGGERS:
    - Triggers implement Domain Integrity.
    - Triggers are database objects in MySQL that are associated with a table and 
      automatically perform an action when a certain event occurs on the table.
    - These events can be INSERT, UPDATE, or DELETE operations.
    - Its a Special Stored Procedure. Special because it can not called directly like a normal stored procedure.
    - It is called automatically when a data modification event is made against a table.
    - It called automatically when me fire insert/update/delete query.
    - We have 2 Keywords :
			1. OLD : OLD object for old table.
			2. NEW : NEW object for NEW table.
    - And we have 6 operations :
			- Before INSERT		- After INSERT
			- Before UPDATE		- After UPDATE
			- Before DELETE		- After DELETE
    - Trigger event have 2 special keywords.
        1. NEW:
            -  Refers to the new values of columns being affected by an INSERT or UPDATE operation.
            - INSERT / UPDATE
        2. OLD:
            - Refers to the old values of columns being affected by an UPDATE or DELETE operation.
            - DELETE / UPDATE
    - MySQL ERRORS:
        SIGNAL SQLSTATE 'Error_Message_No' SET MESSAGE_TEXT = 'Error Message';
        - SQLSTATE is error code. Index number 45000 to 45010 are kept for user access.
Syntax:
    CREATE TRIGGER trigger_name
    {BEFORE | AFTER} {INSERT | UPDATE | DELETE}
    ON table_name
    FOR EACH ROW
    BEGIN
        -- Trigger action
    END;
*/
-- DROP:
    DROP TRIGGER IF EXISTS trigger_name;
-- SHOW:
    SHOW TRIGGERS;
    SHOW TRIGGERS IN database_name;
    SELECT Trigger_NAME, EVENT_OBJECT_TABLE FROM INFORMATION_SCHEMA.triggers where INFORMATION_SCHEMA.triggers.TRIGGER_SCHEMA LIKE '%classroom%' ;

-- RETURN ERROR Message.
CREATE TRIGGER prevent_duplicate_emails
BEFORE INSERT ON users
FOR EACH ROW
BEGIN
    IF EXISTS (SELECT * FROM users WHERE email = NEW.email) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Email must be unique';
    END IF;
END;

---------- Practice on each type: ----------
-- Q. If someone date of join of employee out of year 1990-2020. Give proper error messages.
-- Setup:
    CREATE TABLE Numbers (num int);
    INSERT INTO Numbers values (10);
    CREATE TABLE NumbersBackup (no int);
    -- CREATE TABLE audit_log (event_type varchar(20), event_description varchar(50), event_timestamp date);
    -- INSERT INTO audit_log (event_type, event_description, event_timestamp) VALUES ('INSERT', CONCAT('New employee added: ', NEW.employee_name), NOW());
-- A. BEFORE:
-- A1. BEFORE INSERT:
-- Q. Employee joining data should not be in future.
    delimiter /
    create trigger checkyr 
    before insert ON Employee
    for each row
    BEGIN
        DECLARE greaterMsg varchar(100);
        SET greaterMsg = concat("Sorry.. Year can't be greater than: ", now() );
        if year(new.doj) > year(now()) then
            SIGNAL SQLSTATE '45001'	SET MESSAGE_TEXT = greaterMsg;

        Elseif year(new.doj) < 1990 then
            SIGNAL SQLSTATE '45002' SET MESSAGE_TEXT = "Sorry.. Year can't be less than 1900";				
        end if;
    end /
    delimiter ;
    insert into employee values ("E8", "Sagar", 145000, "2028-02-14", "D4","M2");
    insert into employee values ("E9", "Sagar", 145000, "1989-02-14", "D4","M2");

-- Q1) Number should be between 0 to 100;
    DELIMITER $
    CREATE TRIGGER roundOffNumbersIn100
    BEFORE INSERT
    ON Numbers
    FOR EACH ROW
    BEGIN
        IF NEW.num > 100 THEN 
            SET NEW.num = 100;
        ELSEIF NEW.num < 0 THEN
            SET NEW.Num = 0;
        END IF;
    END $
    DELIMITER ;
-- TEST:
    INSERT INTO Numbers values(120);	//100
	INSERT INTO Numbers values(-20);	//0

-- A2. BEFORE Delete:
-- Create a backup, if record is going to delete.
    DELIMITER //
    CREATE TRIGGER backupUpdatedData
    BEFORE DELETE
    ON Numbers
    FOR EACH ROW
    BEGIN 
        insert into NumbersBackup values (OLD.num);
    END //
    DELIMITER ;
-- TEST:
    delete from employee where emp_id = "E1";

-- New updated salary of employee will be always greater than current salary, else retun SQL error.
    DELIMITER //
    CREATE TRIGGER UpdateEmpSalary BEFORE UPDATE
    ON Employee FOR EACH ROW
    BEGIN
        IF NEW.salary < OLD.salary THEN 
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'New salary cannot be less than current salary.';
        END IF;
    END //
    DELIMITER ;
    update employee set salary = 10000 where emp_id = "E1";
    -- ERROR 1644 (45000): New salary cannot be less than current salary


-- --------TRIGGER + PROCEDURE ---------
-- Q. Call procedure inside function.
-- > PROCEDURE:
    delimiter /
    create Procedure checkYear(IN yr int(10))
    BEGIN
        DECLARE greaterMsg varchar(100);
        SET greaterMsg = concat("Sorry.. Year can't be greater than: ", now() );

        if yr > year(now()) then
            SIGNAL SQLSTATE '45000'	SET MESSAGE_TEXT = checkYear;
        ElseIf yr < 1990 then
            SIGNAL SQLSTATE '45001' SET MESSAGE_TEXT = 'Sorry.. Year cant be less than 1900';
        end if;
    end /

    Create trigger checkYrInsert 
    before insert 
    on Employee for each row
    Begin
        Call CheckYear(year(new.doj));  -- procedure call
    End /

    delimiter ;


/*
TODO:
    1. While DO
    2. Repeat
    3. LOOP
    4. Cursor
*/


-- ================ END ==========