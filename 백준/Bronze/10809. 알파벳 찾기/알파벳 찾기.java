import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String input = br.readLine();
		char[] c = input.toCharArray();
		int[] loca = new int[26];
		Arrays.fill(loca, -1);
		for (int i = 0; i < c.length; i++) {
			if(loca[c[i]-97]==-1) {
				loca[c[i]-97] = i;
			}
		}
		for (int i = 0; i < loca.length; i++) {
			sb.append(loca[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
}