import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            int[] files = new int[K + 1];
            int[] prefix = new int[K + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                files[i] = Integer.parseInt(st.nextToken());
                prefix[i] = prefix[i - 1] + files[i];
            }

            int[][] dp = new int[K + 1][K + 1];

            // 길이 2부터 시작하여 구간을 점차 넓혀가며 최소 비용 계산
            for (int len = 2; len <= K; len++) {
                for (int i = 1; i <= K - len + 1; i++) {
                    int j = i + len - 1;
                    dp[i][j] = Integer.MAX_VALUE;

                    for (int k = i; k < j; k++) {
                        int cost = dp[i][k] + dp[k + 1][j] + prefix[j] - prefix[i - 1];
                        dp[i][j] = Math.min(dp[i][j], cost);
                    }
                }
            }

            System.out.println(dp[1][K]); // 정답 출력
        }
    }
}
