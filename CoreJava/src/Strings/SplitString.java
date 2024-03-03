package Strings;

// Divide the string into multiple string is called as Split string.
public class SplitString 
{
	public static void main(String[] args) {
		
		String sp = ("Sagar , Suraj , Bharati , Yogita");
		
		System.out.println(sp);		
		
		String names[] = sp.split(",");				//String splited by comma.
		
		System.out.println("name[2]  : "+names[2]);
		
		System.out.println("---------------------------");

		for(String val : names)				//Advance for loop.
			System.out.println(val);
		
		
	}
}
