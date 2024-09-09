import java.util.Arrays;

public class _53ArrayObj {
    public static void main(String[] args) {
    	
    	Book [] books = new Book[4];
        books[0] = new Book(1, "Java", 300);
        books[1] = new Book(1, "C++", 100);
        books[2] = new Book(1, "JavaScript", 200);
        books[3] = new Book(1, "Python", 200);
        
        System.out.println(Arrays.toString(books));	// [Book [id=1, name=Java], Book [id=1, name=C++], Book [id=1, name=JavaScript], null]
        
        Arrays.sort(books, (o1, o2) -> o1.price - o2.price ); // ASC order	// Comparator : compare()
        System.out.println(Arrays.toString(books));
    }
}


class Book {
    int id;
    String name;
    int price;
    Book(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + "]";
	}

  
}