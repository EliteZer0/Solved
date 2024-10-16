import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Fish{
		int no;
		int dir;
		int r;
		int c;
		boolean isAlive = true;
		
		public Fish(int no, int dir, int r, int c, boolean isAlive) {
			this.no = no;
			this.dir = dir;
			this.r = r;
			this.c = c;
			this.isAlive = isAlive;
		}		
	}
	
	static class Shark{
		int dir;
		int r;
		int c;
		int eat;
		
		public Shark(int dir, int r, int c, int eat) {
			this.dir = dir;
			this.r = r;
			this.c = c;
			this.eat = eat;
		}
	}
	
	static int maxEat;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] map = new int[4][4];
		Fish[] fishList = new Fish[17];
		
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int no = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) -1;
				
				fishList[no] = new Fish(no, dir, i, j, true);
				map[i][j] = no;
			}
		}
			
		Fish start = fishList[map[0][0]];
		Shark shark = new Shark(start.dir, 0, 0, start.no);
		start.isAlive = false;
		map[0][0] = -1;
		
		maxEat = 0;
		moveShark(map, shark, fishList);
		System.out.println(maxEat);
	}
	
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; //북 북서 서 남서 남 남동 동 북동
	static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
	
	private static void moveShark(int[][] map, Shark shark, Fish[] fishList) {
		maxEat = Math.max(maxEat, shark.eat);
		
		moveFish(map, fishList);
		
		for (int move = 1; move < 4; move++) {//상어는 끝까지 이동할 수 있음. 최대 3칸 더 감
			int nr = shark.r + (dr[shark.dir]*move);
			int nc = shark.c + (dc[shark.dir]*move);
			
			if(!check(nr, nc)) continue;
			if(map[nr][nc] == 0) continue;
			
			int[][] copyMap = CopyMap(map);
			Fish[] copyFish = CopyFish(fishList);
			
			int fishNo = copyMap[nr][nc];
			Fish fish = copyFish[fishNo];
			Shark curShark = new Shark(fish.dir, fish.r, fish.c, shark.eat+fish.no);
			
			copyMap[shark.r][shark.c] = 0;//상어 자리 빈칸
			copyMap[nr][nc] = -1;//물고기 먹은 자리가 현재 상어 위치
			fish.isAlive = false;
			
			moveShark(copyMap, curShark, copyFish);
		}
	}

	private static void moveFish(int[][] map, Fish[] fishList) {
		for (int i = 1; i < 17; i++) {
			Fish fish = fishList[i];
			if(!fish.isAlive) continue;
			
			for (int d = 0; d < 8; d++) {
				int newDir = (fish.dir + d) % 8;
				int nr = fish.r+dr[newDir];
				int nc = fish.c+dc[newDir];
				
				if(!check(nr, nc)) continue;
				if(map[nr][nc] == -1) continue;
				
				if (map[nr][nc] == 0) {//빈칸일 때
					map[fish.r][fish.c] = 0;
					map[nr][nc] = fish.no;
				}else {//자리교환
					Fish temp = fishList[map[nr][nc]];
					temp.r = fish.r;
					temp.c = fish.c;
					
					map[fish.r][fish.c] = temp.no;
					map[nr][nc] = fish.no;
				}
				
				fish.r = nr;
				fish.c = nc;
				fish.dir = newDir;
				
				break;
			}
		}
	}
	
	private static int[][] CopyMap(int[][] arr){
		int[][] temp = new int[4][4];
		
		for (int i = 0; i < 4; i++) {
			System.arraycopy(arr[i], 0, temp[i], 0, 4);
		}
		
		return temp;
	}

	private static Fish[] CopyFish(Fish[] fishList) {
		Fish[] temp = new Fish[17];
	    for (int i = 1; i<17; i++) {
	    	Fish fish = fishList[i];
	    	temp[i] = new Fish(fish.no, fish.dir, fish.r, fish.c, fish.isAlive);
	    }
	    return temp;
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<4 && c>=0 && c<4;
	}

}