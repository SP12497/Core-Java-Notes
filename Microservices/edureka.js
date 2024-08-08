/* https://youtu.be/tuJqH3AV0e8?si=MfAqdiZrm8GfcGta
Monolithic Architecture: 
    - It is like a big container wherein all the software components of an application are assembled together and tightly packaged.
    - Challenges:
        - Large and complex applications
        - Slow Development
        - Blocks Continuos Deployment
        - Unscalable: 
        - Unreliable: If one part of the application fails, the entire application goes down.
        - Inflexible : If you want to change one part of the application in different technology/language, you have to change the entire application. 

- Microservices Architecture:
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

- Eureka Service:
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

        */