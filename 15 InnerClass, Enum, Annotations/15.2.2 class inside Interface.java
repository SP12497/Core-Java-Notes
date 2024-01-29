//Yes, you can define a class inside an interface. 
//In general, if the methods of the interface use this class and if we are not using it anywhere else we will declare a class within an interface.

//Example 1

interface Library {
   void issueBook(Book b);
   void retrieveBook(Book b);

   public class Book {			//Liabrary.Book
      int bookId;
      String bookName;
      int issueDate;
      int returnDate;
   }
}

public class Sample implements Library {

   public void issueBook(Book b) {
      System.out.println("Book Issued");
   }

   public void retrieveBook(Book b) {
      System.out.println("Book Retrieved");
   }

   public static void main(String args[]) {
      Sample obj = new Sample();
      obj.issueBook(new Library.Book());
      obj.retrieveBook(new Library.Book());
   }
}

//Output :
//Book Issued
//Book retrieveBook

//--------------------------------------
//If we need to provide a default implementation of the interface, we will define a class inside an interface as:


// Example 2

interface Library {
   
   void issueBook(Book b);
   void sampleBook();
   void retrieveBook(Book b);
   
   public class Book implements Library {
      int bookId;
      String bookName;
      int issueDate;
      int returnDate;
	  
	  void sampleBook()
	  {
		System.out.println("book Sample");
	  }
	  
      public void issueBook(Book b) {
         System.out.println("book issued");
      }

      public void retrieveBook(Book b) {
         System.out.println("book retrieved");
      }
   }
}
public class Sample {
   public static void main(String args[]) {
	  
	  Library l = new Library.Book();
      l.sampleBook();
	  l.issueBook(new Library.Book());
	  
	  
   }
}