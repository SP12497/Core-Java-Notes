create table employee (
    emp_id varchar(20) primary key,
    emp_name varchar(50),
    salary integer,
    dept_id varchar(20),
    manager_id varchar(20)
);

insert into employee values 
("E1", "Rahul", 15000, "D1","M1"),
("E2", "Manoj", 15000, "D1","M1"),
("E3", "James", 55000, "D2","M2"),
("E4", "Michael", 25000, "D2","M2"),
("E5", "Ali", 20000, "D10","M3"),
("E6", "Robin", 35000, "D10","M3");

create table department (
    dept_id varchar(20) primary key,
    dept_name varchar(50)
);

insert into department values 
("D1", "IT"),
("D2", "HR"),
("D3", "Finance"),
("D4", "Admin");

create table manager (
    manager_id varchar(20) primary key,
    manager_name varchar(50),
    dept_id varchar(20)
);

insert into manager values
("M1", "Prem", "D3"),
("M2", "Shripadh", "D4"),
("M3", "Nick", "D1"),
("M4", "Cory", "D1");

create table projects (
    project_id varchar(20),
    project_name varchar(50),
    team_member_id varchar(20)
);

insert into projects values 
("P1", "Data Migration", "E1"),
("P1", "Data Migration", "E2"),
("P1", "Data Migration", "M3"),
("P2", "ETL Tool", "E1"),
("P2", "ETL Tool", "M4");

create table company (
    company_id varchar(20),
    company_name varchar(20),
    location varchar(20)
);

insert into company values
("C001", "SP Technologies", "Maharashtra"),
("C002", "Sagar Techie", "Bengaluru");

create table family(
    member_id varchar(20) primary key,
    name varchar(20),
    age integer,
    parent_id varchar(20)
);
insert into family values 
("F1", "David", 4, "F5"),
("F2", "Carol", 10, "F5"),
("F3", "Michael", 12, "F5"),
("F4", "Johnson", 36, ""),
("F5", "Maryam", 40, "F6"),
("F6", "Stewart", 70, ""),
("F7", "Rohan", 6, "F4"),
("F8", "Asha", 8, "F4");