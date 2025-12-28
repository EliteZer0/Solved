import java.io.*;
import java.util.*;

public class Main {
    static long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long G = Long.parseLong(st.nextToken());
        long L = Long.parseLong(st.nextToken());

        long K = L / G; // a*b = K
        long bestA = 1, bestB = K;

        long limit = (long)Math.sqrt(K);

        for (long a = 1; a <= limit; a++) {
            if (K % a != 0) continue;
            long b = K / a;

            if (gcd(a, b) == 1) {
                bestA = a;
                bestB = b;
            }
        }

        long x = G * bestA;
        long y = G * bestB;
        if (x > y) { long tmp = x; x = y; y = tmp; }

        System.out.println(x + " " + y);
    }
}
