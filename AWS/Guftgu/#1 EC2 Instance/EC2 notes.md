AWS EC2:
    - Elastic Compute Cloud (EC2) is a web service that provides resizable compute capacity in the cloud. It allows users to rent virtual servers (instances) to run applications and services.
    - Mainly consist:
        - instances: Renting virtual servers
        - EBS: Storing data on virtual (drive) hard disks
        - ELB: Distributing incoming traffic across multiple instances
        - ASG (Auto Scaling Group): Automatically adjusting the number of instances based on demand
    - Configuration:
        - OS
        - CPU
        - RAM
        - Storage space:
            - network attached (EBS & EFS)
            - hardware (EC2 instance store)
        - Network card: speed of the card, Public IP address
        - Firewall rules: security groups and network ACLs
        - Bootstrap scripts:
            - launching commands when a machine starts for the first time
            - installing software or updates, configuring settings, etc.
            - The EC2 user data scripts runs with the root user privileges, so it can perform any actions that the root user can perform on the instance.

User Data script for AWS Linux 2:
```
#!/bin/bash
# Use this for your user data (script from top to bottom)
# install httpd (Linux 2 version)
yum update -y
yum install -y httpd
systemctl start httpd
systemctl enable httpd
echo "<h1>Hellow World from $(hostname -f)</h1>" > /var/www/html/index.html
```

Note:
    - private IP of instance wont change but Public IP may change when stop and start the instance again. To avoid this, you can use Elastic IP (EIP) which is a static public IP address that can be associated with an EC2 instance.

SSH Summary table: Connect to EC2:
    SSH: MAC, Linux, and Windows 10 or 10+
    Putty: All windows versions
    EC2 Instance Connect: Browser-based SSH client, no need for key pair, works with Amazon Linux 2 or Ubuntu instances.

Instance Types:
    - Overview:
        - m5.2xlarge
            - m: instance type
            - 5: generation (AWS improves them over time)
            - 2xlarge: size within the instance class ()
    - Types:
        - General Purpose:
            - diversity of workloads such as web server or code repositories
            - Balance between compute, memory, and networking resources
            - Instance types: Mac, T, M, A1
        - Compute Optimized:
            - compute0 intensive tasks that require high performance processors
            - uses:
                - Batch processing workloads
                - Media transcoding
                - High performance web servers
                - High performance computing (HPC)
                - dedicated gaming servers
                - scientific modeling and machine learning
                - Instance types: C series as C5, C6g, C6i
        - Memory Optimized:
            - fast performance for workloads that process large data sets in memory
            - uses:
                - High performance, relational/non-relational databases
                - Distributed web scale in-memory caches
                - In-memory databases optimized for BI business intelligence
                - Applications performing real time processing of big unstructured data
                - Instance types: 
                    - R series as R5, R6g, R6i
                    - X series as X1, X1e
                    - z1d
        - Storage Optimzied:
            - great for storage intensive tasks that require high, sequential read and write access to very large data sets on 'local storage'
            - uses:
                - High frequency online transaction processing (OLTP) systems
                - Relational and NoSQL databases
                - Data warehousing applications
                - Cache for in-memory databases (Redis)
                - Distributed file systems
            - Instance types:
                - D series as D2, D3, D3en
                - H series as H1
                - I series as I3, I3en
        - Accelerated Computing
        - HPT Optimized
        - Instance Fetures
        - Measuring Instance Performance

Putty:
    - Install putty and also save EC2 putty gen file
    - Puttygen:
        - load the .pem file and 'save as private key' and save as .ppk format
    - Putty:
     