import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Ice {
    int r;
    int c;
    int sea; // 인접한 바다의 개수

    public Ice(int r, int c, int sea) {
        this.r = r;
        this.c = c;
        this.sea = sea;
    }
}

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        int cnt = 0;

        while (true) {
            visited = new boolean[N][M]; // 각 단계마다 visited 배열 초기화
            cnt = Separate(); // 분리된 빙산의 개수를 세기
            // 빙산이 다 녹아버린 경우
            if (cnt == 0) {
                answer = 0;
                break;
            }
            // 빙산이 두 개 이상으로 나눠진 경우
            if (cnt >= 2) {
                break;
            }

            Melt(); // 빙산을 녹임
            answer++; // 시간을 증가시킴
        }

        System.out.println(answer);
    }

    private static int Separate() {
        int cnt = 0; // 분리된 빙산 덩어리 수
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;
                if (!visited[i][j]) { // 방문하지 않은 빙산을 발견하면 DFS 호출
                    DFS(i, j);
                    cnt++; // 새로운 빙산 덩어리 발견
                }
            }
        }
        return cnt; // 분리된 빙산 개수 반환
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    private static void DFS(int r, int c) {
        visited[r][c] = true;

        int nr, nc;

        for (int d = 0; d < 4; d++) {
            nr = r + dr[d];
            nc = c + dc[d];

            if (!check(nr, nc)) continue;
            if (map[nr][nc] == 0) continue;
            if (visited[nr][nc]) continue;
            DFS(nr, nc);
        }
    }

    private static boolean check(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static void Melt() {
        Queue<Ice> que = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;

                int sea = 0;

                for (int d = 0; d < 4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];

                    if (!check(nr, nc)) continue;
                    if (map[nr][nc] == 0) sea++;
                }

                que.offer(new Ice(i, j, sea)); // 녹을 양을 포함해 큐에 저장
            }
        }

        // 큐에 저장된 정보로 한 번에 빙산을 녹임
        while (!que.isEmpty()) {
            Ice ice = que.poll();
            map[ice.r][ice.c] -= ice.sea; // 바다에 의해 녹는 양만큼 빙산 높이 감소
            if (map[ice.r][ice.c] < 0) {
                map[ice.r][ice.c] = 0; // 음수가 되지 않도록 처리
            }
        }
    }
}