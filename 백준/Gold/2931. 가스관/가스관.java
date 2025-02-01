import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * .을 기준으로 상에 |, +, 1, 4가 하에 |, +, 2, 3가 있는 경우 : |
 * .을 기준으로 좌에 -, +, 1, 2가 우에 -, +, 3, 4가 있는 경우  : -
 * .을 기준으로 상에 |, +, 1, 4가 하에 |, +, 2, 3가 좌에 -, +, 1, 2가 우에 -, +, 3, 4가 있는 경우 : +
 * .을 기준으로 하에 |, +, 2, 3가 우에 -, +, 3, 4가 있는 경우 : 1
 * .을 기준으로 상에 |, +, 1, 4가 우에 -, +, 3, 4가 있는 경우 : 2
 * .을 기준으로 상에 |, +, 1, 4가 좌에 -, +, 1, 2가 있는 경우 : 3
 * .을 기준으로 하에 |, +, 2, 3가 좌에 -, +, 1, 2가 있는 경우 : 4
 */
public class Main {
	static char[][] map;
	static int r, c;
	static char[] up = {'|', '+', '1', '4'};
	static char[] down = {'|', '+', '2', '3'};
	static char[] left = {'-', '+', '1', '2'};
	static char[] right = {'-', '+', '3', '4'};	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new char[r][c];
		
		for (int i = 0; i < r; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int[] dr = {-1, 1, 0, 0}; // 상하좌우
		int[] dc = {0, 0, -1, 1}; // 상하좌우
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(map[i][j]=='.') {
					int[] moveUp = {i+dr[0], j+dc[0]};
					int[] moveDown = {i+dr[1], j+dc[1]};
					int[] moveLeft = {i+dr[2], j+dc[2]};
					int[] moveRight = {i+dr[3], j+dc[3]};
					
					// +
					if(check(moveUp[0], moveUp[1]) && check(moveDown[0], moveDown[1]) && 
							check(moveLeft[0], moveLeft[1]) && check(moveRight[0], moveRight[1])) {
						if(contains(up, map[moveUp[0]][moveUp[1]]) && 
								contains(down, map[moveDown[0]][moveDown[1]]) &&
								contains(left, map[moveLeft[0]][moveLeft[1]]) && 
								contains(right, map[moveRight[0]][moveRight[1]])) {
							sb.append(i+1).append(" ").append(j+1).append(" ").append('+');
							break;
						}
					}
					
					// |
					if(check(moveUp[0], moveUp[1]) && check(moveDown[0], moveDown[1])) {
						if(contains(up, map[moveUp[0]][moveUp[1]]) && 
								contains(down, map[moveDown[0]][moveDown[1]])){
							sb.append(i+1).append(" ").append(j+1).append(" ").append('|');
							break;
						}
					}
					
					// -
					if(check(moveLeft[0], moveLeft[1]) && check(moveRight[0], moveRight[1])) {
						if(contains(left, map[moveLeft[0]][moveLeft[1]]) && 
								contains(right, map[moveRight[0]][moveRight[1]])) {
							sb.append(i+1).append(" ").append(j+1).append(" ").append('-');
							break;
						}
					}
					
					// 1번
					if(check(moveDown[0], moveDown[1]) && check(moveRight[0], moveRight[1])) {
						if(contains(down, map[moveDown[0]][moveDown[1]]) && 
								contains(right, map[moveRight[0]][moveRight[1]])) {
							sb.append(i+1).append(" ").append(j+1).append(" ").append(1);
							break;
						}
					}
					
					// 2번
					if(check(moveUp[0], moveUp[1]) && check(moveRight[0], moveRight[1])) {
						if(contains(up, map[moveUp[0]][moveUp[1]]) && 
								contains(right, map[moveRight[0]][moveRight[1]])) {
							sb.append(i+1).append(" ").append(j+1).append(" ").append(2);
							break;
						}
					}
					// 3번
					if(check(moveUp[0], moveUp[1]) && check(moveLeft[0], moveLeft[1])) {
						if(contains(up, map[moveUp[0]][moveUp[1]]) && 
								contains(left, map[moveLeft[0]][moveLeft[1]])) {
							sb.append(i+1).append(" ").append(j+1).append(" ").append(3);
							break;
						}
					}
					// 4번
					if(check(moveDown[0], moveDown[1]) && check(moveLeft[0], moveLeft[1])) {
						if(contains(down, map[moveDown[0]][moveDown[1]]) && 
								contains(left, map[moveLeft[0]][moveLeft[1]])) {
							sb.append(i+1).append(" ").append(j+1).append(" ").append(4);
							break;
						}
					}
				}
			}
		}
		System.out.println(sb.toString());
	}
	
	private static boolean check(int row, int col) {
		return row>=0 && row<r && col>=0 && col<c;
	}
	
	private static boolean contains(char[] arr, char target) {
	    for (char c : arr) {
	        if (c == target) {
	            return true;
	        }
	    }
	    return false;
	}
}