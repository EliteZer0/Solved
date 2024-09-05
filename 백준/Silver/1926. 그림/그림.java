import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int row;
	static int col;
	static int count;
	static int maxSize;
	static int[][] paint;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		paint = new int[row][col];
		
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				paint[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		count = 0;
		maxSize = 0;
		visited = new boolean[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if(visited[i][j]) continue;
				if(paint[i][j] == 1) {
					bfs(i,j);
					count++;
				}
			}
		}
		
        System.out.println(count);
        System.out.println(maxSize);
		
	}
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static void bfs(int r, int c) {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {r, c});
		visited[r][c] = true;
		int size = 1;
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int curRow = cur[0];
			int curCol = cur[1];
			
			for (int d = 0; d < 4; d++) {
				int moveRow = curRow + dr[d];
				int moveCol = curCol + dc[d];
				
				if(!check(moveRow, moveCol)) continue;
				if(visited[moveRow][moveCol]) continue;
				if(paint[moveRow][moveCol] == 1) {
					size++;
					que.offer(new int[] {moveRow, moveCol});
					visited[moveRow][moveCol] = true;
				}
			}
			
			maxSize = Math.max(maxSize, size);
		}
	}

	static boolean check(int r, int c) {
		return r>=0 && r<row && c>=0 && c<col;
	}
}