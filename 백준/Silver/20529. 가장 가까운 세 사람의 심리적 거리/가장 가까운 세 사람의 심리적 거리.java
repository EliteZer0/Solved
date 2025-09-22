import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            String[] mbtiTypes = br.readLine().split(" ");
            
            // N이 32보다 크면 비둘기집 원리에 의해 같은 타입이 3개 이상 존재
            // 따라서 최소 거리는 0
            if (N > 32) {
                sb.append("0\n");
                continue;
            }
            
            int minDistance = Integer.MAX_VALUE;
            
            // 모든 3명의 조합을 확인
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    for (int k = j + 1; k < N; k++) {
                        int distance = calculateTripleDistance(mbtiTypes[i], mbtiTypes[j], mbtiTypes[k]);
                        minDistance = Math.min(minDistance, distance);
                    }
                }
            }
            
            sb.append(minDistance).append("\n");
        }
        
        System.out.print(sb.toString());
    }
    
    // 세 사람 사이의 심리적 거리 계산
    private static int calculateTripleDistance(String mbti1, String mbti2, String mbti3) {
        int dis12 = calculateDistance(mbti1, mbti2);
        int dis23 = calculateDistance(mbti2, mbti3);
        int dis13 = calculateDistance(mbti1, mbti3);
        
        return dis12 + dis23 + dis13;
    }
    
    // 두 MBTI 타입 간의 심리적 거리 계산
    private static int calculateDistance(String mbti1, String mbti2) {
        int dis = 0;
        
        for (int i = 0; i < 4; i++) {
            if (mbti1.charAt(i) != mbti2.charAt(i)) {
                dis++;
            }
        }
        
        return dis;
    }
}