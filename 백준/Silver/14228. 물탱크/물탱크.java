import java.util.*;
import java.lang.*;
import java.io.*;

// 1. 각 종료 구간마다 c를 넘지 않아야함
// 2. 물의 양은 음수가 될 수 없기 때문에 0이되면 0 유지
class Main {
    static int n, C;
    static int[] t, x;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        t = new int[n];
        x = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            t[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            x[i] = Integer.parseInt(st.nextToken());
        }
        
        // 절대/상대 오차는 10^-9까지 허용
        double left = 0.0;
        double right = 0.0;
        for (int i = 0; i < n; i++) {
            right = Math.max(right, x[i]);
        }
        
        // 이진 탐색으로 최소 R 찾기
        // 원하는 오차는 10^-9
        // 이진 탐색은 매번 범위를 반으로 줄이기 때문에 log2(10^6)−log2(10^-9) 최소 50번 정도의 탐색이 필요함
        double answer = right;
        for (int i = 0; i < 100; i++) {
            double mid = (left + right) / 2.0;
            
            if (canSurvive(mid)) {
                answer = mid;
                right = mid;
            } else {
                left = mid;
            }
        }
        
        sb.append(answer);
        System.out.print(sb.toString());
    }
    
    // 주어진 R로 모든 구간을 통과할 수 있는지 확인
    static boolean canSurvive(double R) {
        double v = 0.0; // 현재 물탱크의 물의 양
        
        for (int i = 0; i < n; i++) {
            // 구간 i에서의 순 증가율: (x[i] - R) 리터/초
            double increaseRate = x[i] - R;
            
            if (increaseRate >= 0) {
                // 물이 증가하는 구간: 구간 끝에서 최대값
                v += increaseRate * t[i];
                if (v > C) {
                    return false;
                }
            } else {
                // 물이 감소하는 구간: 구간 시작에서 최대값 (이미 v로 체크됨)
                if (v > C) {
                    return false;
                }
                v = Math.max(0.0, v + increaseRate * t[i]);
            }
        }
        
        return true;
    }
}