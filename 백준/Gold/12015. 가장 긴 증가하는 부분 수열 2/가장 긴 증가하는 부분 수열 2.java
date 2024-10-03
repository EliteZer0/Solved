import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		int N = Integer.parseInt(br.readLine());
 
		int[] num = new int[N];
		int[] LIS = new int[N];
 
		StringTokenizer st = new StringTokenizer(br.readLine());
 
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
 
		LIS[0] = num[0];
		int idx = 1;
		
		for (int i = 1; i < N; i++) {
 
			int key = num[i];
			
			if (LIS[idx - 1] < key) {
				idx++;
				LIS[idx - 1] = key;
			} 
			else {
				int left = 0;
				int right = idx;
				while (left < right) {
					int mid = (left + right)/2;
					
					if(LIS[mid] < key) {
						left = mid + 1;
					}
					else {
						right = mid;
					}
 
				}				
				LIS[left] = key;
			
			}
			
		}
		System.out.println(idx);
	}
}