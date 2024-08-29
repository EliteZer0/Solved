import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int max;
	static int min;
	static int n;
	static int[] nums;
	static int[] operators; 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		n = Integer.parseInt(br.readLine());
		nums = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());	
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		operators = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}
		
		calculrator(1, nums[0]);
		System.out.println(max);
    	System.out.println(min);
	}
	
	static void calculrator(int idx, int sum) {
	    if (idx == n) {
	    	max = Math.max(max, sum);
	    	min = Math.min(min, sum);
	        return;
	    }
	 
	    for (int i = 0; i < operators.length; i++) {
			if(operators[i] > 0) {
				operators[i] --;
				if(i == 0) {
					calculrator(idx+1, sum+nums[idx]);
				}
				if(i == 1) {
					calculrator(idx+1, sum-nums[idx]);
				}
				if(i == 2) {
					calculrator(idx+1, sum*nums[idx]);
				}
				if(i == 3) {
					calculrator(idx+1, sum/nums[idx]);
				}
				operators[i] ++;
			}
		}
	}
}