import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] A;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        // dp[i] = i번째 돌까지 도달하는데 필요한 최소 K값
        long[] dp = new long[N];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // 시작점
        
        for (int i = 0; i < N - 1; i++) {
            if (dp[i] == Integer.MAX_VALUE) continue;
            
            for (int j = i + 1; j < N; j++) {
                long cost = (j - i) * (1L + Math.abs(A[i] - A[j]));
                // i에서 j로 가는데 필요한 K = max(지금까지의 최대 K, 이번 이동 비용)
                long needK = Math.max(dp[i], cost);
                dp[j] = Math.min(dp[j], needK);
            }
        }
        
        System.out.println(dp[N - 1]);
    }
}