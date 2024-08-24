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
5. Action:
    - Actions are defined by services and are the things that you can do to a resource.
      Such as viewing, creating, editing, or deleting that resource.
    - IAM supports approx 40 actions for a user resources including create User, Delete User, etc
    - Any actions or Resources that are not explicitly allowed are denied by default.
    - After your request has been authenticated and authorized,  AWS approves the actions in request.
6. Resource:
    - A resource is an entity that exist within a service.
    - Examples, are EC2 instances, S3 buckets, DynamoDB tables, etc.
    - Each resource has a unique Amazon Resource Name (ARN) that identifies it.
    - Each AWS service defines a set of actions that can ben performed on each resources.
    - After AWS approves the actions in your request, those actions can be performed on the related resources withing your account.
    - If you create a request to perform an unrelated action on a resource, that request is denied.
    - When you provide permissions using an identity based policy in IAM, then you provide permissions to access resources only within the same account.
*/

/*
Part 3:
    3 ways to create IAM Polity:
        - JSON
        - Visual Editor:
            - The visual editor is a tool that helps you create policies by providing a visual representation of the policy.
        - Imort Managed Policy:
            - You can import () a managed policy and attach it to a user, group, or role.
*/

/*
Part 4:
- Identity Federation:
    - Like, gmail/facebook/linkedIn third party account is allowed to access AWS account.
    - if your account users already have a way to be authenticated such as authentication through your corporate network.
    - You can federate those user identities into AWS so that your users can sign in to the AWS Management Console or call the AWS API operations without you having to create an IAM user for each identity.
    - The corporate can replace their existing identity with a temporary identity in your AWS account.
    - The user can work in the AWS management console.
    - Similarly, an application that the user is working with can make programatic request using permission that you define.
    - Federations is particularly useful in these cases:
        1. Your User already have identities in a Corporate Directory (My company credentials)
            - Corporate Directory should compatible with SAML 2.0 (Security Assertion Markup Language)
                If not, You can create "Identity Broker" to convert the corporate directory to SAML 2.0
            - AWS recommends to use AWS SSO for Identity Federation.
        2. Your users already have Internet Identities (Gmail, Facebook, etc)
            - "Web Federation (OIDC Open ID Connect)" is reuired to login AWS account using Internet Identities.
    - AWS recommends to use AWS Cognito for Identity Federation.

    IAM Identity:
        - User: Individual IAM Identity (User or Application). (Sagar, Suraj)  (create upto 5000 users in account) (access by credentials or Programmatic access)
            - Each IAM user is associated with a single AWS account.
            - IAM user do not have to do the payment for usage of the AWS resources. The account owner is responsible for the payment.
        - Group: 
            - Collection of IAM Users. (HR Group, Developer Group)
            - create upto 300 groups in account.
            - User can be a member of 10 groups max.
        - Role: 
            - is a set of permissions that you can apply to a user or group. (Admin Role, Developer Role)
            - A role does not have standard long-term credentials (password or access keys) associated with it.
            - If a user assumes a Role, temporary credentials are created for the user.
            - create upto 1000 roles in account.
            - Following entities can assume a role:
                - IAM User
                - IAM user in another account
                - A Web Services offered by AWS such Amazon Elastic Compute Cloud.
    - IAM Identities is what you create under your AWS account to provide authentication for people, application and processes in your AWS account.
    - Identities represents the user and can be authenticated and then authroized to actions in AWS.
    - IAM Temporary Credentials:
        - Temporary credentials are short-term credentials that are generated dynamically and provided to the user.
        - A benefit of temporary credentials is that they expire automatically after a set period of time.
    - A User only have a One role at a time.
        eg. User has developer role, 
          he wants to do testing. Temporary, he has to leave the developer role and assume the tester role.
          Once he is done with testing, he can leave the tester role and assume the developer role again automatically.
    - A Role can be assigned to multiple users.

Permissions and Policies:
    - Permissions are granted to IAM identities using policies.
    - Policies are JSON documents that define permissions.
    - Policies are attached to IAM identities (users, groups, and roles).
Policies and User:
    - By default, IAM users can't access anything in your account.
    - You must attach policies to the IAM user to grant permissions.
    - Any actions or resources that are not explicitly allowed are denied by default.
IAM Multiple Policies:
    - You can attach multiple policies to an IAM user, group, or role.
    - The user's permissions are calculated by combining all the permissions from all the policies that are attached to the user.
    - If a single policy includes a denied action, IAM denies the entire request and stops evaluating the policy. This is called an explicit deny.
Resource Based Policy:
    - In some cases (like S3 bucket), you can attach a policy directly to a resource.
    - A resource-based policy slightly different information than a user-based policy.
    - In a resource-based policy, you specify what actions are permitted and what resources is affected.
    - You also explicitly list who is allowed to access the resource (the principal). 
    - can apply only on:
        S3 (Amazon Simple Storage Service)
        SNS (Amazon Simple Notification Service)
        SQS (Amazon Simple Queue Service)
        Lambda
        IAM Roles
        API Gateway
        KMS (AWS Key Management Service)
        Secrets Manager
        
Cross Account Access:
    - How to Access S3 bucket (Resource) from another account.
    - Example: 
        Account 1: Trusted Account
            - User (Role A)
        Account 2: Trusting Account
            - S3 Bucket (Resource based policy)
            - Role B (IAM Role)
    - 1. Iam Role (User based policy/identity based policy):
        - Here, User in Account 1 havind Role A, wants to access S3 bucket in Account 2 using Role B.
            then, User in Account 1 has to leave the current role (Role A) and assume the Role B in Account 2 to access the S3 bucket
        Issue: User can not hold multiple roles at a time. He has to leave the current role permission to assume the new role.
        - Issue by example: Copy and paste operation.
            Role A: Permission to Paste in Trusted Account
            Role B: Permission to Copy in Trusting Account
            User can copy the data from Trusting Account but can not paste the data in Trusting account.
    - 2. Resource Based Policy:
        - Here, User in Account 1 havind Role A, wants to access S3 bucket in Account 2 using Role B.
            then, User in Account 1 can access the S3 bucket in Account 2 using Resource Based Policy.
        - Benefits: User in Account 1 can access the S3 bucket in Account 2 without leaving the current role (Role A).
IAM Delagation:
    - Delegation is the granting of permissions to someone to allow access to resources that you control.
    - Trust created between two accounts.
        - Trusted Account will request for trust to the Trusting Account.
        - Trusting Account will accept the trust request. (Trust Policy)
        - Trust Created by Trusting Account.
    - The trusted and trusting accounts can be any of the following:
        - The same account: 
        - Two different accounts that are owned by the same person or organization.
        - Two different accounts that are owned by different people or organizations.
    - To delegate permissions to access a resouce, you create an IAM role that has two policies attached:
        - A trust policy:
            - Specifies who can assume the role.
            - Contains :
                - account details of the trusted account.
            - Principal can not specify as wildcard (*) in trust policy.
        - Permissions policy:
            - Specifies what permissions the role has.
            - Contains: 
                - permissions to access the resource. (eg. S3 bucket PUT, GET, DELETE)

Resouce Based Policies can apply on:
    Amazon S3 (Simple Storage Service)
    Amazon SNS (Simple Notification Service)
    Amazon SQS (Simple Queue Service)
    AWS Lambda
    IAM Roles
    Amazon API Gateway
    AWS KMS (Key Management Service)
    AWS Secrets Manager
    Amazon ECR (Elastic Container Registry)
    AWS Glue (for data catalogs)
    AWS Backup (for backup vaults)
    Amazon EFS (Elastic File System)
    AWS CodeArtifact
    AWS CodeBuild
    AWS CodePipeline
    AWS CloudTrail (for trails)
    Amazon RDS (Relational Database Service, with IAM authentication)
    Amazon Redshift (with IAM authentication)
    AWS Organizations (for SCPs on accounts and OUs)
    AWS Transfer Family (for SFTP resources)
    AWS IoT Core
    Amazon CloudWatch Logs
    AWS Systems Manager Parameter Store (for parameters)
    AWS Elemental MediaStore
    Amazon MQ (for brokers)
    AWS Resource Access Manager (RAM)
    AWS CodeCommit
    Amazon Managed Blockchain
    AWS AppConfig (within Systems Manager)
    AWS Directory Service (for managed directories)
    AWS Data Exchange
*/

