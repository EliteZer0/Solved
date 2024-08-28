import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int r;
	static int[] numList;
	static int[] lottoNum;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = 6;
		while(n != 0) {
			numList = new int[n];
			for (int i = 0; i < n; i++) {
				numList[i] = Integer.parseInt(st.nextToken());
			}
			lottoNum = new int[r];
			visited = new boolean[n];
			combination(0,0);
			System.out.println();
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
		}
	}
	
	static void combination(int idx, int start) {
		if(idx == r) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < lottoNum.length; i++) {
				sb.append(lottoNum[i]).append(" ");
			}
			System.out.println(sb.toString());
			return;
		}
		for (int i = start; i<n; i++) {
			visited[i] = true;
			lottoNum[idx] = numList[i];
			combination(idx+1, i+1);
			lottoNum[idx] = 0;
			visited[i] = false;
		}
	}	
}