import java.util.Scanner;

/**
 * All index start from 0, ... - 1 when getting input
 * use crates
 * 
 * 
 * @author reobj_
 *
 */
public class SecretCode {

	public static void main(String[] args) {
		Scanner consoleIn = new Scanner(System.in);
		
		String codeIn = consoleIn.next(); 
		
		System.out.println(decryptCount(codeIn) - 1);
		
		consoleIn.close();
	}
	
	
	
	private static int decryptCount(String s) {
		int result = 1;
		int len = s.length();
		
	    if(len % 2 == 0 || len == 1) {
	        return 1;
	    }
	    
	    if(s.substring(0, len/2).equals(s.substring(len/2, len-1))) {
	    	result += decryptCount(s.substring(len/2));
	    }
	    if(s.substring(0, len/2).equals(s.substring(len/2 + 1))) {
	    	result += decryptCount(s.substring(0, len/2 + 1));
	    	result += decryptCount(s.substring(len/2));
	    }
	    if(s.substring(1, len/2 + 1).equals(s.substring(len/2 + 1))) {
	    	result += decryptCount(s.substring(0, len/2 + 1));
	    }
	    
	    return result;
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
