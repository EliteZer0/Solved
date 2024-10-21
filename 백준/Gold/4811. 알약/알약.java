import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = 0;
		while((N = Integer.parseInt(br.readLine())) > 0) {
			long dp[][] = new long[N+1][N+1];
			
			for (int i = N; i >= 0 ; i--) {
				for (int j = N-i; j >=0 ; j--) {
					dp [N][0] = 1;
					
					if(N-i == j) {
						dp[i][j] = 1;
					}
					
					if(i<N && j<N-i && j>0) {
						dp[i][j] = dp[i+1][j-1] + dp[i][j+1]; 
					}
					
					dp[i][0] = dp[i][1];
				}
			}
						
			System.out.println(dp[0][0]);
		}
	}
}