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
		N = nextInt();
		E = nextInt();
		
		adjList = new ArrayList[N+1];
		dis = new int[N+1];
		
		for (int i = 1; i < N+1; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		Arrays.fill(dis, Integer.MAX_VALUE);
		
		for (int i = 0; i < E; i++) {
			int a = nextInt();
			int b = nextInt();
			int c = nextInt();
			
			adjList[a].add(new Node(b, c));
			adjList[b].add(new Node(a, c));
		}
		
		int v1 = nextInt();
		int v2 = nextInt();
		
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
	
	private static byte[] buffer = new byte[1 << 16];
    private static int bufferPointer = 0, bytesRead = 0;

    private static int read() throws IOException {
        if (bufferPointer == bytesRead) {
            bufferPointer = 0;
            bytesRead = System.in.read(buffer);
            if (bytesRead == -1) return -1;
        }
        return buffer[bufferPointer++];
    }

    private static int nextInt() throws IOException {
        int result = 0;
        int c = read();
        
        // 공백 문자를 건너뜁니다.
        while (c <= ' ') c = read();
        
        // 양수 또는 음수 판별
        boolean negative = (c == '-');
        if (negative) c = read();

        // 숫자 부분 읽기
        do {
            result = result * 10 + (c - '0');
            c = read();
        } while (c >= '0' && c <= '9');
        
        return negative ? -result : result;
    }
}