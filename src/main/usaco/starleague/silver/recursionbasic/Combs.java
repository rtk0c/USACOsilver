import java.util.Scanner;

public class Combs {

	public static void main(String[] args) {
		Scanner consoleIn = new Scanner(System.in);
		
		lN = consoleIn.nextInt();
		mN = consoleIn.nextInt();
		out = new int[lN];
		combs(0);
		
		consoleIn.close();
	}

	
	static final int ALL = 0;
	static final int ONLY_EVENS = 1;
	static final int ONLY_ODDS = 2;
	static int mode = 1;
	
    static int lN;
    static int mN;
    static int[] out;

    static void combs(int d) {
        if(d >= lN) {
            for(int i = 0; i < out.length; i++) {
                System.out.print(out[i] + " ");
            }
            System.out.println("");
            return;
        }
        
        int i = mode == ALL ? 1 : mode == ONLY_EVENS ? 2 : mode == ONLY_ODDS ? 1 : 0;
        
        for(; i <= mN; i += (mode == ALL ? 1 : 2)) {
            out[d] = i;
            combs(d + 1);
        }
    }
	
}
