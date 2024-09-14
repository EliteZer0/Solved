import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		double max = 0;
		double sum = 0;
		double avg = 0;
		double [] scores = new double[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i<n; i++) {
			scores[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i<n; i++) {
			max = Math.max(scores[i], max);
		}
		
		for (int i = 0; i<n; i++) {
			double newScores = scores[i]/max*100;
            scores[i] = newScores;
			sum += scores[i];
		}
		
		avg = sum/n;
		System.out.println(avg);
	}
}