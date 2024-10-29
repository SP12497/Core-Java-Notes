# AWS Route 53 & DNS:
## Route 53:
    - DNS runs on port 53, hence the name Route 53.
    - Its global service, not limited to a single region. Also, it supports IPv4 and IPv6.
    - Route 53 is a scalable and highly available Domain Name System (DNS) web service.
    - Route 53 is a global service, which means it is not limited to a single region.
    - Route 53 is a managed DNS service.
    - You can use Route 53 to register new domain, transfer existing domains, 
      route traffic for your domains to your AWS resources, such as EC2 instances, S3 buckets and external resources. 
      also monitor the health of your resources.
    - Route 53 functions:
        - DNS Management
        - Traffic Management
        - Availability Monitoring
        - Domain Registration

## DNS (Domain Name System):
- How DNS works:
    - DNS is a distributed database that maps domain names to IP addresses.
    - DNS is a hierarchical system.
    - DNS is a client-server system.
    - DNS is a TCP/IP application layer protocol.
    - DNS uses port 53.
    - DNS uses UDP for queries and TCP for zone transfers.

**DNS Components**
    - Recursive DNS:
        A recursive DNS is a server that receives DNS queries from clients and queries other DNS servers to resolve the queries.
    - DNS Authoritative Server:
        A DNS authoritative server is the final step in the DNS lookup process. It is the server that has the authoritative information for a domain name.
    - Root Domain Server:
        A root domain server is the first step in the DNS lookup process. It is the top-level server in the DNS hierarchy.
    - Name Server:
        A name server is a server that stores DNS records for a domain name.
    - DNS Resolver:
        A DNS resolver is a server that receives DNS queries from clients and queries other DNS servers to resolve the queries.
    - DNS Top-Level Domain (TLD) Server:
        A DNS TLD server is the second step in the DNS lookup process. It is the second-level server in the DNS hierarchy.
    - DNS Cache:
        A DNS cache is a temporary storage location for DNS records. It is used to speed up the DNS lookup process by storing previously resolved DNS records.
    - DNS Zone:
        A DNS zone is a portion of the DNS namespace that is managed by a single organization or administrator. It is a collection of DNS records for a specific domain name.
      
**DNS Terminology**:
    - **Domain Name**: A human-readable name that represents an IP address. Example: `google.com`.
    - **Top-Level Domain (TLD)**: The highest level in the hierarchical Domain Name System. Examples: `.com`, `.org`, `.net`.
    - **Second-Level Domain (SLD)**: The portion of the domain name directly to the left of the TLD, representing the main entity. Example: `google.com`, `amazon.com` (where `google` and `amazon` are second-level domains).
    - **Fully Qualified Domain Name (FQDN)**: A complete domain name that specifies its exact location in the DNS hierarchy, including the hostname and domain. Example: `www.google.com`, `mail.amazon.com`, `blog.twitter.com`.
    - **Hostname**: The label assigned to a specific device or service within a domain, often used as a prefix. For example, `www` in `www.google.com` or `mail` in `mail.google.com`.
 

**DNS WorkFlow**
    - When client wants to access a website, eg. www.google.com, it sends a DNS query to the DNS resolver.
    - The DNS resolver sends a query to the DNS root server to resolve the domain name.
        - Root server responds with the TLD (Top-Level Domain/ Name server) server for the domain name. eg. .com, .in
    - The DNS resolver sends a query to the DNS TLD server to resolve the domain name. 
        - Here, the .com TLD server have all the information about the .com domain names. eg. .com TLD server responds with the authoritative server for the domain name. eg. google.com
    - The DNS resolver sends a query to the DNS authoritative server to resolve the domain name.
        - If .com Name server (TLD) have the information about the domain name, then it called as DNS Authoritative server.
        - The authoritative server responds with the IP address of the domain name.
    - The DNS resolver sends the IP address to the client.
    - The client uses the IP address to access the website.
    - The client caches the IP address for future use.
    - The client sends a request to the website using the IP address.

## Part 2: Route 53 - 
**3 Main fuctions**
    - Register Domain Names: You can register domain names with Route 53.
    - As a DNS, it routes internet traffic to the resources for your domain.
    - As a DNS, it checks the health of your resources.
        - Route 53 sends automated requests over the internet to a resource (can be a webserver) to verify that server is reachable, available and functional.
        - Also, you can choose to receive notifications if your resources become unavailable or unhealthy.

    - You can use Route 53 for any combination of these functions:
        - for eg. you can use Route 53 for both domain registration and DNS routing. (domain registration + DNS routing)
        - You can use Route 53 to route internet traffic for a domain that you registered with another registrar eg GoDaddy.com. (DNS routing)

    - If you are using another domain provider and you did all the changes:
        - When you migrate existing domain from one DNS provider (goDaddy) to another DNS provider (Route 53 or FreeNom), this change can take up to 48 hours to propagate across the internet.
        - This is because DNS information is cached by internet service providers (ISPs) and other DNS servers globally on the internet upto 48 hours TTL (Time To Live) period.

