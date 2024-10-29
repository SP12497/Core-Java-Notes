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
    - Launch Configuration (Scaling Policy): 
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

# Part 3: 
## Autoscaling SNS: 
    - In four situation, ASG sends a SNS notification:
        - instance Launched
        - instance Terminated
        - instance Fail to Launch
        - instance fail to terminate

## Merging ASG:
    - Merging ASG: Combining two or more ASG into one ASG.
    - This can be done by AWS CLI or AWS SDK. Not by AWS Management Console.
        eg. 
        - ASG1: AZ1   | ASG2: AZ2, AZ3
        -> Now merging ASG2 into ASG1
            Step 1: Change the AZ of ASG2 to AZ1
                - ASG1: AZ1, AZ2, AZ3  | ASG2: AZ2, AZ3
            Step 2: Delete ASG2
                - ASG1: AZ1, AZ2, AZ3
    - Launch Configuration: 
        - Template for the instances that are launched as part of the autoscaling group.
        - It defines the instance type, AMI, key pair, security group, block device mapping.
        - *Launch Configuration can not be modified/edit. But we can Copy or Delete the Launch Configuration.

## ASG Monitoring:
    - AWS EC2 services sends EC2 metrics to cloudwatch about the ASG instances.
    - Types of Moniroting:
        - Basic Monitoring: 5 min interval (Default for AWS Console) (free of cost)
        - Detailed Monitoring: 1 min interval (Default for AWS CLI/ SDK) (additional charges)

## EC2 Standby State:
    - EC2 Standby State: Temporarily stop the instance from serving the traffic.
    - We can manually move the instance from ASG and put it in standby state.
    - Instances in standby state are not terminated and still part of the ASG.
      But ELB will not send traffic to the instances in standby state.
    - Instances in standby state can be moved back to the InService state.
    - Uses of stand by mode:
        - To perform maintenance, troubleshooting, software updates or any other tasks on the instance.
    - ASG will not do Health Check of the instances in standby state.

## Autoscaling Policies:    
    - Scaling Policies: Add or remove instances based on the conditions.
    - Types of Scaling Policies:
        - Manual: 
            - AWS CONSOLE : Step 3 Configure advanced options: select "No scaling policies"
            - eg. No of instances must be: 2.  => min=2, max=2, desired=2
                - if 1 instance is failed, then automatically add 1 instance is created and ready to serve the traffic. 
                  2 running -> 1 running, 1 failed (Health check failed) -> 1 running + 1 created -> 2 running
                - It wont do autoscale, It will manage the number of instances fix count.
            - Add or remove instances manually.
        - Dynamic:
            1. Target Tracking Scaling:
                - adjusts resources to maintain a specific metric (like CPU utilization) at a target value automatically.
                - Maintain the target value of the metric.
                - real life example: 1. Just like maintaining AC room temperature at 22 degree. If the temperature is high, then AC will be turned on and if the temperature is low, then AC will be turned off.
                - eg. CPU Utilization: 70% criteria, 
                    - scale out scenario: running 4 instances, CPU Utilization: 80% (crossing 70% criteria), 
                        - then add 1 instance to make it 5 instances. 
                        - Now, Total running instances = 5, CPU Utilization: 80*4 / 5 => 64% (less than 70% criteria)
                    - scale in scenario: running 5 instances, CPU Utilization: 55%  (less than 70% criteria)
                        - then remove 1 instance to make it 4 instances.
                        - Now, Total running instances = 4, CPU Utilization: 55*5 / 4 => 68.75% (less than 70% criteria) 
                    - ASG will add 1 instance to maintain the CPU Utilization at 80%.
                    - ASG will add 1 instance to maintain the CPU Utilization at 80%.
            - Simple Scaling: 
                -  adds or removes instances in response to a single CloudWatch  alarm, one scaling action at a time.
                - Add or remove instances based on the conditions.
                -- Warm up period: (default: 300 seconds)
                    - Time to wait before checking the health of the instances. Once health check is passed, then the instance is ready to serve the traffic.
                -- Cooldown period: (default: 300 seconds)
                    - Time to wait before the next scaling activity.
                    - Ignore the scaling activity during the cooldown period.
                    - cloudwatch is continuously sending the metrics (Instances status: like CPU Utilization is 83%) to ASG. But ASG will ignore the scaling activity during the cooldown period.
                - eg. running 4 instances, CPU Utilization: 70% criteria, No of instance to Create/scale: 2
                    - scale out scenario: CPU Utilization: 80% (crossing 70% criteria), 
                        - then add 2 instances to make it 6 instances. 
                        - Now, Total running instances = 6, CPU Utilization: 80*4 / 6 => 53.33% (less than 70% criteria)
                        - ASG will wait to complete the warm up period and cooldown period. And wont create new instances during this period.
            - Step Scaling:
                - triggers scaling in steps, adjusting the amount of resources based on the magnitude of the metric breach.
                - Add or remove instances based on the conditions.
                - It only support warm up period and not cooldown period. So, every cloudwatch alarm (eg CPU:60% used, 70% used, 80% used) will trigger the scaling activity.
                - eg. 
                    Scale out=> CPU Utilization range: Number of instances
                        0-50%:  create 0 new instances
                        50-70%: create 1 new instances
                        70-80%: create 2 new instances
                        80-90%: create 2 new instances
                        90-100%:create 3 new instances
                    Scale in=> CPU Utilization range: Number of instances
                        0-50%:  remove 1 instances
                        50-70%: remove 2 instances
                        70-80%: remove 2 instances
                        80-90%: remove 3 instances
                        90-100%:remove 0 instances
    - Predective/Schedule/Cyclic Scaling: 
        - Predictive Scaling: 
            - uses machine learning to predict the future load and adjust the resources accordingly.
            - It uses machine learning model to forecast the daily and weekly load.
        - Schedule Scaling: 
            - adjusts resources based on the schedule.
            - eg. Sat-Sun: 10 instances, Mon-Fri: 5 instances
            - Note: Each schedule must have unique data/time. cant schedule 2 actions at the same time.
        - Cyclic Scaling: 
            - adjusts resources based on the cyclic pattern.
            - eg. 9 AM to 5 PM: 10 instances, 5 PM to 9 AM: 5 instances

