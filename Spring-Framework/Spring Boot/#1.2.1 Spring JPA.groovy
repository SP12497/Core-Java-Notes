Java Persistence API (JPA) - Spring Data JPA:

Dependency: spring-boot-starter-data-jpa
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-jpa</artifactId>
	</dependency>

Core Concepts:
	JPA: 
		A specification for managing relational data in Java applications using an object-relational mapping (ORM) approach.
	Spring Data JPA: 
		A Spring framework module that simplifies JPA implementation by providing repositories and other abstractions.

Repository Interfaces:
	public interface Repository<T, ID> {}
		- Base interface for repository classes.
	public interface CrudRepository<T, ID> extends Repository<T, ID> {}
		- Adds CRUD methods to the repository.
	public interface PagingAndSortingRepository<T, ID> extends CrudRepository<T, ID> {}
		- Adds paging and sorting methods to the repository.
	public interface JpaRepository<T, ID> extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {}
		- Adds JPA-specific methods to the repository.

	interface StudentRepository extends JpaRepository<Student, Integer> {}
		- Custom repository interface that extends JpaRepository.
		- Supports only Wrapper classes (Integer, Long, etc.) as ID types. Does not support primitive types (int, long, etc.).
		- can handle CRUD operations, pagination, sorting, and custom queries for the Student entity.

-----------------------------------
JPA Provides:
	EntityManagerFactory:
		- Represents a factory for creating EntityManager instances.
		- EntityManagerFactory is a heavyweight object that should be created once per application.
		- EntityManagerFactory is thread-safe and can be shared among multiple threads.
		- Creates EntityManager instances.
	EntityManager:
		- Provides APIs to perform CRUD operations on entities. It's responsible for interacting with the database in a JPA-compliant manner.
		- Create, Update, Read, Delete (CRUD) operations.
		- EntityManagerFactory creates EntityManager instances.

------------
CRUD:
	Create & Update:
		save(entity)
		saveAll(entities)
		saveOrUpdate(entity)
	Delete:
		deleteById(id)
		deleteAll()
	Read:
		findById(id)
		findAll()
		findAllById(ids)

Custom Query Methods:
	- Spring Data JPA allows creating custom queries based on method names:
		- https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
		- These methods follow the pattern: findBy + PropertyName + Criteria.
		List<User> findByName(String name); 		=> introducer (findBy) + criteria (Name)
		List<User> findByUsernameAndPassword(String username, String password)
		List<User> findByAgeLessThan(int age);
		List<User> findByNameContaining(String words);
		- findByNameStartingWith(String prefix)
		- findByNameEndingWith(String suffix)
		- findByNameOrderByName(String name)
		- findByAgeIn(Collection<Integer> ages)

Approaches for Custom Queries:
	- Spring data jpa provides three different approaches for creating custom queries with query methods.
	1. Query Methods by Method Names:
		- Spring Data JPA derives queries from method names, 
		  such as findByEmailAndLastname(String email, String lastname).
	2. Named Queries:
		- Define queries using @NamedQuery and @NamedNativeQuery annotations.
		- @NamedQuery annotation : for JPA query language
		- @NamedNativeQuery annotation : allow you to define the query in native SQL by losing the database platform independent.
		- Syntax: 
				@NamedQueries(value ={
					@NamedQuery (name="" , query= ""),
					@NamedQuery (name="" , query= "")
				})
				public class Person{}
		- Example:
			@NamedQueries({
				@NamedQuery(name = "Person.findByLastname", query = "SELECT p FROM Person p WHERE p.lastname = :lastname")
			})
			public class Person {
				// Fields, getters, setters
			}

			@Repository
			public class PersonRepository {

				@PersistenceContext
				private EntityManager entityManager;

				public List<Person> findByLastname(String lastname) {
					return entityManager.createNamedQuery("Person.findByLastname", Person.class)
										.setParameter("lastname", lastname)
										.getResultList();
				}
			}
				- Explanation:
					@PersistenceContext: Injects the EntityManager instance.
					createNamedQuery("Person.findByLastname", Person.class): Creates a query using the named query "Person.findByLastname".
					setParameter("lastname", lastname): Binds the lastname parameter to the query.
					getResultList(): Executes the query and returns the result as a list of Person entities.
			- Call:
				@Autowired
				private PersonRepository personRepository;
				public void someMethod() {
					List<Person> people = personRepository.findByLastname("Smith");
					// Process the result
				}
	3. Query Annotation:
		a. JPQL/HQL: (Java Persistence Query Language / Hibernate Query Language)
			- 	@Query("SELECT p FROM Person p WHERE LOWER(p.lastname) = LOWER(:lastname)")
				List<Person> find(@Param("lastname") String lastName);
			- 	@Query(value="select u from User u where u.emailAddress = ?1")
				User findByEmailAddress (String emailAddress);	// ?1 -> first param
		
		b. Native SQL:
			@Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
			User findByEmailAddress(String emailAddress);

		c. Modifying Queries:
			- When you need to perform insert, update or delete operations within a custom query:
			@Transactional	// for insert, update, delete
			@Modifying		// for insert, update, delete
			@Query("UPDATE User u SET u.firstname = ?1 WHERE u.lastname = ?2")
			int setFixedFirstnameFor(String firstname, String lastname);
			
			- @Modifying: Indicates that the query modifies the database.
			- @Transactional: Ensures that the query is executed within a transaction. 
				- These annotations ensure the operation is executed in a transactional context, 
				  and @Modifying indicates that the query is altering the data.
			- int: Returns the number of affected rows.
