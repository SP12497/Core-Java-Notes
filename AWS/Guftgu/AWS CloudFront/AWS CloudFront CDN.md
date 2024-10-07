# AWS CloudFront (CDN)
    - Information:
        - AWS CloudFront is a webservice that gives businesses and web application developers an easy and cost-effective way to distribute content with low latency and high data transfer speeds.
        - AWS CloudFront is a content delivery network (CDN) that speeds up the distribution of your web content to users around the world.
        - CloudFront delivers your content through a worldwide network of data centers called edge locations. 
        When a user requests content that you're serving with CloudFront, 
        the user is routed to the edge location that provides the lowest latency (time delay), 
        so that content is delivered with the best possible performance.
        - If content is present in edge location, it will be directly delivered to user.
        If content is not present in edge location, it will be fetched from origin server and then delivered to user.
        - CloudFront also keep persistent (keep alive) connection with origin server to fetch content.
    - access cloudfront using:
        - AWS Management Console
        - AWS Command Line Interface (CLI)
        - AWS SDKs
        - AWS CloudFront API
    - Edge Location:
        - An edge location is where content will be cached. 
        - Its on global level (Account level). This is separate to an AWS Region/AZ.
        - Edge locations are endpoints for CloudFront. Requests for your content are automatically routed to the nearest edge location, so content is delivered with the best possible performance.
        - Typically this will be the closest location to your user.
        - You can see the edge locations on the CloudFront details page.
        - Today, CloudFront has over 400 Points of Presence in 90 cities and across 47 different countries.
    - Regional Edge Cache:
        - This is a new feature which allows you to cache your content at a regional level.
        - This means that even if your content is not cached at an edge location, it will be cached at the regional level.
        - This helps to reduce the number of requests that go back to your origin.
        - eg. data fetched from origin server will be cached edge location.
          we are not using edge location data for long time(eg. 24 hours), then data will be moved to regional edge cache.
          Next time, if user request for same data, it will be fetched from regional edge cache instead of origin server.
          request > edge location > regional edge cache > origin server
        - Regional Edge Cache working as a alternative of origin server to reduce burden on origin server.
`          also it works as a backup of edge location.
    - Request > DNS > CloudFront > Edge Location > Regional Edge Cache > Origin Server
    - HTTP (PUT/POST/PATCH/OPTIONS/DELETE) Request / Dynamic Content > DNS > CloudFront > Edge Location > Origin Server
    - Types of cloudfront distributions:
        - Web Distribution: Typically used for websites
            - Web distributions are used to distribute web content, including static and dynamic content.
            - eg. images, CSS, JavaScript, etc.
        - RTMP: Used for media streaming (Removed in 2020)
            - works on Adobe Flash Media player. Adobe Flash is no longer supported.
            - RTMP distributions are used for media streaming and Adobe Flash content.
            - eg. media files, videos, etc. www.youtube.com


## Lab:
    - Open S3 console
        - Create a S3 bucket (uncheck block all public access)
        - Upload a file to the S3 bucket (Grant public read access permission)
    - Open CloudFront console
        - Create a CloudFront distribution
        - Origin Domain Name: S3 bucket URL
        - Origin Path: /<folder_name> (if file is in folder)
        - Origin Name: <S3 bucket name>
        - Origin Response Timeout: 30 seconds
            - The amount of time that CloudFront waits for a response from the origin server before timing out.
        - Origin  