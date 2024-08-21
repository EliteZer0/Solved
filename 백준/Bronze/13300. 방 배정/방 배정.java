import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int K;
	static int count;
	static int roomCount;
	static int[][] students;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		students = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				students[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int totalRoom = 0;
		
		for (int i = 0; i < 2; i++) {
			for (int j = 1; j < 7; j++) {
				roomCounting(i, j);
				totalRoom += roomCount;
			}
		}
		
		System.out.println(totalRoom);
	}
	
	static void roomCounting(int sex, int grade) {
		count = 0;
		for (int i = 0; i < N; i++) {
			if(students[i][0] == sex && students[i][1] == grade) {
				count++;
			}
		}
		if(count%K != 0) {
			roomCount = count/K + 1;
		}else {
			roomCount = count/K;
		}
	}
}
