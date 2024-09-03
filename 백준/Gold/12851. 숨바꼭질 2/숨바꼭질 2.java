import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int visited[];
	static int time;
	static int count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		visited = new int[100001];
		bfs(start, end);
		
		System.out.println(time);
		System.out.println(count);
	}
	
	static void bfs(int start, int end) {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {start, 0});
		visited[start] = 1;
		time = 0;
		count = 0;

		while(!que.isEmpty()) {
			int[] cur = que.poll();
			
			if(cur[0] == end) {
				if (time == 0) {
					time = cur[1];
				}
				if(time == cur[1]) {
					count++;
				}
				continue;
			}
			
			int teleport = cur[0]*2;
			int foward = cur[0]+1;
			int back = cur[0]-1;
			
			if(check(teleport) && (visited[teleport] == 0 || visited[teleport] == cur[1]+1)) {
				que.offer(new int[]{teleport, cur[1]+1});
				visited[teleport] = cur[1]+1;
			}
			if(check(foward) && (visited[foward] == 0 || visited[foward] == cur[1]+1)) {
				que.offer(new int[]{foward, cur[1]+1});
				visited[foward] = cur[1]+1;
			}
			if(check(back) && (visited[back] == 0 || visited[back] == cur[1]+1)) {
				que.offer(new int[]{back, cur[1]+1});
				visited[back] = cur[1]+1;
			}
		}
	}
	
	static boolean check(int n) {
		return n >= 0 && n <= 100000;
	}
}