/*

Lab 1: Enable MFA for Root User: (Multi-Factor Authentication) (Google Authenticator, Authy, etc.)
- IAM > Dashboard > Activate MFA on your root account
    - Activate MFA:
        - select: Virtual MFA device (for Google Authenticator, Authy, etc.)
        - scan the QR code using Google Authenticator

Lab 2: Create IAM User:
    - IAM > Users > Add User 
        - User Name: Sagar
        - Access Type: 
            - Programmatic access: Access Key ID and Secret Access Key
            - AWS Management Console access: Password
        - Console Password: Custom Password
        - Require password reset: Yes  (User has to reset the password on first login)
        - Next: Permissions
            - Attach Group, existing or new policy: eg. AmazonS3FullAccess, AmazonLambdaFullAccess, etc.
        - Next: Tags
            Name: Sagar
        - Download .csv file: (Access Key ID and Secret Access Key)
        - Done    
    - Login with the User Name and Password:
        - IAM -> Dashboard -> Sign-in link: (Sign-in URL) -> Open in Incognito Window
        - Sign-in with the User Name and Password-> Change Password
        - By default, the user has no permissions. You have to attach the policy to the user from Parent account (Root account) 
    - Add Inline Policy:
        - IAM > Users > Sagar > 
            - Add permissions:
                - Group, Existing Policy or Copy from existing user.
            - Add Inline policy:
                - service: S3
                - Actions: ListBucket, GetObject, PutObject, DeleteObject
                - Resources: All resources
                - Review Policy
        - Done

Lab 2: Create IAM Group:
    - Create 2 Users: Sagar, Suraj
    - IAM > Groups > Create Group
        - Group Name: Developer
        - Attach Policy: AmazonEC2FullAccess (Existing Policy) or empty
        - Create Group
        - Done
    - Attach Users to the Group:

Lab 3: Allow Billing Access to IAM User:
    - IAM > Users > Sagar > Add permissions
        - Attach Policy: Billing (ReadOnlyAccess)
        - Add permissions
        - Done
    - My Account > My Billing Dashboard > IAM User and Role Access to Billing Information
        - Enable Access to Billing Information
        - Update

Lab 4: Cross Account Access:
    - Account 1: MyAccount1
        - Create group: 
            - Group Name: Freshers
            - Attach Policy: AmazonEC2ReadOnlyAccess
        - Create 2 user. 
            - User 1: Sagar (empty policy or AmazonEC2ReadOnlyAccess)       // Sagar can see EC2 instances in Same account
            - User 2: Suraj ()
            - Attach both users to the Freshers Group
    - Account 2: MyAccount2
        - create S3 bucket: mybucket
        - Create Role:
            - IAM > Roles > Create Role:
                -> Another AWS account:
                    - Account ID: MyAccount1 account ID                 // MyAccount1 Users can see the S3 bucket in MyAccount2
                    - Create Policy: AmazonS3ReadOnlyAccess
                -> Role Name: S3AccessRole  
                - Role policy will look like
                    {
                        "Version": "2012-10-17",
                        "Statement": [
                            {
                                "Effect": "Allow",
                                "Principal": { "AWS": "arn:aws:iam::MyAccount1_Id:root" },  // allow all users from MyAccount1
                                // "Principal": { "AWS": "arn:aws:iam::MyAccount1_Id:user/Sagar" },  // Sagar user arn: allow only Sagar user from MyAccount1
                                "Action": "sts:AssumeRole",
                                "Condition": {}
                            }
                        ]
                    }
                - Done
        - Open Role > Trust Relationship
            - can see Trusted entity: MyAccount1
    - MyAccount1:
        - open group: Freshers
            - Permissions: 
                - Inline Policy
                    - Policy Generator:
                        Effect: Allow
                        AWS Service: Amazon Security Token Service  (AWS STS)
                        Actions: AssumeRole
                        ARN: MyAccount2 S3AccessRole Role ARN OR * (wildcard)
                        - Add Statement
        - user got the permission to access the MyAccount2 s3AccessRole
        - Now, Login with Sagar User in MyAccount1
            - Sagar have only have AmazonEC2ReadOnlyAccess policy. He can see EC2 instances only. Not able to access S3 bucket in same account.
            - Profile (Right Top) > Switch Role > 
                - Account: MyAccount2
                - Role: S3AccessRole
                - Switch Role
            - Now, Sagar entered into MyAccount2 and can see the S3 bucket in MyAccount2
            - Profile (Right Top) > Back to Sagar: MyAccount1
                - Sagar entered into MyAccount1 and can see the EC2 instances in MyAccount1
*/
