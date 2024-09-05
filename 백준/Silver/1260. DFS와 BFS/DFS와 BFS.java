import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb;
	static int N, M, V;
	static boolean [] visited;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		N=Integer.parseInt(st.nextToken().trim());
		M=Integer.parseInt(st.nextToken().trim());
		V=Integer.parseInt(st.nextToken().trim());
		map=new int[N+1][N+1];
		
		for (int i = 0; i < M; i++) {
			st=new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken().trim());
			int end = Integer.parseInt(st.nextToken().trim());
			map[start][end]=1;
			map[end][start]=1;
		}
		
		visited = new boolean[N+1];
		dfs(V);
		
		sb.append("\n");
		
		visited = new boolean[N+1];
		bfs(V);
		
		System.out.println(sb);
	}
	
	static void dfs(int start) {
		visited[start]=true;
		sb.append(start).append(" ");
		
		for (int i = 1; i <N+1; i++) {
			if(visited[i]) continue;
			if(map[start][i]==1) {
				dfs(i);
			}
		}
	}
	
	static void bfs(int start) {
		Queue<Integer> que=new LinkedList<>();
		que.offer(start);
		visited[start]=true;
		while(!que.isEmpty()) {
			start =que.poll();
			sb.append(start).append(" ");
			
			for (int i = 1; i <N+1; i++) {
				if(visited[i]) continue;
				if(map[start][i]==1) {
					que.offer(i);
					visited[i]=true;
				}
			}
		}
	}
}