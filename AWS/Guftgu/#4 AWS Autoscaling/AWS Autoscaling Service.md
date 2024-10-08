# AWS Autoscaling:
    - Autoscaling in on region level ie. inside VPC and outside Availability Zones. 
    - It is a feature of AWS that allows you to automatically adjust the number of EC2 instances in your application.
    - Scale OUT / Scale UP : Increase the number of instances
    - Scale IN / Scale DOWN : Decrease the number of instances
    - Creating group of EC2 instances that can scale up or down based depending on the conditions you set.
    - Enable Elasticity by scaling Horizontal through adding or terminating EC2 instances
        - Vertical Scaling: Increase the RAM, CPU, Storage of the instance
        - Horizontal Scaling: Increase the number of instances
    - Autoscaling ensures that you have the correct number of EC2 instances running to handle the load of your application.
    - Autoscaling helps you save cost by cutting down the number of instances when the load is low.

## AWS Autoscaling Components:
    - Launch Configuration: 
        - Template for the instances that are launched as part of the autoscaling group.
        - It defines the instance type, AMI, key pair, security group, block device mapping.
    - Autoscaling Group:
        - Group of instances that are launched based on the Launch Configuration.
        - It defines the minimum, maximum, desired number of instances. Health check Period (by default 5 min).
        - Group Name, Group Size, VPC, Subnets, Load Balancer, Health Check Type, Health Check Grace Period, Termination Policy.
    - Scaling Policies:
        - Metric Type:
            - CPU Utilization: Maximum, Minimum, Average
                - Number of Instances: 5
                - CPU Utilization: 80%
                - Scale Out: 5 instances
                - Scale In: 2 instances
        - Target Value: The value that the metric should maintain.

## (Part 2) Autoscaling Balancing:
    - Autoscaling scaling groups (ASG) contains information about the instances that are launched as part of the group with availability zones.
    - ASG=>  AZ1: 5 instances, AZ2: 3 instances, AZ3: 1 instance
    - Autoscaling balancing ensures that the number of instances in each availability zone is balanced.
    - Working:
        - Before: AZ1: 6 instances, AZ2: 2 instances, AZ3: 1 instance
        - create new instance to balance: total instances = 9
            AZ1: 6 instances, AZ2: 2 + 1 = 3 instances, AZ3: 1 + 2 = 3 instances
        - After (shut down the extra instances): AZ1: 3 instances, AZ2: 3 instances, AZ3: 3 instances
    - In ASG, we can mention the minimum and maximum number of instances that can be running in the group.
    - During the balancing process (creation of new instances), the total number of instances are increased to 12.
      if in ASG, min = 3, desired= 4, max = 9,
        then this max limit is exceeded only during the balancing process.
            formula: either 10% of the desired capacity or 1 instance (whichever is greater)
        - max is 9: 10% of 9 = 0.9 = 1 instance
        -- if max = 50, then 10% of 50 = 5 instances
      working: create 1 instance in AZ2 and shut down 1 instance in AZ1, then go for next scaling (create new and shut down old)


## Attach EC2 Instances to Autoscaling Group:
    - We can attach a running EC2 instance to an autoscaling group:
        - by using the AWS Management Console
        - by using the AWS CLI
        - by using the AWS SDK
    - Instances must be in running state (not stopped or terminated) to attach to an autoscaling group.
    - Instances is not part of the autoscaling group until it is attached.
    - (must needed AMI) AMI used to launch the EC2 still exists even after the instance is attached to the autoscaling group.
    - *Instances is part of only one ASG at a time.
    - Instances must be in the same availability zone as mentioned in the autoscaling group. 
      eg. ASG: configure with AZ2 and AZ3, then attach the instance in AZ1 will not work.

## Autoscaling Group:
        - Desired Capacity: Number of instances that should be running in the group.
        - Min Capacity: Minimum number of instances that should be running in the group.
        - Max Capacity: Maximum number of instances that should be running in the group.
        - Health Check Type: EC2 or ELB
        - Health Check Grace Period: Time to wait before checking the health of the instances.
        - Termination Policy: How to select the instance to terminate when scaling in.
        - Scaling Policies: Add or remove instances based on the conditions.
    - Decrease max limit:
        if you are detaching EC2 instaces from the ASG, then will get option to decrease the max limit.
        - ASG Issue: max=5, desired=3, min=1 and running are 5 instances.
            - if you detached running instance, then running instances = 5-1 = 4. Then new instance will be launched to make it 5.
            -> if you want to decrease the max limit, then detach the instance and then decrease the max limit.
    - Delte ASG will delete all EC2 instances: if we delete ASG, then all instances in the group are terminated. then min, max, desired capacity are set to 0. 
    - delete only ASG: detach all instances from the ASG and then delete the ASG.

## Elastic Load Balancer:
    - On region level. Must be in the same region as the autoscaling group.
    - Instance and the ELB must be in the same VPC.
    - We can associate multiple load balancers with the autoscaling group.
    - Load balancer distributes the incoming traffic to the instances in the autoscaling group.
    - Once you attach the EC2 instance to the autoscaling group, then it will automatically register with the load balancer.
      If you detached the instance from the ASG, then it will automatically deregister from the load balancer.
      no need to register the instances with the load balancer, ASG will do it.*
    
## Health Check:
    - Health Check used to determine the health of the instances.
        - Health Check Type: EC2 or ELB
        - Health Check Grace Period: Time to wait before checking the health of the instances. (default: 300 seconds)
    - If the instance fails the health check, then the instance is terminated and a new instance is launched.
    - Health check grace period:
        - Time to wait before checking the health of the instances.
        - Default: 300 seconds.
        - 0 sec: health check is performed immediately after the instance is launched. There is high chance that the instance is not ready to serve the traffic. 
          issue: instance is not ready to serve the traffic, then it will fail the health check and terminated.
            again new instance is launched and the same issue will be repeated. It will create a loop.
        - If the instance is not in running state, then the health check is not performed.
    - Health Check Type:
        - EC2: Health check is performed by the EC2 instance.
        - ELB: Health check is performed by the ELB.
    - When you have one or more ELBs associated with the autoscaling group, then configure/enable autoscaling to use both EC2 and ELB health checks to determine the health of the instances.
      eg. if we don't accept ELB health check:
            - EC2 health check success: instance is healthy
            - ELB health check failed: instance is unhealthy but ASG is not replacing the instance, and also load balancer will not send traffic to the instance. So issue is, the instance is not serving the traffic. Other instances are serving the traffic and getting high load.
        eg. if we have ELB health check:
            - EC2 health check success: instance is healthy
            - ELB health check failed: instance is unhealthy, autoscaling will terminate the instance and launch a new instance. So, the new instance will be healthy and serve the traffic.
    - Unhealthy instances scenario's to recreate the instance:
        - EC2 instance health check status other that 'running'. eg. stopped, terminated, pending, shutting-down, stopping.
        - ELB health check failed. (ELB health check is enabled) eg. instance is not ready to serve the traffic.
        - Availability Zone Balancing: If the number of instances in each availability zone is not balanced.
          ASG will create a new instance and 'terminate unhealthy instance first' to balance the instances in each availability zone, later it will terminate the extra instances (running instances) 
        - Elastic IP and EBS volumes gets detached from terminated instances, you need to manually attached to the new instance. (if you want to keep the same IP and EBS volume) (Elastic IP and EBS volume are not part of the ASG) (You will get charged for the EBS volume and Elastic IP even if the instance is terminated)

# Part 3: Scaling Policies.