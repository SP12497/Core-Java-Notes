Spring JDBC:
    - Spring Jdbc is a powerful mechanism to connect to the database and execute SQL queries.
    - Developed on top of Java JDBC
    - JDBC is a API to perform operation with database.

Problem of Java JDBC:
    - We need to write a lot of code
    - Exception handling problem: Checked exception, SQLException
    - (Imp: Switching from MySql to Oracle) Repeating of all these code from one to another database logic is a time consuming task. 

Benefits of Spring JDBC:
    - Solution of JDBC problems are provided.
    - Spring JDBC provide class JdbcTemplate which has all the important methods to perform operation with database.

JdbcTemplate:
    update()
    execute()
    queryForObject()
    query()
    ... and many more

Project:
    - create maven quick start project
    - add dependencies: spring core, spring context, 
        - spring-jdbc 5.2.3.RELEASE version
        - mysql-connector-java.

===============================
interface DataSource {}
class DriverManagerDataSource implements DataSource {}
class JdbcTemplate { DataSource dataSource; }

class org.springframework.jdbc.datasource.DriverManagerDataSource
    - driverClassName = com.mysql.jdbc.Driver
    - url   = jdbc:mysql://localhost:3306/<db_name>
    - username = root
    - password = root

@Configuration
public class JavaConfig {
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/springjdbc");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean("jdbcTemplate")
    public JdbcTemplate getTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(getDataSource());
        retun jdbcTemplate;
    }
}

------------------
@Autowired
JdbcTemplate template;  // it will automatically inject the JdbcTemplate object.

- String query = "insert into student(id, name, city) values(?,?,?)";
int result = template.update(query, 01, "Sagar", "NDB"); // same use for insert, update delete query

- Selecting data using Spring JDBC:
    for single row: public T queryForObject(String sql, RowMapper<T> rowMapper, Object args)
    for multiple rows: public List<T> query(String sql, RowMapper<T> rowMapper)
    interface RowMapper: It convert ResultSet Object into T/class Object.

Practical:
    public class RowMapperImpl implements RowMapper<Student> {
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {           // interface RowMapper {T mapRow(...);}
            Student student = new Student();
            student.setId(rs.getInt(1));        // first column: id
            student.setName(rs.getString(2));   // second column: name
            student.setCity(rs.getString(3));   // third column: city
            return student;
        }
    }

    public class StudentDaoImpl {                   // Service class
        public Student getStudent(int studentId) {
            String query = "select * from student where id=?";  // "select id, name, city from student where id=?"
            RowMapper<Student> rowMapper = new RowMapperImpl();
            Student student = this.jdbcTemplate.queryForObject(query, rowMapper, studentId);
            return student;
        }
    }

-----
Spring Boot Jdbc : (MySQL DB)
    - dependencies: 
        spring-boot-starter-jdbc
        mysql-connector-java
        spring-boot-starter-web
    - application.properties:
        spring.datasource.url=jdbc:mysql://localhost:3306/myJdbcDb
        spring.datasource.username=root
        spring.datasource.password=root
    - Repository:
        @Repository
        public class UserDao {
            @Autowired
            private JdbcTemplate jdbcTemplate;

            public int createTable() {
                String query ="CREATE TABLE IF NOT EXIST User(id int primary key, name varchar(50), age int, city varchar(50))";
                int affectedRows = this.jdbcTemplate.update(query);
                return update;
            }

            public int insertUser(Integer id, String name, Integer age, String city) {
                String query = "insert into user(id, name, age, city) values(?,?,?,?)";
                int affectedRows = this.jdbcTemplate.update(query, new Object[] {id, name, age, city});
            }
        }
    - call createTable():
        // CommandLineRunner: It will run the code after the application context is loaded.
        public class SpringBootMainApplication implements CommandLineRunner {
            @Autowired
            private UserDao userDao;
            
            public static void main(String [] args) {
                SpringApplication.run(BootjdbcexampleApplication.class, args);
            }

            @Override
            public void run(String... args) throws Exception {
                this.userDao.createTable();     // this not static method, can't run in static main class. So implemented CommandLineRunner for non static method to run our code.
            }
        }

-----------------
Spring Boot Jdbc : (PostGres DB)
    - dependencies:
        postgresql
        spring-boot-starter-jdbc
        spring-boot-starter-web
    - application.properties:
        spring.datasource.url=jdbc:postgresql://localhost:5432/mydb
        spring.datasource.username=postgres
        spring.datasource.password=root
    - Repository:
        @Repository
        public class UserDao {
            @Autowired
            private JdbcTemplate jdbcTemplate;

            public int createTable() {
                String query ="CREATE TABLE Student(id SERIAL primary key, name varchar(50) NOT NULL, city VARCHAR(255))";
                int affectedRows = this.jdbcTemplate.update(query);
                return update;
            }

            public int insertUser(Integer id, String name, Integer age, String city) {
                String query = "insert into student(name, city) values(?,?)";
                int affectedRows = this.jdbcTemplate.update(query, name, city);
            }
        }