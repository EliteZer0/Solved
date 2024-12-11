import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		boolean[] isNotPrime = new boolean[N+1];
		isNotPrime[0] = true;
		isNotPrime[1] = true;
		
		for (int i = 2; i * i <= N; i++) {
            if (!isNotPrime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }
		
		List<Integer> primes = new ArrayList<>();
		for (int i = 2; i < isNotPrime.length; i++) {
			if(!isNotPrime[i]) primes.add(i);
		}
		
		int start = 0;
		int end = 0;
		int sum = 0;
		int cnt = 0;
		
		// 투포인터
		while(true) {
			if (sum >= N) {
				if (sum == N) cnt++;
				sum -= primes.get(start++);
			}
			else if(end == primes.size()) break;
			else {
				sum += primes.get(end++);
			}
		}
		System.out.println(cnt);
	}
}