import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 메모이제이션을 위한 3차원 배열
    // 실제로 메모가 필요한 경우는 1 <= a, b, c <= 20 범위
    private static Integer[][][] memo = new Integer[21][21][21];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            // 종료 조건
            if (a == -1 && b == -1 && c == -1) {
                break;
            }
            
            int result = w(a, b, c);
            sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ").append(result).append("\n");
        }
        
        System.out.println(sb.toString());
    }
    
    public static int w(int a, int b, int c) {
        // a <= 0 or b <= 0 or c <= 0
        if (a <= 0 || b <= 0 || c <= 0) {
            return 1;
        }
        
        // a > 20 or b > 20 or c > 20
        if (a > 20 || b > 20 || c > 20) {
            return w(20, 20, 20);
        }
        
        // 메모이제이션 체크
        if (memo[a][b][c] != null) {
            return memo[a][b][c];
        }
        
        int result;
        
        // a < b and b < c
        if (a < b && b < c) {
            result = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
        }
        // otherwise
        else {
            result = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
        }
        
        // 메모이제이션 저장
        memo[a][b][c] = result;
        return result;
    }
}