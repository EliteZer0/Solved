import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int r;
	static int[] nums;
	static int[] choice;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		nums = new int[n];
		
		for (int i = 0; i < n; i++) {
			nums[i] = i+1;
		}
		
		choice = new int[r];
		visited = new boolean[n];
		
		perm(0);
	}

	static void perm(int idx) {
		StringBuilder sb = new StringBuilder();
		if(idx == r) {
			for (int i = 0; i < choice.length; i++) {
				sb.append(choice[i]).append(" ");
			}
			System.out.println(sb.toString());
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choice[idx] = nums[i]; 
				perm(idx+1);
				visited[i] = false;
			}
		}
	}
}