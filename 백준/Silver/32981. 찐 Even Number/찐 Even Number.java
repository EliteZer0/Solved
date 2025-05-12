import java.util.*;
import java.io.*;

public class Main {
    static final int MOD = 1000000007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.parseInt(br.readLine());

        for (int i = 0; i < Q; i++) {
            int N = Integer.parseInt(br.readLine());
            if (N == 1) {
                System.out.println(5);
            } else {
                long result = (4 * pow(5, N - 1)) % MOD;
                System.out.println(result);
            }
        }
    }

    static long pow(long base, int exp) {
        if (exp == 0) return 1;
        long half = pow(base, exp / 2);
        long result = (half * half) % MOD;
        if (exp % 2 == 1) {
            result = (result * base) % MOD;
        }
        return result;
    }
}
