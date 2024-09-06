import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int row;
	static int col;
	static int day;
	static int tomatoCnt;
	static int[][] box;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		col = Integer.parseInt(st.nextToken());
		row = Integer.parseInt(st.nextToken());
		
		box = new int[row][col];
		Queue<int[]> tomato = new LinkedList<>();
		tomatoCnt = 0;
		
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				
				if(box[i][j] == 0) {
					tomatoCnt++;
				}
				if(box[i][j] == 1) {
					tomato.offer(new int[] {i, j});
				}
				
			}
		}
		
		day = -1;
		
		bfs(tomato);
		
		if(tomatoCnt>0) {
			System.out.println(-1);
		}else {
			System.out.println(day);
		}		
	}
	
	static int[] dr = {-1,1,0,0};//상하좌우
	static int[] dc = {0,0,-1,1};
	static void bfs(Queue<int[]> que) {		
		while(!que.isEmpty()) {
			int cycle = que.size();
			for (int i = 0; i < cycle; i++) {
				int[] cur = que.poll();
				int curRow = cur[0];
				int curCol = cur[1];
				
				for (int d = 0; d < 4; d++) {
					int moveRow = curRow+dr[d];
					int moveCol = curCol+dc[d];
					
					if(!check(moveRow, moveCol)) continue;
					
					if(box[moveRow][moveCol] == 0) {
						box[moveRow][moveCol] = 1;
						tomatoCnt--;
						que.offer(new int[] {moveRow, moveCol});
					}
				}
			}
			day++;			
		}
	}

	static boolean check(int r, int c) {
		return r<row && r>=0 && c<col && c>=0;
	}
}