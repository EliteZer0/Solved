import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int ans = 0;

        for (int n = N; n > 0; n--) {
            int half = 1 << (n - 1);
            int area = half * half; 

            if (r < half && c < half) {
            } else if (r < half && c >= half) {
                ans += area;
                c -= half;
            } else if (r >= half && c < half) {
                ans += 2 * area;
                r -= half;
            } else {
                ans += 3 * area;
                r -= half;
                c -= half;
            }
        }

        System.out.println(ans);
    }
}
