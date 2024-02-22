-- Login:
	-- mysql -u root -p

-- Databases:
	show databases;
    create database myDb;
    use myDb;
    drop database myDb; -- delete DB;

-- tables:
	show tables;	-- only tables
    show full tables; -- tables , temporary table , views
	
    describe book;	-- show structure of book
    desc book;
-- SHOW ----
    SHOW databases;
    show tables;
    show COLUMNS from book; -- same as: desc book;
    show INDEX from my_table;
    show CREATE TABLE my_table;
    show GRANTS FOR 'user'@'localhost';
    show VARIABLES;
    show STATUS;
    show PROCESSLIST;
    show table status LIKE "book"; -- internal details of table;
/*
    
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
    - sed for managing transactions within the database.
        COMMIT: Saves changes made during a transaction.
        ROLLBACK: Undoes changes made during a transaction.
        SAVEPOINT: Sets a point within a transaction to which you can later roll back.
        SET TRANSACTION: Specifies characteristics for the transaction.

============= 1. DDL =============
1. Create, 2. Alter, 3. Drop 4. Truncate
DDL 1. Create:
    Create Table, create Index, create View, create Trigger
*/
create table Book (
	bookId varchar(10) not null,
    bookName varchar(255) not null default 'No Book',
    bookCode varchar(10) UNIQUE,
    authorId varchar(10) not null,
    rackId varchar(10),
    category varchar(20),
    bookPrice Double,
    noOfPages int,
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

create table Books As select * from book;
/*
-----------------------
DDL 2. Alter:
    used to change the structure of the table.
    - Clause: 2.1 Add, 2.2 Modify, 2.3 Drop, 2.4 Change, 2.5 Rename To */
    create Primary Key
        add PK
        alter table newBook add Primary Key (bookId);
        alter table newBook modify bookId varchar(10) primary key;
        alter table newBook2 add bookId int primary key; -- add new column
        -- remove PK
        alter table newBook drop primary key;  -- only delete PK on bookId, not deleting the field.
        alter table book2 drop bookId; -- delete PK and field both
        -- alter table newBook modify bookId varchar(10) -- this will not remove PK.
    -- create foreign key.
        alter table newBook add foreign key (authorId) references author (authorId);
        alter table newBook add constraint Foreign Key (rackId) references Rack(RackId);

    -- Add Column:
        alter table rack add Column rackFloor int(10);
        alter table rack add rackFloor int(10);
        alter table Rack add rackName varchar(20) after rackId;
    -- Modify Column:
        alter table Rack Modify Column rackFloor varchar(20);
        alter table Rack Modify rackFloor varchar(20);
        alter table Rack Modify rackFloor varchar(22) not null; -- before applying not null, make sure rackFloor must be present in all existing records.
        alter table Rack Modify rackFloor varchar(20) after bookName;
        alter table Rack modify rackName varchar(20) after rackCapacity;

    -- Drop Column:
        alter table rack Drop Column rackFloor;
        alter table rack Drop rackFloor;
    
    -- Change: rename column name:
        alter table department RENAME COLUMN dept_id to department_id;
        alter table rack change rackFloor rackFloorNo int(20);
        alter table rack change column rackFloor rackFloorNo int(20);
    
    -- Rename: rename table name:
        alter table book1 Rename To book2;

-- DDL 5. Rename:
        Rename table newBook to newBook1;   -- only one command.
-- DDL 3. Drop:
    -- used to delete existing database objects such as tables, indexes, views, or databases themselves.
    DROP DATABASE database_name;
    DROP TABLE table_name;
    DROP INDEX index_name ON table_name;
    DROP VIEW view_name;
    DROP TRIGGER trigger_name;
    DROP FUNCTION function_name;
    DROP PROCEDURE procedure_name;

/* DDL 4. Truncate:
    use to delete all data but not the structure.
    Once it delete , we cant rollback.
    TRUNCATE is faster than DELETE.
    TRUNCATE does not return the number of rows.
    Truncate bypasses contraints and triggers.
    Note on delete mechanism: it will drop the table and create a new structure, so deletion in TRUNCATE is faster than DELETE query.
*/  TRUNCATE TABLE newbook;
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
    insert into student_archive (sId, sName) select sId, sName from students where sMarks > 40;
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
    UPDATE employees SET department = 'HR' WHERE employee_id IN (SELECT employee_id FROM new_hires);

/*
-------------------
DML 3: DELETE ... FROM:
    - used to remove rows from a table based on specified conditions.
DELETE FROM table_name
    WHERE condition;
*/
-- 1. Delete All Rows:
    delete from Students;
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
    select * from employees;
-- 2. Select Specific Columns from a Table:
    SELECT first_name, last_name FROM employees;
    SELECT first_name AS "first name", last_name AS lastName FROM employees; -- AS, display column name last_name as lastName
-- 3. Select Distinct Values from a Column:
    select Distinct departments from employees; -- return unique departments.
-- 4. Select with WHERE Clause:
    select * from employees where department = "IT";
    -- select bookName from book where count(noOfPages) >200;  -- error: we can't use aggregate funcition in where clause.

-- # Intermediate SELECT Queries:
-- Select with Order By Clause:
    select * from students Order By sname;  -- by default ascending
    select * from students Order By sname ASC;
    select * from students Order By sname DESC;
-- Select with Limit Clause:
    select * from employees LIMIT 10; -- return first 10 records.
    SELECT * FROM employees LIMIT 10 OFFSET 5; -- display row 6-15, skip first 5 rows.
    SELECT * FROM employees LIMIT 10, 5;  -- LIMIT row_count, offset_value. (rows: 6-15).
-- Select with Group By Clause:
    select avg(salary) from employees; -- no error
    select department, avg(salary) from employees; -- error: Group by clause required on department.
    select department, avg(salary) from employees GROUP BY department;
    select subject, count(salary) as salaryCount, avg(salary) as "salary Average" from Teacher group by subject;

--# Advanced SELECT Queries:
-- Select with Subquery:
    SELECT first_name, last_name FROM employees WHERE department_id IN (SELECT department_id FROM departments WHERE location = 'New York');
-- Select with Aggregate Functions:
    SELECT AVG(salary), MAX(age), MIN(sales) FROM employees;
    -- we can not use aggregate functions in where clause:
        -- SELECT AVG(salary) from employees WHERE MAX(age) = 20;   ...Wrong
-- Select with Having Clause:
    -- Having we use on top of "Group By", because we can't use 2 where clause. select where groupby having.
    -- In having, we can use aggregate function, but in where clause, we can not.
    -- used to filter groups of rows returned by a GROUP BY clause.
    -- It allows you to specify conditions on aggregated data, similar to the WHERE clause, but for groups rather than individual rows.
    SELECT department, AVG(salary) FROM employees GROUP BY department HAVING AVG(salary) > 50000;
    select category, avg(bookPrice) from book group by category having avg(bookPrice)>500;
    select authorId, category, avg(bookPrice) from book group by category, authorId having avg(bookPrice)>500;
    select authorId, category, avg(bookPrice) from book where bookPrice>500 group by authorId, category having avg(bookPrice)>1000;
/*
-- JOINS --
There are 6 types of joins:
1. Join / Inner Join: Returns records that have matching values in both tables
2. Outer Join:
    2.1 LEFT Join: Returns all records from the left table, and the matched records from the right table
    2.2 RIGHT Join: Returns all records from the right table, and the matched records from the left table.
    2.3 (Not In MYSQ) Full Join: Full join returns all rows from both tables. Return all matching and non-matching records from both tables.
3. CROSS JOIN: Cross join returns the Cartesian product of the two tables, i.e., it combines each row of the first table with every row of the second table.
4. SELF JOIN: Self join is used to join a table to itself. It is helpful when you want to compare rows within the same table.
Note: Below employee queries are from "tables-for-joins.sql" file.
*/
--1. Join / Inner Join:
    -- Inner Join = Fetches matching records only.
        SELECT e.emp_name, d.dept_name FROM employee e JOIN department d ON e.dept_id = d.dept_id;
    -- below queries are same:
        select b.bookName, a.authorName from Book b INNER JOIN Author a ON b.authorId = a.authorId;
        select b.bookName, a.authorName from Book b JOIN Author a ON b.authorId = a.authorId;
        select b.bookName, a.authorName from Book b JOIN Author a where b.authorId = a.authorId;
        -- we can also achieve inner join withor JOIN keyword
        select b.bookName, a.authorName from book b, author a where a.authorId = b.authorId;
-- 2. Left OUTER Join:
    -- left join = inner join + any additional records in the left table.
        -- if on condition will fail, then right table columns will show null.
        SELECT e.emp_name, d.dept_name FROM employee e LEFT JOIN department d ON e.dept_id = d.dept_id;
        SELECT e.emp_name, d.dept_name FROM employee e LEFT OUTER JOIN department d ON e.dept_id = d.dept_id;
        select b.bookName, a.authorName from Book b LEFT JOIN Author a ON b.authorId = a.authorId;
-- 3. Right OUTER Join:
    -- right join = inner join + any additional records in the right table (left table columns will be null).
        -- if on condition will fail, then .
        SELECT e.emp_name, d.dept_name FROM employee e RIGHT JOIN department d ON e.dept_id = d.dept_id;
        SELECT e.emp_name, d.dept_name FROM employee e RIGHT OUTER JOIN department d ON e.dept_id = d.dept_id;
        select b.bookName, a.authorName from Book b RIGHT JOIN Author a ON b.authorId = a.authorId;

-- Problem: Fetch details of ALL emp, their manager, their department and the projects they work on.
    select e.emp_name, m.manager_name, d.dept_name, p.project_name from Employee e Join Manager m ON e.manager_id = m.manager_id left Join department d ON e.dept_id = d.dept_id left JOIN projects p ON e.emp_id = p.team_member_id;

/* 4. FULL OUTER Join: (Not In MySQL)
   -- Full Join = Inner Join 
                  + any additional records in the left table (right table columns will be null) 
                  + any additional records in the right table (left table columns will be null)
*/  -- SQL:
        SELECT e.emp_name, d.dept_name FROM employee e FULL JOIN department d ON e.dept_id = d.dept_id;
        SELECT e.emp_name, d.dept_name FROM employee e FULL OUTER JOIN department d ON e.dept_id = d.dept_id;
    -- MySQL: Left Join + UNION + Right Join.
        SELECT e.emp_name, d.dept_name FROM employee e LEFT JOIN department d ON e.dept_id = d.dept_id UNION SELECT e.emp_name, d.dept_name FROM employee e RIGHT JOIN department d ON e.dept_id = d.dept_id;
        SELECT e.emp_name, d.dept_name FROM employee e LEFT JOIN department d ON e.dept_id = d.dept_id UNION ALL SELECT e.emp_name, d.dept_name FROM employee e RIGHT JOIN department d ON e.dept_id = d.dept_id where e.dept_id IS NULL;

-- 5. CROSS JOIN:
    -- returns cartesian project. : Employee->6records, Department->4records = Total 24 records. (each record match each other)
    -- Cross Join don't need "ON"
    SELECT e.emp_name, d.dept_name FROM employee e CROSS JOIN department d;
-- Problem 2: Write a query to fetch the employee name and their corresponding department name.
-- Also make sure to display the company name and the company location corresponding to each employee.
    select e.emp_name, d.dept_name, c.company_name, c.location from Employee e LEFT JOIN Department d ON e.dept_id = d.dept_id CROSS JOIN Company c;
    select e.emp_name, d.dept_name, c.company_name, c.location from Employee e LEFT JOIN Department d ON e.dept_id = d.dept_id CROSS JOIN Company c where c.company_id = "C001";

-- 6. Natural Joins
    -- Natural Join don't need "ON"
    -- If 2 tables, sharing the same column name -> then it will do INNER JOIN.
        SELECT e.emp_name, d.dept_name FROM employee e NATURAL JOIN department d;
    -- If 2 tables, sharing the different column name -> then it will do CROSS JOIN.
        alter table department rename column dept_id to department_id;
        SELECT e.emp_name, d.dept_name FROM employee e NATURAL JOIN department d;
    -- control goes to sql to choose the column, where the join should happen. So using natural joins are highly not recommended.

-- 7. self Join:
  -- there is no keywork as SELF, When we do join table to itself, then this called as self join.
  -- Problem: Write a query to fetch the child name and their age corresponding to their parent name and parent age.
  select child.name as child_name, child.age as child_age, parent.name as parent_name, parent.age as parent_age 
  from family as child JOIN family as parent 
  ON child.parent_id=parent.member_id;