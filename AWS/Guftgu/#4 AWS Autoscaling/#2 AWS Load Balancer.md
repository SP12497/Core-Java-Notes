# AWS Load Balancer:
    - High Availability and Fault Tolerance
    - Load Balancer distributes the web traffic to the available servers.
    - Load Balancing refers to efficiently distributing incoming network traffic across a group of backend servers.
    - Region specific
    - Hourly Charges
    - Before deleting the Load Balancer, it is recommended that, you point the route 53 to somewhere else other than the ELB.
    - Deleting the Load Balancer will also delete the DNS name associated with it. But it will not delete the EC2 instances that are registered with the ELB.
    - By default, ELB forwards traffic to "Ethernet 0" of your registered EC2 instances.
    - In case the EC2 registered instances has multiple IP addresses on ethernet 0, then the ELB will forward the traffic to the Primary IP address of the EC2 instance.
    - Supports IPv4, IPv6, and Dual Stack (both together).
    - Load balancer can be associated with only 1 subnet in each AZ.
    - ELB name should be unique within the AWS account.
    

## Types of Load Balancer:
    - Application Load Balancer (ALB) : 
        - Layer 7 (Application Layer)
        - Protocol: HTTP, HTTPS, WebSockets
        - Benefits over CLB:
            - Routing based on URL
            - Routing based on Host
            - Routing based on HTTP Headers
            - Routing based on Query String
            - Routing based on Method
            - Routing based on Path
            - Routing based on Source IP
            - Routing based on User-Agent
    - Network Load Balancer (NLB) : 
        - Layer 4 (Transport Layer)
        - Protocol: TCP, TLS, UDP
        - Benefits over ALB:
            - High Throughput
            - Low Latency
            - Static IP
            - Preserve Source IP
    - Classic Load Balancer (CLB) : (Previous Generation)
        - Layer 4 and Layer 7
        - Protocol: HTTP, HTTPS, TCP, SSL 


# Application Load Balancer (ALB):
    - Layer 7 (Application Layer)
    - Protocol: HTTP, HTTPS, WebSockets

# Parts of ALB:
    - Listeners:
        - A listener is a process that checks for connection requests.
        - It is configured with a protocol and a port for connections from clients to the load balancer.
    - Target Groups
        - A target group is a group of resources, such as EC2 instances, that you register with a load balancer.
    - Target
        - A target is a destination for traffic based on the target group.
        - Types of Targets:
            - EC2 Instances
            - IP Addresses:
                - We can register IP addresses with the target group.
                - We can not use Public IP addresses.
                - It have Fixed CIDR Range. We can only use below ranges:
                    - 10.0.0.0/8
                    - 100.64.0.0/10
                    - 172.16.0.0/12
                    - 192.168.0.0/16
            - Lambda Functions
            - Containers
    - Health Checks
    - Rules

## ELB Listener:
    - A listener is a process that checks for connection requests.
    - It is configured with a protocol and a port for connections from clients to the load balancer.
    - AWS Elastic Load Balancers (ELB) should always be accessed using their DNS name, not by IP address.
    - 4 Types of Listeners:
        - HTTP: 80
        - HTTPS: 443
        - TCP: 22
        - SSL: 443
    - FrontEnd Listener:
        - It is the listener that listens for incoming requests from the clients.
    - BackEnd Listener:
        - Distributed traffic to the target groups (EC2 Instances).
        - Backend listners are configured with protocol and port to check for traffic from the ELB to the EC2 instances.
        - It is the listener that listens for incoming requests from the target groups.

## ALB:
    - It may take sometime for the registration of the EC2 instances under the ELB to complete.
    - It is a regional service.
    - (EC2 initiated api calls will not go through ELB) ELB has nothing to do with the outbound traffic that is initiated/generated from the registered EC2 instances destined to the internet or to any other instances withing the VPC.

# Part 3: How Load Balancer finds unhealthy instances.
https://youtu.be/yLd4peEUmpQ?si=VLayRyg_sdZpBajQ

## AWS ELB Subnet and Availability Zone (AZ) Association:
    - *ELB can be associated with only one subnet per Availability Zone (AZ). Subnet can be Public or Private.
    - Min subnet CIDR range should be /27. -> 193.168.10.0/27
      At least 8 IP addresses are required for the ELB to function properly.
    - eg. IP: 193.168.10.0/27
        - Cidr range is : 2^5 = 32 IP addresses
        - first 4 IP addresses are reserved for AWS. And last IP address is reserved for broadcast. => 32 - 5 = 27 IP addresses.
        - 8 IP addresses are required for the ELB to function properly. So, 27 - 8 = 19 IP addresses are available for the ELB to use.
        - We can create 19 EC2 instances in this case.
        -- In case of /28, 16 - 5 - 8 = 3 IP addresses are available for the ELB to launch EC2 instances. We can not create more than 3 EC2 instances in this case.
    - for fault tolerance, it's recommended that you distribute your registered EC2 instances across multiple AZs withing the VPC region.
    - If possible, try to allocate same no of register instances in each AZ.
      eg. ELB will distribute the traffic by AZ zone wise.
          Registered AZ->  AZ1: 4 instances, AZ2: 1 instance
          ELB will send traffice 50% to AZ1 and 50% to AZ2. Hence AZ2 instance will be overloaded. 
          To overcome this, we should have same no of instances in each AZ.
