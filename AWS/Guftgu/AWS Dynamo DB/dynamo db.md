Types of AWS Databases:
    - Relational Databases
        - Amazon Aurora
        - Amazon RDS
    - Key-Value Stores
        -> Key-Value stores are a type of NoSQL database that uses a simple key-value method to store data.
        - Amazon DynamoDB
    - In-Memory Caching
        - Amazon ElastiCache
    - Graph Databases
        -> Graph databases is basically a collection of nodes and edges. Nodes represent entities and edges represent relationships between entities.
        - Amazon Neptune
    - Document Databases
        -> Document databases are a type of NoSQL database that is designed to store semi-structured data as documents.
        - Amazon DocumentDB
    
# AWS RDS: Relational Database Service
    - In an AWS fully managed relational DB engines services, where AWS is responsible for:
        - Security and Patching
        - Automated Backups
        - Software Updates for the DB Engine
        - If selected, Multi-AZ deployments for high availability. Automatic failover if multi-AZ is enabled. 
        eg. AZ1: Primary DB, AZ2: Standby DB => If AZ1 fails, AZ2 will be promoted to Primary DB
        - By default, every DB has weekly maintenance window (max 35 days)
    - AWS RDS supports the following DB engines:
        1. Amazon Aurora : High performance, MySQL and PostgreSQL compatible
        2. PostgreSQL: Highly reliable, open-source, supports 64 TB of storage
        3. MySQL : supports 64 TB of storage
        4. MariaDB: MySQL compatible, open-source, supports 64 TB of storage
        5. Oracle
        6. Microsoft SQL Server
        7. Amazon RDS on VMware
    - There are two Licensing models for RDS:
        1. License Included: Pay as you go model, no need to buy separate license
        2. Bring Your Own License (BYOL): If you have existing license, you can use it
    - RDS Limits:
        - Max DB instances: 40
        - Max DB instances with Multi-AZ: 20
        - License Included DB instances: 10 (for Oracle, SQL Server, MariaDB, MySQL)
    - Storage Type: 
        - DB only uses EBS volumes for storage, which are only below 2 types:
        1. General Purpose SSD (gp3): 
            - Performance scanle independently from storage
            - 3 IOPS per GB, minimum 100 IOPS, max 16,000 IOPS
        3. Provisioned IOPS SSD (io2):
            - Low latency, highly durable, I/O intensive storage.
        2. Provisioned IOPS SSD (io1):
            - Flexible in provisioning IOPS (I/O)
            - IOPS: Input/Output Operations Per Second
            - High performance, mission-critical workloads
            - 50 IOPS per GB, minimum 100 IOPS, max 64,000 IOPS
            - Use for: I/O intensive workloads, large database workloads, OLTP workloads (Online Transaction Processing)
            - Note: Always use Provisioned IOPS SSD for production databases or Multi-AZ deployments (When creating standby DB)
    - Templates Available for RDS:
        - Dev/Test:
            - Low cost, non-production environments
        - Production: 
            - High availability, production environments
        - Free Tier: 
            - 750 hours of db.t2.micro instance, 20 GB of storage, 20 GB of backup storage, No Multi-AZ
    - DB Instance size:
        Production:
            Standard classes (includes m classes)
            Memory optimized classes (includes r classes)
            Compute optimized classes (includes c classes)
        Free Tier:
            Standard classes (includes m classes)
            Memory optimized classes (includes r and x classes)
            Burstable classes (includes t classes)
        
        - Standard classes (includes m classes)
            - cpu: 2-96 vCPUs, memory: 8-384 GB
            - Use for: Web servers, small to medium databases
            - instance types: M (General Purpose), T (Burstable Performance), R (Memory Optimized)
        - Memory optimized classes (includes r classes):
            - R3: Memory optimized, memory optimized
        - Compute optimized classes (includes c classes)
    - Multi-AZ in RDS:
        - Info:
            - Same Region, different AZs, synchronous replication (ie. data is written to both DBs before commit)
            - You can select Multi-AZ deployment option when creating a new RDS instance
            - Multi-AZ provides high availability and failover support for DB instances
            - You can not read/write from standby DB, it is only for failover
            - AWS decides AZs for primary and standby DBs. But we can see AZ once DB is created
            - Working:
                - AZ1: Primary DB, AZ2: Standby DB
                - If AZ1 fails, AZ2 will be promoted to Primary DB
                - DNS name will be updated to point to new Primary DB
                - Now, AZ1 recovered from failure, it will be promoted to Standby DB. It wont be Primary DB.
            - Multi-AZ is not for scaling, it is for high availability
            - AWS recommends to use Provisioned IOPS SSD for Multi-AZ deployments
        - When Multi-AZ RDS Failover Triggers:
            - failure of the primary DB instance
            - failure of the Availability Zone
            - loss of network connectivity to the primary DB instance
            - loss of primary EC2 instance failure
            - EBS storage failure of the primary DB instance
            - The primary DB instance is changed (e.g., instance type, storage, or engine version)
            - Patching the OS of the primary DB instance
            - Manual failover of the primary DB instance.
                - 'Reboot with failover' option
                - after software installation/patching, reboot is required to take effect the changes.
        - Multi-AZ RDS failure consequences:
            - During failover, the CNAME (Canonical Name : like url ie internally linked to IP in DNS) record is updated to point to the standby DB
            - recommended to use the endpoint (CNAME) instead of the IP address.
            - CNAME does not change, because the RDS endpoint is a DNS record that points to the primary DB instance.
        - When failure occurs
            - AWS RDS sends SNS notification (charged)
            - Using API or CLI, we can view RDS events of 14 days, AWS Console shows 7 days events.
        - Software Patching/ Vestion Upgrade in Multi-AZ / Maintenance: 
            - First AWS patches the standby DB, then promotes it to primary DB. Then patches the old primary DB and makes it standby DB.
            - Patching is done during the maintenance window (eg. 3 AM)
        - Backup in Multi-AZ:
            - There are 2 types of Backup:
                1. AWS RDS automated backups
                2. User-initiated DB snapshots (manual backups)
            - Automated backups are taken from the standby DB, so it does not affect the performance of the primary DB.
            - backup of:
                - entire DB instance
                - transaction logs
                - Storage volume snapshots
            - Automated backups are enabled by default, and are taken daily during the maintenance window.
            - Backup stored in S3.
            - In non-Multi-AZ (single DB) backup, we can not perform any crud operation during backup.
            - DB instance mub be in 'available state' to take backup.
            - No changes for Multi-AZ backup. Charged for backup storage.
            - Backup retention period: 0-35 days (default 7 days) (0 for disable) (AWS Arora: 1 day only)
            - Backup window: 30 minutes
        - Manual Backup:
            - Suppose backup initiated at 3:00 AM, it will be completed at 3:30 AM. So, we can not take backup between 3:00-3:30 AM.
                Will get backup of 2:55 AM (<3AM) data. Not the exact 3:00 AM data.
            - RPO: Recovery Point Objective: How much data you can afford to lose   : 5 minutes (2:55 AM data, initiated at 3:00 AM)
            - RTO: Recovery Time Objective: How much time it takes to recover       : 30 minutes (3:30 AM)
            - Deletion:
                Automatic backups: Deleting DB instance deletes all automated backups (stored in S3)
                Manual snapshots: Deleting DB instance does not delete manual snapshots (stored in S3), Manually delete backup from S3.
                - Take final snapshot before deleting DB instance, so that you can restore it later.
            - Share:
                - Automated backups: need to take copy of backup and share it
                - Manual snapshots: directly share the backup snapshot.
            - Restore:
                - when restoring, you can change the DB instance class, storage type, and storage size.
                - We cant restore in existing DB instance, we have to create new DB instance.
    - Encryption:
        - At rest: 
            - Encrypts the underlying storage
            - Uses AWS KMS (Key Management Service) for encryption
            - Once encrypted, can not be disabled
            - Encrypts backups, read replicas, snapshots
        - In transit:
            - SSL certificates
            - Force SSL: to enforce SSL connections
        - Encryption of DB instance is done during creation of DB instance.
            You can not encrypt existing DB instance.
        - How to encrypt existing DB instance:
            Create new Encrypted DB instance
                1. Migrate data from old DB instance to new DB instance
                2. Create snapshot of old DB instance and restore it to new Encrypted DB instance.
        - RDS supports 'Encryption at rest' for all RDS engines.
            that means, data stored in encrypted format in underlying storage.
        - What actually encrypted, when data at rest?
            1. All its snapshot
            2. Automated backups (S3 storage)
            3. Data on EBS volumes
            4. Read replicas created from snapshot of encrypted DB
    - Billing:
        - No Upfront cost (No advance payment)
          Pay as you go model
        - Hourly billing (DB instance server), eg. used for 10 min, charged for 1 hour
        - storage GB/month (EBS volumes) , eg. 10 GB used for 10 days, charged for 1 month
        - Backup storage GB/month (S3 storage)
        - Internet Data transfer (Data transfer out):
            - Data transfer out from RDS to internet is charged
            - Data transfer out from RDS to EC2 in same region is free
            - Data transfer out from RDS to EC2 in different region is charged
        - Multi-AZ deployment:
            - Charged for standby DB
            - Charged for Multi-AZ storage
            - Double Write IOPS (I/O Operations Per Second) for Multi-AZ (write to both DBs)
            - No charge for data transfer from primary to standby DB.

