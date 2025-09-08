import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        if (N % 2 == 1) {
            int k = N / 2;          // floor(N/2)
            int cur = 1;
            for (int i = 0; i < N; i++) {
                sb.append(cur).append(i + 1 == N ? "" : " ");
                cur += k;
                if (cur > N) cur -= N; // (cur + k) mod N in 1..N
            }
        } else {
            int k = N / 2;
            for (int i = k; i >= 1; i--) {
                sb.append(i).append(" ");
                sb.append(i + k).append(i == 1 ? "" : " ");
            }
        }

        System.out.print(sb.toString());
    }
}