### Key Points:
- **Single Subnet per AZ**: AWS Elastic Load Balancers (ALB, NLB, CLB) can be associated with only **one subnet per Availability Zone (AZ)**.
- **Multi-AZ Deployment**: ELBs support multi-AZ deployment for high availability and fault tolerance, but in each AZ, only one subnet is allowed.
- **AZ Selection**: When creating or updating an ELB, you can choose multiple AZs, but only one subnet per AZ will be utilized for routing traffic.
- **IPv4 and IPv6 Support**: ELBs can handle both IPv4 and IPv6 traffic, and each associated subnet in an AZ can be configured to support either or both.
  
#### Best Practices:
- Distribute the ELB across multiple AZs to improve availability and resilience.
- Use distinct subnets in each AZ to ensure proper traffic distribution.


## Health Checks:
    - Health checks are used to determine the health of the registered EC2 instances.
    - Health checks are performed by the ELB on the registered EC2 instances at regular intervals.
    - ELB sends a HTTP/HTTPS request to the registered EC2 instances to check their health. If status code is 200, then the instance is healthy.
    - If the health check fails, the ELB will stop sending traffic to the unhealthy EC2 instances.
    - If the health check passes, the ELB will start sending traffic to the healthy EC2 instances

    - AWS uses ping TCP (port 80) for health checks.
    - Success Codes: 200,201
    - Response time-out: is 30 seconds. (Range is 2-120 seconds)
    - Health check interval:  is 35 seconds. (Range is 5-300 seconds)
    - Unhealthy threshold: Number of consecutive failed health checks before the instance is declared unhealthy. 
        - Default is 2. (Range is 2-10)
    - Healthy threshold: Number of consecutive successful health checks before the instance is declared healthy.
        - Default is 5. (Range is 2-10)
    - eg. ELB will send a health check request to the registered EC2 instances by HTTP protocol on port 80 every 30 seconds interval.
      if ELB does not receive a response within 5 seconds time-out period, then the health check will be considered as failed.
        - If the health check fails for 2 consecutive times, then the EC2 instance will be considered as unhealthy.
        After that, ELB will stop sending traffic to the unhealthy EC2 instance.
        - If the health check passes for 10 consecutive times (returning 200 status code 10 times), then the EC2 instance will be considered as healthy.

## Cross Zone Load Balancing:
    - By default, ELB distributes the traffic evenly across the registered EC2 instances in the same AZ.
    - Cross Zone Load Balancing allows the ELB to distribute the traffic evenly across the registered EC2 instances in all the AZs within the VPC.
    - ALB: Cross Zone Load Balancing is enabled by default.
      NLB/CLB: Cross Zone Load Balancing is disabled by default.
    - eg. registerd AZs -> AZ1: 4 instances, AZ2: 1 instance
      100% divided by 5 instances = 20% traffic to each instance.

## Types of ELB Facing:
    - #2.2 ELB Types.png
    - An ELB can be Internet facing or Internal facing.
    - Internet facing ELB:
        - It is accessible from the internet.
        - It routes the incoming traffic from the internet to the registered EC2 instances.
        - It must have public IP addresses.
        - DNS will resolve the ELB DNS name to the public IP address.
        - You need one "Public Subnet" in each AZ to create an Internet facing ELB, because it requires public IP addresses to route the traffic from the internet.
        - format of the public ELB DNS name of internet facing ELB: <DNS name>.<region>.elb.amazonaws.com
        - You must assign a security groups to your ELB. This will control the traffic that can reach your ELB front end listener.
    - Internal facing ELB:
        - It is not accessible from the internet.
        - It routes the incoming traffic from the internal network to the registered EC2 instances.
        - It must have private IP addresses.
        - DNS will resolve the ELB DNS name to the private IP address.
        - You need one "Private Subnet" in each AZ to create an Internal facing ELB, because it requires private IP addresses to route the traffic from the internal network.
        - format of the private ELB DNS name of internal facing ELB: <DNS name>.<region>.elb.amazonaws.com

## Target Groups:
    - Target groups are used to route the incoming traffic to the registered EC2 instances.
    - Target groups are associated with the listeners of the ELB.
    - Target groups are used to route the incoming traffic to the registered EC2 instances based on the rules defined in the listeners.
    - Logical grouping of the targets behind the load balancer. Target can be EC2 instances, IP addresses, Lambda functions, or containers.
    - Target Group can be exist independently from the Load Balancer.
    - Target Group can be associated an auto scaling group.
    - ALB:
        - Targets per ALB: Up to 1,000 targets per load balancer.
        - Targets per Target Group: Each target group can support up to 1,000 targets.
        You can register EC2 instances, IP addresses, or Lambda functions as targets.
    NLB:
        - Targets per NLB: Up to 500,000 connections per minute and 100,000 new connections per second.
        - There isn't a strict limit on the number of targets like ALB, but the scalability is designed to handle very high throughput. However, the typical target registration limit is 1,000 targets per target group.
    - Target Types:
        - EC2 Instances
        - IP Addresses
        - Lambda Functions
        - Application Load Balancer


