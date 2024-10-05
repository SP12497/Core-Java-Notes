# AWS Storage:
    - used to store and retrieve data
    - Types of storage services: 
        - Object Storage: Amazon S3 (Simple Storage Service)
        - Block Storage: Amazon EBS (Elastic Block Store)
        - File Storage: Amazon EFS (Elastic File System)
        - Data Transfer and Edge Storage: AWS Storage Gateway
        - Archive Storage: Amazon S3 Glacier, Amazon S3 Glacier Deep Archive
        - Database Storage: Amazon RDS (Relational Database Service), Amazon DynamoDB
        - Backup and Disaster Recovery: AWS Backup
        - Hybrid Storage: AWS Outposts
    - Types of storage:
        1. Object Storage
            Amazon S3 (Simple Storage Service)
            - (Assume like EC2 instance HDD, We can only access by that perticular EC2 instance)
            Use Cases: Backup and restore, data lakes, big data analytics, archive, serverless computing, and content storage and distribution.
            Features: Scalability, high availability, durability, lifecycle policies, event notifications, versioning, replication, 
                and storage classes (S3 Standard, S3 Intelligent-Tiering, S3 Standard-IA, S3 One Zone-IA, S3 Glacier, S3 Glacier Deep Archive).

        2. Block Storage
            Amazon EBS (Elastic Block Store)
            Use Cases: Persistent block storage for Amazon EC2 instances.
            Features: SSD-backed (gp2, gp3, io1, io2) and HDD-backed (st1, sc1) volumes, snapshot capabilities, encryption, and replication within an Availability Zone.

        3. File Storage
            Amazon EFS (Elastic File System)
                Use Cases: Lift-and-shift enterprise applications, web serving and content management, home directories, development and testing environments, and media workflows.
                Features: Fully managed NFS file system, scalable, high availability, multiple access points, and performance modes (General Purpose and Max I/O).
            Amazon FSx: (Fully managed file storage services)
                Amazon FSx for Windows File Server:
                    Use Cases: Windows-based applications requiring file storage, such as SharePoint, SQL Server, home directories, and line-of-business applications.
                    Features: Fully managed Windows file systems, Active Directory integration, and support for SMB protocol.
                Amazon FSx for Lustre
                    Use Cases: High-performance workloads such as machine learning, high-performance computing (HPC), and media processing.
                    Features: Seamless integration with S3, POSIX-compliant file systems, and scalable performance.

        4. Data Transfer and Edge Storage
            AWS Storage Gateway
                Use Cases: Hybrid cloud storage, backup and restore, tiered storage, and disaster recovery.
                Types: File Gateway, Tape Gateway, and Volume Gateway.
            AWS Snowball:
                - like, AWS will send you a physical device to transfer your data to AWS data center. 
                  Suppose, you have 100TB data and you want to transfer to AWS, then AWS will send you a physical device like HDD, you will transfer your data to that HDD and send back to AWS. AWS will transfer your data to AWS data center.
                Use Cases: Large-scale data transfers and edge computing.
                Features: Secure, ruggedized devices to transfer large amounts of data into and out of AWS, 
                    including Snowball, Snowball Edge (with compute and storage capabilities), 
                    and Snowmobile (for petabyte-scale data transfer).
                - AWS Snowball: 
                    A petabyte-scale data transport solution that uses secure appliances to transfer large amounts of data into and out of the AWS cloud.
                - AWS Snowball Edge:
                    Adds on-device storage and compute capabilities to the data transfer capabilities of Snowball. It is designed for edge computing, enabling data collection and analysis in remote environments before transferring the data to AWS.
                - AWS Snowmobile:
                    An exabyte-scale data transfer service used to move extremely large datasets (up to 100 PB per Snowmobile) to AWS. It uses a secure, 45-foot-long shipping container pulled by a semi-trailer truck.

         5. Archive Storage
            Amazon S3 Glacier
                - Cheper than S3. So, Access time slightly higher than S3. We can use for backup and restore, disaster recovery, and long-term data archiving.
                Use Cases: Long-term data archiving and digital preservation.
                Features: Low-cost, secure, and durable storage designed for data archiving and backup, with retrieval options ranging from minutes to hours.
            Amazon S3 Glacier Deep Archive
                Use Cases: Archival storage for data that is rarely accessed.
                Features: The lowest-cost storage option with retrieval times of 12 hours or more.

        6. Database Storage
            Amazon RDS (Relational Database Service)
                Use Cases: Managed relational databases.
                Features: Automated backups, snapshots, and point-in-time recovery.
            Amazon DynamoDB
                Use Cases: Managed NoSQL database service for applications that require low-latency data access.
                Features: Automatic scaling, backup and restore, and in-memory caching with DynamoDB Accelerator (DAX).

        7. Backup and Disaster Recovery
            AWS Backup
                Use Cases: Centralized backup management for AWS services.
                Features: Automated backup scheduling, policy-based management, and compliance reporting.

        8. Hybrid Storage
            AWS Outposts
                Use Cases: On-premises data processing and storage with seamless integration to AWS.
                Features: Fully managed and configurable compute and storage racks.

    - These services can be combined and tailored to meet specific requirements,
      providing a flexible and powerful set of tools for various storage needs in the cloud.

# Part 2: Block vs Object Storage:
    - Block Storage:
        - Example: Amazon EBS (Elastic Block Store)
        - Block storage is suitable for transactional databases, randon read/write loads and structured database storage.
        - Block storage divides the data to be stored into evenly sized blocks (data cache) for instance, a file can be split into evenly sized blocks of 128 KB each.
        - Data blocks stored in block storage would not contain Metadata (data created, modified, accessed, etc).
        - Block storage only keeps the address (index (pointer)) where the data blocks are stored, it does not care what is in that block, just how to retrive it when requiered.
    - Object Storage:
        - Example: Amazon S3 (Simple Storage Service)
        - Object storage stores the files as a whole and does not divide them.
        - In Object storage, 
            - The file/data itself
            - Its metadata
            - Object Global Unique Identifier (GUID)
        - The Object Global Unique ID is a unique identifier for the object (can be object name itself) and it must be unique such that it can be used to retrieve the object disregarding the object's physical location.
        - Object storage is suitable for unstructured data, such as videos, images, and log files.
        - it cannot be mounted as a drive to an operating system, it can only be accessed via API calls.

## AWS EFS (Elastic File System):
    - [Link Text] https://aws.amazon.com/efs/faq/
    - EFS is a fully managed, elastic, scalable, and highly available file storage service.
    - EFS is a file storage service for Amazon EC2 instances.
    - EFS is a regional service, which means it is not limited to a single Availability Zone.
    - *If you have multiple EC2 instances in different AZs, you can use EFS to share files between them.
    - Only works with Linux EC2 instances.