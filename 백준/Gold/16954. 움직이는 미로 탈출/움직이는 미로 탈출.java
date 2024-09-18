import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int n;
	static char[][] map;
	static boolean[][] visited;
//	static boolean canEscape;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = 8;
		map = new char[n][n];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		visited = new boolean[n][n];
//		canEscape = false;
		escapeMaze();
	}
	
	static int[] dr = {0, -1, -1, 0, 1, 1, 1, 0, -1};//제자리, 북쪽부터 시계방향
	static int[] dc = {0, 0, 1, 1, 1, 0, -1, -1, -1};
	
	static void escapeMaze() {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {7, 0});
		while(!que.isEmpty()) {
			int size = que.size();
            visited = new boolean[n][n];
            //visited 초기화 문제때문에 맵 변경 사이클마다 초기화할 수 있도록 묶음.
			for (int i = 0; i < size; i++) {
				int[] cur = que.poll();
				int row = cur[0];
				int col = cur[1];
				//벽이 캐릭터가 있는 칸으로 이동하면 더 이상 캐릭터는 이동할 수 없다.
				if(map[row][col] == '#') continue;
				//맵에 벽이 없으면 종료
				if(!isAnyWall()) {
					//canEscape = true;
					System.out.println(1);
					return;
				}
				//도착지에 도착하면 종료
				if (row == 0 && col == 7) {
					//canEscape = true;
	                System.out.println(1);
	                return;
	            }
				//9방 탐색
				for (int d = 0; d < 9; d++) {
					int nr = row+dr[d];
					int nc = col+dc[d];
					
					if(!check(nr, nc)) continue;
					if(map[nr][nc] == '#') continue;
					if(visited[nr][nc]) continue;
					
					que.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
			moveWall();
		}
		System.out.println(0);
	}

	static void moveWall() {
		for (int i=7; i>=0; i--) {
            for (int j=0; j<8; j++) {
                if (map[i][j] == '#') {
                    if (i != 7) {
                        map[i+1][j] = '#';
                    }
                    map[i][j] = '.';
                }
            }
        }
	}
	
	static boolean check(int r, int c) {
		return r>=0 && r<n && c>=0 && c<n;
	}
	
	static boolean isAnyWall() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(map[i][j] == '#') return true;
			}
		}
		return false;
	}
}