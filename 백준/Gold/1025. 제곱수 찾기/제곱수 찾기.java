import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] a;

    static boolean isSquare(long x) {
        if (x < 0) return false;
        long r = (long) Math.sqrt(x);
        return r * r == x;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        a = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < M; j++) {
                a[i][j] = line.charAt(j) - '0';
            }
        }

        long ans = -1;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                long v = a[i][j];
                if (isSquare(v)) ans = Math.max(ans, v);
            }
        }

        for (int sr = 0; sr < N; sr++) {
            for (int sc = 0; sc < M; sc++) {

                for (int dr = -(N - 1); dr <= (N - 1); dr++) {
                    for (int dc = -(M - 1); dc <= (M - 1); dc++) {
                        if (dr == 0 && dc == 0) continue;

                        long num = 0;
                        int r = sr, c = sc;

                        while (0 <= r && r < N && 0 <= c && c < M) {
                            num = num * 10 + a[r][c];

                            if (isSquare(num)) ans = Math.max(ans, num);

                            r += dr;
                            c += dc;
                        }
                    }
                }
            }
        }

        System.out.println(ans);
    }
}
