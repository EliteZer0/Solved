import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] cost = new int[N + 1][3];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        int answer = INF;

        for (int firstColor = 0; firstColor < 3; firstColor++) {
            int[][] dp = new int[N + 1][3];
            for (int c = 0; c < 3; c++) dp[1][c] = INF;
            dp[1][firstColor] = cost[1][firstColor];

            for (int i = 2; i <= N; i++) {
                dp[i][0] = cost[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
                dp[i][1] = cost[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
                dp[i][2] = cost[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
            }

            for (int lastColor = 0; lastColor < 3; lastColor++) {
                if (lastColor == firstColor) continue;
                answer = Math.min(answer, dp[N][lastColor]);
            }
        }

        System.out.println(answer);
    }
}
