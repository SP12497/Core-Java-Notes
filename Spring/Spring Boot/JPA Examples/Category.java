@Entity
public class Category {
    @Id
    private String cId;
    private String title;

    // @ManyToMany(mappedBy = "categories", cascase = CascadeType.ALL)  // @ManyToMany Product.categories: this will saves thedata in both the tables, but new Mapping table is created, and that mapping table will not update.
    @ManyToMany(cascase = CascadeType.ALL, fetch = FetchType.EAGER)   // @ManyToMany(mappedBy = "products") Product.categories // EAGER: early loading, by default Lazy loading.
    private List<Product> products = new ArrayList<> ();
}

// categoryRepo.save(category1);
// categoryRepo.save(category2);
// Category category = categoryRepo.findById("cid1").get();
// category.getProducts().size();

Product product = productRepo.findById("pid1").get();
product.getCategories().size();