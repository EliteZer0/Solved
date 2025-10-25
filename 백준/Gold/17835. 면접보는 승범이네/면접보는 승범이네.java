import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int to, cost;
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    
    static class Node implements Comparable<Node> {
        int vertex;
        long dist;
        
        Node(int vertex, long dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
        
        @Override
        public int compareTo(Node o) {
            return Long.compare(this.dist, o.dist);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        // 역방향 그래프 생성
        List<Edge>[] reverseGraph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            reverseGraph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // 역방향으로 저장
            reverseGraph[v].add(new Edge(u, c));
        }
        
        // 면접장 위치
        st = new StringTokenizer(br.readLine());
        List<Integer> interviews = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            interviews.add(Integer.parseInt(st.nextToken()));
        }
        
        // 다익스트라 - 모든 면접장에서 시작
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        // 모든 면접장을 시작점으로
        for (int interview : interviews) {
            dist[interview] = 0;
            pq.offer(new Node(interview, 0));
        }
        
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;
            long d = current.dist;
            
            if (d > dist[u]) continue;
            
            for (Edge edge : reverseGraph[u]) {
                int v = edge.to;
                long newDist = d + edge.cost;
                
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    pq.offer(new Node(v, newDist));
                }
            }
        }
        
        // 최대 거리를 가진 도시 찾기
        long maxDist = -1;
        int resultCity = -1;
        
        for (int i = 1; i <= N; i++) {
            if (dist[i] != Long.MAX_VALUE && dist[i] > maxDist) {
                maxDist = dist[i];
                resultCity = i;
            }
        }
        
        sb.append(resultCity).append("\n");
        sb.append(maxDist).append("\n");
        
        System.out.print(sb.toString());
    }
}