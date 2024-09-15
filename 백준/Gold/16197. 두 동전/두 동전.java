import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int row, col, btnCnt, minBtnCnt;
	static char[][] board;
	static boolean[][][][] visited;
	static Queue<int[]> coinState;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		board = new char[row][col];
		visited = new boolean[row][col][row][col];
		coinState = new LinkedList<>();
		int[] coin1 = null; 
		int[] coin2 = null;
		
		for (int i = 0; i < row; i++) {
			board[i] = br.readLine().toCharArray();
			for (int j = 0; j < col; j++) {
				if(board[i][j] == 'o') {
					if(coin1 == null) {
						coin1 = new int[] {i, j};
					}else {
						coin2 = new int[] {i, j};
					}
				}
			}
		}
		coinState.add(new int[] {coin1[0], coin1[1], coin2[0], coin2[1], 0});
		visited[coin1[0]][coin1[1]][coin2[0]][coin2[1]] = true;
		minBtnCnt = Integer.MAX_VALUE;
		
		bfs();
		
		if(minBtnCnt == Integer.MAX_VALUE || btnCnt == 10) {
			System.out.println(-1);
		}else {
			System.out.println(minBtnCnt);
		}
	}
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static void bfs() {
		
		while(!coinState.isEmpty()) {			
			int[] state = coinState.poll();
			
			int r1 = state[0];
			int c1 = state[1];
			int r2 = state[2];
			int c2 = state[3];
			
			btnCnt = state[4];
			
			if(btnCnt>=10) continue;
			
			for (int d = 0; d < 4; d++) {
				int nr1 = r1+dr[d];
				int nc1 = c1+dc[d];
				int nr2 = r2+dr[d];
				int nc2 = c2+dc[d];
				
				if(isEscape(nr1, nc1) && isEscape(nr2, nc2)) continue;
				if(isEscape(nr1, nc1) || isEscape(nr2, nc2)) {
					minBtnCnt = Math.min(minBtnCnt, btnCnt+1);
					return;
				}
				
				if(board[nr1][nc1] == '#') {
					nr1 = r1;
					nc1 = c1;
				}
				
				if(board[nr2][nc2] == '#') {
					nr2 = r2;
					nc2 = c2;
				}
                
				if(!visited[nr1][nc1][nr2][nc2]) {
					visited[nr1][nc1][nr2][nc2] = true;
					coinState.add(new int[] {nr1, nc1, nr2, nc2, btnCnt+1});
				}
			}
		}
	}

	static boolean isEscape(int r, int c) {
		return !(r>=0 && r<row && c>=0 && c<col);
	}
}