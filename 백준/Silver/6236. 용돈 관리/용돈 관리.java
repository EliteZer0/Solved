import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] money = new int[N];
        int maxDaily = 0;
        int totalSum = 0;
        
        for (int i = 0; i < N; i++) {
            money[i] = Integer.parseInt(br.readLine());
            maxDaily = Math.max(maxDaily, money[i]);
            totalSum += money[i];
        }
        
        // 이진 탐색 범위 설정
        int left = maxDaily; // K는 최소한 하루 최대 사용금액 이상이어야 함
        int right = totalSum; // K의 최대값은 전체 금액
        
        while (left < right) {
            int mid = (left + right) / 2;
            
            if (isPossible(money, N, M, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        sb.append(left);
        System.out.println(sb.toString());
    }
    
    // K원으로 M번 인출해서 N일을 보낼 수 있는지 확인
    private static boolean isPossible(int[] money, int N, int M, int K) {
        int withdrawCount = 0;
        int currentMoney = 0;
        
        for (int i = 0; i < N; i++) {
            // 돈이 부족하면 인출
            if (currentMoney < money[i]) {
                withdrawCount++;
                currentMoney = K;
                
                // M번을 초과하면 불가능
                if (withdrawCount > M) {
                    return false;
                }
            }
            
            // 오늘 사용
            currentMoney -= money[i];
            
            // 남은 일수와 남은 인출 횟수 확인
            int remainingDays = N - i - 1;
            int remainingWithdraws = M - withdrawCount;
            
            // 남은 인출 횟수가 남은 일수와 정확히 같을 때 강제 인출
            if (remainingWithdraws > 0 && remainingWithdraws == remainingDays && remainingDays > 0) {
                withdrawCount++;
                currentMoney = K;
            }
        }
        
        return withdrawCount == M;
    }
}