## AWS RDS Lab:
    Lab 1: Create RDS DB instance:
        - Service: AWS RDS
        - RDS > Create Database
            - Engine options: MySQL
            - Templates: Free tier
            - DB instance size: db.t2.micro
            - Storage type: General Purpose SSD (gp3)
            - Allocated storage: 20 GB
            - Connectivity:
                - Virtual Private Cloud (VPC): default VPC
                - Additional connectivity configuration: 
                    - Subnet group: default
                    - Publicly accessible: Yes
                    - VPC security group: new security group
                    - availability zone: choose any
            - Additional configuration:
                - Database name: db1
                - Master username: admin
                - Master password: admin123
            - done
        - RDS > Databases > db1 > Connectivity & Security
            - Security group: Inbound rules: Add rule
                - 
                    - Type: MySQL/Aurora
                    - Source: Anywhere
                - 
                    - Type: SSH
                    - Source: Anywhere
            - Linux Instance:
                - EC2 > Create instance
                    - AMI: Amazon Linux 2
                    - Instance type: t2.micro
                    - Configure Instance: 
                        - Auto assign Public IP: Enable
                        - Security group: existing DB security group
                    - Download PEM key
                - Install Putty and Putty Gen:
                    - Putty Gen:
                        - Load PEM key, select pem key file.
                        - Save private key in any folder : key.ppk
                    - Putty:
                        - Hostname: Public IP of EC2
                        - Connection > SSH > Auth:
                            - Browse: Private key file for authentication: key.ppk
                        - Open: login as ec2-user
                        - it will open terminal
                    - Terminal commands:
                        - 'ec2-user'    // login as ec2-user
                        - 'sudo su -'   // login as root
                        - 'yum install -y mysql'   // install mysql
                        - 'mysql -h <db-endpoint> -u admin -p' db1  // db1 is database name
                            - password: admin123
                            - show databases;
                            -> Successfully connected to RDS DB instance
                                - create database db2;
                                - use db2;
                                - create table t1 (id int, name varchar(20));
                                - insert into t1 values (1, 'abc');
                                - select * from t1;
                                - exit;
                - Windows Instance:
                    - DB Security group: Inbound rules: Add rule
                        - Type: MySQL/Aurora
                        - Type: RDP
                        - Type: HTTP
                        - Type: HTTPS
                        - Source: Anywhere
                    - EC2 > Create instance
                        - AMI: Windows Server 2019
                        - Instance type: t2.micro
                        - Configure Instance: 
                            - Auto assign Public IP: Enable
                            - Security group: existing DB security group
                        - Download RDP file and connect to Windows instance
                    - Setup:
                        Windows button > Server Manager > Local Server > 
                            IE Enhanced Security Configuration > Off
                            Windows Defender Firewall > Off
                        - Browser:
                            - Download and Install 'Web Platform Installer' from Microsoft website
                            - Open 'Microsoft Web Platform Installer'
                                - Search 'MySQL' > Add > Install 
                                    - set password: 123..
                        - cmd:
                            - 'mysql -h <db-endpoint> -u admin -p' db1  // db1 is database name
                                - password: admin123
                                - show databases;
                                -> Successfully connected to RDS DB instance
                                    - Run MySQL commands