# Lab 1: Application Load Balancer (ALB) with EC2 Instances:
    - AZ1 : create 1 EC2 instance (select AZ1 subnet) (Security groups: Anywhere: RDP, HTTP, HTTPS)
      AZ2 : create 1 EC2 instance (select AZ2 subnet) (Security groups: Anywhere: RDP, HTTP, HTTPS)
    - 
      - connect to both EC2 instances and install Web server IIS on both.
        - open 'Server Manager' -> 'Add Roles and Features
          - Server Roles: Web Server (IIS)
          - Install
        - This PC>C:>inetpub>wwwroot>
            - delete all files
            - create a new file: index.html 
                data: Server 1 hello AZ     // for AZ1
                      Server 2 hello AZ     // for AZ2
    - Create Load Balancer:
        - Application Load Balancer (HTTP/HTTPS):
            Name: ApplicationOnLB
            Scheme: Internet facing
            IP address type: IPV4
            Listeners:
                - HTTP: 80
            VPC: Default VPC
            Availability Zones:
                - Select both AZs subnet
            Security Groups:
                - create new or use existing EC2 security group
            Create Target Group:
                - Target Type: Instance
                -- Register targets: choose created EC2 isntances
            Done
    - Copy 'ApplicationOnLB' Load Balancer DNS Name
    - Test: Search DNS name in browser. It should show the data from both servers alternatively.

    - Now create 1 more EC2 instance in AZ3, and do the setup (create index.html file with data: Server 3 hello AZ)
    - Update AZ3 in Load Balancer
    - Add new EC2 instance to Target Group
    - Test: Search DNS name in browser. It should show the data from all 3 servers alternatively.

    Now, delete:
        - Load Balancer
        - Target Group
        - EC2 instances Terminate

# Lab 2: Network Load Balancer (NLB)
    - Steps Overview:
        - 1. Create 2 EC2 instances in 2 different AZs.
        - 2. connect RDP -> WEB Server IIS -> index.html file with data: Server 1 hello AZ
        - 3. Create Network Load Balancer (NLB)
        - 4. Create Target Group : Register Private IP's of all EC2 instances

    Step 3: Create Load Balancer:
        - Network Load Balancer (TCP, TLS, UDP):
            - Name: NetworkOnLB
            - Scheme: Internet facing
            - Load Balancer Protocol: TCP (80)
            - Availability ZOnes:
                - Select both AZs subnet
            - Create New target Groups:
                - Name: NLBTargetGroup
                - Target Type: IP
                - Protocol: TCP
                - Port: 80
                - Health checks:
                    - Protocol: TCP
                - Register Targets: Update private ips of both EC2 instances
            - Done
    Test: Search DNS name in browser. It should show the data from both servers alternatively.

# Lab 3: How to establish a load balancer between two VPC?
Steps:
    1. Create 2 VPCs (VPC1: 10.0.0.0/16, VPC2: 192.168.0.0/16)
    2. Create Subnets in each VPC (VPC1: subnet1: 10.0.1.0/24, subnet2: 10.0.2.0/24  | VPC2: 192.168.1.0/24)
       Note: To create a Load Balancer in a VPC1, you need at least 2 subnets VPC1.
    3. Create Internet Gateway in each VPC (VPC1: igw1, VPC2: igw2)
    4. Create Route Table in each VPC and (VPC1: 0.0.0.0/0=> igw1, Subnet Association: Select Both Subnets   |  VPC2: 0.0.0.0/0=> igw2, Subnet Association: Select a Subnet)
    5. Create VPC Peering Connection between 2 VPCs and Update Route Table in each VPC point to each other. (VPC1 route table: 192.168.0.0/16-> VPC2 Peering Connection ID | VPC2 route table: VPC1 Peering Connection ID)
    6. Create EC2 instances in each VPC (min 1 in each subnet) (Security groups: Anywhere: RDP, HTTP, All ICMP-IPv4)
    7. Install Webserver IIS on all EC2 instances and create index.html file.
    8. Create Application Load Balancer (ALB) in VPC1 (Target: All 3 instance private IP's)  (Security groups: Anywhere: All ICMP-IPv4, HTTP)
    9. Register Private IP's of all EC2 instances in Target Group
    10. Copy DNS of ALB and test in browser. It should show the data from all servers alternatively.

    # Clean Up:
        - Delete ALB
        - Delete EC2 instances
        - Delete VPC Peering Connection
        - Delete Internet Gateway
        - Delete VPCs


# AWS WAF & Shield: (Web Application Firewall)
[text](https://youtu.be/FHRXXrQ765M?si=Yxf1vqUhhqIy1JPe)
    - #2.3 WAF.png
    - Protects against DDoS attacks and Malicious Web Traffic.
    - Protect your web applications from common web exploits that could affect application availability, compromise security, or consume excessive resources.
    - We can allow or block traffic based on the rules that we define. (eg. Allow only traffic from specific IP addresses)