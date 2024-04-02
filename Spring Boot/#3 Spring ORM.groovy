Spring with ORM Framework:

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
            - hibernate.dialect = org.hibernate.dialect.MySQL57Dialect
            - hibernate.show_sql = true
            - hibernate.hbm2ddl.auto = update
        3. Annotation Clas

------------
pom.xml dependencies:
    - create maven quick start project
    - dependencies: spring core, spring context and mysql-connector-java, 
                    spring-orm, hibernate-core

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
    private HibernateTemplate hibernateTemplate;

    @Transactional // for insert, update, delete
    public int insert (Student student) {
        Interger i = (Integer) this.hibernateTemplate.save(student);
        return i;
    }
}


CRUD:
Create:     hibernateTemplate.save(student)
Read:       Student student = hibernateTemplate.get(Student.class, studentId) , hibernateTemplate.load()
Read All:   List<Student> students = hibernateTemplate.loadAll(Student.class)
Delete:     void = hibernateTemplate.delete(student)
Update:     hibernateTemplate.update(student), saveOrUpdate(Student.class, student)