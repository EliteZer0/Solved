import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    final static int MOD = 1_000_000_007;
    
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
        
        for(int i = 0; i<N-1; i++){
            for(int j = N-1; j>i; j--){
                long cal = ((arr[j]-arr[i]) * powMod(2, j-i-1)) % MOD;
                sum = (sum+cal) % MOD;
            }
        }
        
        System.out.println(sum);
    }
}