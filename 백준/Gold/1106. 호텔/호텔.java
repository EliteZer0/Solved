import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] costList = new int[N];
        int[] gain = new int[N];

        int maxGain = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            costList[i] = Integer.parseInt(st.nextToken());
            gain[i] = Integer.parseInt(st.nextToken());
            maxGain = Math.max(maxGain, gain[i]);
        }

        int LIMIT = C + maxGain;
        int[] dp = new int[LIMIT + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int k = 0; k < N; k++) {
            int g = gain[k];
            int cost = costList[k];
            for (int i = g; i <= LIMIT; i++) {
                if (dp[i - g] != INF) {
                    dp[i] = Math.min(dp[i], dp[i - g] + cost);
                }
            }
        }

        int ans = INF;
        for (int i = C; i <= LIMIT; i++) ans = Math.min(ans, dp[i]);

        System.out.println(ans);
    }
}
