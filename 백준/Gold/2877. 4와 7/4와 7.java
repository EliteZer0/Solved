import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        int digits = 1;
        int count = 0;

        // 각 자릿수 별로 2^(자릿수)개만큼의 수가 존재
        // K번째 수가 몇 자리인지 찾기
        while (count + (1 << digits) < K) {
            count += (1 << digits);
            digits++;
        }
        
        // 해당 자리 내에서 몇 번째인지 (0-indexed)
        int index = K - count - 1;
        
        // index를 이진수로 보고 0은 4 1은 7로 변환
        StringBuilder sb = new StringBuilder();
        for (int i = digits - 1; i >= 0; i--) {
            if ((index & (1 << i)) != 0) {
                sb.append('7');
            } else {
                sb.append('4');
            }
        }
        
        System.out.println(sb.toString());
    }
}