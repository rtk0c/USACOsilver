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
public class MooCast {

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
		
		Arrays.sort(cows);
		
		int solution = 0;
		result = 0;
		for(int i = 0; i < amountCows; i++) {
			distSearch(i);
			solution = Math.max(solution, result);
			result = 0;
		}
		System.out.println(solution);
		
		consoleIn.close();
	}

	private static int result;
	private static int amountCows;
	private static BoardcasterCow[] cows;
	
	
	/**
	 * All the distance right here are a^2 + b^2, so no sqrt()
	 * for both saving time and decimal (float) problems
	 */
	private static void distSearch(int d) {
		/*if(cows[d].isReached) {
			return;
		}*/
		
		cows[d].isReached = true;
		result++;
		
		// Mark all cows in range
		for(int i = 0; i < amountCows; i++) {
			// Except the next possible is already marked
			if(cows[d].isInRange(cows[i]) && !cows[i].isReached) {
				distSearch(i);
			}
		}
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
	boolean isReached;
	
	public BoardcasterCow(int x, int y, int transmitRadius) {
		this.x = x;
		this.y = y;
		this.transmitionRadius = transmitRadius;
	}
	
	
	public boolean isInRange(BoardcasterCow p) {
		return this.dist(p) <= this.transmitionRadius;
	}
	public int dist(BoardcasterCow p) {
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

