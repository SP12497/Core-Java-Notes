import java.io.Serializable;

public class Book implements Serializable
{

	int bid;
	String bname;
	float price;
	
	
	public Book(int bid, String bname, float price)
	{
		
		this.bid = bid;
		this.bname = bname;
		this.price = price;
	}


//	@Override
//	public String toString() {
//		return "Book [bid=" + bid + ", bname=" + bname + ", price=" + price + "]";
//	}


	void dispBook()
	{
		System.out.println(bid+bname+price);
	}
	
	
	
	
	
}
