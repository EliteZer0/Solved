import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for(int t = 0; t<T; t++){
            int N = Integer.parseInt(br.readLine());
            double[][] diamonds = new double[N][2];
            int[] dp = new int[N];
            for(int i = 0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                diamonds[i][0] = Double.parseDouble(st.nextToken());
                diamonds[i][1] = Double.parseDouble(st.nextToken());
            }
            for(int i = 0; i<N; i++){
                dp[i] = 1;
                for(int j = 0; j<i; j++){
                    if(diamonds[i][0] > diamonds[j][0] && diamonds[i][1]<diamonds[j][1]){
                        dp[i] = Math.max(dp[i], dp[j]+1);
                    }
                }
            }
            int ans = 0;
            for(int i = 0; i<N; i++){
                ans = Math.max(ans, dp[i]);
            }
            sb.append(ans);
            if(t<T-1) sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}