import java.util.*;
import java.io.*;

public class Main {
    
    // 간선 정보를 저장하는 클래스
    static class Edge {
        int to;
        int cost;
        
        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    
    // 다익스트라에서 사용할 노드 클래스
    static class Node implements Comparable<Node> {
        int vertex;
        int distance;
        
        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
        
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 헛간 수
        int M = Integer.parseInt(st.nextToken()); // 길의 수
        
        // 그래프 초기화
        ArrayList<Edge>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            // 양방향 그래프
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }
        
        // 다익스트라 알고리즘 실행
        int result = dijkstra(graph, N, 1, N);
        System.out.println(result);
    }
    
    // 다익스트라 알고리즘 구현
    static int dijkstra(ArrayList<Edge>[] graph, int N, int start, int end) {
        // 거리 배열 초기화
        int[] distances = new int[N + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
        
        // 우선순위 큐 (최소 힙)
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentVertex = current.vertex;
            int currentDistance = current.distance;
            
            // 이미 처리된 노드면 건너뛰기
            if (currentDistance > distances[currentVertex]) {
                continue;
            }
            
            // 도착지에 도달했으면 결과 반환
            if (currentVertex == end) {
                return currentDistance;
            }
            
            // 인접 노드들 확인
            for (Edge edge : graph[currentVertex]) {
                int nextVertex = edge.to;
                int newDistance = currentDistance + edge.cost;
                
                // 더 짧은 경로를 발견했으면 업데이트
                if (newDistance < distances[nextVertex]) {
                    distances[nextVertex] = newDistance;
                    pq.offer(new Node(nextVertex, newDistance));
                }
            }
        }
        
        return distances[end];
    }
}