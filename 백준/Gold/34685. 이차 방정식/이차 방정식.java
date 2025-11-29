import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int k = Integer.parseInt(br.readLine());
        long k2 = (long) k * k;
        
        Set<Long> pSet = new HashSet<>();
        
        // k^2의 약수를 찾기
        long limit = (long) Math.sqrt(k2);
        for (long d = 1; d <= limit; d++) {
            if (k2 % d == 0) {
                long d1 = d;
                long d2 = k2 / d;
                
                // (d1, d2) 쌍
                long p = 2L * k - d1 - d2;
                pSet.add(p);
                
                // (-d1, -d2) 쌍
                p = 2L * k - (-d1) - (-d2);
                pSet.add(p);
                
                // (d2, d1) 쌍 (d1 != d2인 경우)
                if (d1 != d2) {
                    p = 2L * k - d2 - d1;
                    pSet.add(p);
                    
                    // (-d2, -d1) 쌍
                    p = 2L * k - (-d2) - (-d1);
                    pSet.add(p);
                }
            }
        }
        
        int count = pSet.size();
        long sum = 0;
        for (long p : pSet) {
            sum += p;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(count).append(" ").append(sum);
        System.out.println(sb.toString());
    }
}