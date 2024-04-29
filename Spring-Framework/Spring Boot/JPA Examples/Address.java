@Entity
public class Address {
    @Id
    private int addressId;
    private String street;
    private String city;
    private String country;

    @ManyToOne  // create FK
    @JoinColumn(name = "student_id")    // rename
    private Student student;
}
