package learn;

import java.io.File;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
class Node implements Comparable<Node>
{
	String path, name;
	long dist;
	boolean relax;
	HashMap<String, Integer> edges;
	Node(String temp)
	{
		path = temp;
		name = temp;
		dist = Long.MAX_VALUE;
		relax = false;
		edges = new HashMap<>();
		
	}
	@Override
	public int compareTo(Node o)
	{
		if(dist- o.dist>=0)
			return 1;
		else 
			return -1;
	}
}

public class dikstras
{
	
	public static void main(String[] args) throws Throwable
	{
		Scanner kb = new Scanner(new File("short.dat"));
		HashMap<String, Node> map = new HashMap<>();
		PriorityQueue<Node> que = new PriorityQueue<>();
		String b = kb.next(),e = kb.next();
		while(kb.hasNext())
		{
			String one = kb.next(),two = kb.next();
			int temp = kb.nextInt();
			if(!map.containsKey(one))
				map.put(one, new Node(one));
			
			if(!map.containsKey(two))
				map.put(two, new Node(two));
			
			map.get(one).edges.put(two, temp);
			map.get(two).edges.put(one, temp);
		}
		map.get(b).dist = 0;
		
		for(String tem:map.keySet())
			que.add(map.get(tem));
		
		while(que.size()>0)
		{
			Node temp = que.remove();
			temp.relax = true;
			if(temp.name.equals(e))
				break;
			for(String x:temp.edges.keySet())
			{
				if(map.get(x).relax)
					continue;
				if(map.get(x).dist > temp.dist + temp.edges.get(x))
				{
					que.remove(map.get(x));
					map.get(x).dist = temp.dist + temp.edges.get(x);
					map.get(x).path = temp.path + map.get(x).name;
				  	que.add(map.get(x));
				}
			}
		}
		System.out.println(map.get(e).dist + " " + map.get(e).path);
	}
}