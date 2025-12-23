import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] prev = new int[3];
        int[] cur = new int[3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (i == 0) {
                prev[0] = r;
                prev[1] = g;
                prev[2] = b;
            } else {
                cur[0] = r + Math.min(prev[1], prev[2]);
                cur[1] = g + Math.min(prev[0], prev[2]);
                cur[2] = b + Math.min(prev[0], prev[1]);

                prev[0] = cur[0];
                prev[1] = cur[1];
                prev[2] = cur[2];
            }
        }

        int ans = Math.min(prev[0], Math.min(prev[1], prev[2]));
        System.out.println(ans);
    }
}
