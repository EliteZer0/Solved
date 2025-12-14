import java.io.*;
import java.util.*;

public class Main {
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;
            do c = readByte(); while (c <= ' ' && c != -1);
            long sign = 1;
            if (c == '-') { sign = -1; c = readByte(); }
            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }

        String next() throws IOException {
            int c;
            do c = readByte(); while (c <= ' ' && c != -1);
            StringBuilder sb = new StringBuilder();
            while (c > ' ') {
                sb.append((char) c);
                c = readByte();
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int N = (int) fs.nextLong();
        String S = fs.next(); // length N
        long[] a = new long[N];
        for (int i = 0; i < N; i++) a[i] = fs.nextLong();

        char[] target = {'U', 'O', 'S', 'P', 'C'};
        long INF = Long.MAX_VALUE / 4;

        long[] dp = new long[6];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            char ch = S.charAt(i);
            long cost = a[i];

            for (int k = 4; k >= 0; k--) {
                if (ch == target[k] && dp[k] < INF) {
                    dp[k + 1] = Math.min(dp[k + 1], dp[k] + cost);
                }
            }
        }

        System.out.println(dp[5] >= INF ? -1 : dp[5]);
    }
}
