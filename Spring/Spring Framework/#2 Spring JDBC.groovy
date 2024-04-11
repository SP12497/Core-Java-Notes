Spring JDBC:
    - Spring Jdbc is a powerful mechanis to connect to the database and execute SQL queries.
    - Developed on top of Java JDBC
    - JDBC is a API to perform operation with database.

Problem of Java JDBC:
    - We need to write a lot of code
    - Exception handling problem: Checked exception, SQLException
    - Repeating of all these code from one to another database logic is a time consuming task.

Benefits of Spring JDBC:
    - Solution of JDBC problems are provided.
    - Spring JDBC provide class JdbcTemplate which has all the important methods to perform operation with database.

JdbcTemplate:
    update()
    execute()
    queryForObject()
    query()
    ...

Project:
    - create maven quick start project
    - add dependencies: spring core, spring context, spring-jdbc 5.2.3. RELEASE version and mysql-connector-java.

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
public class JavaConfig{
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
JdbcTemplate template;

- String query = "insert into student(id, name, city) values(?,?,?)";
int result = template.update(query, 01, "Sagar", "NDB"); // same use for insert, update delete query

- Selecting data using Spring JDBC:
    for single row: public T queryForObject(String sql, RowMapper<T> rowMapper, Object args)
    for multiple rows: public List<T> query(String sql, RowMapper<T> rowMapper)
    interface RowMapper: It convert ResultSet Object into T/class Object.

Practical:
    public class RowMapperImpl implements RowMapper<Student> {
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {   // RowMapper {T mapRow(...); }
            Student student = new Student();
            student.setId(rs.getInt(1));
            student.setName(rs.getString(2));
            student.setCity(rs.getString(3));
            return student;
        }
    }

    public class StudentDaoImpl {
        public Student getStudent(int studentId) {
            String query = "select * from student where id=?";
            RowMapper<Student> rowMapper = new RowMapperImpl();
            Student student = this.jdbcTemplate.queryForObject(query, rowMapper, studentId);
            return student;
        }
    }