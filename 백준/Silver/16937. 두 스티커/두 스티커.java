import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        
        int N = Integer.parseInt(br.readLine());
        int[][] stickers = new int[N][2];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            stickers[i][0] = Integer.parseInt(st.nextToken());
            stickers[i][1] = Integer.parseInt(st.nextToken());
        }
        
        int maxArea = 0;
        
        // 두 스티커 선택
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int r1 = stickers[i][0];
                int c1 = stickers[i][1];
                int r2 = stickers[j][0];
                int c2 = stickers[j][1];
                
                // 각 스티커의 회전 상태 (원본, 90도)
                int[][] s1 = {{r1, c1}, {c1, r1}};
                int[][] s2 = {{r2, c2}, {c2, r2}};
                
                for (int[] size1 : s1) {
                    for (int[] size2 : s2) {
                        int h1 = size1[0];
                        int w1 = size1[1];
                        int h2 = size2[0];
                        int w2 = size2[1];
                        
                        // 가로로 배치
                        if (h1 <= H && h2 <= H && w1 + w2 <= W) {
                            maxArea = Math.max(maxArea, h1 * w1 + h2 * w2);
                        }
                        
                        // 세로로 배치
                        if (h1 + h2 <= H && w1 <= W && w2 <= W) {
                            maxArea = Math.max(maxArea, h1 * w1 + h2 * w2);
                        }
                    }
                }
            }
        }
        System.out.println(maxArea);
    }
}