import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] a = br.readLine().toCharArray();
		char[] b = br.readLine().toCharArray();
		int[][] LCS = new int[a.length+1][b.length+1];
		for (int i = 1; i < a.length+1; i++) {
			for (int j = 1; j < b.length+1; j++) {
				if(a[i-1]==b[j-1]) {
					LCS[i][j] = LCS[i-1][j-1]+1;
				}else {
					LCS[i][j] = Math.max(LCS[i][j-1], LCS[i-1][j]);
				}
			}
		}
		System.out.println(LCS[a.length][b.length]);
	}
}