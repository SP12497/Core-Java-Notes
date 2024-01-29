import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// String ReadWrite on file :
public class CMain
{
	public static void main(String[] args) throws IOException
	{

		//FileOutputStream fos = new FileOutputStream("SP.txt");
		File fl = new File("SP.sp");
		FileOutputStream fos = new FileOutputStream(fl);
		DataOutputStream dos = new DataOutputStream(fos);
		dos.writeUTF("Sagar Patil...\nSuraj Patil...");
		
		FileInputStream fis = new FileInputStream("SP.sp");
		DataInputStream dis = new DataInputStream(fis);
		String str = dis.readUTF();
		System.out.println(str);
	}
}
