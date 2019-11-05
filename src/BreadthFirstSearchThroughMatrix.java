import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BreadthFirstSearchThroughMatrix 
{
	public static boolean found;
	
	public static void main(String[] args) throws FileNotFoundException 
	{
		Scanner kb = new Scanner(new File("bfsthroughmatrix.dat"));
		int cases = kb.nextInt();//number of data sets to run
		while(cases-->0)
		{
			found = false;//tells if we found a path connecting start and end
			int row = kb.nextInt();//number of rows in the matrix
			int col = kb.nextInt();//number of columns in the matrix
			kb.nextLine();//handles end-of-line marker
			char[][] c = new char[row][];
			for(int x = 0; x < row; x++)//fills matrix
				c[x] = kb.nextLine().trim().toCharArray();
			Point start = findChar(c,'S');//returns the point in the matrix where we start
			Point end = findChar(c,'E');//returns the point in the matrix where we try to get to
			//bfsMethod01(c,start);//helps you see what's happening
			bfsMethod02(c,start);//could be faster to type
			System.out.println(found?(c[end.x][end.y]-48):"Impossible");//prints the path length or impossible
			/*for(char[] arr : c)//prints the matrix
				System.out.println(arr);
			System.out.println();*/
		}
	}
	
	public static void bfsMethod01(char[][] c, Point start)
	{
		ArrayList<Point> a = new ArrayList<Point>();//a list of the points we have to check
		a.add(start);
		c[start.x][start.y] = '0';//the path length starts at 0
		while(!a.isEmpty() && !found)//runs through our points
		{
			Point p = a.remove(0);//our current point to check
			int x = p.x;//I do this because typing x is easier than typing p.x several times
			int y = p.y;//same for y to p.y
			if((x+1<c.length) && c[x+1][y] == '.')//if the item below is in range and if it's a '.'
			{
				a.add(new Point(x+1,y));//add it to check from
				c[x+1][y] = (char)(c[x][y]+1);//add it to the path spread. It is the current path length + 1
			}
			if((x+1<c.length) && c[x+1][y] == 'E')//if the item below is in range and if it's our goal (an 'E')
			{
				found = true;//say we found it
				c[x+1][y] = (char)(c[x][y]+1);//add to the path length
			}
			if((x-1>=0) && c[x-1][y] == '.')//same, but for the item above it
			{
				a.add(new Point(x-1,y));
				c[x-1][y] = (char)(c[x][y]+1);
			}
			if((x-1>=0) && c[x-1][y] == 'E')
			{
				found = true;
				c[x-1][y] = (char)(c[x][y]+1);
			}
			if((y+1<c[x].length) && c[x][y+1] == '.')//same, but for the item to the right
			{
				a.add(new Point(x,y+1));
				c[x][y+1] = (char)(c[x][y]+1);
			}
			if((y+1<c[x].length) && c[x][y+1] == 'E')
			{
				found = true;
				c[x][y+1] = (char)(c[x][y]+1);
			}
			if((y-1>=0) && c[x][y-1] == '.')//same, but for the item to the left
			{
				a.add(new Point(x,y-1));
				c[x][y-1] = (char)(c[x][y]+1);
			}
			if((y-1>=0) && c[x][y-1] == 'E')
			{
				found = true;
				c[x][y-1] = (char)(c[x][y]+1);
			}
			//if diagonals are allowed, combine all of the above ones appropriately, I.E. c[x+1][y+1],c[x+1][y-1],c[x-1][y+1],c[x-1][y-1]
		}
	}
	
	public static void bfsMethod02(char[][] c, Point start)
	{
		int[] moves = {1,-1};//this makes it easier to type. If diagonals are allowed, make it: int[] moves = {-1,0,1};
		ArrayList<Point> a = new ArrayList<Point>();//a list of our points to check
		a.add(start);
		c[start.x][start.y] = '0';//our path length starts at 0
		while(!a.isEmpty() && !found)//while we have points to check and haven't reached our goal
		{
			Point p = a.remove(0);//our current point
			int x = p.x;//easier to type x instead of p.x
			int y = p.y;//easier to type y instead of p.y
			for(int i = 0; i < moves.length; i++)//applies each move to the x and y coordinates one at a time
			{
				if(x+moves[i] >= 0 && x+moves[i]<c.length)//is the new x in range
				{
					if(c[x+moves[i]][y] == '.')//if the new x is a step
					{
						a.add(new Point(x+moves[i],y));//add it to our list to be checked
						c[x+moves[i]][y] = (char)(c[x][y]+1);//increase our path length
					}
					if(c[x+moves[i]][y] == 'E')//if the new x is our goal, say we found it
					{
						found = true;
						c[x+moves[i]][y] = (char)(c[x][y]+1);//increase our path length
					}
				}
				//you can put both if statements in the same loop or in different loops if you'd prefer
				if(y+moves[i] >= 0 && y+moves[i]<c[x].length)//is the new y in range
				{
					if(c[x][y+moves[i]] == '.')//if the new y is a step
					{
						a.add(new Point(x,y+moves[i]));//add it to our list to be checked
						c[x][y+moves[i]] = (char)(c[x][y]+1);//increase the path length
					}
					if(c[x][y+moves[i]] == 'E')//if the new y is our goal, save that we found it
					{
						found = true;
						c[x][y+moves[i]] = (char)(c[x][y]+1);//increase the path length
					}
				}
			}
			//if we do diagonals, do this code segment instead of the above loop
			/*for(int i = 0; i < moves.length; i++)
			{
				for(int j = 0; j < moves.length; j++)
				{
					try{//this is so we don't have to check if the next point is in range
						if(c[x+moves[i]][y+moves[j]] == '.')
						{
							a.add(new Point(x+moves[i],y+moves[j]));
							c[x+moves[i]][y+moves[j]] = (char)(c[x][y]+1);
						}
						else if(c[x+moves[i]][y+moves[j]] == 'E')
						{
							found = true;
							c[x+moves[i]][y+moves[j]] = (char)(c[x][y]+1);
						}
					}catch(Exception e){
						continue;
					}
				}
			}*/
		}
	}
	
	public static Point findChar(char[][] c, char goal)
	{
		Point p = new Point(-1,-1);
		for(int x = 0; x < c.length; x++)
		{
			for(int y = 0; y < c[x].length; y++)
			{
				if(c[x][y] == goal)
				{
					p = new Point(x,y);
					break;
				}
			}
		}
		return p;
	}

}