import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] graph;
    static int[] count;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        count = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 역방향 그래프 구성: B를 해킹하면 A도 해킹 가능하므로 B → A로 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[B].add(A);
        }

        int max = 0;
        
        /*2nd
        // 각 정점마다 BFS 실행
        for (int i = 1; i <= N; i++) {
            boolean[] visited = new boolean[N + 1];
            int hacked = bfs(i, visited); // i번 컴퓨터를 해킹했을 때 해킹 가능한 컴퓨터 수
            count[i] = hacked;
            max = Math.max(max, hacked); // 최대 해킹 수 갱신
        }
        */
        
        for (int i = 1; i <= N; i++) {
            if (count[i] == 0) {
                boolean[] visited = new boolean[N + 1];
                count[i] = bfs(i, visited); // 메모이제이션
            }
            max = Math.max(max, count[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (count[i] == max) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb.toString());
    }
    
    /*1st
    // 기존 코드 (시간 초과 발생)
    public static int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.add(start);
        int cnt = 1; // 자기 자신 포함

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : graph[now]) {
                if (!visited[next]) {
                    visited[next] = true;
                    cnt++;
                    q.add(next);
                }
            }
        }
        return cnt;
    }
    */

    // BFS 함수: start 컴퓨터를 시작으로 해킹 가능한 컴퓨터 수 계산
    public static int bfs(int start, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);
        int cnt = 1; // 자기 자신 포함

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : graph[now]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
