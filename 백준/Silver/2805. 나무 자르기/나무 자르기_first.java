import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//119476KB 2916ms
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] trees = new int[n];
		for (int i = 0; i < n; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(trees);
		long sum = 0;
		int height = trees[n-1];
		while(sum<m) {
			sum = 0;
			height--;
			for(int i = n-1; i>=0; i--) {
				if(trees[i] - height>0) {
					sum += trees[i] - height;
				}else {
					break;
				}
			}
		}
		System.out.println(height);
	}
}
