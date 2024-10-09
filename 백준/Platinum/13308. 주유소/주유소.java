import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge {
		int to, cost;//목적지, 이동 시 소요되는 기름
		
		Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	
	static class Node implements Comparable<Node> {
		int cur, minCost;//현재 위치, 가장 싼 주유소 가격
		long totalCost;
		
		Node (int cur, int minCost, long totalCost) {
			this.cur = cur;
			this.minCost = minCost;
			this.totalCost = totalCost;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.totalCost, o.totalCost);
		}
	}
	
	static int N, M;
	static int[] oilPrice;
	static List<Edge>[] edges;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		oilPrice = new int[N + 1];
		edges = new ArrayList[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; ++i) {
			oilPrice[i] = Integer.parseInt(st.nextToken());
			edges[i] = new ArrayList<>();
		}
            
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			edges[f].add(new Edge(t, d));
			edges[t].add(new Edge(f, d));
		}
            
		sb.append(dijkstra());
		System.out.println(sb);
	}
	
	public static long dijkstra() {
    	//dp : i에 minCost를 가지고 방문했을때의 최소 비용 저장
		long[][] dp = new long[N + 1][2501];
		for (int i = 0; i <= N; i++) Arrays.fill(dp[i], Long.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, oilPrice[1], 0));
		
		while (!pq.isEmpty()) {
			Node n = pq.poll();
			
			if (n.cur == N) return n.totalCost;
			
			for (Edge e : edges[n.cur]) {
				if (dp[e.to][n.minCost] <= n.totalCost + (e.cost * n.minCost)) continue;
				dp[e.to][n.minCost] = n.totalCost + (e.cost * n.minCost);
				pq.offer(new Node(e.to, Math.min(n.minCost, oilPrice[e.to]), dp[e.to][n.minCost]));	
			}
		}
		return -1;
	}
}