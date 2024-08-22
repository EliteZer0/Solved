import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] trees = new int[n];
		int max = 0;
		for (int i = 0; i < n; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, trees[i]);
		}
		
		long sum = 0;
		int min = 0;
		
		while(min < max) {
			int mid = (min+max)/2;
			sum = 0;
			for(int treeHeight : trees) {
				if(treeHeight-mid>0) {
					sum += treeHeight-mid;
				}
			}
			if(sum<m) {
				max = mid;
			}else {
				min = mid+1;
			}
		}
		
		System.out.println(min-1);
	}
}