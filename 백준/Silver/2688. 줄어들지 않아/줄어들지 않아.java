import java.util.*;
import java.io.*;

/* 
핵심 원리:
줄어들지 않는 n자리 수 = 중복조합 C(n+9, 9)

- 0~9의 10개 숫자에서 중복을 허용하여 n개를 선택
- 선택한 숫자들을 비내림차순으로 배열
- 이는 "n개의 공을 10개의 상자에 넣는 방법의 수"와 같음
- 중복조합 공식: H(n, r) = C(n+r-1, r)
*/

public class Main {
    
    // 조합 C(n, r) 계산
    private static long combination(int n, int r) {
        if (r > n || r < 0) return 0;
        if (r == 0 || r == n) return 1;
        
        // C(n, r) = C(n, n-r)이므로 더 작은 값으로 계산
        if (r > n - r) {
            r = n - r;
        }
        
        long result = 1;
        for (int i = 0; i < r; i++) {
            result = result * (n - i) / (i + 1);
        }
        
        return result;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            long result = combination(n + 9, 9);
            System.out.println(result);
        }
    }
}