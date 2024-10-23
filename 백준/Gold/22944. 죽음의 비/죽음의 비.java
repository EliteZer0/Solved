import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class UserInfo {
	int r, c, hp, umbDurability, distance;

	public UserInfo(int r, int c, int hp, int umbDurability, int distance) {
		this.r = r;
		this.c = c;
		this.hp = hp;
		this.umbDurability = umbDurability;
		this.distance = distance;
	}
}

public class Main {
	static int N, H, D;
	static char[][] map;
	static UserInfo start;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new char[N][N]; 
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 'S') {
					start = new UserInfo(i, j, H, 0, 0);
				}
			}
		}
		
		int sToE = startToEnd();
		int sWithU = userWithUmb();
		
		if(sToE != -1 && sWithU != -1) {
			System.out.println(Math.min(sToE, sWithU));
		}
		else if(sToE != -1 && sWithU == -1) {
			System.out.println(sToE);
		}
		else if(sToE == -1 && sWithU != -1) {
			System.out.println(sWithU);
		}
		else {
			System.out.println(-1);
		}
	}
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	private static int startToEnd() {
		boolean[][] visited = new boolean[N][N];
		Queue<UserInfo> q = new LinkedList<>();
		q.add(start);
		
		while(!q.isEmpty()) {
			UserInfo user = q.poll();
			int r = user.r;
			int c = user.c;
			int hp = user.hp;
			int distance = user.distance;
			
			for (int d = 0; d < 4; d++) {
				int cr = r+dr[d];
				int cc = c+dc[d];
				int chp = hp-1;
				
				if(!check(cr, cc)) continue;
				if(visited[cr][cc]) continue; 
				if(chp == 0 && map[cr][cc] != 'E') continue;
				if(map[cr][cc] == 'E') {
					return distance+1;
				}
				q.offer(new UserInfo(cr, cc, chp, 0, distance+1));
				visited[cr][cc] = true;
			}
		}
		return -1;
	}
	
	private static int userWithUmb() {
		//2차원으로 두고 조건문 처리 해보고, 3차원으로도 시도해봤는데 안 돼서 4차원으로 하니까 메모리 초과
		//3차원으로 두고 우산 가지고 있는지를 0, 1로 표현해봄
		//근데 67퍼에서 틀리네...
		//visited 대신 체력+우산 내구도 저장하는 int 배열 사용해봄
		int[][] check = new int[N][N];
	    Queue<UserInfo> q = new LinkedList<>();
	    q.add(start);
	    check[start.r][start.c] = H;
	    
	    while (!q.isEmpty()) {
	        UserInfo user = q.poll();
	        int r = user.r;
	        int c = user.c;
	        int hp = user.hp;
	        int umbDurability = user.umbDurability;
	        int distance = user.distance;
	        
	        for (int d = 0; d < 4; d++) {
	            int cr = r + dr[d];
	            int cc = c + dc[d];
	            int chp = hp;
	            int cumbDurability = umbDurability;
	            
	            if (!check(cr, cc)) continue;
	            
	            // 목적지 도달 시 거리 반환
	            if (map[cr][cc] == 'E') {
	                return distance + 1;
	            }
	            
	            // 우산을 발견했을 때
	            // 이 조건이 먼저 들어가야 체력처리할 때 오류가 없음.
	            // 우산이 있는 곳에도 비가 내린다는 사실을 잊지 말자.
	            if (map[cr][cc] == 'U') {
	                cumbDurability = D; // 새로운 우산 내구도로 업데이트
	            }
	            
	            // 우산 내구도가 0이면 체력 감소
	            if (cumbDurability > 0) {
	                cumbDurability -= 1;
	            } else {
	            	chp -= 1;
	            }
	            
	            if (chp <= 0 && map[cr][cc] != 'E') continue; // 체력이 0 이하이면 이동 불가
	            
	            // 생존 가능성이 더 높을 때만 방문
	            if (check[cr][cc] < chp + cumbDurability) {
	                check[cr][cc] = chp + cumbDurability;
	                q.offer(new UserInfo(cr, cc, chp, cumbDurability, distance + 1));
	            }
	        }
	    }
	    return -1;
	}


	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
}