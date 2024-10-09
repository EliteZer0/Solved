import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] score;
	static boolean[] visited;
	static int n, answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		score = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				score[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[n];
		answer = Integer.MAX_VALUE;
		combination(0, 0);
		System.out.println(answer);
	}

	static void combination(int idx, int depth) {
		if(depth > 0) {
			answer = Math.min(answer, cal());
			if(answer == 0) {
				return;
			}
		}
		
		for (int i = idx; i<visited.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				combination(i+1, depth+1);
				visited[i] = false;
			}
		}
	}
	
	static int cal() {
		int team1Sum = 0;
		int team2Sum = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(visited[i] && visited[j]) {
					team1Sum += score[i][j];
				}else if(!visited[i] &&!visited[j]) {
					team2Sum += score[i][j];
				}
			}
		}
		return Math.abs(team1Sum - team2Sum);
	}
}