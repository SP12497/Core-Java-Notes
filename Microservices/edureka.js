/* https://youtu.be/tuJqH3AV0e8?si=MfAqdiZrm8GfcGta
Monolithic Architecture: 
    - Definition: It is a single-tiered software application in which different components combined into a single program from a single platform.
    - It is like a big container wherein all the software components of an application are assembled together and tightly packaged.
    - Challenges:
        - Large and complex applications
        - Slow Development
        - Blocks Continuos Deployment
        - Unscalable: 
        - Unreliable: If one part of the application fails, the entire application goes down.
        - Inflexible : If you want to change one part of the application in different technology/language, you have to change the entire application. 

- Microservices Architecture:
    - Defination: A microservices architecture, also known as microservices, is an architectural method that relies on a series of independently deployable services.
    - it is an architectural style that structures an application as a collection of small autonomous services, modeled around a business domain.
    - In micronservice architecture, each service is self-contained and implements a single business capability.
    - Features:
        - Small and focused
        - Loosely coupled
        - Language Neutral
        - Bounded Context: Each service is bounded by a specific context. so each service is not bothered about the other services. Each service can have its own database. 

- Microservice with Spring Boot:
    - Spring Boot is a microservices framework that allows you to build microservices quickly and easily.
    - Embedded servers which are easy to deploy with the containers.
    - It helps in monitoring the multiple components.
    - It helps in configuring the components externally.

- Best Operating System for Microservices:
    - Linux: 
        - It is open-source and free.
        - Most commonly used OS for microservices.
        - A self-contained execution environment.
        - Orchestrate small to big services.
        - Automatic red hat and ubuntu including LXD.
    - Programming Language:
        - Spring Boot: Java
            - Provide auto-configurations to load default configuration
            - Avoids usage of WAR files
            - Opinionated view and maven configuration
            - Consists wide range of plugins, API, and libraries
        - Elixir:
            - Functional programming language
            - Scalable and fault-tolerant
            - Easy to write and maintain
            - High performance
    - Tools for API Management: 
        - Postman:
        - AOI Fortress:
        - Swagger:
    - Tools for messaging:
        (Used for messaging (communication) between microservices)
        - Kafka:
            - Has high throughput for publishing and subscribing to streams of records (messages).
            - Guarantee zero downtime and zero data loss.
            - Messages persist on disk as fast as possible.
            - Applications can plugin and make use of Kafka.
        - RabbitMQ:
            - Offers a variety of features such as reliabilty, flexibility, and scalability.
            - Messages are routed through exchanges before they are delivered to queues.
            - Federation Model: It allows you to connect multiple RabbitMQ brokers together.
            - Supports messaging over multiple protocols.
    - ToolKits:
        (Used for monitoring and managing microservices)
        - Fabric8:
            - Used for building microservices in Java.
            - helps for, configuration management through git. 
            - Open source tool handles port mapping and ip address.
            - Provides a set of wizards to application faster.
            - Comes with on-premise Git repository hosting.
            - Provide maven repository manager.
            - Provides the developer console to create, build and manage microservices.
        - Senaca:
            - Used for building microservices in Node.js.
            - A microservices toolkit for Node.js. 
            - Used to build message-based microservices processes, and its toolkit for node.js. 
              This toolkit helps clean and organized code with systematic business logic of the application.
            - Provides plugins which look after the foundation of the application.
            - Structures the components.
            - Everything is written as a command.
            - Flixible with which command gets the work done.
    Architectural Frameworks:
        - GOA:
            - Used for building microservices in Go.
            - A framework for building microservices in Go.
            - It is a design-first approach.
            - It is a code generation tool.
            - It is a set of libraries.
            - It is a set of guidelines.
            - It is a set of best practices.
            - It is a set of tools.
        - Kong:
            - Used for building microservices in Lua.
            - helps in enhancing the development and deployment of microservices.
            - Provide plugins to extend and connect services.
            - Analysis real-time data and leverage the data.
            - Connects with automation tools.
            - Provides role-based access control.
    - Tools for Orchestration:  (Scaling and managing microservices)
        - Kubernetes:
            - Open-source platform for container management (orchestration) tools.
            - Deploy and update secrets and application configuration
            - Manage your batch and CI workloads.
            - Can scale up or scale down the number of containers.
            - You can mount the storage system of your choice.
        - Istio:
            - Service mesh (connect, secure, control, and observe services) for Kubernetes.
            - Open-source platform for microservices.
            - Connect, secure, control, and observe services.
            - Provides automatic tracing, monitoring, and logging.
            - Secures services throw encryption and authentication.
            - Controls the flow of traffic and API calls between services.
            - Applies policies and ensures that they are enforced.
    - Tools for Monitoring:
        - Prometheus:
            - it is monitoring tool, allows visualizing, monitoring information and supports time-based tracking for anamalous patterns which has to be detected.
            - Provides a flexible query language.
            - Distributed storage and single server nodes.
            - Discovers targets via service discovery or static configuration.
            - Supports multi-dimensional data model.
            - Provides dashboarding and graphing support.
        - Grafana:
        - Logstash:
            - Used for log data collection and processing.
            - Supports a variety of inputs and outputs.
            - Transforms data from one format to another.
            - You can choose your own stash and transport data.
            - A pluggable framework consisting of 200 plugins.
    - Serverless Tools:
        (optimizes the methodology of breaking stuff into small pieces)
        - Cloudia:
            - Used to deploy and manage serverless applications. (deploy and manage AWS lambda and API gateway)
            - Deploy and update using a single command.
            - Reduces the boiler plate code.
            - You can manage multiple versions.
            - Use standard NPM packagaes.
        - AWS Lambda:
            - Serverless computing service.
            - Run code without provisioning or managing servers.
            - Automatically scales and manages the infrastructure.
            - Pay only for the compute time you consume.
            - Supports multiple languages.
            - Runs Code in response to events
            - Runs the code without managing the servers.
            - Automatically scales the applications.
            - Builds a serverless back-end.

10 Reasons to Leand Microservice:
    - Easy to build and maintain.
    - Continuous Delivery.
    - Hybrid Technology.
    - Cross-Functional Teams.
    - Higher Quality Code.
    - Smarter Scaling.
    - Risk Reduction.
    - Promote Bid Data best practices.
        - Fit within a data pipeline-oriented architecture, which aligns with the big data should be collected, ingested, process and delivered.
        - Each service can be designed to store data in a way that is optimized for the specific needs of the service.
    - Improved ROI with reduced TCO
        - Microservices can be deployed independently, which means that you can scale only the services that need to be scaled.
        - Microservices allows to optimize the resources and reduce the cost.
    - Popularity ang high salaries.

- Design patters:
    - Why do we need?
        - By using Desing pattern, the team working on various projects use the same pattern to build similar applications.
    - What are desing patterns?
        - Software Desing Pattern can be defined as software template or a description to
          solve a problem that occurs in multiple instances while designing a Softwate application or software framework.
- Pricipal Behind Microservices:
    - Independent and Autonomous services.
    - Scalability.
    - Decentralization : Each service is independent and can be deployed independently.
    - Resilience: If one service goes down, the other services are not affected.
    - Real-time Load Balancing.
    - Availability.
    - Continuous delivery through DevOps Integration.
    - Seamless API integration and Continuous Monitoring 
    - Isolation on Failures: If one service fails, the other services are not affected.
    - Auto-Provisioning: It can automatically provision the services.

- Microservices Desing Patterns:
    - Aggregator Pattern:
        - Collects related items of Data and Displays them based on DRY (Don't Repeat Yourself) principle.
        - Example: Web Page wants to display the Student Details and the Marks of the Student. (
            Student Details and Marks are two different services
            Now, web page will call the Student Details Service and Marks Service together and gather and display the data.
        - When to use?
            - When you want to combine multiple requests into a single request.
            - When you want to reduce the number of requests.
    - API Gateway:
        - Can convert the protocol request from one type of other.
        - API Gateway is a single entry point for all the clients, which forwards the request to the appropriate microservices.
    - Chain or Chain of Responsibility:
        - Produces a single output which is a combination of multiple chained outputs (response from multiple services are combine and returned).
        - Use Synchrnous HTTP request or response for messaging.
        - EX:  client -> service1 <-> service2 <-> service3
    - Asynchronous Messaging:
        - Used to communicate between microservices.
        - It is a messaging pattern that allows the sender and receiver to communicate with each other without knowing each other.
        - eg. Http request -> RabbitMQ (SQS) -> Service1 -> RabbitMQ (SQS) -> Service2
        - All the services can communicate with each other, but they do not have to communicate with each other sequentially.
    - Database Design pattern: 
        - Database Per Service: Each service has its own database.
        - Shared Database: All the services or some services share the same database.
    - Event Sourcing:
        - It is a design pattern that involves capturing all changes to an application state as a sequence of events.
        - Creates events regarding the changes in the Application State.
        - Event is stored in the Event Store.
    - Branch Pattern:
        - Simultanously process the request and responses from two or more independent microservices.
    - CQRS (Command Query Responsibility Segregation):
        - Application is divided into two parts: Command and Query.
        - Command Part: handles all the requests related to the write operation (insert, update, delete) of the database.
        - Query Part: handles all the requests related to the read operation (get/query) of the database.
    - Circuit Breaker:
        - It is a design pattern that allows the system to continue to operate when a failure occurs.
        - For example, if a service is down, the user is not aware and keep sending the request to the service, which results bad user experience (user waits for timeout) and high load on the service.
            Circuit Breaker is used to avoid this situation. 
            Circuit Breaker will maintain the Proxy Service, which will check the status of the service. If number of failures exceeds the threshold, the Circuit Breaker will open and the request will be redirected to the fallback service for certain time.
                This is avoid the high load on the service and bad user experience.
            After the certain time, the Circuit Breaker will close and the request will be redirected to the actual service.
    - Decomposition:
        - Decompose the monolithic application into multiple services.
        - Decompose the monolithic application into multiple services.
        - Decomposition (Divide) of application according to:
             - Sub Domain of Application: Divide the application based on the subdomain(like user, product, order, payment, etc).
             - Business Capability: Divide the application based on the business.
             - Strangler or Vine Pattern: Decompose the application based on the functionality,

- Spring Boot CLI:
    - Download Maven and set the path in the environment variable.
    - Download Spring Boot CLI from the official website.
    - Set the path of the Spring Boot CLI in the environment variable.
    - Open the command prompt and type spring --version to check the version of the Spring Boot CLI.
    - RUN => mvn spring-boot:run

- Spring Boot Benefits with Microservices:
    - Embedded servers which are easy to deploy with the containers.
    - It helps in monitoring the multiple components.
    - It helps in configuring the components externally.

- Eureka Service:
    - Register the services with the Eureka Server.
    - Practical Example:
        - Project 1: Eureka Server
            - Dependency:
                Eureka Server.
            - application.properties:
                server.port=8761
                eureka.client.register-with-eureka=false        // Disable the registration of the service with the Eureka Server.
                eureka.client.fetch-registry=false
                eureka.client.service-url.default-zone=http://localhost:8761/eureka
            - Enable Eureka server:
                - Add annotation in Spring boot starter class.
                - @EnableEurekaServer @SpringBootApplication class EurekaServerApplication 
        - Project 2: Eureka Client (Item Catalog Service)
            - Dependency:
                Eureka Discovery, Actuator, Web, DevTools, JPA, H2, Rest Repositories, Lombok
            - application.properties:
                server.port=8081
                spring.application.name=Item-Catalog-Service
                // eureka.client.service-url.default-zone=http://localhost:8761/eureka
            - application-cloud.properties:
                eureka.instance.hostname=localhost
                eureka.client.service-url.default-zone=http://localhost:8761/eureka
                ...

            - Enable Eureka Client:
                - Add annotation in Spring boot starter class.
                - @EnableDiscoveryClient @SpringBootApplication class ItemCatalogApplication
            - How to run Project:
                - Select project and Maven Build:
                    Goals: spring-boot:run
            - Test the service:
                - Now, service is registered with the Eureka Server (http://localhost:8761/).
                - Open the browser and type http://localhost:8081/items to check the service is running or not.
        - Project 3: edge-service
            - It is used to fallback the service if the service is down.
            - Dependency:
                Eureka Discovery (register in Eureka server), Feign (to call the service), Zuul (intelligence routing system), Rest Repositories (expose JPA repositories as REST endpoints), web (Spring MVC and REST and embedded Tomcat), \
                hytrix (circuit breaker), 
                Lombok (reduce boilerplate code), Web (Spring MVC and REST), DevTools (auto-restart the server), Actuator (monitor and manage the application), H2 (in-memory database), MySQL (database), Config Client (to read the configuration from the config server).
            - application.properties:
                server.port=8080
                spring.application.name=edge-service
            - application-cloud.properties:
                eureka.instance.hostname=localhost
                eureka.client.service-url.default-zone=http://localhost:8761/eureka
            - Enable edge-service:
                - Add annotation in Spring boot starter class.
                - @EnableFeignClients @EnableCircuitBreaker @EnableZuulProxy @EnableDiscoveryClient @SpringBootApplication class EdgeServiceApplication
            - Test:
                - Open the browser and type http://localhost:8080/items to check the service is running or not.
    - service discovery server.
    - Dependency: Eureka Discovery, 
        Actuator, Web, DevTools, JPA, H2, Rest Repositories, MySQL, Lombok, Web, Lombak
    - @EnableEurekaServer @SpringBootApplication
    - application.properties: 
        server.port=8761
        eureka.client.register-with-eureka=false
        eureka.client.fetch-registry=false
        eureka.client.service-url.default-zone=http://localhost:8761/eureka
    - @EnableEurekaServer: This annotation is used to make your Spring Boot application acts as a Eureka Server.

Microservices Security:
    - Problems Faced in Microservices:
        - Login and Security:
            - When you have multiple services, you have to manage the login and security for each service.
        - Client details and permissions need to be verifed as and when a request is send.
        - Microservices communicate with each other simultaneously in addition to the 3rd party applications.
            - developer `need to make sure that the client does not get access to the data of microservices, in a way that, he/she might exploit them.
    - Best Practices to Secure Microservices:
        - Defence in Depth Mechnism:
            - It is a security strategy that involves multiple layers of security to protect the system.
            - Identify the services with the most sensitive information and apply the security layers to them.
            - Or you can apply the security layers to all the services 
            - or different layers of security to different services. So that, if one layer is breached, the other layer will protect the system.
        - Tokens and API Gateway:
            - Tokens are used to easily identify the client and stored in the form of cookies. And the permissions of the client.
            - JWT (JSON Web Token) is used to store the information in the form of JSON.
            - API Gateway is used to manage the tokens and permissions.
        - Distributed Tracing and Session Management:
            - Distributed Tracing is used to trace the request from the client to the server and back to the client.
            - it uses the unique ID (correlation ID) to trace the request.
            - Session Management: 
                It is used to manage the session of the client. 
        - First Session and Mutual SSL:
            - First Session: 
                - Users need to login to the application once and then they can access all the services in the application.
            - Mutual SSL:
                - With mutual SSL, the data transferred between the services will be `encrypted` and `decrypted` using the SSL certificate (TLS protocol).
        - 3rd Party Application Access:
            - OAuth:
                - The application promts the user to authorize the 3rd party application, to use the required information and generated a token for it.

What are the characteristics of Microservices?
    1. Organized around Business Capabilities.
    2. Products not Projects.
    3. Smart Endpoints and Dumb Pipes.
    4. Decentralized Data Management.
    5. Decentralized Governance.
    6. Infrastructure Automation.
    7. Design for Failure.

What is DDD (Domain Driven Design)?
    - Domain Driven Design is an approach to software development that centers the development on programming a domain model that has a rich understanding of the processes and rules of a domain.
    - Aims to ease the creation of complex applications by connecting the related pieces of the software into an ever-evolving model.
    - DDD focuses on 3 Principles:
        - Focus on the Core Domain and Domain Logic. (focus on specific domain (specific domain) on specific service)
        - Find complex designs on models of the domain.
        - Constantly collaborate with domain experts to improve the application model and resolve any emerging domain-related issues.

Pros and Cons of Microservices:
    - Pros:
        - Scalability: You can scale the services independently.
        - Flexibility: You can use different technologies for different services.
        - Resilience: If one service fails, the other services are not affected.
        - Easy to understand and maintain.
        - Easy to deploy and test.
        - Easy to develop and deploy.
        - Easy to develop and deploy.
    - Cons:
        - Complex to manage.
        - Complex to deploy.
        - Complex to monitor.
        - Complex to test.
        - Complex to secure.
        - Complex to maintain.
        - Complex to develop.
        - Complex to deploy.

What is Actuator in Spring Boot?
    - Spring Boot Actuator is a sub-project of Spring Boot.
    - It is used to access current state of running application in production.
    - Spring Boot Actuator provides web services endpoints which you can simply use and check various metrics.
    - Spring Boot Actuator helps you to monitor your application and gather the information about the application.

Different types of Testing in Microservices:
    1. Unit Testing:
        - It is used to test the individual components of the application.
    2. Exploratory Testing:
        - It is used to test the application without any prior knowledge of the application.
    3. Acceptance Testing:
        - It is used to test the application against the requirements.
    4. Performance Testing:
        - It is used to test the performance of the application.

*/
