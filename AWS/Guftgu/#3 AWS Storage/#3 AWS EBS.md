# Elastic Block Store (EBS)
    # Types of Block store devices are available for EC2:
        - Elastic Block Store (EBS)
            - EBS is a block storage service that you can use with your EC2 instances.
            - Persistent:
                - Data will be retained even after instance termination.
            - Network-attached:
                - EBS volumes are network-attached, and persist independently from the life of an instance.
        - Instance Store (Ephemeral Storage)
            - Instance store Backed EC2.
            - Virtual hard disks that are physically attached to the host computer.
            - Faster than EBS. (Because it is physically attached to the host computer)
            - Non-persistent. Data will be lost if instance is terminated/stopped. Data wont loss on reboot the instance.
            - Limited to 10GB per instance.

## Elastic Block Store: (EBS Volumes)
    - Must be in the same AZ as the EC2 instance. And One EBS volume can be attached to only one EC2 instance.
    - We can install OS on EBS volume or Instance store.
    - EBS is a block storage service that you can use with your EC2 instances.
    - EBS volumes are highly available and reliable storage volumes that can be attached to any running instance that is in the same AZ.
    - EBS volumes are placed in a specific AZ, where they are automatically replicated to protect you from the failure of a single component.
    - EBS volumes are network-attached, and persist independently from the life of an instance.
    - Block storage: Data is stored in blocks just like linked list in distributed memory locations.
    - EBS Volume data replicates in same AZ.
    - Per account, 5000 EBS volumes can be created. (Default limit)

## Types of EBS Volumes:
    - #3 AWS EBS.png
    - root volume: The volume that contains the OS.
    1. SSD Backed :
        - Can install OS (Bootable).
        - SSD measures IOPS (Input/Output Operations Per Second).
        a. General Purpose SSD (gp2) : (Default: gp3)
            - Two types: gp2 and gp3.
            - Price: $0.10 per GB per month.
            - General purpose, balances price and performance.
            - Ratio of 3 IOPS per GB with up to 10,000 IOPS and the ability to burst up to 3,000 IOPS for extended periods of time for volumes at 3334 GiB and above.
            - Boot Volume having low latency.
        b. Provisioned IOPS SSD (io1)
            - two types: io1 and io2.
            - Price: $0.125 per GB per month.
            - Designed for I/O intensive applications such as large relational or NoSQL databases.
            - Use if you need more than 10,000 IOPS.
            - Can provision up to 20,000 IOPS per volume.
            - Use for extremely low latency and throughput intensive workloads.
            - eg. Mission-critical applications (like eye surgery, Rocket launch, etc.)
            - Designed for Database workloads.
    2. HDD Backed Volumes:
        - Can not install OS (Not bootable).
        - HDD measures throughput (Amount of data that can be transferred per second). eg. 500MB/s
        a. Throughput Optimized HDD (st1)
            - Price: $0.045 per GB per month.
            - Big data, data warehousing, log processing.
            - frequently accessed workloads.
            - Volume size: Minimum 500GB and Maximum 16TB
            - Volume IOPS: 500 IOPS.
        b. Cold HDD (sc1):
            - Price: $0.025 per GB per month. (Cheapest)
            - Lowest cost storage for infrequently accessed workloads.
            - File server.
            - Cannot be a boot volume.
            - Volumne size: Minimum 500GB and Maximum 16TB
            - Volume IOPS: 250 IOPS.
    3. Magnetic Standard: (Previous Generation)
        - Price: $0.05 per GB per month.
        - Volume size: Minimum 1GB and Maximum 1TB
        - Can install OS (Bootable). (Cheapest in Bootable)
        - Magnetic volumes provide the lowest cost per gigabyte of all EBS volume types.
        - Magnetic volumes are backed by magnetic drives and are suited for workloads where data is accessed infrequently, and scenarios where low-cost storage for small I/O operations is important.
        - Cannot be a boot volume.

## EBS snapshot:
    - stores in S3.
    - supports incremental snapshots. eg. If we have 5 snapshots and we take 6th snapshot, then it will only store the changes from 5th snapshot to 6th snapshot.
    - AZ specific: must be in the same AZ as the EC2 instance.
    - Per account, 1000 EBS snapshots can be created. (Default limit)
    - Region specific: must be in the same region as the EC2 instance.
    - Using EBS snapshot, we can create EBS volume.
    - You can have upto 5 snapshot copy request running in a single destination per account.
    Q. How to migrate EBS volume from one AZ to another AZ?
        -> Create a snapshot of EBS volume (region specific) and then create a new EBS volume in another AZ using that snapshot.
    Q. How to migrate EBS volume from one region to another region?
        -> Create a snapshot of EBS volume (region specific) and then copy that snapshot to another region. Then create a new EBS volume in another region using that snapshot.
    Q. How to migrate EBS volume from one region to another region?
        - S3 and snapshot are region specific. So, copy the snapshot from one region to another region S3 bucket and then create a new EBS volume in another region using that snapshot.
    Q. If snapshot size is 20GB, can we reduce the size of EBS volume while creating from snapshot?
        -> No, we can not reduce the size of EBS volume less than snapshot size.
        -> Note: snapshot size <= EBS volume size
    - Root volume: The volume that contains the OS.
        - We can not take snapshot of root volume while instance is running. 
        - We can take snapshot of root volume while instance is stopped.
        - or We can detach the root volume and then take snapshot.
    - Non-root volume: The volume that does not contain the OS.
        - We can take snapshot of non-root volume while instance is running/stopped/detached.
        - Snapshot during running state may slows I/O performance.
        
