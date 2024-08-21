import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		star(n);
	}
	static void star(int n) {
		for (int t = 0; t < n; t++) {
			if(n%2 != 0) {
				for(int i=0; i<(n+1)/2; i++) {
					System.out.print("* ");
				}
				System.out.println();
				for(int i=1; i<(n+1)/2; i++) {
					System.out.print(" *");
				}
				System.out.println();
			}
			
			if(n%2 == 0) {
				for(int i=0; i<(n+1)/2; i++) {
					System.out.print("* ");
				}
				System.out.println();
				for(int i=0; i<(n+1)/2; i++) {
					System.out.print(" *");
				}
				System.out.println();
			}
		}
	}
}
