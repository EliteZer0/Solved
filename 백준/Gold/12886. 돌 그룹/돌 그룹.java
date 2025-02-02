import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean isSame;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		isSame = false;
		
		bfs(A,B,C);
		
		if(!isSame) System.out.println(0);
		else System.out.println(1);
	}
	
	private static void bfs(int a, int b, int c) {
		if((a+b+c)%3 != 0) {
			isSame = false;
			return;
		}
		
		Queue<int[]> que = new LinkedList<>();
		boolean[][] visited = new boolean[1501][1501];
		
		que.offer(new int[] {a, b, c});
		visited[a][b] = true;
		visited[a][c] = true;
		visited[b][c] = true;
		
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			a = cur[0];
			b = cur[1];
			c = cur[2];
			
			if(a==b && b==c) {
				isSame = true;
				return;
			}
			
			if(a!=b) {
				int na = a;
				int nb = b;
				if(a>b) {
					na = a-b;
					nb = b*2;
				}else if(a<b) {
					na = a*2;
					nb = b-a;
				}
				if(!visited[na][nb]) {
					que.add(new int[] {na, nb, c});
					visited[na][nb] = true;
				}
			}
			
			if(a!=c) {
				int na = a;
				int nc = c;
				if(a>c) {
					na = a-c;
					nc = c*2;
				}else if(a<c) {
					na = a*2;
					nc = c-a;
				}
				if(!visited[na][nc]) {
					que.add(new int[] {na, b, nc});
					visited[na][nc] = true;
				}
			}
			
			if(b!=c) {
				int nb = b;
				int nc = c;
				if(b>c) {
					nb = b-c;
					nc = c*2;
				}else if(b<c) {
					nb = b*2;
					nc = c-b;
				}
				if(!visited[nb][nc]) {
					que.add(new int[] {a, nb, nc});
					visited[nb][nc] = true;
				}
			}
		}
	}
}