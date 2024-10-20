import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int time;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int distance = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());
		
		if(distance == 0) {
        	System.out.println(0);
            return;
        }
        
        if (time < distance) {
            System.out.println((int)Math.pow(2, time));
            return;
        }
		
		long[][] dp = new long[time+1][time*2 +1];
		
		dp[0][time] = 1;
		
		for(int i = 1; i<time+1; i++) {
			for(int j = 0; j < time*2 + 1; j++) {
				if(j == time+distance) {
					dp[i][j] = 0;
				} else if(j == 0) {
					dp[i][j] = dp[i-1][j+1];
				} else if(j == time*2) {
					dp[i][j] = dp[i-1][j-1];
				} else {
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1];
				}
			}
		}
		
//		for (int i = 0; i < time+1; i++) {
//			for (int j = 0; j < time*2+1; j++) {
//				System.out.printf("%3d ", dp[i][j]);
//			}
//			System.out.println();
//		}
		
		long count = 0;
		for (int j = 0; j < time+distance; j++) {
			count += dp[time][j];
		}
		
		System.out.println(count);
	}
}