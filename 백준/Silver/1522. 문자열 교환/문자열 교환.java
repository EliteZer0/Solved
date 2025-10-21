import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String s = br.readLine();
        int n = s.length();
        
        // a의 총 개수 세기
        int aCount = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') {
                aCount++;
            }
        }
        
        // a가 없거나 모두 a인 경우
        if (aCount == 0 || aCount == n) {
            sb.append(0);
            System.out.println(sb.toString());
            return;
        }
        
        // 원형 문자열을 처리하기 위해 문자열을 2배로 확장
        String doubled = s + s;
        
        // 초기 윈도우에서 b의 개수 세기
        int bCount = 0;
        for (int i = 0; i < aCount; i++) {
            if (doubled.charAt(i) == 'b') {
                bCount++;
            }
        }
        
        int minSwap = bCount;
        
        // 슬라이딩 윈도우로 최소 교환 횟수 찾기
        for (int i = 1; i < n; i++) {
            // 왼쪽 끝 문자 제거
            if (doubled.charAt(i - 1) == 'b') {
                bCount--;
            }
            // 오른쪽 끝 문자 추가
            if (doubled.charAt(i + aCount - 1) == 'b') {
                bCount++;
            }
            minSwap = Math.min(minSwap, bCount);
        }
        
        sb.append(minSwap);
        System.out.println(sb.toString());
    }
}