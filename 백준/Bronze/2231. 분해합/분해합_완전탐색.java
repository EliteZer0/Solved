import java.util.Scanner;

public class Main2231 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int ans = 0;
		int sum = 0;
		for(int i = 1; i<N; i++) {
			String num = String.valueOf(i);
			sum = i;
			if(sum !=N) {
				for (int j = 0; j < num.length(); j++) {
					sum += num.charAt(j)-'0';
				}
			}
			if (sum == N) {
				ans = i;
				break;	
			}
		}
		System.out.println(ans);
	}
}