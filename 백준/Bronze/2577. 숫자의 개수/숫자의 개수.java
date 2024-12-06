import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int A = Integer.parseInt(br.readLine());
		int B = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());
		
		String times = Integer.toString(A*B*C);
		
		char[] c = times.toCharArray();
		int[] cnt = new int[10];

		for (int i = 0; i < c.length; i++) {
			cnt[c[i]-48]++;
		}
		for (int i = 0; i < cnt.length; i++) {
			sb.append(cnt[i]).append("\n");
		}
		System.out.println(sb.toString());
	}
}