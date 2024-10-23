import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static char[][] map;
	static int answer;
	static boolean[] visited;
	static int[] select;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[5][5];
		
		for (int i = 0; i < 5; i++) {
			map[i] = br.readLine().toCharArray();
		}
		answer = 0;
		//학생 수 총 25명
		select = new int[25];
		combi(0, 0, 0);
		System.out.println(answer);
	}
	
	private static void combi(int depth, int selectY, int start) {
		//이다솜이 4명 이상이어야하니까
		if(selectY > 3) return;
		
		if(depth == 7) {
			visited = new boolean[7];
			bfs();
			return;
		}
		//25C7
		for (int i = start; i < 25; i++) {
			select[depth] = i;
			//행은 5로 나눈 몫, 열은 5로 나눈 나머지
			if(map[i/5][i%5] == 'Y') {
				combi(depth+1, selectY+1, i+1);
			}
			else {
				combi(depth+1, selectY, i+1);
			}
		}
	}
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {select[0]/5, select[0]%5});//select[0]에 들어간 값 2차원 배열 좌표로 변환
		visited[0] = true;
		
		int count = 1;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			for (int d = 0; d < 4; d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(!check(nr, nc)) continue;
				
				for (int i = 0; i < 7; i++) {
					if(visited[i]) continue;
					//2차원 배열 좌표 1차원 배열 인덱스로 변환
					if(select[i] != nr*5+nc) continue;
					
					q.offer(new int[] {nr, nc});
					visited[i] = true;
					count++;
				}
			}
		}
		if(count == 7) answer++;
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<5 && c>=0 && c<5;
	}
}