## Steps to configure Route 53:
    - Register a domain name with Route 53.
        Registerer can be Route 53 or any other domain registrar.
    - Create a hosted zone.
        - AWS Registered domain: Route 53 automatically creates a hosted zone for the domain.
        - Non-AWS Registered domain: You need to create a hosted zone for the domain.
    - Create a record set inside Hosted zone

## Delegation of Route 53:
    - Connect the domain name to the route 53 hosted zone is called delegation.
    - Update your domain registrar with the correct 'name servers' for your Route 53 hosted zone.

## Transfer Domain to another domain provider:
    - eg. from GoDaddy to Route53/FreeNom/NameCheap
    - When you transfer a domain to another domain provider, you update the name servers for the domain to use the name servers of the new domain provider.
    - You can transfer domain to another domain provider, if the TLD (Top-Level Domain) included in the list of TLDs that the new domain provider supports for domain registration.
    - When you migrated from one DNS provider to another DNS provider, this change can take up to 48 hours TTL (Time to leave) period to propagate across the internet because DNS information is cached by internet service providers (ISPs) and other DNS servers globally on the internet.
    - for most TLD, you need to get authorization code from the current registrar to transfer the domain to another domain provider.
    - Transferring a domain to Route 53:
        - You can transfer domain to route 53, if  the TLD (Top-Level Domain) included in the list of TLDs that Route 53 supports for domain registration. (same domain should support/present in route 53) eg. .com, .org are present in AWS but not .test .example .test
    - for most TLDs, you can't transfer the domain to Route 53.
    - for most TLD, you need to get authorization code from the current registrar to transfer the domain to Route 53.
    - Traferring from AWS account to another AWS account:
        - contact AWS Supports to transfer the domain from one AWS account to another AWS account.
        - (IMP) Only DNS can tranfer, Hosted zone can't transfer.
          Here, after transfer, Accoung 2 will have the domain name but Account 1 will have the hosted zone.

# Part 3:
## Route 53 Hosted Zones:
    - It is a collection of DNS records for a specific domain name.
    - Per account, you can create 500 hosted zones. (default limit)
      Per hosted zone, you can create 10,000 records. (default limit)
    - You create a hosted zone for a domain and tell you create records to tell the domain name system, how you want traffic to be routed for that domain (www.sagar.com) or its subdomains (www.engg.sagar.com , www.dr.sagar.com).
    - 2 types of Hosted Zones:
        1. public (internet) hosted zones
        2. private  (internal DNS - within VPC) hosted zones.
    - For each public hosted zone, Route 53 creates a
        - 4 name server (NS) records.
            -  Dont change these records.
            - Contains the unique sets of name servers for a hosted zone.
        - SOA (Start of Authority) records.
            - Contains information about the hosted zone.
    - Route 53 automatically creates a Name Server (NS) record with the same name as your hosted zone.
    - By defaults, each AWS hosted zones provide four name servers that are the authoritative name servers for your domain. 
      eg. 4 TLD: 
        ns-2048.awsdns-64.com,
        ns-2049.awsdns-65.net,
        ns-2050.awsdns-66.org,
        ns-2051.awsdns-67.co.uk
    - You can create more than one hosted zone with the same name and add different/unique records to each hosted zone.
        - Now, Hosted zones names are same but each hosted zone will provide its own name servers. So, update the correct name servers in the domain registrar.
    - Note: Hosted zones are not transferable between AWS accounts. Only DNS can transfer.

# Part 4:
## Supported DNS Record types by Route 53:
    1. A (Address) Record:
        - Maps a domain name to an IPv4 address.
        - eg. www.sagar.com | IN(Internet)  | A  | 5.5.5.5 (IPv4 address)
    2. AAAA (IPv6 Address) Record:
        - Maps a domain name to an IPv6 address.
        - eg. www.sagar.com | IN | AAAA | 2001:0db8:85a3:0000:0000:8a2e:0370:7334
    3. CNAME (Canonical Name) Record:
        - Maps a domain name to another domain name.
        - Maps the alias to a hosted zone
        - Helpful in redirection or if you want to hide details about your actual server from the users.
        - eg. Web | IN | CNAME | www.sagar.com
        - eg. if someone is using web, it will redirect to www.sagar.com
            DNS name: sagar.com
                CNAME: www.sagar.com | www..support.sagar.com | www.sales.sagar.com
        - CNAME is an alias for the domain name.
        - We cannot create a CNAME record for the root domain (sagar.com) because the root domain already has an A record. We can create support.sagar.com, sales.sagar.com, etc.
    4. NS (Name Server) Record:
        - Specifies the name servers for the domain.
        - eg. sagar.com | IN | NS | ns-2048.awsdns-64.com
    5. SOA (Start of Authority) Record:
        - Specifies the authoritative information about the domain.
        - Every single hosted zone has one and only one SOA resource record at the beginning of the zone.
        - It is not an actual record, it includes the following information:
            - Who is the owner of the zone. (email address)
            - The authoritative name server for the zone.
            - The serial number which is incremented with each changes to the zone data.
            - The time to refresh the zone.
    6. MX (Mail Exchange) Record:
        - Specifies the mail servers for the domain.
        - eg. sagar.com | IN | MX | 10 mail.sagar.com

