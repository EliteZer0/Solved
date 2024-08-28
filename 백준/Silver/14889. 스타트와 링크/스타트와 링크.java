import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] team1;
	static int[] team2;
	static int[] people;
	static int[][] score;
	static boolean[] visited;
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int r = n/2;
		people = new int[n];
		for (int i = 0; i < n; i++) {
			people[i] = i;
		}
		score = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				score[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		team1 = new int[r];
		team2 = new int[r];
		visited = new boolean[n];
		answer = Integer.MAX_VALUE;
		combination(0, 0, n, r);
		System.out.println(answer);
	}

	static void combination(int idx, int depth, int n, int r) {
		if(idx == r) {
			int size = 0;
			for (int i = 0; i < n; i++) {
	            if (!visited[i]) {
	                team2[size++] = people[i];
	            }
	        }
			calculator();
			return;
		}
		for (int i = depth; i<n; i++) {
			visited[i] = true;
			team1[idx] = people[i];
			combination(idx+1, i+1, n, r);
			team1[idx] = 0;
			visited[i] = false;
		}
	}

	private static void calculator() {
		int team1Sum = 0;
		int team2Sum = 0;
		for (int i = 0; i < team1.length; i++) {
			for (int j = 0; j < team1.length; j++) {
				team1Sum += score[team1[i]][team1[j]];
				team2Sum += score[team2[i]][team2[j]];
			}
		}
		int difference = Math.abs(team1Sum - team2Sum);
		answer = Math.min(answer, difference);
	}	
}