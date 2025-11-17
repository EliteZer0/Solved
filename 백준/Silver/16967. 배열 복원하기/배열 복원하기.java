import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        
        int[][] B = new int[H + X][W + Y];
        for (int i = 0; i < H + X; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W + Y; j++) {
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int[][] A = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (i < X || j < Y) {
                    // 겹치지 않는 영역
                    A[i][j] = B[i][j];
                } else {
                    // 겹치는 영역
                    A[i][j] = B[i][j] - A[i - X][j - Y];
                }
            }
        }
        
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                sb.append(A[i][j]);
                if (j < W - 1) sb.append(" ");
            }
            sb.append("\n");
        }
        
        System.out.print(sb.toString());
    }
}