# Part 5: Routing Policies:
Types of AWS Route 53 Routing Policy:
    - When you create a record, you choose a routing policy, which determines how AWS Route 53 responds to queries.
1.  Simple Routing Policy:
    - Use when you have a single resource that performs a given function for your domain.
    - eg. www.sagar.com -> 10.0.44.21
2. Failover Routing:
    - Use when you want to configure active-passive failover.
    - Only works on Public Hosted Zones.
    - Failover routing lets you route traffic to a resource, when a resource is healthy (Active), if the main (Active) resource is unhealthy, then route traffic to the backup (Passive) resource.
3. Geolocation Routing:
    - Use when you want to route traffic based on the location of your users.
    - eg. Route traffic to the nearest server based on the geo location of the user. It wont check the latency.
    - Benefits: 
        - We can localize your content and present some or all of your website in the language of your users.
        - We can restrict distribution of content to only the locations where you have distribution rights only specific by Country, State, City.
    - If you create separate records for overlapping geographic regions. Then priority goes to the smallest geographic region.
        eg. Canada region is inside North America region. like North America => [Canada, USA, Mexico]
          and request comes from Canada or outer border of Canada, then it will go to Canada region.
4. Latency based Routing:
    - if your application is hosted in multiple AWS regions, you can create latency-based routing records to route traffic to the region that provides the lowest latency.
    - To use latency based routing, you create latency records for the same domain name in multiple regions.
    - When Route 53 receives a query for the domain or subdomain name
        - It determines which AWS region has the lowest latency for the user.
        - Then, it routes the query to the corresponding resource.
5. Weighted Routing:
    - Weighted routing policy lets you associate multiple resources with a single domain name or subdomain name, and choose how much traffic is routed to each resource.
    - Useful for: load balancing, testing new version of software (reduce weight).
    Weight can be 0-255 number.
    - This policy can be applied when there are multiple resources that perform multiple resource that perform the same function. eg. Running same website in each server. 
    - eg. Suppose for www.sagar.com, we have 3 servers, 
        Server 1: 1     | Server 2: 1     | Server 3: 3
        Total weight: 5 , then Server 1: 1/5 ie 10% | Server 2: 1/5 ie 10% | Server 3: 3/5 ie 30%
6. Geo Proximity Routing: (Not in exam)
    - Use when you want to route traffic based on the geographic location and optionally, shift traffic from resources in one location to resources in another.
    - You can also optionally choose to route more traffic or less to a given resource by specifying a value, known as a 'bias'. A 'bias' expand or shrinks the size of the geographic region from which traffic is routed to a resource.
7. Multi-Value Answer Routing:
    - Use when you want Route 53 to respond to DNS queries with up to eight healthy records selected at random.
    - Multi-Value Answer routing lets you configure Amazon Route 53 to return multiple values, such as IP addresses for your web servers, in response to DNS queries.
    - You can specify multiple values for almost any record, but Multi-Value Answer routing lets you check the health of each resource and only returns values for the resources that are healthy. (Its not a substitute for a load balancer)
    - Ability to return multiple health checkable IP addresses is a way to use DNS to improve availability and load balancing.
    



# Lab on Route 53:
    - Register a domain name with Route 53.
        - Go to Route 53 console.
        - Choose Registered domains and Click Register domain for create:
            - Enter the domain name that you want to register.
    - Or, you can transfer a domain to Route 53.
        - Go to Route 53 console.
        - Choose Registered domains and Click Transfer domain for create:
            - Enter the domain name that you want to transfer.
    - Create a hosted zone.
        - Go to Route 53 console.
        - Choose Hosted zones and Click Create hosted zone for create:
            - Enter the domain name that you want to create a hosted zone for.
            - "Hosted Zone.png"
    - Create a record set.
        - Go to Route 53 console.
        - Choose Hosted zones and Click on the hosted zone that you want to add a record set to.