
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
        - Secondary Index: An index that allows you to query the table using an alternate key (non-primary key)
        - Partition Key: The primary key attribute that determines the partition in which an item is stored (Mandatory for Query)
        - Sort Key: The primary key attribute that determines the sort order of items with the same partition key (Optional for Query)
        - Local Secondary Index: An index that has the same partition key as the table, but a different sort key (partition key + Any other key) (Note: table must have sort key)
        - Global Secondary Index: An index with a partition key and sort key that can be different from those on the table (non-partition key and non-sort key index)
            - Projection: Attributes to be copied from the table into the index
            - eg. Create Global Index with Projection:
                Table: rollNo (Partition Key), name, age, Division, section
                - if GSI: name | Projection: name, age, Division (can not be partition key)
                - then, it will create new table with name as partition key as name and fields as name, age, Division ( name (Partition Key), age, Division)
            - Test:
                1. Query by rollNo (Partition Key): will return all fields: rollNo, name, age, Division, section
                2. Query by name (GSI): will return: rollNo, name, age, Division


                
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
## LAB:
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

## AWS DynamoDB Interview Questions:
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
    