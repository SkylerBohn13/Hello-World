package learn;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
public class bfs
{
	public static boolean found;
	public static void main(String[] args) throws Throwable
	{
		Scanner kb = new Scanner(new File("bfsthroughmatrix.dat"));
		int cases = kb.nextInt();
		while(cases-->0)
		{
			int row = kb.nextInt();
			int col = kb.nextInt();
			found = false;
			char[][] k = new char[row][col];
			kb.nextLine();
			for(int i = 0;i<row;i++)
			{
				k[i] =kb.nextLine().toCharArray(); 
			}
			Point start = find(k,'S');
			Point end = find(k,'E');
			bf(k,start);
			System.out.println(found?(k[end.x][end.y]-48):"Impossible");
			
		}
	}
	private static void bf(char[][] k, Point start)
	{
		int[] moves = {1,-1};
		ArrayList<Point> path = new ArrayList<>();
		path.add(start);
		k[start.x][start.y] = '0';
		while(!path.isEmpty()&&!found)
		{
			Point p = path.remove(0);
			int x = p.x;
			int y = p.y;
			for(int i = 0; i<moves.length;i++)
			{
				if(x+moves[i]>=0&&x+moves[i]<k.length)
				{
					if(k[x+moves[i]][y] == '.')
					{
						path.add(new Point(x+moves[i],y));
						k[x+moves[i]][y] = (char)(k[x][y]+1);
					}
					if(k[x+moves[i]][y] == 'E')
					{
						found = true;
						k[x+moves[i]][y] = (char)(k[x][y]+1);
					}
				}
				
				if(y+moves[i]>=0&&y+moves[i]<k[x].length)
				{
					if(k[x][y+moves[i]] == '.')
					{
						path.add(new Point(x,y+moves[i]));
						k[x][y+moves[i]] = (char)(k[x][y]+1);
					}
					if(k[x][y+moves[i]] == 'E')
					{
						found = true;
						k[x][y+moves[i]] = (char)(k[x][y]+1);
					}
				}
			}
		}
	}
	private static Point find(char[][] k, char c)
	{
		for(int i = 0;i<k.length;i++)
		{
			for(int s = 0;s<k[0].length;s++)
			{
				if(k[i][s]==c)
				{
					return new Point(i,s);
				}
			}
		}
		return new Point(0,0);
	}

}
