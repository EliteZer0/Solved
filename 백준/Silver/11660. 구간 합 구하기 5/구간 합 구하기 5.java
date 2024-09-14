import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer nm = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(nm.nextToken());
		int m = Integer.parseInt(nm.nextToken());
		int[][] table = new int[n+1][n+1];
		int[][] slidingWindow = new int[n+1][n+1];

		for (int i = 1; i <= n; i++) {
			StringTokenizer row = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				table[i][j] = Integer.parseInt(row.nextToken());
			}
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				slidingWindow[i][j] = table[i][j] 
				                + slidingWindow[i-1][j] 
				                + slidingWindow[i][j-1] 
				                - slidingWindow[i-1][j-1];
			}
		}

		for (int i = 0; i < m; i++) {
			StringTokenizer coordinate = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(coordinate.nextToken());
			int y1 = Integer.parseInt(coordinate.nextToken());
			int x2 = Integer.parseInt(coordinate.nextToken());
			int y2 = Integer.parseInt(coordinate.nextToken());

			int sum = slidingWindow[x2][y2] 
			        - slidingWindow[x1-1][y2] 
			        - slidingWindow[x2][y1-1] 
			        + slidingWindow[x1-1][y1-1];
			
			System.out.println(sum);
		}
	}
}