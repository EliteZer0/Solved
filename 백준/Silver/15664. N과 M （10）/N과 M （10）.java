import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int r;
	static int[] nums;
	static int[] choice;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		nums = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
		
		choice = new int[r];
		
		sb = new StringBuilder();
		
		combi(0,0);
		
		System.out.println(sb.toString());
	}

	static void combi(int idx, int start) {
		if(idx == r) {
			for (int i = 0; i < choice.length; i++) {
				sb.append(choice[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		int before = 0;
		for (int i = start; i < n; i++) {
			if(before != nums[i]) {
				choice[idx] = nums[i]; 
				before = nums[i]; 
				combi(idx+1, i+1);
			}
		}
	}
}