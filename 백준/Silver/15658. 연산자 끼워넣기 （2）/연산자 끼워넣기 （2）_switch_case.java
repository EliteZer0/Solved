import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//14888번 로직이 전부다 시간초과로 터져서 아예 새로운 방법으로 시도함.
public class Main_15658_switch_case {
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
		operators = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}
		
		calculrator(1, nums[0]);//합 무조건 첫번째 항부터 시작이라서 한번이라도 덜 돌려고 이렇게 설정.
		
		System.out.println(max);
    	System.out.println(min);
	}
	
	static void calculrator(int idx, int sum) {
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
					calculrator(idx+1, sum+nums[idx]);
					break;
				case 1 :	
					calculrator(idx+1, sum-nums[idx]);
					break;
				case 2 :	
					calculrator(idx+1, sum*nums[idx]);
					break;
				case 3 :	
					calculrator(idx+1, sum/nums[idx]);
					break;
				}
				operators[i]++;
			}
		}		
	}
}
