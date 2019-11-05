package learn;

import java.util.Scanner;

public class Fibo
{

	public static void main(String[] args)
	{
		Scanner kb = new Scanner(System.in);
		System.out.println("Give me num between 1 and 10");
		while(kb.hasNext())
		{
			int k = kb.nextInt();
			System.out.println(fib(k));
		}
	}

	private static int fib(int num)
	{
		if(num == 0)
		{
			return 1;
		}
		else if(num == 1)
		{
			return 1;
		}
		return fib(num-1)+fib(num-2);
	}

}
