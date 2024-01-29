import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server1 
{
public static void main(String[] args) throws IOException, ClassNotFoundException
	
	{
		
		ServerSocket ss = new ServerSocket(887);
		
		Socket s = ss.accept();
		System.out.println("socket created for object sending");
		
		//read object from socket
		
        InputStream is = s.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);
		Book   obj       = (Book) ois.readObject();
        
		
                
        obj.price -= obj.price*0.1f;
        
		
        //writing modified object to the client socket
        
        OutputStream os = s.getOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(os);
		oo.writeObject(obj);
        
        
        
		
		ss.close();
		
		
		
		
		
	}

}
