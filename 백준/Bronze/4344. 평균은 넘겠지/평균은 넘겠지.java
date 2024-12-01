import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] score = new int[N];
			int sum = 0;
			
			for (int i = 0; i < N; i++) {
				score[i] = Integer.parseInt(st.nextToken());
				sum += score[i];
			}
			
			double avg = sum/N;
			
			double cnt = 0;
			
			for (int i = 0; i < N; i++) {
				if(score[i]>avg) {
					cnt++;
				}
			}
			
			double percentage = (cnt/N)*100;
			System.out.printf("%.3f%%\n", percentage);
		}
	}
}