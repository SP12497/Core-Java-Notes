# Part 3: Amazon S3 (Simple Storage Service):
    - Amazon S3 is an "object storage" service that offers industry-leading scalability, data availability, security, and performance.
    - Amazon S3 is designed to store and retrieve any amount of data from anywhere on the web (internet http, https).
    - we cant install OS on S3, we can only store data.
    - S3 has distributed data storage architecture, which means that data is stored across multiple devices and multiple facilities within a region. 
      (multiple copies of data, minimum 3 copies of data in any availability zone in same region)
    - Data is stored in Buckets, which are containers for objects.
    - A Bucket is a flat container for objects, (like a folder in a file system). We can not created nested buckets (Tree).
      and each object is stored in a unique key within a bucket. 
    - Max capacity of a bucket is 5TB. But we can store unlimited data in S3.
    - upto 100 buckets can be created in an account.
    - You can create folders in S3, but they are not real folders, they are just part of the object name.
    - Bucket ownership is not transferable. (If you created a bucket, you are the owner of that bucket, you can not transfer ownership to someone else)
    - S3 bucket is region specific. We cant select AZ while creating bucket.

## Types of Storage Classes in S3:
    1. S3 Standard:
        Storage Cost: Highest
        Retrieval Cost: Lowest.
        Use Cases: Frequently accessed data.
        Features: High availability, low latency, and high durability. Suitable for a wide range of use cases such as cloud applications, dynamic websites, content distribution, mobile and gaming applications, and big data analytics.
        Storage Duration: No minimum storage duration. You pay for what you use.
    2. S3 Intelligent-Tiering:
        Storage Cost: Moderate
        Retrieval Cost: Depends on access pattern, small monitoring feeCost: Moderate Cost
        Amazon will automatically move data between two access tiers (frequent and infrequent) based on changing access patterns.
        Use Cases: Unknown or changing access patterns.
        Storage Duration: Minimum storage duration of 30 days. If you delete an object before 30 days, you will be charged for 30 days.
        Features: Automatically moves data between two access tiers (frequent and infrequent) based on changing access patterns. It has a small monthly monitoring and automation fee.
        - it is designed to optimize costs by automatically moving data to the most cost-effective access tier. Works with lifecycle policies.
        - AWS will not change the storage class of the object, it will only move the object to the frequent or infrequent access tier.
    3. S3 Standard IA (Infrequent Access):
        Storage Cost: Low
        Retrieval Cost: ModerateCost: Cheaper than S3 Standard. 
        Use Cases: Long-lived but infrequently accessed data.
        Features: Lower cost than S3 Standard, but with retrieval charges. Suitable for data that is accessed less frequently but requires rapid access when needed, such as backups and disaster recovery.
    4. S3 One Zone-IA
        Storage Cost: Low
        Retrieval Cost: ModerateCost: Cheaper than S3 Standard
        - stored in a single Availability Zone. 
        - No copy of data. If AZ is down, data will be lost. But all other storage classes have multiple copies of data in different AZ.
        Use Cases: Infrequently accessed data that does not require multiple Availability Zone resilience.
        Features: Lower cost compared to S3 Standard-IA, with data stored in a single Availability Zone. Suitable for data that can be easily re-created if lost.
        - Storage Duration: Minimum storage duration of 30 days. If you delete an object before 30 days, you will be charged for 30 days.
    5. S3 Glacier
        Storage Cost: Very low, Cheap. 
        Retrieval Cost: Moderate, retrieval times (minutes to hours), 10GB free retrieval per month. Different retrieval options available, such as Expedited, Standard, and Bulk.
        Use Cases: Long-term archival and digital preservation.
        Features: Very low cost for storage, with retrieval times ranging from minutes to hours. Suitable for data that is rarely accessed but must be retained for long periods.
        Storage Duration: Minimum storage duration of 90 days. If you delete an object before 90 days, you will be charged for 90 days.
    6. S3 Glacier Deep Archive
        Storage Cost: Cheapest
        Retrieval Cost: High, slow retrieval (12 hours or more), Storage cost is 75% less than S3 Glacier.
        Use Cases: Archival storage for data that is rarely accessed.
        Features: The lowest-cost storage class, with retrieval times of 12 hours or more. Suitable for data that is accessed less frequently than once a year and for long-term digital preservation.
        Storage Duration: Minimum storage duration of 180 days. If you delete an object before 180 days, you will be charged for 180 days.
    7. S3 Reduced Redundancy Storage (RRS)
        Use Cases: Non-critical, reproducible data.
        Features: Lower cost for storage of non-critical data that can be easily recreated if lost. Provides 99.99% durability compared to the 99.999999999% durability of other storage classes.
    8. S3 Redundancy Storage (RRS) is deprecated. (No longer available for new customers)

