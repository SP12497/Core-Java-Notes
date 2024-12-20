/*
VPC (Virtual Private Cloud)

Part 1: What is VPC?
-------
[text](https://youtu.be/K7Nxx0YuVxo?si=XdDtEeMr84XV_Hkq)
Defn: A VPC is a virtual network that closely resemble a traditional networking that you operate in your own data centre, with the benefits of using the scalable infrastructure of AWS.
Defn: VPC is virtual network or DataCenter inside AWS of one client.
- https://docs.aws.amazon.com/vpc/latest/userguide/amazon-vpc-limits.html
- It is logically isolated from other virtual network in the AWS cloud.
- In one region, max 5 VPC can be created and 200 subnets in 1 VPC.
- Elastic IP addresses per Region: 5 and adjustable
- Once we created VPC, DHCP, NACL and Security Group will be authomatically created
- A VPC is contained to an AWS region and does not extent between regions.
- Architecture: "#1 VPC Architecture.png"
    - AWS
        - Region
            - Internet Gateway
            - VPC:  cidr - 10.0.0.0/16   // now we cant use same cidr for other VPC in region. // instances can communicate each other in VPC
                - Implied Router
                - Avability Zone-A    cidr- 10.0.0.0/24
                    - Public subnet // can go on internet if custom routing table has 0.0.0.0/0 cidr.
                        - Internet Gateway(Route traffic to internet)  (if we attach IG to Subnet, then subnet becomes public subnet) 
                        - NAT Gateway (only in public subnet) (Using NAT Gateway, private subnet can access internet) (private subnet -> NAT Gateway -> Internet Gateway)
                        - EC2 Instance
                - Avability Zone-B    cidr- 10.0.0.1/24
                    - Private subnet-B    // local
                        - EC2 Instance
- Once the VPC is created, you cannot change its CIDR block range.
- If you need a different CIDR size, create a new VPC.
- The different subnets within a VPC cannot overlap
- You can however expand your VPC CIDR by adding new/extra IP address ranges (Except US GovCloud and AWS China)


4 steps to create VPC:
    - create VPC
        - create subnet
            - create Internet Gateway
                - Create Route table

Components Of VPC:
    - CIDR and its address subnets
    - Implied Router and routing
    - Internet Gateway
    - Security Groups
    - Network ACL (NACL)
    - Virtual Private Gateway
    - Peering Connections
    - Elastic IP

Real Life Example:
1. AWS Account:
    Your real estate company.
2. Society (AWS Region):
    Each society is located in a different city. For example, one society is in New York, another in San Francisco, etc.
3. Apartment Building (VPC):
    Each society (city) has multiple apartment buildings. Each apartment building is a Virtual Private Cloud (VPC). For instance, Building A and Building B in the New York society.
4. Floor (Availability Zone):
    Each apartment building has several floors. Each floor represents an Availability Zone within the VPC. For example, Floor 1, Floor 2, and Floor 3 in Building A.
5. Flat (Subnet):
    Each floor has multiple flats (apartments). Each flat represents a subnet. For instance, Flat 101, Flat 102, etc., on Floor 1.
6. Room within a Flat (EC2 Instance):
    Each flat (subnet) contains several rooms. Each room represents an EC2 instance. For example, the bedroom, living room, and kitchen in Flat 101.
7. Electricity System for Flat (Network ACLs):
    Each flat has an overarching electricity system that ensures the proper flow of electricity to the entire flat. This represents Network ACLs, which control traffic at the subnet level.
8.  Individual Room's Electrical Outlets (Security Groups):
    Each room has its own electrical outlets and breakers that control the flow of electricity to that particular room. This represents Security Groups, which control traffic at the EC2 instance level.
9.  Main Entrance of the Apartment Building VPC (Internet Gateway):
    The main entrance to the apartment building, allowing external visitors (internet traffic) to enter and exit. This represents the Internet Gateway, allowing VPC resources to communicate with the internet.
10. Apartment Building's Internal Mail System (Router and Route Table):
    The apartment building has an internal mail system (router) with a detailed list of addresses (route table) to ensure that mail (network traffic) gets to the correct flat (subnet) or room (EC2 instance). The router directs internal traffic based on the route table.
11. Security Booth at the Main Entrance (NAT Gateway):
    A security booth at the main entrance that allows residents to send mail to the outside world while blocking unsolicited external mail from entering directly. This represents the NAT Gateway, allowing instances in a private subnet to connect to the internet while preventing the internet from initiating connections.
12. Secure Tunnel to Another Society (Virtual Private Gateway):
    A secure tunnel connecting the apartment building to another society, allowing residents to communicate privately and securely with another location. This represents the Virtual Private Gateway, which connects the VPC to an on-premises network or another VPC.

Part 2: VPC And Subnets
-------
[text](https://youtu.be/tXJiWva4lsQ?si=3mioBACxSxOhbkpb)
2 Types of VPC:
    1. Default VPC:
        - created in each aws region when an aws account is created
        - Has default CIDR, security group, NACL and route table settings.
        - Has an Internet gateway by default.
    2. Custom VPC:
        - Is a VPC on AWS account owner creates 
        - AWS user creating the custom VPC can decide the CIDR.
        - Has its own default security group, NACL and route tables.
        - Does not have an Internet Gateway by default, One need to be created if needed.

Name	                        Default	        Adjustable
- VPCs per Region	                5	            Yes
- Subnets per VPC	                200	            Yes
- IPv4 CIDR blocks per VPC	        5	            Yes (up to 50)
- IPv6 CIDR blocks per VPC	        5	            Yes (up to 50)


Subnet:
    - For example, House is VPC and Rooms in house are Subnets.
 Types of Subnets:
    - Public Subnet:
        - If a subnet's traffic is routed to an Internet Gateway (outside AWS account), the subnet is known as public subnet.
        - If you want your instance in a Public subnet to communicate with the internet over IPv4, it must have a public IPv4 address or an Elastic IP address.
    2. Private Subnet:
        - If a subnet does not have a route to the Internet Gateway, the subnet is knows as Private Subnet.
        - (Important) When you create a VPC, you must specify an IPv4 CIDR block for the VPC. The allowed block size is betweed /16 to /28 network.
        - The first four and last IP address of subnet cannot be assigned.
        eg. CIDR: 10.0.0.0/16 => total 65536 - 5 reserved = 655331 Available.
            - 10.0.0.1 = network addresss
            - 10.0.0.2 = reserved by AWS for the VPC route.
            - 10.0.0.3 = reserved for future use.
            - 10.0.0.4 = reserved by AWS for DNS.
            - 10.0.0.255 = Broadcast address (note: AWS do not support broadcast in a VPC but reserve the address)

Part 3: Implied Router, Route Table and Internet Gateway. 
-------
[text](https://youtu.be/OKuPIlVGEjg?si=9YUTv9NqCPHt0Uz8)
Implied Router, Route Table:
    - It is the central routing function
    - It connects the diferent Availability zones together and connects the VPC to the Internet Gateway.
    - You can have upto 200 routes table per VPC.
    - You can have upto 50 routes entries per route table (200 * 50 = 10000)
    - (IMP) each subnet must be associated with only one route table at any Given time. (1 subnet -> 1 route)
    - You can associate multiple subnets with the same route table (1 route -> multiple subnets)
    - If you do not specify a subnet to route table association, the subnet will be associated with the default VPC route table.
    - You can also edit the Main route table if you need, but you cannot delete Main route table. (By default each VPC have main route table)
    - However you can make a custom route table manually become the Main route table then you can delete the former main, as it is no longer a main route table.

Internet Gateway:
    - An Internet Gateway is a virtual router that connects a VPC to the internet.
    - Default VPC is already attached with an Default Internet Gateway.
    - If you create a new VPC (custom VPC) then you must attach the Internet Gateway in order to access the Internet.
    - Ensure that your subnet's route table points to the internet gateway.
    - It perfoms NAT between your private and public IPv4 address.
    - It supports both IPv4 and IPv6.

Part 4: NAT Gateway, Security Groups, NACL and VPC Peering
-------
[text](https://youtu.be/wVqrZjF1ics?si=6BHMdZIYwOo73Tsk)

NAT Instance:
    - Network Address Translation (NAT)
    - In Nat instance, we can use public or elastic ip's.
    - But in NAT Gateway, we can only use elastic ip's.

NAT Gateway: (NAT instance is different service) (#2 Lab on VPC NAT GW.png)
    - Its in public subnet
    - An AWS NAT Gateway allows private subnets to initiate outbound connections to the internet or other AWS services but does not accept any inbound connections from the internet. This means it only facilitates outbound traffic from the private subnet to external destinations.
        - Outbound-Only Traffic: Private resources can send requests out (e.g., accessing the internet or other services) through the NAT Gateway.
        - No Inbound Connections: The NAT Gateway does not accept inbound traffic from external sources. It is purely for allowing private subnet resources to initiate traffic.
        - In essence, the NAT Gateway only supports one-way outbound traffic for resources within a private subnet.
    - When private subnet instance wants to communicate over internet, subnet instance have private IP and subnet private gateway will call/request NAT gateway in public subnet,
      convert our private ip to public ip to send over internet or vise versa, request from public ip send to our private gateway
    - You can use a network address translation gateway (NAT Gateway) to enable instances in a private subnets to connect to the internet or other AWS services, but prevent internet from initiating a connection with those instances.
    eg. db server stored in private subnet and we have to update the DB version, so we have to take update from internet.
    Private Subnet (route table 0.0.0.0./0=>public subnet NAT ) --> Public Subnet NAT (route table 0.0.0.0./0=>logical id ) => Internet Gateway 
    - You are charged for creating and using a NAT gateway in your account. NAT Gateway hourly usage and data processing rates apply. Amazon EC2 changes for data tranfer also apply.
    - To create NAT gateway, you must specify the public subnet in which the NAT Gateway should reside.
    - You must also specify an elastic ip address to associate with nat gateway when you create it.
    - No need to assign public ip address to your private instance. (if public ip is present in private subnet, then private subnet can directly communicate over an internet and it becomes public subnet).
    - After you have created a NAT gateway, you must update the route table associated with one or more of your private subnets to point internet bound traffic to the NAT gateway. This enables instances in your private subnet to communicate with the internet.
    (update route table of all private subnet 0.0.0.0/0 -> NAT Gateway presents in public subnet).
    - Deleting a NAT gateway, disassociates its elastic ip address, but does not release the address from your account. You are charged for the elastic ip address as long as it is allocated to your account.
    - NAT Gateway Connectivty types:
        - Public: 
            Directly connected to internet gateway for internet access.
            Can goes to internet or communicate with other AWS services (other VPC).
            - Need to have Elastic IP.
        - Private: 
            Can not go to internet and only communicate with other AWS services (other VPC) via NAT Gateway.
    - Scenario:
        - To allow private subnet to access internet, we need to register Internet Gateway in Route Table of Private Subnet. 
          This makes private subnet as public subnet, so to avoid this, we use NAT Gateway.
        - NAT Gateway is used to allow private subnet to access internet without making it public subnet.
        - Will create NAT Gateway in Public Subnet and update Route Table of Private Subnet to point to NAT Gateway.
          Now, private subnet can access internet without making it public subnet.
    - Lab:
        - Create VPC, 2 Subnet, 2 EC2 instance, 2 Route Table, 1 IG for Public subnet and 1 NAT Gateway.
        - Nat Gateway:
            - subnet: public
            - Elastic ip is mandatory for NAT GW: create new Elastic IP
        - Update route table:
            - Public Subnet:
                - Destination: 0.0.0.0/0
                  Target: Select created IG
            - Private Subnet:
                - Destination: 0.0.0.0/0
                  Target: select created NAT GW
    - Test:
        Connecct to private EC2 instance and try to access internet. (cmd: ping www.google.com)

Security Groups: (#3 Security Groups architecture.png)
    - inside subnets but on top of instances. EC2 instance level.
    - It is a virtual firewall works at ENI Level(#4 ENI Dirgram.png : on top of hipervisor)
    - What is ENI Level? -> ENI is Elastic Network Interface, it is a virtual network card that you can attach to an instance in a VPC. It can have one or more private IP address, one primary private IP address that is used as the instance's source/destination in the network.
    - ENI Level: means, it is a firewall that works at the network interface level.
    - Upto 5 security groups per EC2 instance interface can be applied.
    - Can only have allows/permit rules, can't have deny rules(by default other that allowed are deny)
    - Stateful (if inbound(request) port is 80 so by default outbound post is also 80. No need to declare separately), return traffic, of allowed inboud traffic is allowed, even if there are no rules to allow it.
    - NACL are stateless outbound traffic for an allowed inbound traffic must be explicitly allowed too.

NACL:   (https://docs.aws.amazon.com/vpc/latest/userguide/vpc-network-acls.html) (#5 NACL points to 2 subnet.png | #6 NACL= 1 subnet ponints to 2 NACL not possible.png)
    - placed at Router level, Inside VPC, its on all Subnet level, not bound/tied to specific availability zone.
    - 1 NACL can point multiple subnets in any availability zones within VPC  (#5 NACL points to 2 subnet.png)
      but 1 subnets can not point more than 1 NACL (#6 NACL= 1 subnet ponints to 2 NACL not possible.png)
    - You can associate a NACL with multiple subnet, however a subnet can be associated with only one NACL at a time. When you associated a NACL with a subnet the previous association is removed.
    - It is a function perfomed on the implied router
    - NACL is an optional layer of security for your VPC that acts as a firewall for controlling traffic in and out of one or more subnets.
    - Your VPC automatically comes with a modifiable default NACL. by default, it allows all inbound and outbound IPv4 traffic and if applicable, IPv6 traffic.
    - You can create a custom NACL and associate it with a subnet. By default, each custom NACL denies all inbould and outbound traffic until you add rules.
      IMP Diff: Default NACL: by default allow all traffic | Custom NACL: by default deny all traffic
    - Each subnet in your VPC must be associated with a NACL. If you don't explicitly associate a subnet with a NACL, the subnet is automatically associated with the default NACL.
    - A NACL contains a numbered line of rules that we evaluate in order, starting with the lowest numbered rule.
     eg. sequence of execution => Rule 1 (port 80 allowed) -> Rule 50 (port 80 deny) -> Rule 77 (here, post 80 call will not come) -> Rule 120
    - The highest number that you can use for a rule is 32766. Recommended that you start by creating rules with numbers that a multiple of 100, So that you can insert new rule where you need later.
    - It functions at a Subnet level.
    - NACL are stateless outbound traffic for an allowed inbound traffic must be explicitly allowed too.
    - You can have allows/permit and deny rules in NACL. (But in Security group, only have allows rules).

Real Life example:
    - For example, House is VPC and Room in house is Subnet.
    - Main door of house is NACL. (security for all rooms door. Allow and deny rule.)
    - Each room door inside house in Security Groups (only for 1 room(1 or more EC2 instance, max 5 Security group) only allow rules ).
Different Between Security Groups and NACL:
    - Security Groups:
        - Operate at instance level.
        - Support allows rules only.
        - Stateful, retun traffic is automatically allowed.
        - Applies to an instance only.
    - NACL: 
        - Operate at the subnet level
        - It permits allow as well as deny rules.
        - Stateless, return traffic must explicitly allowed by rules.
        - Applies to all instance in the subnet.

VPC Peering: (#7 VPC Peering.png)
    - A VPC peering connect is a networking connection between two VPC that enables you to route traffic between them using private IPv4/IPv6 address.
    - Instance in either VPC can communicate with each other as if they are withing the same network.
    - You can create a VPC peering connection between your own VPC, or with a VPC in another AWS account. The VPC can be in different region.
    - Transitive peering not possible in AWS.
      eg. VPC A --communicating--> VPC B | VPC B -> VPC C
        So, it doesn't mean VPC A can communicate VPC C.


Demo 1: Creating VPC,Subnets,Route table
-------
https://youtu.be/v01oaZBRRpY?si=FOzY-0Mh_DcpZSB0

4 steps to create VPC:
    1. create VPC:
        cidr: 10.0.0.0/16
        tenancy: Default
    2. create subnet
        - choose created vpc and availability zone
        cidr: 10.0.0.0/25       // upto /28
    3. create Internet Gateway
        - create Gateway
        - once created, attach vpc:
            - select gateway -> attach to VPC
    4. Create Route table:
        - select created vpc while creating.
        - Subnet Associations:
            - select created subnet and save.
        - Route: 
            - Destination: 0.0.0.0/0
              Target: Select created IG
Testing: 
    Create Ec2 instace:
        windows t2 micro r2 base:
        network: "created VPC"
        Subnet: "created Subnet"
        Auto-assign public IP: Enable
        Security Group: RDP -> Source(Anywhere)
    - Connect to instace:
        open ec2 cmd:  
            "ping 8.8.8.8"   // if it returns response then EC2 is connected to Internet Gateway

Demo 2: Lab On VPC NAT Gateway: (#2 Lab on VPC NAT GW.png)
-------
    1. create VPC:
        cidr: 10.0.0.0/16
        tenancy: Default
    2. create subnet
        - Public Subnet:
            - choose created vpc and availability zone
            cidr: 10.0.0.0/24      // upto /28
        - Private Subnet:
                cidr: 10.0.1.0/24      // upto /28
    3. create Internet Gateway
        - create Gateway
        - once created, attach to vpc:
            - select gateway -> attach to VPC
    4. Create 2 Route table:
        - Route table 1: Public Route Table for Public Subnet:
            - Subnet Associations:
                - select created Public Subnet and save.    // Public subnet can go over internet
            - Route: 
                - Destination: 0.0.0.0/0
                  Target: Select created IG
        - Route table 2: Private Route Table for Private Subnet::
            - Subnet Associations:
                - select created Private Subnet and save.

Testing: 
    Create 2 Ec2 instace: #1 in Public and #2 in Private Subnet:
        windows t2 micro r2 base:
        network: "created VPC"
        Subnet: "Public Subnet" and "Private Subnet"
        Auto-assign public IP: Enable(for public Subnet) | Disable(Private Subnet)
        Security Group: RDP -> 
            Public | Private: RDP | HTTP | HTTPS -> Source(Anywhere)
    Validation:
        - We don't have public IP in private Gateway, so we cant connect private subnet EC2 instance from our local machine,
          but we can access by public subnet EC2 instance
        - Connect to public subnet EC2 instance:
            cmd: "ping 8.8.8.8"
            Connect to Private EC2:
                Search "Remote Desktop Connection"
                    - insert "Private EC2 Instance private IP"
                    - username: administrator
                      password: get from generated ec2 key pair file
                    - cmd: "ping 8.8.8.8 -t"    (Wont go to internet, because NAT Gateway is not present)
                    Conslusionprivate subnet route table dont have address to go to internet.

  How to Access Internet in Private Subnet?
    - Create NAT Gateway:
        - subnet: public
            Elastic ip is mandatory for NAT GW: create new Elastic IP
    - Update route table of Private subnet:
        - Routes:
            - Destination: 0.0.0.0/0
              Target: select created NAT GW
    - Validation:
        - Now you can access Internet from Private Subnet.

Demo 3: VPC Peering: 2 VPC within same region (Communicate using private IP)
-------
    1. create VPC1 (10.0.0.0/16) | Subnet | IG | Route Table
    2. create VPC2 (192.168.0.0/16) | Subnet | IG | Route Table
    - Peering Connections:
        - Select a local VPC to peer with: select "requester/sender"    // eg. VPC1
        - Select another VPC to peer with: "accepter/receiver"          // eg. VPC2
        - Account: are both are in Same AWS Account?
        - Region: are both are in same AWS region?
    
    - Now Issue after peering connection: VPC peering showing, status is pending access
        -> select and Actions "Accept Request"

    Validation:
        - Create Ec2 instace in both VPC:
            windows t2 micro r2 base:
            network: "created VPC"
            Subnet: created subnet for respective VPC.
            Auto-assign public IP: Enable
            Security Group: RDP -> 
                Public: RDP -> Source(Anywhere)

        - Connect to public subnet EC2 instance:
            - VPC 1 (requester)
                - private IP: 10.0.0.93
                cmd: "ping 192.168.0.64 -t"     // call VPC 2 Instance (Find the IP in EC2 Instance details)
            - VPC 2 (receiver)
                - privae IP: 192.168.0.64
                cmd: "ping 10.0.0.93 -t"        // call VPC 1 Instance
            - Now here, will not receing the ping yet, to get ping,
                -> Update Security Group of each EC2 instance:
                    - Type: ALL ICMP IPV4   // ICMP : used to ping another vpc from a vpc. // ICMP: Internet Control Message Protocol: used by network devices to send error messages and operational information indicating, for example, that a requested service is not available or that a host or router could not be reached.
                      Source: Anywhere
                -> Update Route Table:
                    Routes:
                        VPC1:
                            - Destination: 192.168.0.0/16
                              Target: VPC Peering
                        VPC2:
                            - Destination: 10.0.0.0/16
                              Target: VPC Peering
                -> Now, cmd ping will receive ping.

----------
Demo 4: VPC Peering across Two Region: (Communicate using private IP)
    - https://youtu.be/Evj6DQr6gwM
    - create VPC and EC2 instance (security group have ICMP) in 2 Regions (reference: Demo 3). eg. Tokyo and Mumbai.
        - Update Routing table of both VPC: 
            Destination: set "private IP" of another vpc route
            Peering: (pcx-*) vpc peering
    - Go to any region and create peering:
        - Tokyo Region:
            Create peering :
                requester: TokyoVPC
                accepter:
                    region: Mumbai
                    vpc: Mumbai VPC ID
        - Mumbai Region:
            - Accept VPC Peering request.
            
----------
Demo 5: Lab on "Network ACL's inside my VPC" (https://youtu.be/5pI8IcBTM2s?si=A-CnsDXTcVTMzMXq)
    (#6.2 NACL Practical.png)
    - create VPC, Subnet, Route Table, Internet Gateway and EC2 Instance (Demo 1)
    - Create NACL:
        - VPC Page -> Security Section -> Network ACLs: Create NACL
        - Create NACL:
            Name: CustomNacl
            VPC:  select created VPC 
            click on CREATE button.
        - Subnet Association to NACL: 
            - select created NACL -> Subnet Association -> Edit subnet associations -> Select subnet -> edit button -> done
        - Apply Inbound and Outboud rule:
            Demo 1:
                - Inbound rule:
                    - Rule # : 100
                      Type: RDP (3389)
                      Protocol : TCP
                      Port Range: 3389
                      Source: 0.0.0.0/0
                      Allow/Deny: ALLOW
                - Outbound rule:
                    - Rule #: 100
                      Type: HTTP (80)
                      other configurations are default
                    - Rule #: 200
                      Type: HTTPS (443)
                Conclusion: 
                    Since NACL are stateless, Only Inbout RDP is allowed, So RDP outbound is by default disable.
                    Now, we are not able to connect to EC2 instance by "Remote Desktop Connection".
            Demo 2: (allow RDP for inbound and outbound)
                - Edit NACL outboud rule and add below rule to enable all ports:
                - Outbound rule:
                    - Rule # : 250
                      Type: Custom TCP Rule
                      Protocol : TCP (6)
                      Port Range: 1024-65535        // Allow all ports
                      Source: 0.0.0.0/0
                      Allow/Deny: ALLOW
                - Conclusion:
                    - Able to connect EC2 instance by "Remote Desktop Protocol"
                    - Issue: now we are not able to access internet in EC2 enternet exploral browser.
                      This is because, we allows HTTP and HTTPS traffic in outbound to go towards internet. But we did not allowed incoming traffic in Inbound rule.

            Demo 3: Enable HTTPS and HTTP inbound traffic:
                - Inbound rule:
                    - Rule # : 200
                      Type: Custom TCP Rule
                      Protocol : TCP (6)
                      Port Range: 1024-65535        // Allow all ports. (or 80, 443)
                      Source: 0.0.0.0/0
                      Allow/Deny: ALLOW
                - Conclusion:
                    Now, internet is working in EC2 browser.


---------
VPC Endpoint: (#8 VPC Endpoint.png) : https://youtu.be/ab036IW3ASw?si=oZQXI9zbgCnmwd8u
    - If we want to access AWS services privately without NAT Gateway, then we can use VPC Endpoint.
    - A VPC Endpoint enables you to privately connect your VPC to supported AWS services instances in your VPC,
      do not require public IP address to communicate with resources in the service.
    - Private subnet route table:
        - Destination: AWS Service
          Target: VPC Endpoint
        - Destination: S3 enpoint   // Private subnet can access S3 bucket without going to internet. 
          Target: VPC-ID
    - VPC Endpoint Types:
        - Gateway Endpoint: S3, DynamoDB, Glacier, SSM, KMS, CloudWatch, CloudTrail, and Config.
        - Interface Endpoint: Rest of the AWS services. like EC2, RDS, S3, etc.
    - VPC Endpoint is not a single point of failure, it is highly available and scalable.
    - VPC Endpoint does not require an internet gateway, NAT device, VPN connection, or AWS Direct Connect connection.
    - VPC Endpoint does not impose availability risks or bandwidth constraints on your network traffic.
    - Practical: (#8 VPC Endpoint.png)
        - Create VPC, 2 Subnet, 2 EC2 instance, 2 Route Table, 1 IG for Public subnet and 1 S3 bucket. (like Demo 2, without NAT GW)
        - Create VPC Endpoint:
            - VPC: select created VPC
            - Service Name: select S3   // com.amazonaws.ap-south-1.s3
            - Route Table: select private subnet route table
            - Policy: Full Access
            - Create Endpoint
        - Testing:
            - Login to Public EC2 instance:
                - Login to Private EC2 instance from Public EC2 instance.
                    - cmd: "aws s3 ls"    // list all S3 bucket
                    - cmd: "aws s3 cp s3://bucket-name/file-name ."     
        - Conclusion:
            - We are able to access S3 bucket from private EC2 instance without going to internet and without NAT GW.


---------
Access VPC Instance through VPN:
    - https://youtu.be/WxBiGioCBDk?si=Q3Y0O_v08djr4i43
    - Create VPC, 2 Subnet, 2 EC2 instance, 2 Route Table, 1 IG for Public subnet and 1 S3 bucket. (like Demo 2, without NAT GW)
    - Create Public EC2 Instance for VPN:
        -> Launch Instance:
        -> Choose AMI: AWS Marketplace -> search "OpenVPN Access Server" -> select and continue
        -> Choose Public Subnet for VPN
    - Create Private EC2 Instance:
        -> windows t2 micro r2 base:
            network: "created VPC"
            Subnet: "created Subnet"
            Auto-assign public IP: Disable
            Security Group: RDP -> Source(Anywhere)
    - Testing:
        - Connect to Public VPN EC2 instance By PuttyGen:
            - once login, will get VPN IP address:
                - Admin UI: https://VPN-IP:943/admin
                  Client UI: https://VPN-IP:943
            - Create password for OpenVPN: "sudo passwd openvpn"
            - OpenVPN Admin UI: https://VPN-IP:943/admin
            - Brower: https://VPN-IP:943
                - Login with "openvpn" as username and password
                - Download OpenVPN Connect
                    - We created Private EC2 windows instance, so choose download "Windows" OpenVPN Connect
                - Download Configuration file
                - Open OpenVPN Connect and import configuration file
                - Connect to VPN
        - Now, we can access Private EC2 instance by private IP address Using RDP connection.
*/
/*
Transit Gateway:
    - It is a service that enables customers to connect their VPCs and their on-premises networks to a single gateway.
    - It simplifies the network and reduces operational overhead.
    
    - Steps:
        - Create multiple VPCs
        - Create Transit Gateway
        - Transit Gateway Attachments: Attach VPCs to Transit Gateway
        - Update Route Table of each VPCs:
            - Destination: another vpc-cidr
              Target: Transit Gateway-> select Transit Gateway of another VPC


*/