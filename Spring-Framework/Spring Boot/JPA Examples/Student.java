@Entity
@Table(name="students")
public class Student {
    @Id
    private int studentId;
    private String studentName;
    private String about;

    // @OneToOne(mappedBy = "student") // student: Laptop.student // neither create column or foreign key // Student have laptop object, but when saving Studnet in Db, wiil get only saves Student in db, not Laptop. save Laptop separately.
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)  // ALL: create,update,delete Student will do same operation on laptop. // .REMOVE: only for delete student will delete laptop. // PERSIST: saving student will save Laptop
    private Laptop laptop;

    // @OneToMany      // column will not created in Student table. // New reference mapping table is created: |student_student_id|address_list_addres_id|
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL) // create FK in AddressTable instead of creating new Reference Mapping Table.
    private List<Address> address = new ArrayList<>();

}
// Student student = studentRepository.save(studentObj);
// student.getLaptop().getModelNumber();
