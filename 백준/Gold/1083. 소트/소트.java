import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        int[] A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

        long S = Long.parseLong(br.readLine().trim());

        for (int i = 0; i < N && S > 0; i++) {
            int limit = (int)Math.min(N - 1, i + S);

            int maxIdx = i;
            for (int j = i + 1; j <= limit; j++) {
                if (A[j] > A[maxIdx]) maxIdx = j;
            }

            for (int j = maxIdx; j > i; j--) {
                int tmp = A[j];
                A[j] = A[j - 1];
                A[j - 1] = tmp;
            }

            S -= (maxIdx - i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (i > 0) sb.append(' ');
            sb.append(A[i]);
        }
        System.out.println(sb);
    }
}
