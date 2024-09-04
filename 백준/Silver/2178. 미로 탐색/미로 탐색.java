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
	static int[][] maze;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		count = Integer.MAX_VALUE;
		maze = new int[row][col];
		visited = new boolean[row][col];
		
		for (int i = 0; i < row; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < col; j++) {
				maze[i][j] = input[j]-'0';
			}
		}
		
		bfs(0, 0, 1);
		
		System.out.println(count);
	}
	
	static int[] dr = {-1,1,0,0};//상하좌우
	static int[] dc = {0,0,-1,1};
	
	static void bfs(int startRow, int startCol, int cnt) {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {startRow, startCol, cnt});
		
		visited[startRow][startCol] = true;
		
		while(!que.isEmpty()) {
			
			int[] cur = que.poll();
			
			int curRow = cur[0]; 
			int curCol = cur[1];
			int curCnt = cur[2];
			
			if(curRow==row-1 && curCol==col-1) {
				count = Math.min(count, curCnt);
			}
			
			for(int d = 0; d<4; d++) {
				int moveRow = curRow + dr[d];
				int moveCol = curCol + dc[d];
				int moveCnt = curCnt + 1;
				
				if(!check(moveRow, moveCol)) continue;
				if(visited[moveRow][moveCol]) continue;
				
				if(maze[moveRow][moveCol] == 1) {
					que.offer(new int[] {moveRow, moveCol, moveCnt});
					visited[moveRow][moveCol] = true;
				}
			}
		}
	}
	
	static boolean check(int r, int c) {
		return r>=0 && r<row && c>=0 && c<col; 
	}
}