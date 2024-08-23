import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] time = new int[n];
		int[] price = new int[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			price[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[n+1];
		for (int i = 0; i < n; i++) {
			if(i+time[i]<=n) {
				dp[i+time[i]] = Math.max(dp[i+time[i]], dp[i]+price[i]);
			}
			dp[i+1] = Math.max(dp[i+1], dp[i]);
		}
//		System.out.println(Arrays.toString(dp));
		System.out.println(dp[n]);
	}
}