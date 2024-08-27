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
		int[] inequalityCnt = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			inequalityCnt[i] = Integer.parseInt(st.nextToken());
		}
		ArrayList<Character> addInequality = new ArrayList<>();
		for (int i = 0; i < inequalityCnt[0]; i++) {
			addInequality.add('+');
		}
		for (int i = 0; i < inequalityCnt[1]; i++) {
			addInequality.add('-');
		}
		for (int i = 0; i < inequalityCnt[2]; i++) {
			addInequality.add('*');
		}
		for (int i = 0; i < inequalityCnt[3]; i++) {
			addInequality.add('/');
		}
		int size = 0;
		char[] inequality = new char[addInequality.size()];
		for(char temp : addInequality) {
			inequality[size++] = temp;
		}
		permutation(nums, inequality, 0, inequality.length, inequality.length);
		System.out.println(max);
    	System.out.println(min);
	}
	
	static void permutation(int[] nums, char[] inequality, int depth, int n, int r) {
	    if (depth == r) {
	    	int[] claNums = Arrays.copyOf(nums, nums.length);
	    	for (int i = 0; i < inequality.length; i++) {
				if(inequality[i] == '+') {
					claNums[i+1] = claNums[i]+claNums[i+1];
				}
				if(inequality[i] == '-') {
					claNums[i+1] = claNums[i]-claNums[i+1];
				}
				if(inequality[i] == '*') {
					claNums[i+1] = claNums[i]*claNums[i+1];
				}
				if(inequality[i] == '/') {
					if(claNums[i] < 0) {
						claNums[i+1] = -((-claNums[i])/claNums[i+1]);
					}else {
						claNums[i+1] = claNums[i]/claNums[i+1];
					}
				}
			}
	    	max = Math.max(max, claNums[inequality.length]);
	    	min = Math.min(min, claNums[inequality.length]);
	        return;
	    }
	 
	    for (int i=depth; i<n; i++) {
	        swap(inequality, depth, i);
	        permutation(nums, inequality, depth + 1, n, r);
	        swap(inequality, depth, i);
	    }
	}
	 
	static void swap(char[] inequality, int depth, int i) {
	    char temp = inequality[depth];
	    inequality[depth] = inequality[i];
	    inequality[i] = temp;
	}
}