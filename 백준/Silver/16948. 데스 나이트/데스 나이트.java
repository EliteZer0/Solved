import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] start, end;
	static int N, minDistance;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		start = new int[3];
		end = new int[2];
		
		for (int i = 0; i < 2; i++) {
			start[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < 2; i++) {
			end[i] = Integer.parseInt(st.nextToken());
		}
		
		minDistance = Integer.MAX_VALUE;
		visited = new boolean[N][N];
		
		bfs();
		
		if(minDistance == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(minDistance);
		}
	}
	
	static int[] dr = {-2, -2, 0, 0, 2, 2};
	static int[] dc = {-1, 1, -2, 2, -1, 1};

	static void bfs() {
		Queue<int[]> que = new LinkedList<>();
		que.offer(start);
		visited[start[0]][start[1]] = true;
		
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int curRow = cur[0];
			int curCol = cur[1];
			int curMove = cur[2];
			
			if(curRow == end[0] && curCol == end[1]) {
				minDistance = Math.min(minDistance, curMove);
				return;
			}
			
			for (int d = 0; d < 6; d++) {
				int moveRow = curRow+dr[d];
				int moveCol = curCol+dc[d];
				
				if(!check(moveRow, moveCol)) continue;
				if(visited[moveRow][moveCol]) continue;
				
				visited[moveRow][moveCol] = true;
				que.offer(new int[] {moveRow,moveCol,curMove+1});
			}
		}
	}

	static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
}