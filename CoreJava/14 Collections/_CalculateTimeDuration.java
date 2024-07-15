import java.util.ArrayList;

class _CalculateTimeDuration
{
	public static void main(String[] args) 
	{
		long startTime =  System.currentTimeMillis();
		
		ArrayList<Integer> ls = new ArrayList<Integer>();
		for (int i = 0; i < 1000000; i++) 
		{
			ls.add(i);
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println(endTime - startTime + "msec");
	}
		
}