//Deep Copy using File Handling :

import java.io.*;
import java.util.Properties;

// The Serializable marker interface allows an object to have its state saved to a file.

class Save implements Serializable	// java.io
{
	transient int password; // This field will not be serialized (i.e., it will not be stored in the file).

	int i, j;
}

public class _12_3_ObjectSerialization
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
