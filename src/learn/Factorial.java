package learn;

import java.util.Scanner;

public class Factorial
{

	public static void main(String[] args)
	{
		Scanner kb = new Scanner(System.in);
		while(kb.hasNext())
		{
			int k = kb.nextInt();
			System.out.println(fact(k));
		}
	}
	private static int fact(int num)
	{
		if(num == 0)
		{
			return 1;
		}
		else
			return fact(num-1)*num;
	}
}
