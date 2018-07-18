import java.util.Arrays;
import java.util.Scanner;

/**
 * All index start from 0, ... - 1 when getting input
 * use crates
 * 
 * 
 * @author reobj_
 *
 */
public class TheLeisurelyStroll {

	public static void main(String[] args) {
		Scanner consoleIn = new Scanner(System.in);
		
		amountNodes = consoleIn.nextInt() - 1;
		nodes = new Node[amountNodes];
		for(int i = 0; i < amountNodes; i++) {
			nodes[i] = new Node(consoleIn.nextInt() - 1,
								consoleIn.nextInt() - 1,
								consoleIn.nextInt() - 1);
		}
		
		Arrays.sort(nodes);
		//System.out.println(nodes[8].hasNextNodes());
		
		pathFind();
		System.out.println(result);
		
		consoleIn.close();
	}
	
	
	private static int result;
	private static int amountNodes;
	private static Node[] nodes;
	
	private static void pathFind() {
		pathFind(0, 0);
	}
	private static void pathFind(int d, int dist) {
		// If node[d] doesn't have child nodes, it must have two pastures
		if(nodes[d].hasPasture()) { 
			// Pasture have a road lead to it, so distance + 1
			result = Math.max(result, dist + 1);
		}
		
		if(nodes[d].hasNextNode1()) pathFind(nodes[d].leadsTo1, dist + 1);
		if(nodes[d].hasNextNode2()) pathFind(nodes[d].leadsTo2, dist + 1);
	}
	
	
}

class Node implements Comparable<Node> {
	
	int id;
	int leadsTo1;
	int leadsTo2;
	
	public Node(int id, int cNode1, int cNode2) {
		this.id = id;
		this.leadsTo1 = cNode1;
		this.leadsTo2 = cNode2;
	}
	
	boolean hasPasture() {
		if(this.leadsTo1 == -1 || this.leadsTo2 == -1) 
			return true;
		
		return false;
	}
	
	boolean hasNextNode1() {
		return (this.leadsTo1 != -1);
	}
	boolean hasNextNode2() {
		return (this.leadsTo2 != -1);
	}
	boolean hasNextNodes() {
		return this.hasNextNode1() || this.hasNextNode2();
	}
	
	
	@Override
	public int compareTo(Node n) {
		return this.id - n.id;
	}
	
}

