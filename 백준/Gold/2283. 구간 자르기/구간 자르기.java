import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        int[][] seg = new int[N][2];
        int maxR = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            seg[i][0] = L;
            seg[i][1] = R;
            if (R > maxR) maxR = R;
        }

        int[] diff = new int[maxR + 2];
        for (int i = 0; i < N; i++) {
            int L = seg[i][0], R = seg[i][1];
            if (L < R) {
                diff[L] += 1;
                diff[R] -= 1;
            }
        }
        int[] cover = new int[maxR + 1];
        int cur = 0;
        for (int x = 0; x < maxR; x++) {
            cur += diff[x];
            cover[x] = cur;
        }

        int A = 0;
        long sum = 0;
        for (int B = 0; B <= maxR; B++) {
            while (sum > K && A < B) {
                sum -= cover[A];
                A++;
            }
            if (sum == K && A < B) {
                System.out.println(A + " " + B);
                return;
            }
            if (B < maxR) sum += cover[B];
        }

        System.out.println("0 0");
    }
}
