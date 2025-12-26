import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    
    private static class Edge {
        int from, to, w;
        Edge(int from , int to, int w){
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int TC = Integer.parseInt(br.readLine());

        for(int t = 0; t<TC; t++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            List<Edge> edges = new ArrayList<>();

            // 도로
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                edges.add(new Edge(S, E, T));
                edges.add(new Edge(E, S, T));
            }

            // 웜홀
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                edges.add(new Edge(S, E, -T));
            }

            boolean canTimeWarp = checkCycle(N, edges);
            sb.append(canTimeWarp ? "YES" : "NO").append('\n');
        }

        System.out.print(sb);
    }
    
    static boolean checkCycle(int N, List<Edge> edges) {
        // 모든 정점에서 시작 가능
        long[] dist = new long[N + 1];
        Arrays.fill(dist, 0);

        // 벨만–포드의 핵심 명제 = 음수 사이클이 없다면
        // N-1번의 완화 이후에는 더 이상 어떤 거리도 줄어들 수 없다
        // N번 시도 중, N번째에서도 갱신되었다는 소리는
        // 동일 정점을 재방문했다는 의미이며,
        // 그 사이클을 포함했음에도 거리가 감소했다는 의미
        // 즉 음수 사이클
        for (int i = 1; i <= N; i++) {
            boolean updated = false;
            for (Edge e : edges) {
                if (dist[e.to] > dist[e.from] + e.w) {
                    dist[e.to] = dist[e.from] + e.w;
                    updated = true;
                    
                    // N 번째에도 
                    if (i == N) return true; // 음수 사이클 존재
                }
            }
            if (!updated) break; // 더 이상 갱신 없으면 조기 종료
        }
        return false;
    }
}