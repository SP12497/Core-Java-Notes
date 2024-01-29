package Numbers;
//Static Block:
// first all static block will execute sequencialyy then main static block will execute.


import static java.lang.System.out;

public class Static_Block
{
	static int i ;	//by default 0;
	static
	{
		i++;
		out.println("Hello : "+ i);
	}

	public static void main(String [] args)
	{		
		out.println(++i);
		out.println("java : "+ ++i);
	}

	static
	{
		out.println("Not Bye : "+ ++i);
	}
	
	//This is instance block calls when obj created before constructor call
	{
		
	}

}
