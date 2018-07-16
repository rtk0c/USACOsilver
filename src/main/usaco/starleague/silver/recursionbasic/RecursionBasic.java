package usaco.starleague.silver.recursionbasic;

import java.util.Scanner;

public class RecursionBasic {

	public static void main(String[] args) {
		Scanner consoleIn = new Scanner(System.in);
		
		System.out.print("input (int): ");
		int fibIn = consoleIn.nextInt();
		System.out.println("fib(): " + fib(fibIn));
		
		System.out.print("input (int): ");
		int sigmaIn = consoleIn.nextInt();
		System.out.println("sigma(): " + sigma(sigmaIn));
		
		System.out.print("input (int): ");
		int printStarsBIn = consoleIn.nextInt();
		System.out.println("printStarsBottom(): ");
		printStarsBottom(printStarsBIn);
		
		System.out.print("input (int): ");
		int printStarsTIn = consoleIn.nextInt();
		System.out.println("printStarsTop(): ");
		printStarsTop(printStarsTIn);
		
		System.out.print("input (String): ");
		String reverseIn = consoleIn.next();
		System.out.println("reverseStr(): ");
		reverseStr(reverseIn);
		
		consoleIn.close();
	}
	
	private static int fib(int n) {
		if(n == 1 || n == 2) return 1;
		return fib(n - 1) + fib(n - 2);
	}
	
	private static int sigma(int n) {
		if(n == 1) return n;
		return n + sigma(n - 1);
	}
	
	private static void printStarsBottom(int n) {
		printStarsBottom(n, 1);
	}
	private static void printStarsBottom(int n, int level) {
		// last level
		if(n == 1) {
			System.out.println("*");
			return;
		}
		// last of the line
		if(level == n) {
			System.out.println("*");
			printStarsBottom(n - 1, 1);
			return;
		}
		System.out.print("*");
		printStarsBottom(n, level + 1);
	}
	
	private static void printStarsTop(int n) {
		printStarsTop(n, 1, 1);
	}
	private static void printStarsTop(int n, int level, int levelMax) {
		System.out.print("*");
		
		// this line is done
		if(level == levelMax) {
			System.out.println(""); // a new line
			if(n != 1) printStarsTop(n - 1, 1, levelMax + 1);
			return;
		}
		printStarsTop(n, level + 1, levelMax);
		
	}
	
	private static void reverseStr(String s) {
		reverseStr(s, s.length() - 1);
	}
	private static void reverseStr(String s, int i) {
		System.out.print(s.charAt(i));
		if(i == 0) 
			return;
		reverseStr(s, i - 1);
	}

}
