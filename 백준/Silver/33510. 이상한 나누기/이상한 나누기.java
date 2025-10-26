import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        String str = br.readLine();

        long cnt = 0;
        int carry = 0; // 올림수
        
        // 홀수의 경우 +1을 한 뒤 2로 나눔
        // 2진수의 경우 +1을 할 때 오른쪽부터 처음으로 0을 만날 때까지 모든 자릿수가 반전
        // 맨 앞자리(인덱스 0)는 제외하고, 끝자리(len-1) ~ 1까지 뒤에서 붙어 탐색
        for (int i = len - 1; i >= 1; i--) {
            int bit = (str.charAt(i) == '1') ? 1 : 0;
            int t = bit + carry;
            
            // 끝자리에 0이 반복되면 짝수
            // 홀수가 되는 지점
            if (t == 1) {        
                cnt++;
                carry = 1;       // +1에 의한 올림수
            }
            // 짝수, 올림수 없음
            else if (t == 0) {
                carry = 0;
            }
            // t == 2
            else {             
                // 짝수지만 이전에 올림됨
                carry = 1;
            }
        }
        System.out.println(cnt);
    }
}
