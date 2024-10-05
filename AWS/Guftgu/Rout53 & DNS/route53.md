# AWS Route 53 & DNS:
## Route 53:
    - DNS runs on port 53, hence the name Route 53.
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
    - Recursive DNS: A recursive DNS is a server that receives DNS queries from clients and queries other DNS servers to resolve the queries.
    - DNS Authoritative Server: A DNS authoritative server is the final step in the DNS lookup process. It is the server that has the authoritative information for a domain name.
    - Root Domain Server: A root domain server is the first step in the DNS lookup process. It is the top-level server in the DNS hierarchy.
    - Name Server: A name server is a server that stores DNS records for a domain name.

    - DNS Resolver: A DNS resolver is a server that receives DNS queries from clients and queries other DNS servers to resolve the queries.
    - DNS Root Server: A DNS root server is the first step in the DNS lookup process. It is the top-level server in the DNS hierarchy.
    - DNS Top-Level Domain (TLD) Server: A DNS TLD server is the second step in the DNS lookup process. It is the second-level server in the DNS hierarchy.
    - DNS Cache: A DNS cache is a temporary storage location for DNS records. It is used to speed up the DNS lookup process by storing previously resolved DNS records.
    - DNS Zone: A DNS zone is a portion of the DNS namespace that is managed by a single organization or administrator. It is a collection of DNS records for a specific domain name.
      
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
    - You can use Route 53 to route internet traffic for a domain that you registered with another registrar.

- If you are using another domain provider and you did all the changes:
    - When you migrate existing domain from one DNS provider (goDaddy) to another DNS provider (Route 53 or FreeNom),
        this change can take up to 48 hours to propagate across the internet.
    - This is because DNS information is cached by internet service providers (ISPs) and other DNS servers globally on the internet upto 48 hours TTL (Time To Live) period.
- Transformin a domain to Route 53:
    - When you transfer a domain to Route 53, you update the name servers for the domain to use Route 53 name servers.
    - You can transfer domain to route 53, if  the TLD (Top-Level Domain) included in the list of TLDs that Route 53 supports for domain registration. (same domain should support/present in route 53)
    - for most TLDs, you can't transfer the domain to Route 53.
    - for most TLD, you need to get authorization code from the current registrar to transfer the domain to Route 53. 

## Route 53 Hosted Zones:
    - A hosted zone is a container for DNS records for a domain.
    - It is a collection of DNS records for a specific domain name.
    - You create a hosted zone for a domain and tell you create records to tell the domain name system, how you want traffic to be routed for that domain.
    - Basically, a hosted zone is a container that holds information about how you want to route traffic for a domaom and its subdomains.
      eg. www.subdomain1.domain.com
          www.subdomain2.domain.com
    - You can create public (internet) hosted zones and private (internal- within VPC) hosted zones.
    - For each public hosted zone, Route 53 creates a
        - 4 name server (NS) records. Dont change these records.
        - SOA (Start of Authority) records. Contains information about the hosted zone.
    - Route 53 supports both IPv4 and IPv6 addresses.
    - Route 53 automatically creates a Name Server (NS) record with the same name as your hosted zone.
    - It list the four name servers that are the authoritative name servers for your domain. 
      eg. 4 TLD: ns-2048.awsdns-64.com, ns-2049.awsdns-65.net, ns-2050.awsdns-66.org, ns-2051.awsdns-67.co.uk
    - You can create more than one hosted zone with the same name and add different records to each hosted zone.


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
