import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        for (int k = L; k <= 100; k++) {
            long sumFirst = (long) k * (k - 1) / 2;
            long t = N - sumFirst;

            if (t < 0) break;
            if (t % k != 0) continue;

            long x = t / k;
            if (x < 0) continue;

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < k; i++) {
                if (i > 0) sb.append(' ');
                sb.append(x + i);
            }
            System.out.println(sb);
            return;
        }

        System.out.println(-1);
    }
}
