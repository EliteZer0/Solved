import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int row, col, maxSum;
	private static int[][] paper;
	private static boolean[][] visited;	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		paper = new int[row][col];
		visited = new boolean[row][col];
				
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				visited[i][j] = true;
				dfs(i, j, paper[i][j], 1);
				visited[i][j] = false;
			}
		}
		
		System.out.println(maxSum);
	}
	
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	
	static void dfs(int r, int c, int sum, int cnt) {
		if(cnt == 4) {
			maxSum = Math.max(sum, maxSum);
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			int nCnt = cnt+1;
			
			if(!check(nr, nc)) continue;
			if(visited[nr][nc]) continue;
			if(cnt == 2) {
				visited[nr][nc] = true;
				dfs(r, c, sum + paper[nr][nc], cnt + 1);
				visited[nr][nc] = false;
			}

			visited[nr][nc] = true;
			dfs(nr, nc, sum + paper[nr][nc], cnt + 1);
			visited[nr][nc] = false;
		}
	}
	
	private static boolean check(int r, int c) {
		return r>=0 && r<row && c>=0 && c<col;
	}
}