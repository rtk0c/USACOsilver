import java.util.Arrays;
import java.util.Scanner;

public class BreedAssignment {

	public static void main(String[] args) {
		Scanner consoleIn = new Scanner(System.in);
		
		amountCows = consoleIn.nextInt();
		amountConditions = consoleIn.nextInt();
		combBuffer = new int[amountCows];
		conditions = new char[amountCows][amountCows];
		
		for(int i = 0; i < amountConditions; i++) {
		    char cond = consoleIn.next().charAt(0);
		    int x = consoleIn.nextInt() - 1;
		    int y = consoleIn.nextInt() - 1;
		    
		    conditions[x][y] = cond;
		    conditions[y][x] = cond;
		}
		
		rec(0);
		
		System.out.println(possibleCombs);
		
		consoleIn.close();
	}
	
	private static int possibleCombs = 0;
	
	private static int amountCows;
	private static int amountConditions;
    private static int[] combBuffer;
	private static char[][] conditions;
	
	
	private static void rec(int d) {
	    if(d >= amountCows) {
	        possibleCombs++;
//	        combBuffer = new int[amountCows];
//	        Arrays.fill(combBuffer, 0);
	        return;
	    }
	    
	    for(int i = 0; i < 3; i++) {
	        boolean doesMatch = true;
	        
	        for(int j = 0; j < d; j++) {
	            if(conditions[j][d] == 'S' && i != combBuffer[j]) {
	                doesMatch = false;
	                break;
	            }
	            if(conditions[j][d] == 'D' && i == combBuffer[j]) {
	                doesMatch = false;
	                break;
	            }
	        }
	        
	        if(!doesMatch)
	            continue;
	        
	        combBuffer[d] = i;
	        rec(d + 1);
	    }
	}
	
}
