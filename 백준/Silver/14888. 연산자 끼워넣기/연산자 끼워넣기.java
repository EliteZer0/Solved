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
		int[] operatorsCnt = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operatorsCnt[i] = Integer.parseInt(st.nextToken());
		}
		ArrayList<Character> addOperators = new ArrayList<>();
		for (int i = 0; i < operatorsCnt[0]; i++) {
			addOperators.add('+');
		}
		for (int i = 0; i < operatorsCnt[1]; i++) {
			addOperators.add('-');
		}
		for (int i = 0; i < operatorsCnt[2]; i++) {
			addOperators.add('*');
		}
		for (int i = 0; i < operatorsCnt[3]; i++) {
			addOperators.add('/');
		}
		int size = 0;
		char[] operators = new char[addOperators.size()];
		for(char c : addOperators) {
			operators[size++] = c;
		}
		permutation(nums, operators, 0, operators.length);
		System.out.println(max);
    	System.out.println(min);
	}
	
	static void permutation(int[] nums, char[] operators, int depth, int n) {
	    if (depth == n) {
	    	int[] claNums = Arrays.copyOf(nums, nums.length);
	    	for (int i = 0; i < operators.length; i++) {
				if(operators[i] == '+') {
					claNums[i+1] = claNums[i]+claNums[i+1];
				}
				if(operators[i] == '-') {
					claNums[i+1] = claNums[i]-claNums[i+1];
				}
				if(operators[i] == '*') {
					claNums[i+1] = claNums[i]*claNums[i+1];
				}
				if(operators[i] == '/') {
					if(claNums[i] < 0) {
						claNums[i+1] = -((-claNums[i])/claNums[i+1]);
					}else {
						claNums[i+1] = claNums[i]/claNums[i+1];
					}
				}
			}
	    	max = Math.max(max, claNums[operators.length]);
	    	min = Math.min(min, claNums[operators.length]);
	        return;
	    }
	 
	    for (int i=depth; i<n; i++) {
	        swap(operators, depth, i);
	        permutation(nums, operators, depth + 1, n);
	        swap(operators, depth, i);
	    }
	}
	 
	static void swap(char[] operators, int depth, int i) {
	    char c = operators[depth];
	    operators[depth] = operators[i];
	    operators[i] = c;
	}
}