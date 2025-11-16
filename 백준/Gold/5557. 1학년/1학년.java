import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        
        // dp[i][j] = i번째 숫자까지 사용했을 때, 계산 결과가 j인 경우의 수
        long[][] dp = new long[n][21];
        
        // 첫 번째 숫자로 시작
        dp[0][nums[0]] = 1;
        
        // 마지막 숫자 전까지 처리 (마지막은 = 이므로)
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i][j] == 0) continue;
                
                // + 연산
                int plus = j + nums[i + 1];
                if (plus >= 0 && plus <= 20) {
                    dp[i + 1][plus] += dp[i][j];
                }
                
                // - 연산
                int minus = j - nums[i + 1];
                if (minus >= 0 && minus <= 20) {
                    dp[i + 1][minus] += dp[i][j];
                }
            }
        }
        
        // 마지막 숫자와 같은 값으로 끝나는 경우의 수
        long answer = dp[n - 2][nums[n - 1]];
        
        System.out.println(answer);
    }
}