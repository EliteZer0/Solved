import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());  // 아이템 수
        int K = Integer.parseInt(st.nextToken());  // 최대 무게

        int[] weight = new int[N + 1];
        int[] value = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            for (int k = 1; k <= K; k++) {
                dp[i][k] = dp[i - 1][k];  // 현재 물건을 선택하지 않은 경우
                if (k - weight[i] >= 0) {
                    dp[i][k] = Math.max(dp[i][k], value[i] + dp[i - 1][k - weight[i]]);
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
