import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        int[] scores = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }
        
        int[][] dp = new int[N][N];
        
        for (int len = 1; len <= N; len++) {
            for (int i = 0; i <= N - len; i++) {
                int j = i + len - 1;
                
                if (len == 1) {
                    dp[i][j] = 0;
                } else {
                    int max = scores[i];
                    int min = scores[i];
                    for (int k = i + 1; k <= j; k++) {
                        max = Math.max(max, scores[k]);
                        min = Math.min(min, scores[k]);
                    }
                    dp[i][j] = max - min;
                    
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k+1][j]);
                    }
                }
            }
        }
        
        sb.append(dp[0][N-1]);
        System.out.print(sb.toString());
    }
}