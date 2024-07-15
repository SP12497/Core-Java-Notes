//first create Bundle.properties file and  add
// wish = Happy Birthday

import java.util.Locale;
import java.util.ResourceBundle;

public class Localization_and_Internationalization_using_SwingJFrame 
{	
	public static void main(String[] args)
	{
		
		String lang = "en";
		String country = "US";
		
		Locale ll = new Locale(lang, country);
		
		ResourceBundle r = ResourceBundle.getBundle("MyPackage/Bundle", ll);
		
		String str =  r.getString("wish");
		
		System.out.println(str);
	}
	
	
}
