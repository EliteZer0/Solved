import java.io.*;
import java.util.*;

public class Main {
    static String M;
    static int K;
    static int len;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String input = st.nextToken();
        long N = Long.parseLong(input); // 1 ≤ N ≤ 10^15
        K = Integer.parseInt(st.nextToken());

        long next = N + 1;
        M = String.valueOf(next);
        len = M.length();

        String ans = solveSameLength();

        if (ans == null) {
            ans = solveLonger();
        }

        System.out.println(ans);
    }

    static String solveSameLength() {
        String[][][] dp = new String[len + 1][K + 2][2];
        return dfs(0, 0, 1, dp);
    }

    static String dfs(int pos, int count5, int tight, String[][][] dp) {
        if (count5 > K) count5 = K;
        if (pos == len) {
            return (count5 >= K) ? "" : null;
        }
        if (dp[pos][count5][tight] != null) {
            if (dp[pos][count5][tight].equals("#")) return null;
            return dp[pos][count5][tight];
        }

        int limitDigit = tight == 1 ? M.charAt(pos) - '0' : 9;

        int startDigit;
        if (tight == 1) {
            startDigit = M.charAt(pos) - '0';
        } else {
            startDigit = 0;
        }
        if (pos == 0) startDigit = Math.max(startDigit, 1);

        String best = null;

        for (int d = startDigit; d <= 9; d++) {
            int ntight;
            if (tight == 1) {
                if (d == limitDigit) ntight = 1;
                else if (d > limitDigit) ntight = 0;
                else continue;
            } else {
                ntight = 0;
            }

            int nCount5 = count5 + (d == 5 ? 1 : 0);
            if (nCount5 > K) nCount5 = K;
            int remain = len - pos - 1;
            if (nCount5 + remain < K) continue;

            String suffix = dfs(pos + 1, nCount5, ntight, dp);
            if (suffix != null) {
                best = (char)('0' + d) + suffix;
                break;
            }
        }

        if (best == null) {
            dp[pos][count5][tight] = "#";
        } else {
            dp[pos][count5][tight] = best;
        }

        return best == null ? null : best;
    }
    static String solveLonger() {
        int L = Math.max(K, len + 1);
        StringBuilder sb = new StringBuilder();

        if (K == L) {
            for (int i = 0; i < L; i++) sb.append('5');
        } else {
            sb.append('1');
            for (int i = 0; i < L - 1 - K; i++) sb.append('0');
            for (int i = 0; i < K; i++) sb.append('5');
        }

        return sb.toString();
    }
}