##$ Summary Based on Cost:
    Cheapest Storage: S3 Glacier Deep Archive
    Most Expensive Storage: S3 Standard
    Cheapest Retrieval: S3 Standard
    Most Expensive Retrieval: S3 Glacier Deep Archive

### S3 Bucket Naming Rules:
    - S3 bucket names (keys) are globally unique across all AWS accounts.
    - Bucket names cannot be change/rename after they are created.
    - If a bucket is deleted, its name becomes available again to you or other account to use.
    - Bucket names must be between 3 and 63 characters long.
    - Bucket names are part of the URL used to access objects in a bucket.
    - Bucket name must be a series of one or more labels separated by a period (.), each label must start with a lowercase letter or a number. eg. my.bucket.name
    - Bucket names can contain lowercase letters, numbers, and hyphens. eg. my-name_123.bucket
      each bucket can start and end with a number or letter. eg. 1mybucket, mybucket1
      can not start or end with hyphen. eg. -mybucket, mybucket-, my--bucket
      can not contain underscores, uppercase letters, or periods. eg. my_bucket, my.Bucket, MYBUCKET
      should not be an IP address.
    - By default, buckets and its objects are private. (Only bucket owner can access the bucket and its objects)
    - The S3 url is: 
        https://s3-<region>.amazonaws.com/<bucket-name>/<object-name>

### Subresources of S3 bucket:
    - Lifecycle:
        - to define rules for objects in the bucket.
        - eg. initialy store data in S3 standard, after 30 days move to S3 IA, after 60 days move to Glacier. after 90 days delete.
    - Versioning:
        - to keep multiple versions of an object in the same bucket.
        - If versioning is enabled, then we can not delete an object, we can only delete a version of an object.
    - Website Hosting:
        - to host a static website on S3.
    - Access Control List (ACL):
        - to control access to objects in the bucket.
        - bucket policy.

### S3 Object:
    - An object is a file and any metadata that describes that file.
    - size of an object can be from 0 bytes to 5TB.
    - each object is stored and retrieved using a unique key (Id or name).
    - object is uniquely identified and address through:
        - Service endpoint
        - Bucket name
        - Object key (Id or name)
        - Optionally object version ID
    - Object stored in S3 bucket in a region. It will never leave that region unless we move it.
    - Bucket owner can grant cross-account permission to another AWS account to access objects in the bucket.
    - You can grant S3 bucket/object permission to:
        - an AWS account
        - Individual AWS users
        - Make reource publically accessible
        - Use IAM policies to control access to S3 resources.

### Versioning in S3:
    - Versioning is enabled at the bucket level.
        - Versioning is a way of keeping multiple variants of an object in the same bucket.
        - Once versioning is enabled, it can not be disabled, only suspended. (IMPORTANT NOTE)
        - When versioning is enabled, S3 generates a unique ID for each version of an object.
        - When you delete an object, it is not deleted permanently, only the current version is deleted. accidentially deleted object can be restored.
        - You can delete a specific version of an object.
        - Versioning is a great backup tool.
        - Updating object refers to PutObject, PutObjectAcl, CopyObject, CompleteMultipartUpload, and PostObject, DeleteObject, DeleteObjectVersion.
        - Delete Marker: When you delete an object, a delete marker is created to indicate that latest version of the object is deleted.
        You can still view the object and its versions, but the latest version is deleted.
        If you delete the delete marker, then the object is restored.
        - charges: you will be charged for all versions of an object stored in increased storage cost.
            eg. Version 1: 5MB => cost = 5MB
                Version 2: 5.2MB => cost 0.2MB
                Version 3: 5.5MB => cost 0.3MB
                Total cost = 5MB + 0.2MB + 0.3MB = 5.5MB
        - use lifecycle policies to archive or delete old versions of objects.
        - Bucket Versioning State:
            - Unversioned: Default state, no versioning. (once enabled, can not be disabled/Unversioned, only suspended)
            - Enabled: Versioning is enabled.
            - Suspended: Versioning is suspended, no new versions are created, but existing versions are retained.
        - Versioning applies to all objects in the bucket, including new objects.
        Object in Bucket before enabling versioning will have version ID null.

