import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int ladder, snake, minRoll;
	static ArrayList<Integer> ladderListStart, snakeListStart, ladderListEnd, snakeListEnd;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		ladder = Integer.parseInt(st.nextToken());
		snake = Integer.parseInt(st.nextToken());
		
		ladderListStart = new ArrayList<>();
		ladderListEnd = new ArrayList<>();
		snakeListStart = new ArrayList<>();
		snakeListEnd = new ArrayList<>();
		visited = new boolean[101];
		
		for (int i = 0; i < ladder; i++) {
			st = new StringTokenizer(br.readLine());
			ladderListStart.add(Integer.parseInt(st.nextToken()));
			ladderListEnd.add(Integer.parseInt(st.nextToken()));
		}
		
		for (int i = 0; i < snake; i++) {
			st = new StringTokenizer(br.readLine());
			snakeListStart.add(Integer.parseInt(st.nextToken()));
			snakeListEnd.add(Integer.parseInt(st.nextToken()));
		}
		
		minRoll = Integer.MAX_VALUE;
		
		bfs();
		
		System.out.println(minRoll);
	}

	private static void bfs() {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {1, 0});
		visited[1] = true;
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int loca = cur[0];
			int roll = cur[1];
			
			if(loca == 100) {
				minRoll = Math.min(minRoll, roll);
				return;
			}
			
			for (int i = 1; i <= 6; i++) {
				int moveLoca = loca + i;
				
				if(moveLoca>100) continue;
				if(visited[moveLoca]) continue;
				
				if(ladderListStart.contains(moveLoca)) {
					int idx = ladderListStart.indexOf(moveLoca);
					moveLoca = ladderListEnd.get(idx);
				} else if(snakeListStart.contains(moveLoca)) {
					int idx = snakeListStart.indexOf(moveLoca);
					moveLoca = snakeListEnd.get(idx);
				}
				
				if(!visited[moveLoca]) {
					visited[moveLoca] = true;
					que.offer(new int[] {moveLoca, roll+1});
				}
			}
		}
	}
}