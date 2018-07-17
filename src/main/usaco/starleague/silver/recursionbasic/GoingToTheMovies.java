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
public GoingToTheMovies {

	public static void main(String[] args) {
		Scanner consoleIn = new Scanner(System.in);
		
		maxWeight = consoleIn.nextInt();
		amountCows = consoleIn.nextInt();
		cowWeights = new int[amountCows];
		//existence = new boolean[amountCows];
		for(int i = 0; i < amountCows; i++) {
			cowWeights[i] = consoleIn.nextInt();
		}
		
		weightCombs();
		
		System.out.println(result);
		
		consoleIn.close();
	}
	
	private static int result;
	
	private static int amountCows;
	private static int maxWeight;
	private static int[] cowWeights;
	//private static boolean[] existence;
	
	private static void weightCombs() {
		weightCombs(0, 0);
	}
	private static void weightCombs(int d, int currentWeight) {
		if(d >= amountCows) {
			/*if(currentWeight > maxWeight)
				return;*/
			
			// This is layer N + 1, and it's already filter out at outer layer
			result = Math.max(result, currentWeight);
			return;
		}
		
		//existence[d] = false;
		weightCombs(d + 1, currentWeight);
		
		//existence[d] = true;
		currentWeight += cowWeights[d];
		if(currentWeight > maxWeight)
			return;
		weightCombs(d + 1, currentWeight);
		
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
