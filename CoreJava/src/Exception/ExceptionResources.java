package Exception;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExceptionResources 
{
	public static void main(String[] args) throws IOException 
	{
		
// Type 1...  to close resources
	
//		BufferedReader br = null;
//		try
//		{
//			String str = "";	//null
//			br = new BufferedReader(new InputStreamReader(System.in));
//			str = br.readLine();
//		}
//		catch (Exception e)
//		{
//			System.out.println(e);
//		}
//		finally 
//		{
//			br.close();			//after code end , we have to the resources that we are using.
//		}
		
//==================================================================================
// Type 2...  to close resources

		try	(BufferedReader br = new BufferedReader(new InputStreamReader(System.in)))
		{
			String str = "";	//null
			str = br.readLine();
		}
	
	}

}