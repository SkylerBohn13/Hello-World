package learn;

import java.util.Scanner;

public class fibo2
{

	public static void main(String[] args)
	{
		Scanner kb = new Scanner(System.in);
		System.out.println("Give me num between 1 and 10");
		while(kb.hasNext())
		{
			int k = kb.nextInt(),tot = 1,last = 0;
			for(int i = 1;i <= k;i++)
			{
				
				tot+=last;
				last = tot;
			}
			System.out.println(tot);
		}

	}

}
