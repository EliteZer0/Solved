import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        long[] count = new long[m]; // 나머지가 같은 값의 개수를 저장
        long sum = 0;
        long result = 0;
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            sum += Integer.parseInt(st.nextToken());
            int remainder = (int)(sum % m);
            if (remainder == 0) result++;
            count[remainder]++;
        }
        
        // 나머지가 같은 값들 중 2개를 선택하는 조합을 계산
        for (int i = 0; i < m; i++) {
            if (count[i] > 1) {
                result += (count[i] * (count[i] - 1)) / 2;
            }
        }
        
        System.out.println(result);
    }
}