import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited = new boolean[100001];
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		bfs(start, end);
		System.out.println(min);
	}
	
	static void bfs(int start, int end) {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {start, 0});

		while(!que.isEmpty()) {
			int[] cur = que.poll();
			
			int location = cur[0];
			int time = cur[1];
			
			visited[location] = true;
			
			if(location == end) {
				 min = Math.min(min, time);
			}
			
			int teleport = location*2;
			int forward = location+1;
			int back = location-1;
			
			if (check(teleport) && !visited[teleport]) {
				que.offer(new int[]{teleport, time});
			}
			if(check(forward) && !visited[forward]) {
				que.offer(new int[]{forward, time+1});
			}
			if(check(back) && !visited[back]) {
				que.offer(new int[]{back, time+1});
			}
		}
	}
	
	static boolean check(int n) {
		return n >= 0 && n <= 100000;
	}
}