//Yes, you can define a class inside an interface. 
//In general, if the methods of the interface use this class 
// and if we are not using it anywhere else, we will declare a class within an interface.

//Example 1

interface Library {
   void issueBook(Book b);

   void retrieveBook(Book b);

   public class Book { // Liabrary.Book
      int bookId = 1;
      String bookName = "myBook";
      int issueDate;
      int returnDate;
   }
}

class Sample implements Library {

   public void issueBook(Book b) {
      System.out.println("Book Issued: " + b.bookId);
   }

   public void retrieveBook(Book b) {
      System.out.println("Book Retrieved: " + b.bookName);
   }

   public static void main(String args[]) {
      Sample obj = new Sample();
      obj.issueBook(new Library.Book());
      obj.retrieveBook(new Library.Book());
   }
}

// Output :
// Book Issued
// Book retrieveBook

// --------------------------------------
// If we need to provide a default implementation of the interface, we will
// define a class inside an interface as:

// Example 2
interface Library2 {

   void issueBook(Book b);

   void sampleBook();

   void retrieveBook(Book b);

   public class Book implements Library2 {
      int bookId;
      String bookName;
      int issueDate;
      int returnDate;

      public void sampleBook() {
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

class _2_2_ClassInsideInterface {
   public static void main(String args[]) {

      Library2 l = new Library2.Book();
      l.sampleBook();
      l.issueBook(new Library2.Book());

   }
}
