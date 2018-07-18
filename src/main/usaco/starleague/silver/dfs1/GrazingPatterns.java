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
public class GrazingPatterns {
	
	static enum EnumCow {
		BESSIE(),
		MILDRED();
	}

	public static void main(String[] args) {
		Scanner consoleIn = new Scanner(System.in);
		
		amountBroken = consoleIn.nextInt();
		minAcheive = 25 - amountBroken;
		grid = new boolean[5][5];
		for(int i = 0; i < amountBroken; i++) {
			grid[ consoleIn.nextInt() - 1 ][ consoleIn.nextInt() - 1 ] = true;
		}
		
		pathFind();
		System.out.println(solutions);
		
		consoleIn.close();
	}
	
	
	private static int solutions;
	private static int amountBroken;
	private static int minAcheive;
	private static boolean[][] grid;
	
	
	private static void pathFind() {
		pathFind(1, 0, 0);
	}
	private static void pathFind(int d, int x, int y) {
		if(x == 4 && y == 4 && d >= minAcheive) {
			solutions++;
		}
		
		grid[y][x] = true;
		if(x < 4 && !grid[y][x + 1]) pathFind(d + 1, x + 1, y);
		if(x > 0 && !grid[y][x - 1]) pathFind(d + 1, x - 1, y);
		if(y < 4 && !grid[y + 1][x]) pathFind(d + 1, x, y + 1);
		if(y > 0 && !grid[y - 1][x]) pathFind(d + 1, x, y - 1);
		grid[y][x] = false;
	}
	
	
	
	
	private static int square(int x) {
		return x * x;
	}
	
	
}



