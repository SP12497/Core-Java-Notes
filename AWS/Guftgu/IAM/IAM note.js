/*
IAM:
    - IAM refers to Identity and Access Management.
    - it refers to a framework or policies and technologies for ensuring
      that the proper people in an organization have the appropriate access to technology resources.
    - it is a web service that helps you securely control access to AWS resources. 
      You can use IAM to control who is authenticated (signed in) and authorized (has permissions) to use resources.
    
    - When you first create an AWS account, you begin with a single sign-in identity (admin acoount) that has complete access to all AWS services and resources in the account.
    - This identity is called the AWS account root user and is accessed by signing in with the email address and password that you used to create the account.
    - We strongly recommend that you do not use the root user for your everyday tasks, even the administrative ones.
    - Instead, adhere to the best practice of using the root user only to create your first IAM user.

IAM Limits:
    - The following are the default limits for IAM resources in an AWS account:
        - 5000 users per AWS account. Add 10 user at a time.
        - 300 groups per AWS account. (Each group can contain up to 300 users.) (HR Group, Developer Group)
        - 1000 IAM roles per AWS account. (Each role can be used by multiple users, groups, and services.)
        - Policy: Default limit of managed policies attached to an IAM role and user is 10.
        - IAM user can be a member of 10 groups (max).
        - We can assign maximum two access keys to an IAM user. (Access key ID and Secret access key for programmatic access)

IAM Features:
    - Shared access to your AWS account.
        - You can grant other people permission to administer and use resources in your AWS account without having to share your password or access key.
    - Granular permissions.
        - You can grant different permissions to different people for different resources. 
        - For example, you might want someone to be able to create new users but not to delete users.
    - Secure access to AWS resources for applications that run on Amazon EC2.
    - Multi-factor authentication (MFA).
        - You can add two-factor authentication to your account and to individual users for extra security.
    - Identity federation.
        - You can allow users who already have passwords (account) elsewhere to use those credentials to access AWS. 
        - (eg. login pubG using facebook, pubG is trusting on facebook)
    - Identity information for assurance.
        - You can add details about the identity of your users such as a phone number or alternate email address.
    - PCI DSS Compliance.
        - You can request a report that lists all the AWS services that you are using and in scope for PCI DSS compliance.
        - (Payment Card Industry Data Security Standard (PCI DSS) is a set of security standards designed to ensure that all companies that accept, process, store or transmit credit card information maintain a secure environment.)
    - Eventually Consistent.
        - IAM is eventually consistent. Changes made to policies can take time to propagate.

Free to Use:
    - You pay only for the AWS resources that you use. There is no additional charge for creating and using IAM users and groups.
    

An IAM policy has a specific structure that includes the following elements:
    "Version" 
        - The version of the policy language being used.
        - The current version is 2012-10-17. Other versions are: 2008-10-17, 2004-10-21.
    "Statement" 
        - An array of individual statements that define the permissions for the policy. Each statement has the following fields:
        - Each statement has the following fields:
            - "Effect" : Specifies whether the statement allows or denies access.
            - "Action" : Specifies the actions that are allowed or denied.
            - "Resource" : Specifies the resources that the actions apply to.
            - "Sid" : (Optional) A statement ID that you can use to refer to the statement later.
            - "Principal" 
                - (Optional) Identifies the user, group, or role that the policy applies to.
                - A person or application that uses an AWS service is known as a principal.
                - Used for resource-based policies. appling a policy to a resource.
                - if we don't specify the principal, the policy applies to all principals (users, groups, and roles).
                - "Principal": "*"  => allows any user (public access) for the first statement.
            - "Condition" : (Optional) Specifies any additional conditions under which the statement applies.

Example:
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Sid": "ListAllBuckets",
      "Effect": "Allow",
      "Principal": {
        "AWS": "arn:aws:iam::123456789012:user/ExampleUser"
      },
      "Action": [
        "s3:ListAllMyBuckets",
        "s3:GetBucketLocation"
      ],
      "Resource": "arn:aws:s3:::*"
    },
    {
      "Effect": "Allow",
      "Action": "s3:ListBucket",
      "Resource": "arn:aws:s3:::BUCKET-NAME",
      "Condition": {
        "StringLike": {
          "s3:prefix": [
            "",
            "home/",
            "home/${aws:username}/"
          ]
        }
      }
    },
    {
      "Sid": "FullAccessToHomeFolder",
      "Effect": "Allow",
      "Principal": {
        "AWS": "arn:aws:iam::123456789012:user/ExampleUser"
      },
      "Action": "s3:*",
      "Resource": [
        "arn:aws:s3:::BUCKET-NAME/home/${aws:username}",
        "arn:aws:s3:::BUCKET-NAME/home/${aws:username}/*"
      ]
    }
  ]
}
*/

