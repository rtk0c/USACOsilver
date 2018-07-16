import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner consoleIn = new Scanner(System.in);
		
		int in = consoleIn.nextInt();
		triangleBottom(in);
		
        consoleIn.close();
	}
	
	private static void primidRec(int n) {
		if(n == 1) return;
		
		primidRec(n - 1);
		System.out.print(n + " ");
		primidRec(n - 1);
	}
	
	private static void triangleBottom(int n) {
		if(n == 0) return;
		
		for(int i = 1; i <= n; i++) {
			System.out.print(i + " ");
		}
		System.out.println("");
		triangleBottom(n - 1);
	}
}
