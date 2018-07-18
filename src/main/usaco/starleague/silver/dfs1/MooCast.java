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
public class Main {

	public static void main(String[] args) {
		Scanner consoleIn = new Scanner(System.in);
		
		amountCows = consoleIn.nextInt();
		cows = new BoardcasterCow[amountCows];
		for(int i = 0; i < amountCows; i++) {
			cows[i] = new BoardcasterCow(consoleIn.nextInt() - 1,
										 consoleIn.nextInt() - 1,
										 square(consoleIn.nextInt()));
			cows[i].id = i;
		}
		
		//Arrays.sort(cows);
		
		for(int i = 0; i < amountCows; i++) {
			cows[i].markAllReachable(cows);
		}
		
		int solution = 0;
		for(int i = 0; i < amountCows; i++) {
			solution = Math.max(solution, distSearch(i));
		}
		System.out.println(solution);
		
		consoleIn.close();
	}

	private static int amountCows;
	private static BoardcasterCow[] cows;
	
	
	/**
	 * All the distance right here are a^2 + b^2, so no sqrt()
	 * for both saving time and decimal (float) problems
	 */
	private static int distSearch(int d) {
		/*if(cows[d].isReached) {
			return;
		}*/
		
		cows[d].isReached = true;
		int result = 1;
		
		// Mark all cows in range
		for(int i = 0; i < cows[d].cowsReachable.length; i++) {
			// Except the next possible is already marked
			if(!cows[ cows[d].cowsReachable[i] ].isReached) {
				result += distSearch(cows[d].cowsReachable[i]);
			}
		}
		
		return result;
	}
	 
	private static int square(int n) {
		return n * n;
	}
	
	
}

class BoardcasterCow implements Comparable<BoardcasterCow> {
	
	int id;
	int x;
	int y;
	/** Square of actual distance */
	int transmitionRadius;
	int[] cowsReachable;
	boolean isReached;
	
	public BoardcasterCow(int x, int y, int transmitRadius) {
		this.x = x;
		this.y = y;
		this.transmitionRadius = transmitRadius;
	}
	
	
	void markAllReachable(BoardcasterCow[] cows) {
		if(this.cowsReachable != null)
			return;
		
		this.cowsReachable = new int[cows.length];
		int nextInsertion = 0;
		for(int i = 0; i < cows.length; i++) {
			if(this.isInRange(cows[i])) {
				this.cowsReachable[ nextInsertion ] = i;
				nextInsertion++;
			}
		}
		this.cowsReachable = Arrays.copyOfRange(this.cowsReachable, 0, nextInsertion);
	}
	
	boolean isInRange(BoardcasterCow p) {
		if(this.id == p.id) return false;
		
		return this.dist(p) <= this.transmitionRadius;
	}
	int dist(BoardcasterCow p) {
		return square(Math.abs(this.x - p.x)) + square(Math.abs(this.y - p.y));
	}
	
	
	private static int square(int x) {
		return x * x;
	}
	
	
	@Override
	public int compareTo(BoardcasterCow c) {
		return this.id - c.id;
	}
	
}

