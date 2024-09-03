import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int T;
	static int N;
	static int A;
	static int B;
	static int C;
	static int D;
	
	static int dis;
	
	static int[][] map;
	static int[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc < T+1; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			//start
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			
			//end
			C = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			
			visited = new int[N][N];
			
			bfs(A,B);
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ").append(dis);
			
			System.out.println(sb.toString());
		}
	}

	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	static void bfs(int sr, int sc) {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {sr, sc, 0});
		dis = -1;
		visited[sr][sc] = 1;
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int r = cur[0];
			int c = cur[1];
			int time = cur[2];
			if(r==C && c==D) {
				dis = time;
				return;
			}
			for(int d = 0; d<4;d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(!check(nr,nc)) continue;
				if(visited[nr][nc]>0) continue;
				
				if(map[nr][nc] == 0) {
					visited[nr][nc] = 1;
					que.offer(new int[]{nr, nc, time+1});
				}
				
				if(map[nr][nc] == 2) {
					int cycle = (time+1)%3;
					if(cycle == 0) {
						visited[nr][nc] = 1;
						que.offer(new int[]{nr, nc, time + 1});
					} else {
						que.offer(new int[]{r, c, time + 1});
					}
					
				}
			}
		}
	}
	
	static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
}