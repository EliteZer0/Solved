import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] nums = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] operators = new int[4];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}
		
		calculrator(nums, operators, 1, nums[0], n);
		
		System.out.println(max);
    	System.out.println(min);
	}
	
	static void calculrator(int[] nums, int[] operators, int idx, int sum, int n) {
		if(idx == n) {
			max = Math.max(max, sum);
	    	min = Math.min(min, sum);
	    	return;
	    }
		
		for (int i = 0; i < operators.length; i++) {
			if(operators[i]>0) {
				operators[i]--;
				switch (i) {
				case 0 :	
					calculrator(nums, operators, idx+1, sum+nums[idx], n);
					break;
				case 1 :	
					calculrator(nums, operators, idx+1, sum-nums[idx], n);
					break;
				case 2 :	
					calculrator(nums, operators, idx+1, sum*nums[idx], n);
					break;
				case 3 :	
					calculrator(nums, operators, idx+1, sum/nums[idx], n);
					break;
				}
				operators[i]++;
			}
		}		
	}
}