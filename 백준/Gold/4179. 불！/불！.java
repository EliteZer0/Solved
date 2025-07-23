import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    static int[][] fireTime;
    static int[][] jihunTime;
    static boolean[][] visited;
    static Queue<int[]> fireQueue = new LinkedList<>();
    static Queue<int[]> jihunQueue = new LinkedList<>();
    
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        fireTime = new int[R][C];
        jihunTime = new int[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            Arrays.fill(fireTime[i], -1);
            Arrays.fill(jihunTime[i], -1);
        }

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'F') {
                    fireQueue.offer(new int[]{i, j});
                    fireTime[i][j] = 0;
                } else if (map[i][j] == 'J') {
                    jihunQueue.offer(new int[]{i, j});
                    jihunTime[i][j] = 0;
                    visited[i][j] = true;
                }
            }
        }

        // 불 먼저 BFS
        while (!fireQueue.isEmpty()) {
            int[] now = fireQueue.poll();
            int x = now[0], y = now[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
                    if (fireTime[nx][ny] == -1 && map[nx][ny] != '#') {
                        fireTime[nx][ny] = fireTime[x][y] + 1;
                        fireQueue.offer(new int[]{nx, ny});
                    }
                }
            }
        }

        // 지훈 BFS
        while (!jihunQueue.isEmpty()) {
            int[] now = jihunQueue.poll();
            int x = now[0], y = now[1];

            // 가장자리 탈출 조건
            if (x == 0 || y == 0 || x == R - 1 || y == C - 1) {
                System.out.println(jihunTime[x][y] + 1);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
                    if (!visited[nx][ny] && map[nx][ny] == '.') {
                        // 불이 오지 않았거나, 지훈이가 먼저 도착할 수 있을 때만 이동
                        if (fireTime[nx][ny] == -1 || jihunTime[x][y] + 1 < fireTime[nx][ny]) {
                            visited[nx][ny] = true;
                            jihunTime[nx][ny] = jihunTime[x][y] + 1;
                            jihunQueue.offer(new int[]{nx, ny});
                        }
                    }
                }
            }
        }

        System.out.println("IMPOSSIBLE");
    }
}