# Part 4: LAB
    -Create EC2 Instance
        - Launch an EC2 instance using your desired AMI and instance type.
    - Create Auto Scaling Group (ASG)
        1. Choose Launch Template or Launch Configuration:
            - Launch Template: Pre-configured settings for instance launch.
            - Launch Configuration: Similar to templates but simpler.
        2. Choose Instance Launch Options:
            - Select the EC2 instance type and network settings.
            - Choose Multiple: VPC, Availability Zones (AZs), and Subnets.
            - Note: no extra charges for using multiple AZs.
        3. (Optional) Configure Advanced Options:
            - Configure health checks, load balancing, and instance monitoring.
            - Choose:
                - Load Balancer: 'create or  existing a load balancer' or 'No Load Balancer'.
        4. (Optional) Group Size and Scaling:
            - Set the desired number of instances and scaling policies.
            - Group size: Desired Capacity (number of instances) = 2
            - Scaling: Minimum Desired Capacity = 2, Maximum Desired Capacity = 10
                - Target tracking scaling policy:
                    - Target Value: 70  // 70% CPU Utilization
                    - Metric type: choose: average CPU Utilization
                        - 4 types: (IMP: Que. Autoscaling will add or remove instances based on the conditions?)
                            1. Average CPU Utilization : CPU usage percentage
                            2. Average Network In (Bytes) : Incoming network traffic greater than 4 MB per sec or 3GB per second
                            3. Average Network Out (Bytes) : Outgoing network traffic greater than 4 MB per sec or 3GB per second
                            4. Application Load Balancer request count per target : Requested by load balancer
        5. (Optional) Add Notifications:
            - Set up alerts for scaling activities.
            - Value: no/skip
        6. (Optional) Add Tags:
            - Assign tags to help identify resources.
        7. Review:
            - Review all settings and create the Auto Scaling Group.
        - Done.

    - Testing:
        create file in desktop in all EC2 instances:
            file name: a.bat
            data: 'a.bat'
        -> Run a.bat file in all EC2 instances multiple times to increase the CPU Utilization. (It will run in loop.)
    