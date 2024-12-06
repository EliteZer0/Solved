import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String input = br.readLine();
		char[] c = input.toCharArray();
		int[] cnt = new int[26];
		for (int i = 0; i < c.length; i++) {
			cnt[c[i]-97]++;
		}
		for (int i = 0; i < cnt.length; i++) {
			sb.append(cnt[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
}