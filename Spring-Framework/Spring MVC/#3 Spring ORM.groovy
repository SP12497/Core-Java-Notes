Spring with ORM Framework:
    - Spring ORM is a module that provides integration layers for popular object-relational mapping APIs, including JPA, JDBC, Hibernate, and iBatis.
    - #3.1 ORM implementation.png

Advantages:
    - Less coding is required
    - Easy to test
    - Better exception handling
    - Integrated transaction management

------
interface HibernateOperations
interface InitializingBean
class HibernateTemplate
    - save
    - update
    - insert
    - get
    - loadAll

-------
How to work with Spring ORM:
    -> ProductDao -> HibernateTemplate -> interface SessionFactory = class LocalSessionFactoryBean {DataSource datasource} -> DONE
    LocalSessionFactoryBean Object needs:
        1. DataSource properties (url, username, password)
        2. Hibernate Properties (Dialect, format sql ...)
            - hibernate.dialect = org.hibernate.dialect.MySQL8Dialect
            - hibernate.show_sql = true
            - hibernate.hbm2ddl.auto = update   // create, update, validate, create-drop
        3. Annotation Class

------------
pom.xml dependencies:
    - create maven quick start project
    - dependencies: spring core, spring context and 
                    mysql-connector-java, spring-orm, hibernate-core

@Entity
@Table(name = "student_data") 
public class Student{
    @Id
    @Column(name = "student_id")
    private int studentId;
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "student_city")
    private String studentCity
}
public class StudentDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Transactional // for insert, update, delete
    public int insert (Student student) {
        Interger i = (Integer) this.hibernateTemplate.save(student);
        return i;
    }
}


CRUD:
    Create:     hibernateTemplate.save(student)
    Read:       Student student = hibernateTemplate.get(Student.class, studentId) / hibernateTemplate.load()
    Read All:   List<Student> students = hibernateTemplate.loadAll(Student.class)
    Delete:     void = hibernateTemplate.delete(student)
    Update:     hibernateTemplate.update(student), saveOrUpdate(Student.class, student)
