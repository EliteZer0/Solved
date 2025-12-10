import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());

        int[] A = new int[N];
        int[] B = new int[2 * N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            if (A[i] != B[i]) {
                System.out.println("NO");
                return;
            }
        }

        boolean[] seen = new boolean[N + 1];

        for (int i = 0; i < N; i++) {
            seen[A[i]] = true;
        }

        for (int i = N; i < 2 * N; i++) {
            int x = B[i];
            if (!seen[x]) {
                System.out.println("NO");
                return;
            }
            seen[x] = true;
        }

        System.out.println("YES");
    }
}
