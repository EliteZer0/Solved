import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int[] V = new int[N];
        for(int i = 0; i < N; i++) {
            V[i] = Integer.parseInt(st.nextToken());
        }
        
        // dp[i][j] = i번째 곡을 연주한 후 볼륨이 j일 수 있는지
        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[0][S] = true; // 시작 볼륨
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j <= M; j++) {
                if(dp[i][j]) { // i번째 곡까지 연주한 후 볼륨이 j인 경우
                    // i+1번째 곡을 연주하기 전에 볼륨을 조절
                    int plus = j + V[i];
                    int minus = j - V[i];
                    
                    if(plus <= M) {
                        dp[i + 1][plus] = true;
                    }
                    if(minus >= 0) {
                        dp[i + 1][minus] = true;
                    }
                }
            }
        }
        
        // 마지막 곡을 연주할 수 있는 최대 볼륨 찾기
        int maxVolume = -1;
        for(int j = M; j >= 0; j--) {
            if(dp[N][j]) {
                maxVolume = j;
                break;
            }
        }
        
        System.out.println(maxVolume);
    }
}