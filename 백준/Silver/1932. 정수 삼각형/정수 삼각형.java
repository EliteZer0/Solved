import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        int[][] dp = new int[n][n];
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<=i; j++){
                int input = Integer.parseInt(st.nextToken());
                map[i][j] = input;
                dp[i][j] = input;
                if(i>0){
                    if(j==0){
                        dp[i][j] = dp[i-1][j] + dp[i][j];
                    }
                    else if(j==n-1){
                        dp[i][j] = dp[i-1][j-1] + dp[i][j];
                    }
                    else{
                        dp[i][j] = Math.max(Math.max(dp[i][j], dp[i-1][j-1]+dp[i][j]), dp[i-1][j]+dp[i][j]);
                    }
                }
            }
        }

        int ans = 0;
        for(int i = 0; i<n; i++){
            ans = Math.max(ans, dp[n-1][i]);
        }
        
        System.out.println(ans);
    }
}