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
		int l = Integer.parseInt(st.nextToken());
		
		int[] rest = new int[n+2];
		rest[0]= 0;
		rest[n+1]= l;
		
		st = new StringTokenizer(br.readLine());
		
		if(n>0) {			
			for (int i = 1; i <= n; i++) {
				rest[i] = Integer.parseInt(st.nextToken());
			}
		}
		
		Arrays.sort(rest);
		
		int left = 1;
		int right = l-1;
		int ans = 0;
		
		while(left<=right) {
			 int mid = (left+right)/2;
			 int cnt = count(mid,rest);
			 if(cnt>m) {
				 left = mid+1;
			 }else {
				 right = mid-1;
				 ans = mid;
			 }
		}
		System.out.println(ans);
	}

	private static int count(int mid, int[] rest) {
		int cnt = 0;
		for(int i = 0; i<rest.length-1; i++) {
			int temp = rest[i+1]-rest[i];
			cnt += temp/mid;
			if(temp%mid == 0) {
				cnt--;
			}
		}
		return cnt;
	}
}