import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//ExceptionResources :
public class _3_ExceptionResources 
{
	public static void main(String[] args) throws IOException 
	{
	
//There are 2 ways to close the resource definately
		
// Type 1...  to close resources
	
		BufferedReader br = null;
		try {
			String str = "";	//null
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter String : ");
			str = br.readLine();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			br.close();			//after code end , we have to close the resources that we are using.
			System.out.println("Resource Closed...");
		}
		
//==================================================================================
// Type 2...  to close resources

//		try	(BufferedReader br = new BufferedReader(new InputStreamReader(System.in)))		//it automatically closed after try end.
//		{
//			String str = "";	//null
//			System.out.println("Enter String : ");
//			str = br.readLine();
//		}
//		System.out.println("End");
	}
}