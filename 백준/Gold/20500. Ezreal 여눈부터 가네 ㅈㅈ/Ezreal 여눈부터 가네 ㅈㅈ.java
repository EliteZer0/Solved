import java.util.*;
import java.lang.*;
import java.io.*;

// 15의 배수 = 3의 배수이면서 5의 배수
// 5의 배수 : 마지막 자리가 5
// 3의 배수 : 각 자리 수의 합이 3의 배수
class Main {
    static final int MOD = 1_000_000_007;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        
        // dp[자리수][mod3][마지막숫자(인덱스 0:1, 인덱스 1:5)]
        int[][][] dp = new int[N + 1][3][2];
        
        // 첫 자리는 1 또는 5만 가능 (0으로 시작 불가)
        dp[1][1][0] = 1; // 첫 자리에 1을 놓음 (1 % 3 = 1)
        dp[1][2][1] = 1; // 첫 자리에 5를 놓음 (5 % 3 = 2)
        
        for (int i = 1; i < N; i++) {
            for (int mod = 0; mod < 3; mod++) {
                for (int last = 0; last < 2; last++) {
                    if (dp[i][mod][last] == 0) continue;
                    
                    // 다음 자리에 1을 추가
                    int nextMod1 = (mod + 1) % 3;
                    dp[i + 1][nextMod1][0] = (dp[i + 1][nextMod1][0] + dp[i][mod][last]) % MOD;
                    
                    // 다음 자리에 5를 추가
                    int nextMod5 = (mod + 2) % 3; // 5 % 3 = 2
                    dp[i + 1][nextMod5][1] = (dp[i + 1][nextMod5][1] + dp[i][mod][last]) % MOD;
                }
            }
        }
        
        // 답: N자리, mod3 = 0, 마지막 자리 = 5
        System.out.println(dp[N][0][1]);
    }
}