### S3 Multi-Factor Authentication (MFA) Delete:
    - Multifactor authentication (MFA) adds an extra layer of security on top of your username and password.
    - MFA Delete requires two forms of authentication to delete an object version. 
    - MFA Delete can only be enabled on the bucket versioning is enabled.
    - MFA Delete Requieres:
        - Your AWS account credentials
        - A valid MFA code from the device in your possession. like Google Authenticator, Authy, Okta, etc.

### S3 Multi-Part Upload: https://docs.aws.amazon.com/AmazonS3/latest/userguide/qfacts.html
    - Multi-Part upload is used to upload large objects in parts.
      eg. 100MB file can be uploaded in 5 parts of 20MB each part. 
      But the object will be stored as a single object.
    - Helps to upload objects in parallel, which can increase the upload speed.
    - part size: 5 MiB to 5 GiB. There is no minimum size limit on the last part of your multipart upload.
    - Multi-Part upload is recommended for files larger than 100MB.

### Copy Object in S3:
    - Copy object is used to copy objects from one bucket to another bucket.
    - Copy object is used to copy objects within the same bucket.
    - Copy object is used to copy objects with different storage classes.
    - can create a copy of an object upto 5GB in size in a single operation.
      For objects larger than 5GB, use multi-part upload.
    - There is no coping charge if you copy an object within the same region.
      If copying in another region, you will be charged for data transfer/copying. (cross-region data transfer charges)
    - Copy Operation:
        - Rename object: Copy object to another bucket with a new name and delete the old object.
        - Change storage class: Copy object to the same bucket with a different storage class. (eg. S3 Standard to S3 IA)
        - Move object accross regions: Copy object to another bucket in a different region.
        - Change object metadata: Copy object with new metadata. (System metadata and user metadata)

### Cross-Region Replication (CRR):
    - Cross-Region Replication (CRR) is used to replicate objects from one bucket to another bucket in a different region. Vise versa is not possible. only source bucket can replicate to destination bucket.
    - CRR is used to replicate objects across different AWS accounts or different storage classes or different encryption types.
    - Since replication in different regions, you will be charged for data transfer/copying. (cross-region data transfer charges)
    - Whem to use:
        - Compliance requirements: Replicate data to a region that meets compliance requirements.
        - Disaster Recovery: Replicate data to a different region for disaster recovery.
        - Lower latency: Replicate data to a region closer to users to reduce latency. (eg. US East to Mumbai)
        - Data sovereignty: Replicate data to a region that meets data sovereignty requirements.


### Static Website hosting in S3 bucket:
    - S3 can host a static website.
    - Static website: HTML, CSS, JS, images, videos, etc.
    - S3 static website hosting is used to host static websites.

    - Steps:
        - Purchase a free domain name from "FreeNom" or any other domain registrar. (eg. my-example.com)
        - Create a bucket with the same name as the domain name. (eg. my-example.com)
        - Upload the website files to the bucket. (index.html, error.html, etc) and make this file public.
        - S3 Properties -> Static website hosting -> Enable -> Index document: index.html, Error document: error.html
        - Permissions -> Bucket Policy -> Paste the bucket policy to make the bucket public. (get sample from AWS documentation)
        - Route 53:
            - DNS Management service:
                - Create a hosted zone: 
                    - Domain name: my-example.com
                    - Type: Public hosted zone
                - Now, you will get 4 NS records (Name Server) and 2 SOA records (Start of Authority)
                - Copy the all 4 NS records and paste them in the domain registrar (FreeNom) in the domain settings.
                - Create a record set:
                    - Name: www.my-example.com
                    - Type: A - IPv4 address
                    - Alias: Yes
                    - Alias Target: S3 bucket endpoint (eg. my-example.com.s3-website-us-east-1.amazonaws.com)
        - Now, after some time, you can access your website using the domain name. (eg. www.my-example.com)
        - Test: Open browser -> www.my-example.com -> You should see the website.