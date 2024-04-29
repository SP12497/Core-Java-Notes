@Entity
@Table(name="laptops")
public class Laptop {
    @Id
    private int laptopId;
    private String modelNumber;

    @OneToOne                       // birectional mapping // create column with foreign key
    @JoinColumn(name="student_id")  // default name: student_student_id, rename: student_id
    private Student student;
}