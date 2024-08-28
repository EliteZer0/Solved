import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int target;
	static int[] data;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());
		data = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		count = 0;
		powerset(0, 0);
		if(target == 0) count--;//아무 요소도 없을 때 합은 0이 되기 때문.
		System.out.println(count);
	}

	static void powerset(int idx, int sum) {
		if (idx == data.length) {
			if (sum == target) {
				count++;
			}
			return;
		}
		powerset(idx + 1, sum + data[idx]);
		powerset(idx + 1, sum);
	}
}