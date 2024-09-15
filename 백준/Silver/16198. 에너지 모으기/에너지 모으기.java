import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, sum, maxSum;
	static ArrayList<Integer> marble;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		marble = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			marble.add(Integer.parseInt(st.nextToken()));
		}
		
		sum = 0;
		maxSum = 0;
		
		find(N);
		
		System.out.println(maxSum);
		
	}
	private static void find(int size) {
		if(size == 2) {
			maxSum = Math.max(maxSum, sum);
			return;
		}
		
		for (int i = 1; i < size-1; i++) {
			int removedMarble = marble.get(i);
			int energy = marble.get(i-1) * marble.get(i+1);
			
			sum += energy;
			marble.remove(i);
			
			find(size-1);
			
			marble.add(i, removedMarble);
			sum -= energy;
		}
	}
}