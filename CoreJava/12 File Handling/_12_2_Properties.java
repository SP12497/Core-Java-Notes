import java.io.*;
import java.util.Properties;

public class _12_2_Properties
{
	public static void main(String[] args) throws IOException 
	{
		//Store key-value data into the file using Properties :
		Properties p = new Properties();

			FileOutputStream os = new FileOutputStream("MySP.Properties"); //File Name
			p.setProperty("url", "localhost:3306/mydatabase");
			p.setProperty("uname", "Sagar");
			p.setProperty("pass", "0000");
			p.store(os, "This is just a comment"); // save file
			//p.store(os);



		//Fetch data using the Properties :
			Properties p2 = new Properties();
			FileInputStream is = new FileInputStream("MySP.Properties");
			p2.load(is);
			
			System.out.println(p2.getProperty("url"));
			System.out.println(p2.getProperty("uname"));
			System.out.println(p2.getProperty("pass"));
			
			System.out.println("========List All========");
			p2.list(System.out);	
			
	}
}
