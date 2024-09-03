import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static boolean visited[];
	static int[] time;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		visited = new boolean[100001];
		time = new int[100001];
		bfs(start, end);
		
		System.out.println(time[end]);
	}
	
	static void bfs(int start, int end) {
		Queue<Integer> que = new LinkedList<Integer>();
		que.offer(start);
		visited[start] = true;
		time[start] = 0;
		
		while(!que.isEmpty() && !visited[end]) {
			int cur = que.poll();
			
			int teleport = cur*2;
			int foward = cur+1;
			int back = cur-1;
			
			if(check(teleport) && !visited[teleport]) {
				que.offer(teleport);
				visited[teleport] = true;
				time[teleport]=time[cur]+1;
			}
			if(check(foward) && !visited[foward]) {
				que.offer(foward);
				visited[foward] = true;
				time[foward]=time[cur]+1;
			}
			if(check(back) && !visited[back]) {
				que.offer(back);
				visited[back] = true;
				time[back]=time[cur]+1;
			}
		}
	}
	
	static boolean check(int n) {
		return n >= 0 && n <= 100000;
	}
}