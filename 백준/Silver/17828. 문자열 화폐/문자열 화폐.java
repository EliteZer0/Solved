import java.util.*;
import java.lang.*;
import java.io.*;

// 최소 가치: 길이 N인 문자열의 최소 가치는 N × 1 = N (모두 A)
// 최대 가치: 길이 N인 문자열의 최대 가치는 N × 26 = 26N (모두 Z)
// 만약 X < N 또는 X > 26N이면 불가능
class Main {  
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());  // 문자열 길이
        int X = Integer.parseInt(st.nextToken());   // 목표 가치
        
        if (N > Integer.MAX_VALUE / 26 || X < N || X > N * 26) {
            sb.append("!");
        } else {
            int remainingValue = X;
            
            for (int i = 0; i < N; i++) {
                int remainingPositions = N - i - 1;
                
                // 현재 위치에서 사용할 수 있는 최소 문자 찾기
                for (char c = 'A'; c <= 'Z'; c++) {
                    int charValue = c - 'A' + 1;
                    int valueAfterUsingChar = remainingValue - charValue;
                    
                    // 남은 가치의 범위: [remainingPositions * 1, remainingPositions * 26]
                    if (valueAfterUsingChar >= remainingPositions && 
                        valueAfterUsingChar <= remainingPositions * 26) {
                        
                        sb.append(c);
                        remainingValue = valueAfterUsingChar;
                        break;
                    }
                }
            }
        }
        
        System.out.println(sb.toString());
    }
}