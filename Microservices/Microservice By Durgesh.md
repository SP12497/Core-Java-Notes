``` java

Durgesh Microservice course:
https://github.com/LearnCodeWithDurgesh/Microservices-Tutorial-Series

Total 3 Services:
    - Service 1: User Service
    - Service 2: Rating Service
    - Service 3: Hotel Service

    API Gateway -> User Service (with DB1):
                    -> Rating Service (with DB2)
                    -> Hotel Service (with DB2)
                -> Okta Auth (OAuth2.0)
                -> Service Registry (Eureka)
    Github -> Config Server

    Request Flow:
        - API Gateway -> User Service (Get User by User Id)
                            -> Rating Service (Get Rating by User Id)
                            -> Hotel Service (Get Hotel by Hotel Id)
Project:
    - Service 1: User Service (MySQL)
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
            - spring.jpa.hibernate.ddl-auto: update     // create, update, validate, none
            - spring.jpa.show-sql: true
        - model/entity:
            - @Entity public class User {
                @Id
                private String userId;
                private String name;
                private String email;
                private String about;
                @Transient
                private List<Rating> ratings = new ArrayList<>();
            }
            - public class Rating {         // Without @Entity, same structure as Rating Service
                ...
                private List<Hotel> hotels = new ArrayList<>();
            }
            - public class Hotel { ... }
        - repository:
            - public interface UserRepository extends JpaRepository<User, String> {}
        - Controller:
            - GET: /users
            - POST: /users
            - GET: /users/{userId}
            // - PUT: /users/{userId}
            // - DELETE: /users/{userId}
    - Service 2: Hotel Service (PostgreSQL)
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
        - model/entity:
            - @Entity public class Hotel {
                @Id
                private String id;
                private String name;
                private String location;
                private String about;
            }
        - repository:
            - public interface HotelRepository extends JpaRepository<Hotel, String> {}
        - Controller:
            - GET: /hotels
            - POST: /hotels
            - GET: /hotels/{hotelId}
            // - PUT: /hotels/{hotelId}
            // - DELETE: /hotels/{hotelId}
    - Service 3: Rating Service (MongoDB)
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
            - @Entity public class Rating {
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
            - GET: /ratings/user/{userId}
            - GET: /ratings/hotel/{hotelId}
            - POST: /ratings                 // create rating
    
    - ServiceRegistry:  (Eureka Server)
        - Dependencies:
            - Eureka Server (Spring Cloud Discovery)  (spring-cloud-starter-netflix-eureka-server)
            - Cloud Bootstrap (Spring Cloud)      (spring-cloud-starter)
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
                - Cloud Bootstrap (Spring Cloud)    (Optional)
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

Note: 
    - How to call one service from another service?
        - Notes on RestTemplate:
                - Generic:
                    - ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseClass);
                    - ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseClass);
                    - eg. ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
                - GET
                    - restTemplate.getForObject(url, responseClass);
                    - restTemplate.getForEntity(url, responseClass);
                    -eg. ResponseEntity<Hotel> response = restTemplate.getForEntity(url, Hotel.class);
                - POST
                    - restTemplate.postForObject(url, request, responseClass);
                    - restTemplate.postForEntity(url, request, responseClass);
                    -eg. Map<String, Object> payload = new HashMap<>();  payload.put("userId", 1);
                        ResponseEntity<String> response = restTemplate.postForEntity(url, payload, String.class);
                - PUT:
                    - restTemplate.put(url, request);
                    - restTemplate.exchange(url, HttpMethod.PUT, requestEntity, responseClass);
                    - eg. restTemplate.put(url, request);   // response is void
                    - eg. ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
                - DELETE:
                    - restTemplate.delete(url);
                    - eg. restTemplate.delete(url);   // response is void
                - RESPONSE:
                    - response.getBody();
                    - response.getStatusCode();
                    - response.getHeaders();
        - Use RestTemplate:
            - RestTemplate restTemplate = new RestTemplate();
              restTemplate.getForObject("http://localhost:8082/hotel", Hotel.class);        // return Hotel object
            - ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8082/hotel", Hotel.class);        // return ResponseEntity<Hotel> // contains status code, headers, body
              Hotel hotel = forEntity.getBody();

            - using @autowired
                @SpringBootApplication          // @SpringBootApplication is also a @Configuration
                @EnableDiscoveryClient
                public class UserServiceApplication {
                    @Bean
                    @LoadBalanced   // LoadBalanced is used to call service using service name instead of IP address and port
                    public RestTemplate restTemplate() {
                        return new RestTemplate();
                    }

                    public static void main(String[] args) {
                        SpringApplication.run(UserServiceApplication.class, args);
                    }
                }

                public class UserServiceImpl implements UserService {
                    @Autowired
                    private RestTemplate restTemplate;
                    private Logger logger = LoggerFactory.getLogger(this.getClass());

                    public User getUserWithRating(String userId) {
                        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
                        // get rating for this user
                        Rating [] ratings = restTemplate.getForObject("http://rating-service/rating/users/" + userId, Rating[].class);      // required  @LoadBalanced
                        List<Rating> ratings = Arrays.asList(ratings);  // Arrays.stream(ratings).toList();
                        // Way 2: ArrayList<Rating> ratings = restTemplate.getForObject("http://localhost:8083/rating/users/" + userId, ArrayList.class);
                        // Way3: ArrayList<Rating> ratings = restTemplate.getForObject("http://rating-service/rating/users/" + userId, ArrayList.class);

                        // convert Rating[] to List<Rating>
                        List<Rating> ratingsList = Arrays.stream(ratings).toList();

                        // get hotel for this rating
                        List<Rating> ratingsList = ratings.stream().map(rating -> {
                            ResponseEntity<Hotel> hotelResponse = restTemplate.getForEntity("http://hotel-service/hotel/" + rating.getHotelId(), Hotel.class);
                            rating.setHotel(hotelResponse.getBody());
                            // hotelResponse.getStatusCode();
                            return rating;
                        }).collect(Collectors.toList());
                        logger.info("Ratings: " + ratings); 
                        return user;
                    }
                }
            
        - Use Feign Client:
            - Practical:
                - @FeignClient(name = "hotel-service")
                - public interface HotelServiceProxy {
                    @GetMapping("/hotel")
                    public List<Hotel> getAllHotels();
                }
            - Theory:
                - The Feign client is a declarative web service (HTTP client) developed by Netflix. 
                - It is used to make web service calls to other microservices in a more straightforward way.
                - If you want to use Feign, create a new interface and annotate it with @FeignClient.
            - Dependencies:
                - OpenFeign (SPRING CLOUD ROUTING) (spring-cloud-starter-openfeign)
                - Spring Cloud Starter Netflix Eureka Client
            - Configuration:
                @SpringBootApplication
                @EnableEurekaClient
                @LoadBalanced
                @EnableFeignClients             // Enable Feign Client
                public class UserServiceApplication {
                    public static void main(String[] args) {
                        SpringApplication.run(UserServiceApplication.class, args);
                    }
                }

                @FeignClient(name = "hotel-service")
                public interface HotelServiceProxy {
                    public interface HotelServiceProxy {
                        @GetMapping("/hotel/{hotelId}")
                        public Hotel getHotel(@PathVariable String hotelId);
                    }
                }

                @FeignClient(name = "rating-service")
                public interface RatingServiceProxy {
                    @GetMapping("/ratings/users/{userId}")
                    public List<Rating> getRatingByUserId(@PathVariable String userId);

                    @PostMapping("/ratings")
                    public Rating createRating(@RequestBody Rating rating);

                    @PutMapping("/ratings/{ratingId}")
                    public Rating updateRating(@PathVariable String ratingId, @RequestBody Rating rating);

                }
                
                public class UserService {
                
                    @Autowired
                    private HotelServiceProxy hotelServiceProxy;
                
                    public User getUserWithRating(String userId) {
                        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
                        Rating [] ratings = restTemplate.getForObject("http://rating-service/rating/users/" + userId, Rating[].class);

                        Hotel hotel = hotelServiceProxy.getHotel(ratings[0].getHotelId());
                        return user;
                    }
                }




```