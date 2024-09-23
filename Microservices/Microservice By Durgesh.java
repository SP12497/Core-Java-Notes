/* 
Durgesh Microservice course:

Total 3 Services:
    - Service 1: User Service
    - Service 2: Rating Service
    - Service 3: Hotel Service

    API Gateway -> User Service:
                    -> Rating Service
                    -> Hotel Service
*/
/*
Project:
    - Service 1: User Service
        - Dependencies:
            - Spring Web
            - MySQL Driver
            - Spring Boot Data JPA
            - Lombok
        - application.properties:
            - server.port: 8081
            - spring.application.name: user-service
            - spring.datasource.url: jdbc:mysql://localhost:3306/user
            - spring.datasource.username: root
            - spring.datasource.password: root
            - spring.jpa.hibernate.ddl-auto: update
            - spring.jpa.show-sql: true
    - Service 2: Hotel Service
        - Dependencies:
            - Spring Web
            - Postgre Driver
            - Spring Boot Data JPA
            - Lombok
        - application.properties:
            - server.port: 8082
            - spring.datasource.url: jdbc:postgresql://localhost:5432/hotel
            - spring.datasource.username: postgres
            - spring.datasource.password: root
            - spring.jpa.hibernate.ddl-auto: update
            - spring.jpa.show-sql: true
        - repository:
            - public interface HotelRepository extends JpaRepository<Hotel, String> {}
    - Service 3: Rating Service
        - Dependencies:
            - Spring Web
            - Spring Data MongoDB
            - Lombok
        - application.properties:
            - server.port: 8083
            - spring.data.mongodb.host: localhost
            - spring.data.mongodb.port: 27017
            - spring.data.mongodb.database: rating
            // - spring.data.mongodb.uri: mongodb://localhost:27017
        - model/entity:
            - public class Rating {
                @Id
                private String ratingId;
                private String userId;
                private String hotelId;
                private int rating;
                private String feedback;
            }
        - repository:
            - public interface RatingRepository extends MongoRepository<Rating, String> {
                // custom find method
                List<Rating> findByUserId(String userId);
                List<Rating> findByHotelId(String hotelId);
            }
        - service:
            @Autowired
            private RatingRepository ratingRepository;
            repository.save(obj);
            repository.findAll();
            repository.findByUserId(userId);
        - Controller:
            - GET: /rating/user/{userId}
            - GET: /rating/hotel/{hotelId}
            - POST: /rating                 // create rating
    - ServiceRegistry:
        - Dependencies:
            - Eureka Server (Spring Cloud Discovery)  (spring-cloud-starter-netflix-eureka-server)
            - Cloud Bootstrap (Spring Cloud)
        - application.properties:
            - server.port: 8761
            - eureka.instance.hostname: localhost
            - eureka.client.register-with-eureka: false // don't register itself                                // by default true
            - eureka.client.fetch-registry: false       // don't fetch registry information from eureka server  // by default true
        - Main class:
            @SpringBootApplication
            @EnableEurekaServer
            public class ServiceRegistryApplication {
                public static void main(String[] args) {
                    SpringApplication.run(ServiceRegistryApplication.class, args);
                }
            }
    - Service Discovery Client Setup:
        - UserService: (Same for HotelService and RatingService)
            - Dependencies:
                - Eureka Discovery Client (spring-cloud-starter-netflix-eureka-client)
                - Cloud Bootstrap (Spring Cloud)    
            - pom.xml:
                <properties>
                    <java.version>17</java.version>
                    <spring-cloud.version>2023.0.3</spring-cloud.version>
                </properties>
                <dependencies>
                    <dependency>
                        <groupId>org.springframework.cloud</groupId>
                        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
                    </dependency>
                </dependencies>
                <dependencyManagement>
                    <dependencies>
                        <dependency>
                            <groupId>org.springframework.cloud</groupId>
                            <artifactId>spring-cloud-dependencies</artifactId>
                            <version>${spring-cloud.version}</version>
                            <type>pom</type>
                            <scope>import</scope>
                        </dependency>
                    </dependencies>
                </dependencyManagement>
            - Main class:
                @SpringBootApplication
                @EnableDiscoveryClient
                public class UserService {
                    public static void main(String[] args) {
                        SpringApplication.run(UserService.class, args);
                    }
                }
            - application.properties:
                spring.application.name: user-service
                eureka.instance.prefer-ip-address: true
                eureka.client.fetch-registry: true  // Default true
                eureka.client.register-with-eureka: true  // Default true
                eureka.client.service-url.default-zone: http://localhost:8761/eureka    // Register with Eureka Server 
 */