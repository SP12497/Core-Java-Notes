import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client1 
{

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		//create client socket
		
				Socket s = new Socket("192.9.200.231",887);
				
							
				Book  b = new Book(1001,"javan",800);
				//write to the socket
				
				OutputStream os = s.getOutputStream();
				ObjectOutputStream oo = new ObjectOutputStream(os);
				oo.writeObject(b);
				
				
				//read modify object from socket
				
				
				 InputStream is = s.getInputStream();
			        ObjectInputStream ois = new ObjectInputStream(is);
							
			        Book obj  = (Book) ois.readObject();
				
			        
			        //write to the client console
			        
			        System.out.println("client printing modyfied object");
				      obj.dispBook();
				      
				s.close();
				
				
				
				
				
				
		
	}
}
