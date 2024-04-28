@Entity
public class Product {
    @Id
    private String pId;
    private String productName;

    // @ManyToMany      
    // @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
    private List<Category> categories = new ArrayList<> ();
}