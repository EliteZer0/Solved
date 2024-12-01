import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			sb.append("Case").append(" ").append("#")
			.append(tc).append(":").append(" ").append(A).append(" ")
			.append("+").append(" ").append(B).append(" ").append("=").append(" ")
			.append(A+B);
			
			System.out.println(sb.toString());
		}
	}
}