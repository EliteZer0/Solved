import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        System.out.println(countOnes(B) - countOnes(A - 1));
    }

    // 1부터 n까지의 수를 이진수로 표현했을 때 1의 개수 누적합
    public static long countOnes(long n) {
        long result = 0;
        long bit = 1;
        
        while (bit <= n) {
            long totalPairs = (n + 1) / (bit << 1);
            long remainder = (n + 1) % (bit << 1);
            
            result += totalPairs * bit + Math.max(0, remainder - bit);
            bit <<= 1;
        }

        return result;
    }
}