import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc < t+1; tc++) {
			StringBuilder sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			String command = st.nextToken();
			
			if(command.equals("C")) {
				String str = br.readLine().replace(" ", "");
				char[] input = str.toCharArray();
				
				for (int i = 0; i < m; i++) {
					sb.append(input[i] - 64).append(" ");
				}
			}
			if(command.equals("N")) {
				st = new StringTokenizer(br.readLine());
				for (int i = 0; i < m; i++) {
					int answer = Integer.parseInt(st.nextToken()) + 64;
					sb.append((char)answer).append(" ");
				}
			}
			System.out.println(sb.toString());
		}
	}
}