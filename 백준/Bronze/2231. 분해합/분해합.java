import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String n = String.valueOf(N);
		int ans = 0;
		int sum = 0;
		int start = N-9*n.length();
		if(start<1) {
			start = 1;
		}
		for (int i = start; i < N; i++) {
			String strStart = String.valueOf(i);
			sum = i;
			if(sum !=N) {
				for (int j = 0; j < strStart.length(); j++) {
					sum += strStart.charAt(j) -'0';
				}
			}
			if(sum == N) {
				ans = i;
				break;
			}
		}
		System.out.println(ans);
	}
}