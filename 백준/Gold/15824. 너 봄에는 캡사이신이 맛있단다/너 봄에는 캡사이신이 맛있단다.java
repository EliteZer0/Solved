import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    final static int MOD = 1_000_000_007;
    //템플릿
    public static long powMod(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exp /= 2;
        }
        return result;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        long[] arr = new long[N];
        for(int i = 0; i<N; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        long sum = 0;

        // LARGE 조건에서 O(N^2)이라 시간초과 나는듯
        // for(int i = 0; i<N-1; i++){
        //     for(int j = N-1; j>i; j--){
        //         long cal = ((arr[j]-arr[i]) * powMod(2, j-i-1)) % MOD;
        //         sum = (sum+cal) % MOD;
        //     }
        // }

        for(int i = 0; i < N; i++){
            // arr[i]가 최댓값이 되는 경우
            // 자신과 자신보다 작은 원소들로 구성 : 2^i개
            long maxContribution = (arr[i] % MOD * powMod(2, i)) % MOD;
            sum = (sum + maxContribution) % MOD;
            
            // arr[i]가 최솟값이 되는 경우
            // 자신과 자신보다 작은 원소들로 구성 : 2^(N-1-i)개
            long minContribution = (arr[i] % MOD * powMod(2, N - 1 - i)) % MOD;
            sum = (sum - minContribution + MOD) % MOD;
        }
        
        System.out.println(sum);
    }
}