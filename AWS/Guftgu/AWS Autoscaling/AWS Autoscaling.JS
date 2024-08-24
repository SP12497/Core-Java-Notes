/*
AWS Autoscaling:
    - Scale OUT / Scale UP : Increase the number of instances
    - Scale IN / Scale DOWN : Decrease the number of instances
    - Creating group of EC2 instances that can scale up or down based depending on the conditions you set.
    - Enable Elasticity by scaling Horizontal through adding or terminating EC2 instances
        - Vertical Scaling: Increase the RAM, CPU, Storage of the instance
        - Horizontal Scaling: Increase the number of instances
    - Autoscaling ensures that you have the correct number of EC2 instances running to handle the load of your application.
    - Autoscaling helps you save cost by cutting down the number of instances when the load is low.

AWS Autoscaling Components:
    - Launch Configuration: 
        - Template for the instances that are launched as part of the autoscaling group.
        - It defines the instance type, AMI, key pair, security group, block device mapping.
    - Autoscaling Group:
        - Group of instances that are launched based on the Launch Configuration.
        - It defines the minimum, maximum, desired number of instances. Health check Period.
    - Scaling Policies:
        - Metric Type:
            - CPU Utilization: Maximum, Minimum, Average
                - Number of Instances: 5
        - Target Value: The value that the metric should maintain.

*/