## EBS Encryption:
    - EBS encryption feature makes it easy to encrypt your EBS volumes.
    - EBS encryption is supported by all EBS volume types.
    - Encrypted EBS volumes volumes snapshots are also encrypted.
      for Non encrypted EBS volume, we can not create encrypted snapshot.
    - Encryption Types:
        - Data at rest: Data stored in EBS volume.
        - Data in transit: Data transfer between EBS volume and EC2 instance.
    - Encryption Ways:
        - At Rest:
            - Use 3rd party EBS Volume encryption tools. eg. BitLocker, etc.
            - Encryption tools provided by AWS. eg. KMS, etc.
            - Use Encrypted EBS volumes. (Default)
            - Use Encryption at OS level. eg. BitLocker, etc.
            - Encrypt data at application level before storing in EBS volume. eg. encrypt data before storing in database.
            - Use encrypted file system on top of EBS volume. eg. EFS, FSx, etc.
    - We can attach both together encrypted and non encrypted EBS volume to EC2 instance.

# KMS Key: (EBS Encryption Key)
    - AWS Key Management Service (KMS) is a managed service that makes it easy for you to create, control, and manage encryption keys used to encrypt your data.
    - KMS keys are region specific.
    - KMS keys are used to encrypt and decrypt data.
    - To encrypt a EBS volume, we need to create a KMS key, these keys are called **Customer Master Keys (CMK)**.
    - When encryption the first EBS Volume, AWS will create a default KMS key for you. This key should not share with anyone.
    - Same EBS Volume key is needed to encrypt and decrypt the Snapshot.
    - Changing Encryption key of EBS volume:
        - Once EBS volume is encrypted, we can not change the encryption key of EBS volume.
        - To change the encryption key of EBS volume, we need to create a snapshot of EBS volume and then create a new EBS volume using that snapshot with new encryption key.

## EBS Snapshot Sharing:
    - By default, only the account owner can create a volume from a snapshot.
    - We can not make Encrypted snapshot 'public'.
    - By making unencrypted shapshot Public, you can share the snapshot with other AWS accounts.
    - You can share encrypted snapshot with other AWS accounts:
        - Note: We can not share default CMK key.
        - By sharing the KMS key with other AWS accounts. (Non Default CMK Key)
        - Configure 'cross account permission' to use the KMS key.

# AMI:
    - Amazon Machine Image (AMI) is a special type of pre-configured operating system and virtual application software which is used to create a virtual machine within the Amazon Elastic Compute Cloud (EC2).
    - AMI is a template that contains a software configuration (for example, an operating system, an application server, and applications).
    - AMI is used to launch EC2 instances.
    - AMI is region specific.
    - AMI is stored in S3.
    - AMI can be public or private.
        - Private AMI: Only the owner can use the AMI.
        - Public AMI: Anyone can use the AMI.

## Lab: Create AMI and Launch EC2 instance using AMI:
    - Create and Launch an EC2 instance 1 (any OS). (save key pair password)
    - Select EC2 instance -> Actions -> Image and templates -> Create image.
    - Created AMI will show in AMI section. (wait for AMI status to be available)

    Q. How to launch AMI as EC2 instance in different region?
        - Select AMI -> Actions -> Copy AMI -> Select destination region (eg. Mumbai) -> Copy AMI.
        - Go to Mumbai region -> AMI -> Select copied AMI -> Launch instance. 
            - Note: You can create new key pair, but you can not access EC2 using new created key pair. 
            -> So, use the same key pair which was used to create the AMI. (old key pair password)
        - Done.
    Q. How to launch AMI as EC2 instance in different account?
        - (Account 1) Select AMI (public/private) -> Actions -> Modify Image Permissions -> Add AWS account ID (Account 2) , Also 'Create Volume Permissions' -> Save.
        - (Account 2) Go to AMI -> Private Images -> You will see the shared AMI:
            - Copy AMI: if account 1 will remove the permission, then you will not see the AMI. So, Always copy the shared AMI to your account.
            - Now, Launch the instance using copied AMI or shared AMI in Account 2.

## Lab: attach root volume with another EC2 Instance:
    - (Instance 1) Create and Launch an EC2 instance 1 (any OS). (save key pair password)
        - select root volume -> Delete on termination: Yes
        - Copy the root Volume directory address. (eg. /dev/sda1)
    - Snapshot section -> Create snapshot 
        -- You can take snapshot of:
            Instance: root volume + non-root volumes of the instance. (all attached volumes)
            Volume: non-root volume of the instance.
        -- You can create snapshot of root volume while instance is running/stopped/detached. Recommended way is to stop the instance and then create snapshot, because it may slow down the I/O performance and data consistency.
    - Snapshot section -> Create snapshot:
        - select resource type: Instance   // Instance/Volume
        - select instance: Instance 1
        - Done. (wait for snapshot to be completed)
    - terminated the instance 1. (root volume will be deleted)
    - Create Volume from snapshot:
        - Go to snapshot -> select snapshot -> Actions -> Create Volume
        - Volume type: General Purpose SSD (gp2)
        - Volume size: 8GB // >= root volume size
        - Availability Zone: same as instance 1
        - Subnet: same as instance 1
        - Done.
    - (Instance 2) Create and Launch an EC2 instance 2.
    - Volume section:
        - Now, here you will see 2 volumes: 
            1. (Volume 1) root volume (created from snapshot of instance 1)
            2. (Volume 2) Instance 2 root volume

    Q. How to attach root volume with another EC2 Instance?
        -- Instance 2: release the root volume (Volume 2) and attach the root volume (Volume 1) to Instance 2.
        - Note: Availability Zone of both instances/volumes should be same.
        1. Stop the instance 2.
        2. Volume 2 -> Actions -> Detach Volume -> Yes, Detach // EC2
        3. Volume 1 -> Actions -> Attach Volume
            - Instance: Instance 2
            - Device: /dev/sda1  // instance 1 root volume directory
            - Done.
        4. Start the instance 2.