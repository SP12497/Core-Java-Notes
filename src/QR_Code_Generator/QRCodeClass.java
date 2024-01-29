
//QR code means Quick Response code.

// To generate qr code , we have to download QR code liabrary and link into the code.
/*
Libraries are :	(Store in : G:\KP Bangalore\Core JAVA\Practicals\OOPs Concepts\src\QR_Code_Generator\QR Code libraries
		- qrgen 1.0
		- zxing-core-1.7
		- zxinng-j2se-1.7
*/


package QR_Code_Generator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

public class QRCodeClass 
{
	public static void main(String[] args) throws Exception
	{
		String details = "WelCome from Sagar Patil."
				+ "This is my QR code";					//Display this String after QR Code scan
	ByteArrayOutputStream out = QRCode.from(details).to(ImageType.JPG).stream();
	
	File f = new File("G:\\KP Bangalore\\Core JAVA\\Practicals\\OOPs Concepts\\src\\QR_Code_Generator\\QR Code libraries\\SagarQR.JPG");
	FileOutputStream fos = new FileOutputStream(f);																		// File name.
	
	fos.write(out.toByteArray());			// add throws exception to control exceptions
	fos.flush();			//This will flush buffer, otherwise we have to wait for the buffer to full.
			
		
	}

}
