import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int target;
	static int[] data;
	static boolean[] canMake;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		data = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}

		canMake = new boolean[2000000];
		
		powerset(0, 0);
		for (int i = 1; i < canMake.length; i++) {
			if(!canMake[i]) {
				System.out.println(i);
				break;
			}
		}
	}

	static void powerset(int idx, int sum) {
		if (idx == data.length) {
			canMake[sum] = true;
			return;
		}
		powerset(idx + 1, sum + data[idx]);
		powerset(idx + 1, sum);
	}
}