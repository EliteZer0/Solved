import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int v,w;

	public Node(int v, int w) {
		this.v = v;
		this.w = w;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.w, o.w);
	}
	
}

public class Main {
	static int N, E;
	static ArrayList<Node>[] adjList;
	static int[] dis;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[N+1];
		dis = new int[N+1];
		
		for (int i = 1; i < N+1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		Arrays.fill(dis, Integer.MAX_VALUE);
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			adjList[a].add(new Node(b, c));
			adjList[b].add(new Node(a, c));
		}
		
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		dijkstra(1);
		
		int sum1 = 0;
		int sum2 = 0;
		boolean isPath1Possible = true, isPath2Possible = true;

		// 1 - v1 - v2 - N or 1 - v2 - v1 - N
		
		if(dis[v1] == Integer.MAX_VALUE) {
			isPath1Possible = false;
		} else {
			sum1 += dis[v1];
		}
		
		if(dis[v2] == Integer.MAX_VALUE) {
			isPath2Possible = false;
		} else {
			sum2 += dis[v2];
		}
		
		Arrays.fill(dis, Integer.MAX_VALUE);
		dijkstra(v1);
		if(dis[v2] == Integer.MAX_VALUE) {
			isPath1Possible = false;
		} else {
			sum1 += dis[v2];
		}
		
		if(dis[N] == Integer.MAX_VALUE) {
			isPath2Possible = false;
		} else {
			sum2 += dis[N];
		}
		
		Arrays.fill(dis, Integer.MAX_VALUE);
		dijkstra(v2);
		if(dis[v1] == Integer.MAX_VALUE) {
			isPath2Possible = false;
		} else {
			sum2 += dis[v1];
		}
		
		if(dis[N] == Integer.MAX_VALUE) {
			isPath1Possible = false;
		} else {
			sum1 += dis[N];
		}
		
		if(!isPath1Possible && !isPath2Possible) {
			System.out.println(-1);
		}
		else if(!isPath1Possible) {
			System.out.println(sum2);
		}
		else if(!isPath2Possible) {
			System.out.println(sum1);
		}
		else {
			int min = Math.min(sum1, sum2);
			System.out.println(min);
		}	
	}

	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		visited = new boolean[N+1];
		
		dis[start] = 0;
		
		pq.add(new Node(start,0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(visited[cur.v]) continue;
			visited[cur.v] = true;
			
			for(Node node : adjList[cur.v]) {
				if(visited[node.v]) continue;
				if(dis[node.v] > dis[cur.v] + node.w) {
					dis[node.v] = dis[cur.v] + node.w;
					pq.add(new Node(node.v, dis[node.v]));
				}
			}
		}
		
	}
}