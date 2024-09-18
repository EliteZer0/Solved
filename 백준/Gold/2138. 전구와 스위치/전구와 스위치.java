import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		char[] pressFirst = br.readLine().toCharArray();
		char[] notPressFirst = Arrays.copyOf(pressFirst, n);
		char[] target = br.readLine().toCharArray();

		pressFirst[0] = pressFirst[0] == '1' ? '0' : '1';
		pressFirst[1] = pressFirst[1] == '1' ? '0' : '1';

		int pressFirstCount = countSwitches(pressFirst, target, 1) + 1;
		int notPressFirstCount = countSwitches(notPressFirst, target, 1);

		int result = -1;
		if (Arrays.equals(pressFirst, target)) {
			result = pressFirstCount;
		}
		if (Arrays.equals(notPressFirst, target)) {
			if (result == -1 || notPressFirstCount < result) {
				result = notPressFirstCount;
			}
		}

		System.out.println(result);
	}

	static int countSwitches(char[] bulbs, char[] target, int start) {
		int count = 0;
		for (int i = start; i < n; i++) {
			if (bulbs[i - 1] != target[i - 1]) {
				bulbs[i - 1] = bulbs[i - 1] == '1' ? '0' : '1';
				bulbs[i] = bulbs[i] == '1' ? '0' : '1';
				if (i + 1 < n) {
					bulbs[i + 1] = bulbs[i + 1] == '1' ? '0' : '1';
				}
				count++;
			}
		}
		return count;
	}
}