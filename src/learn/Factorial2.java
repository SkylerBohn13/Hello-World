package learn;

import java.util.Scanner;

public class Factorial2
{

	public static void main(String[] args)
	{
		Scanner kb = new Scanner(System.in);
		while(kb.hasNext())
		{
			int k = kb.nextInt(),tot=1;
			for(int i = 1; i <= k ; i ++ )
			{
				tot*=i;
			}
			System.out.println(tot);
		}
	}
}
