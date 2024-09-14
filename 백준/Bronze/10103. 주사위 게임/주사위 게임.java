import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int p1Score = 100;
		int p2Score = 100;
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p1Dice = Integer.parseInt(st.nextToken());
			int p2Dice = Integer.parseInt(st.nextToken());
			
			if(p1Dice>p2Dice) p2Score -= p1Dice;
			if(p1Dice<p2Dice) p1Score -= p2Dice;
		}
		sb.append(p1Score).append("\n").append(p2Score);
		System.out.println(sb.toString());
	}
}