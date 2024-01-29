//Deep Copy using File Handling :

import java.io.*;
import java.util.Properties;

// required Serializable Marker Interface to gives permission to allow saving the State of an Object into a File.

class Save implements Serializable
{
	transient int password;	//do not store in file.

	int i, j;
}

public class CMain
{
	public static void main(String[] args) throws IOException, ClassNotFoundException 
	{

//Write :
		Save obj1 = new Save();
		obj1.i= 11;
		obj1.j = 20;
		obj1.password = 12345;
		
	  //Save the state of obj in the file :
		File fl = new File("Obj1State.txt");
		FileOutputStream fos = new FileOutputStream(fl);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj1);


//Read :			
		FileInputStream fis = new FileInputStream(fl);
		ObjectInputStream ois = new ObjectInputStream(fis);


	  // Deep copy by fetching data from file.
		Save obj2 = (Save) ois.readObject();
		
		System.out.println("Obj2.i  = " + obj2.toString());
	}
}
