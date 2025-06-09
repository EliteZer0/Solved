import java.io.*;
import java.util.*;

public class Main {
    static int N, M, T;
    static int[][] avgLight;
    static boolean[][] visited;

    // 상, 하, 좌, 우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][][] rgb = new int[N][M][3];
        avgLight = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int r = Integer.parseInt(st.nextToken());
                int g = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                rgb[i][j][0] = r;
                rgb[i][j][1] = g;
                rgb[i][j][2] = b;
            }
        }

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int avg = (rgb[i][j][0] + rgb[i][j][1] + rgb[i][j][2]) / 3;
                avgLight[i][j] = (avg >= T) ? 255 : 0;
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (avgLight[i][j] == 255 && !visited[i][j]) {
                    dfs(i, j);
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    static void dfs(int r, int c) {
        visited[r][c] = true;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                if (avgLight[nr][nc] == 255 && !visited[nr][nc]) {
                    dfs(nr, nc);
                }
            }
        }
    }
}
