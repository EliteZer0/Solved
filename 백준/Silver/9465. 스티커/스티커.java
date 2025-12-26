import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for(int T = 0; T<t; T++){
            int n = Integer.parseInt(br.readLine());
            int[][] stickers = new int[2][n];
            // 0 : 윗칸 1 : 아랫칸 2 : 안 뜯음
            int[][] dp = new int[n][3];
            for(int i = 0; i<2; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j<n; j++){
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            }
    
            // 첫번째 열 위 뜯음
            dp[0][0] = stickers[0][0];
            // 첫번째 열 아래 뜯음
            dp[0][1] = stickers[1][0]; 
            // 아무것도 안 뜯음
            dp[0][2] = 0;       
    
            for(int i = 1; i<n; i++){
                // i열에서 위를 뜯으면 i-1열에서 위는 못 뜯음(좌우 인접)
                // 그래서 i-1열 상태는 아래(1) 또는 안 뜯음(2)만 가능
                dp[i][0] = Math.max(dp[i-1][1], dp[i-1][2]) + stickers[0][i];
                // i열에서 아래를 뜯으면 i-1열에서 아래는 못 뜯음
                // 그래서 i-1열 상태는 위(0) 또는 안 뜯음(2)만 가능
                dp[i][1] = Math.max(dp[i-1][0], dp[i-1][2]) + stickers[1][i];
                // i열에서 안 뜯으면 i-1열은 뭐였든 상관없어서 max
                dp[i][2] = Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][2]));
            }
    
            int ans = Math.max(dp[n-1][0], Math.max(dp[n-1][1], dp[n-1][2]));
            
            sb.append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }
}