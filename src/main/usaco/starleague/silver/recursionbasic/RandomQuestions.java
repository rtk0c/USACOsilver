package usaco.starleague.silver.recursionbasic;

import java.util.Scanner;

public class RandomQuestions {

	public static void main(String[] args) {
		Scanner consoleIn = new Scanner(System.in);
		
		int in = consoleIn.nextInt();
        primidRec(in);
		
		consoleIn.close();
	}

    private static void primidRec(int n) {
        primidRec(2, true, n);
    }
    private static void primidRec(int n, boolean isIncrease, int originalN) {
        System.out.print(n + " ");
        // last one, 2
        if(!isIncrease && n == 2) {
            return;
        }
        if(n == originalN) {
            isIncrease = false;
        }
        if(isIncrease) {
            primidRec(n + 1, isIncrease, originalN);
        } else {
            primidRec(n - 1, isIncrease, originalN);
        }
    }

}
