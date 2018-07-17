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
public class ConnectCows {

	public static void main(String[] args) {
		Scanner consoleIn = new Scanner(System.in);
		
		amountCows = consoleIn.nextInt();
		cows = new Pos[amountCows + 2];
		
		// First and last must be origin
		cows[0] = new Pos(0, 0);
		cows[ amountCows + 1 ] = new Pos(0, 0); // index: last in the array (coords.length - 1)
		for(int i = 1; i <= amountCows; i++) {
			cows[i] = new Pos(consoleIn.nextInt(), consoleIn.nextInt());
		}
		
		coords = new int[amountCows + 2];
		coords[0] = 0;
		coords[ amountCows + 1 ] = cows.length - 1;
		
		used = new boolean[amountCows];
		
		
		
		countRoutes();
		
		System.out.println(result);
		
		consoleIn.close();
	}
	
	private static int result;
	
	private static int amountCows;
	private static boolean[] used;
	private static Pos[] cows;
	// Represents cows[i]'s coordinate
	private static int[] coords;
	
	private static void countRoutes() {
		countRoutes(1);
	}
	private static void countRoutes(int d) {
		if(d > amountCows) {
			if(isCoordsValid(coords)) {
				result++;
			}
			return;
		}
		
		for(int i = 1; i <= amountCows; i++) {
			// Put (i - 1) because for() start with 1 for coords[]  (0,0) at first and last
			// but real array index start at 0
			if(used[i - 1] == false) {
				coords[d] = i;
				
				used[i - 1] = true;
				countRoutes(d + 1);
				used[i - 1] = false;
			}
		}
	}

	private static boolean isCoordsValid(int[] coords) {
		// Does not include last one, because checking (i + 1)
		for(int i = 0; i < coords.length - 1; i++) { 
			if(strictVDirection(cows[coords[i]], cows[coords[i + 1]]) == EnumDirection.NON_PARALLEL) {
				return false;
			}
		}
		
		for(int i = 1; i < coords.length - 1; i++) {
			// (i-1) -> (i) has the same direction with (i) -> (i+1)
			if(strictVDirection(cows[coords[i - 1]], cows[coords[i]]) == 
					strictVDirection(cows[coords[i]], cows[coords[i + 1]])) {
				return false;
			}
		}
		
		return true;
	}
	
	private static EnumDirection strictVDirection(Pos origin, Pos vectorPos) {
		if(origin.x == vectorPos.x && origin.y < vectorPos.y) return EnumDirection.UP;
		if(origin.x == vectorPos.x && origin.y > vectorPos.y) return EnumDirection.DOWN;
		if(origin.y == vectorPos.y && origin.x < vectorPos.x) return EnumDirection.RIGHT;
		if(origin.y == vectorPos.y && origin.x > vectorPos.x) return EnumDirection.LEFT;
		
		return EnumDirection.NON_PARALLEL;
	}



	static enum EnumDirection {
		NON_PARALLEL(),
		UP(),
		DOWN(),
		LEFT(),
		RIGHT();
	}
	
	
}

class Pos {
	
	int x;
	int y;
	
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}



class Utils {
	
	private Utils() {
	}
	
	
	
}


interface IConsumer<T> {
	
	void accept(T in);
	
}
interface IFunction<T, O> {
	
	O calculate(T in);
	
}
interface ICondition<T> {
	
	boolean cond(T in);
}
