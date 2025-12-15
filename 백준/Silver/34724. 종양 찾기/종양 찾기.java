import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] g = new char[N][];
        for (int i = 0; i < N; i++) {
            g[i] = br.readLine().trim().toCharArray();
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 1; j++) {
                if (g[i][j] == '1' &&
                    g[i][j + 1] == '1' &&
                    g[i + 1][j] == '1' &&
                    g[i + 1][j + 1] == '1') {
                    System.out.println(1); // 종양 존재
                    return;
                }
            }
        }

        System.out.println(0); // 종양 없음
    }
}
