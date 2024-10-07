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

# (Part 2) Autoscaling Balancing:
    - Autoscaling scaling groups (ASG) contains information about the instances that are launched as part of the group with availability zones.
    - ASG=>  AZ1: 5 instances, AZ2: 3 instances, AZ3: 1 instance
    - Autoscaling balancing ensures that the number of instances in each availability zone is balanced.
    - Working:
        - Before: AZ1: 6 instances, AZ2: 2 instances, AZ3: 1 instance
        - create new instance to balance: total instances = 9
            AZ1: 6 instances, AZ2: 2 + 1 = 3 instances, AZ3: 1 + 2 = 3 instances
        - After (shut down the extra instances): AZ1: 3 instances, AZ2: 3 instances, AZ3: 3 instances
    - During the balancing process (creation of new instances), the total number of instances are increased to 12.
      if in ASG, min = 3, max = 9,
        then this max limit is exceeded only during the balancing process.
            formula: either 10% of the desired capacity or 1 instance (whichever is greater)
        - 10% of 9 = 0.9 = 1 instance
      working: create 1 instance in AZ2 and shut down 1 instance in AZ1, then go for next scaling (create new and shult down old)


Theory:
    - We can attach a running EC2 instance to an autoscaling group.
    - Instances must be in running state (not stopped or terminated) to attach to an autoscaling group.
    - Instances is not part of the autoscaling group until it is attached.
    - AMI used to launch the EC2 still exists even after the instance is attached to the autoscaling group.
    - Instances must be in the same availability zone as mentioned in the autoscaling group.

# Autoscaling Group:
        - Desired Capacity: Number of instances that should be running in the group.
        - Min Capacity: Minimum number of instances that should be running in the group.
        - Max Capacity: Maximum number of instances that should be running in the group.
        - Health Check Type: EC2 or ELB
        - Health Check Grace Period: Time to wait before checking the health of the instances.
        - Termination Policy: How to select the instance to terminate when scaling in.
        - Scaling Policies: Add or remove instances based on the conditions.
    - If we have to change the ASG of an instance, we have to detach the instance from the current ASG and attach it to the new ASG.
    - Delte ASG: if we delete ASG, then all instances in the group are terminated.
    - delete only ASG: detach all instances from the ASG and then delete the ASG.

# Elastic Load Balancer:
    - Load balancer distributes the incoming traffic to the instances in the autoscaling group.
    - Autoscaling group can be associated with multiple load balancers.
    - Load balancer can be associated with multiple autoscaling groups.
    - ASG and ELB must be in the same region.
    - Instances and ELB must be in the same VPC.
    - no need to register the instances with the load balancer, ASG will do it.
    
# Health Check:
    - Health Check used to determine the health of the instances.
        - Health Check Type: EC2 or ELB
        - Health Check Grace Period: Time to wait before checking the health of the instances. (default: 300 seconds)
    - If the instance fails the health check, then the instance is terminated and a new instance is launched.
    - Health Check Type:
        - EC2: Health check is performed by the EC2 instance.
        - ELB: Health check is performed by the ELB.
    - When you have one or more ELBs associated with the autoscaling group, then configure autoscaling to use both EC2 and ELB health checks to determine the health of the instances.
      eg. if we don't have ELB health check:
            - EC2 health check success: instance is healthy
            - ELB health check failed: instance is unhealthy, load balancer will not send traffic to the instance.
        eg. if we have ELB health check:
            - EC2 health check success: instance is healthy
            - ELB health check failed: instance is unhealthy, autoscaling will terminate the instance and launch a new instance.
    
    