# AWS Dynamo DB: (NoSQL)
    -  DynamoDB is a fast and flexible NoSQL database service for all applications that need consistent, single-digit-millisecond latency at any scale. Its flexible data model and reliable performance make DynamoDB a great fit for mobile, web, gaming, advertising technology, Internet of Things, and other applications.
    - Basic:
        - Unstructured data is stored in NoSQL databases
        - Semi-structured data is stored in Document databases
        - DynamoDB is a fully managed NoSQL database service provided by AWS
        - DynamoDB is a key-value and document database
        - DynamoDB is a serverless database
    - Terminoogies:
        - Table: A collection of items
        - Item: A collection of attributes
        - Attribute: A key-value pair
        - Primary Key: A unique attribute that identifies an item
        - Secondary Index: An index that allows you to query the table using an alternate key
        - Partition Key: The primary key attribute that determines the partition in which an item is stored
        - Sort Key: The primary key attribute that determines the sort order of items with the same partition key
        - Local Secondary Index: An index that has the same partition key as the table, but a different sort key
        - Global Secondary Index: An index with a partition key and sort key that can be different from those on the table
    - Limits:
        - Maximum total size of a DynamoDB table (including all items and indexes): 400 GB per partition.
        - Maximum size of a single item: 400 KB 
            - Item more than 400 KB, use S3 to store the item and store the S3 URL in DynamoDB
    - Feature:
        - Fully managed, serverless, multi-region, multi-master, durable, and scalable
        - low latency reads and writes access
    - Read Capacity Units (RCUs) and Write Capacity Units (WCUs):
        - DynamoDB is charged based on RCUs and WCUs.
        - RCU: Read Capacity Unit
            - 1 RCU = 4 KB per second (if used 2KB, charged for 4KB ie. 1RCU)
            - 1 RCU = 2 Eventually consistent reads per second (default) (8 KB per second)                
            - 1 RCU = 1 Strongly consistent read per second (double the cost) (4 KB per second)
            - 1 RCU = 1 Transactional reads per second (4 KB per second)
            
            - Eventually consistent: Read operation might reflect the changes in the table, but not guaranteed.
                eg. If you write data eg. 1,2,3 and during writing data 3 (eg. 3 written takes 1 sec), if dynamo receives read request, it will return 1,2. won't wait to return 3.
            - Strongly consistent: Read operation will reflect the changes in the table, guaranteed.
                eg. during writing data 3, if read request comes, it will for 3 to be written and then return 1,2,3.
            - Transactional: Read operation will reflect the changes in the table, guaranteed. (2x cost of Strongly consistent)
        - WCU: Write Capacity Unit
            - 1 WCU = 1 KB(write) per second
            - 1 WCU = 1 write per second for up to 1 KB of data (standard writes).
            - 1 WCU = 2 WCUs per second for 1 KB of data (transactional writes).
        - DynamoDB is costly for write operations and cheap for read operations. 
        - So, consider DynamoDB for read-heavy applications.
          Do not consider for write-heavy applications.
    - Biilling/Pricing:
        - Reads are cheaper than writes
        - On-Demand Capacity Mode:
            - Pay per request
            - No need to specify read/write capacity
            - No minimum fee
            - Good for unpredictable workloads
        - Reads: $0.00013 per RCU per hour
        - Writes: $0.00065 per WCU per hour
        - Storage: $0.25 per GB per month
        - You pay for:
            - Each tables provisioned read/write throughput (Hourly Rates)
            - Indexed data storage (GB/month) (DynamoDB attributes points to S3 url for large data)
            - Internal data transfer (other regions)
        - Free ties:
            - 25 GB of indexed data storage
            - 25 units of write capacity and 25 units of read capacity
            - 2.5 million write requests and 2.5 million read requests per month
    - DynamoDB DB can do 10,000 RCU and 10,000 WCU per table.
    - 256 tables per region.
    - No limit on number of items in a table.
    - Global tables: 
        - DynamoDB available in multiple regions.
        - Multi-region, multi-master, cross-region replication
        - Read/write data in multiple regions
        - Automatic conflict resolution
        - No additional charge for global tables
    - Datatypes:
        - For Primary Key:
            - Partition Key:
                - Number: 1, 2, 3
                - String: "abc", "xyz"
                - Binary: 0, 1, 0010
        - For Attributes:
            - Scalar: 
                - Number: 1, 2, 3
                - String: "abc", "xyz"
                - Binary: 0, 1
                - Boolean: true, false
                - Null: null
            - Set:
                - Number Set: [1, 2, 3]
                - String Set: ["abc", "xyz"]
                - Binary Set: [0, 1]
            - Document: 
                - List: [1, "abc", 0]
                - Map: { "name": "abc", "age": 20 }
    - LAB:
        - Service: AWS DynamoDB
        - Create Table:
            - Table name: Student
            - Primary key: 
                - Partition key: rollNo (Number) // String/Number/Binary
                - Auto Scaling: On
            - Encryption: Default // Default/KMS Key
        - Open Table > Student > Items
            - Create Item
                - rollNo: 1 (Mandatory, number type)
                - name: abc
                - age: 20
            - Create Item
                - rollNo: 2
                - name: xyz
                - Division: A
            - Create Item
                - rollNo: 3
                - name: pqr
                - Address: India
        - Filter:
            - 1. Scan: 
                - Scan the entire table
                - Slow and expensive
                - Not recommended for large tables
                - Use when you need to scan the entire table
            - 2. Query:
                - Query based on primary key
                - Fast and efficient
                - Recommended for large tables
                - Use when you need to query based on primary key. Partition (Primary) key is mandatory.
# AWS DynamoDB Interview Questions:
    Q. What is DynamoDB?
    -> DynamoDB is a fully managed NoSQL database service provided by AWS. It is a key-value and document database. It is a serverless database.
    Q. What is the difference between RCU and WCU?
    Q. What is the difference between Eventually consistent and Strongly consistent reads?
    Q. What is the difference between Partition Key and Sort Key?
    -> Partition Key: The primary key attribute that determines the partition in which an item is stored
    -> Sort Key: The primary key attribute that determines the sort order of items with the same partition key
    Q. What is the difference between Local Secondary Index and Global Secondary Index?
    -> Local Secondary Index: An index that has the same partition key as the table, but a different sort key
    -> Global Secondary Index: An index with a partition key and sort key that can be different from those on the table
    Q. What is the difference between On-Demand Capacity Mode and Provisioned Capacity Mode?
    