/*
Part 2:
IAM Terms:
    "Principal" 
        - An entity that is allowed or denied access to a resource. 
        - The principal can be an AWS account root user, an IAM user, or a role.
    "Request" 
        - An attempt to access a resource.
    "Authentication" 
        - The process of verifying the identity of a principal. (eg. username and password)
    "Authorization
        - The process of granting or denying access to a resource.
    "Action/Operation"
        - A type of access that can be granted or denied. (eg. Get, List, Put, Delete, Create, Update, etc.)
        - eg. "s3:ListBucket", "s3:GetObject", "s3:PutObject", etc.
    "Resource"
        - An object that an action operates on. (eg. EC2 instance, S3 bucket, DynamoDB table, etc.)

1. Principal:
    - A principal is a person or application that can make a request for an action or operation on an AWS resource.
    - Your administrator IAM user is your first principal.
    - You can allow user and services to assume a role to access resources.
    - IAM User, Roles, Federated User and applications are all AWS principals.
    - You can support federated users or programmatic access to allow an application to access AWS account.
 
2. Request:
    - When a principal tries to use the AWS management console, the AWS API or the AWS cli, that principal sends a request to AWS. 
    - The request includes following information:
        - Action: 
            - The action that the principal wants to perform. 
            - eg. "s3:ListBucket", "s3:GetObject", "s3:PutObject", etc.
        - Resource:
            - The resource that the action should be performed on.
            - eg. EC2 instance, S3 bucket, DynamoDB table, etc.
        - Principal:
            - The principal that is trying to perform the action.
            - eg. IAM User, Role, Federated User, etc.
        - Environment Data:
            - The environment data includes the source IP address, the user agent, the time of the request, etc.    
            - eg. IP address, User-Agent, Time of request, etc.
        - Request Context/Data:
            - The request context includes the user's identity, the user's session, and the user's role.
            - eg. User's identity, User's session, User's role, etc.

3. Authentication:
    - A principal sending a request must be authenticatd (Signed in to AWS) to send a request to AWS.
    - Some AWS services, like AWS S3, allows request from anonymous users (without authentication). They are exception to the role.
    - To Authenticate from the console as a root user, you must sign in with credentials.
    - To authenticate from the API to CLI, you must provide your access key and secret key.
    - You might also be required to provide additional security information like MFA. (Google Authenticator, Authy, etc.)

4. Authorization:
    - To authorize a request, IAM uses value from the request context to check for matching policies and determine whether to allow or deny the request.
    - IAM policies are stored in IAM as JSON documents and specify the permissions that are allowed or denied.
    - By default, AWS root user has access to all resources and services in the account in the AWS Management Console ie allow all.
      But for the IAM user, by default, they have no permissions ie deny all.
    - Types of Policies:
        - Identity(User)-based policies: 
            - specify permission allowed/denied for principals
            - Attach to IAM users, groups, and roles.
        - Resource-based policies: 
            - specify permisson allowed/denied for resources for granting cross-account access.
            - Attach to resources like S3 bucket, SQS queue, etc.
    - If a single policy includes a denied actions, IAM denies the entire request and stops evaluating the policy. This is called an explicit deny.
        - Policy evaluation logic:
            - sequece of evaluation on priority basis: (Explicit Deny > Explicit Allow > Implicit Deny)
            eg. Policy 1: Allow s3:GetObject, Policy 2: Deny s3:GetObject  => Deny
                Policy 1: Allow s3:GetObject, Policy 2: Allow s3:*  => Allow
                Policy 1: Deny s3:GetObject, Policy 2: Allow s3:*  => Deny
                Policy 1: Deny s3:GetObject, Policy 2: Deny s3:*  => Deny
                Policy 1: Deny s3:GetObject, Policy 2: Explicit allow s3:GetObject  => Allow
                Policy 1: Explicit allow s3:GetObject, Policy 2: Deny s3:GetObject  => Deny
                Policy 1: Explicit allow s3:GetObject, Policy 2: implicit deny  => Allow
        - by default, all requests are implicitly denied.
        - An explicit allow overrides an implicit (default) deny.
        - An explicit deny overrides an explicit allow and an implicit allow/deny.






*/
