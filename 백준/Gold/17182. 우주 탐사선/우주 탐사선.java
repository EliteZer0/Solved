import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[][] dist;
    static boolean[] visited;
    static int minTime = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        dist = new int[N][N];
        
        // 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 플로이드 워셜 - 모든 쌍 최단 거리
        for (int mid = 0; mid < N; mid++) {
            for (int from = 0; from < N; from++) {
                for (int to = 0; to < N; to++) {
                    if (dist[from][to] > dist[from][mid] + dist[mid][to]) {
                        dist[from][to] = dist[from][mid] + dist[mid][to];
                    }
                }
            }
        }

        visited = new boolean[N];
        visited[K] = true;
        dfs(K, 1, 0); // 현재 위치, 방문 수, 누적 시간

        System.out.println(minTime);
    }

    static void dfs(int current, int count, int time) {
        if (count == N) {
            minTime = Math.min(minTime, time);
            return;
        }

        for (int next = 0; next < N; next++) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next, count + 1, time + dist[current][next]);
                visited[next] = false;
            }
        }
    }
}
