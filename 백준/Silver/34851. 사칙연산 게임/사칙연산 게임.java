import java.io.*;
import java.util.*;

public class Main {
    static final long MOD = 1_000_000_007L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        long[] a = new long[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i <= N; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }

        long ans;

        if (a[0] == 1) {
            ans = (1 + (a[1] % MOD)) % MOD;
            for (int i = 2; i <= N; i++) {
                long x = a[i];
                if (x == 1) {
                    ans += 1;
                    if (ans >= MOD) ans -= MOD;
                } else {
                    ans = (ans * (x % MOD)) % MOD;
                }
            }
        } else {
            // 첫 숫자가 2 이상
            ans = a[0] % MOD;

            for (int i = 1; i <= N; i++) {
                long x = a[i];
                if (x == 1) {
                    ans += 1;
                    if (ans >= MOD) ans -= MOD;
                } else {
                    ans = (ans * (x % MOD)) % MOD;
                }
            }
        }

        System.out.println(ans);
    }
}
